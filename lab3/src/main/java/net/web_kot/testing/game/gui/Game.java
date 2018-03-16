package net.web_kot.testing.game.gui;

import net.web_kot.testing.game.Cell;
import net.web_kot.testing.game.GameField;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;

import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;

public class Game extends JFrame {
    
    private GameField field;
    private Cell latestAdded;
    
    private boolean gameOver;
    
    public static void main(String[] args) throws Exception {
        (new Game()).setVisible(true);
    }
    
    private Game() throws Exception {
        super("512");
        this.setContentPane(new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                paintHeader((Graphics2D)g);
            }
        });
        this.setLayout(null);
        
        this.getContentPane().setPreferredSize(new Dimension(FieldRenderer.SIZE + 40, FieldRenderer.SIZE + 156));
        this.pack();
        this.setLocationRelativeTo(null);
        
        this.setIconImage(ImageIO.read(Game.class.getResourceAsStream("/favicon.png")));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        this.getContentPane().setBackground(Color.decode("#faf8ef"));
        
        startGame();
        
        FieldRenderer renderer = new FieldRenderer(this);
        renderer.setBounds(20, 136, FieldRenderer.SIZE, FieldRenderer.SIZE);
        this.add(renderer);
        
        this.registerKeys();
        
        JButton button = new JButton() {
            @Override
            public void paint(Graphics g) { }
        };
        button.setOpaque(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener((e) -> startGame());
        button.setBounds(380, 20, 140, 64);
        this.add(button);
    }
    
    private void startGame() {
        field = new GameField();
        for(int i = 0; i < 2; i++) field.addRandomTile();
        
        latestAdded = null;
        gameOver = false;
        
        repaint();
    }
    
    public Cell getLatestAdded() {
        return latestAdded;
    }
    
    public GameField getField() {
        return field;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    private void onMove(GameField.Direction dir) {
        if(gameOver) return;
        if(field.move(dir)) {
            latestAdded = field.addRandomTile();
            if(!field.canMove()) gameOver = true;
            
            this.repaint();
        }
    }
    
    private void paintHeader(Graphics2D g) {
        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHints(hints);
        
        g.setColor(Color.decode("#776e65"));
        g.setFont(FieldRenderer.font.deriveFont(80F));
        g.drawString("512", 20, 80);
        
        String str = "Join the numbers and get to the 512 tile!";
        Font f = FieldRenderer.font.deriveFont(20F).deriveFont(Font.PLAIN);
        int w = g.getFontMetrics(f).stringWidth(str);
        
        g.setFont(f);
        g.drawString(str, 20 + (FieldRenderer.SIZE - w) / 2, 117);

        g.setColor(Color.decode("#bbada0"));
        RoundRectangle2D.Double bubble = new RoundRectangle2D.Double(220, 20, 140, 64, 8, 8);
        g.fill(bubble);
        
        g.setColor(Color.decode("#8f7a66"));
        RoundRectangle2D.Double bubble2 = new RoundRectangle2D.Double(380, 20, 140, 64, 8, 8);
        g.fill(bubble2);
        
        str = "SCORE";
        f = FieldRenderer.font.deriveFont(14F);
        w = g.getFontMetrics(f).stringWidth(str);
        
        g.setFont(f);
        g.setColor(Color.decode("#eee4da"));
        g.drawString(str, 220 + (140 - w) / 2, 44);
        
        str = field.getScore() + "";
        f = FieldRenderer.font.deriveFont(28F);
        w = g.getFontMetrics(f).stringWidth(str);
        
        g.setFont(f);
        g.setColor(Color.WHITE);
        g.drawString(str, 220 + (140 - w) / 2, 72);
        
        str = "New Game";
        f = FieldRenderer.font.deriveFont(21F);
        w = g.getFontMetrics(f).stringWidth(str);

        g.setFont(f);
        g.setColor(Color.WHITE);
        g.drawString(str, 380 + (140 - w) / 2, 59);
    }

    private void registerKeys() {
        JPanel content = (JPanel)this.getContentPane();
        
        InputMap im = content.getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = content.getActionMap();
        
        registerKeyEvents(im, am, KeyEvent.VK_UP, new MoveCommand(GameField.Direction.UP));
        registerKeyEvents(im, am, KeyEvent.VK_DOWN, new MoveCommand(GameField.Direction.DOWN));
        registerKeyEvents(im, am, KeyEvent.VK_LEFT, new MoveCommand(GameField.Direction.LEFT));
        registerKeyEvents(im, am, KeyEvent.VK_RIGHT, new MoveCommand(GameField.Direction.RIGHT));
    }
    
    private void registerKeyEvents(InputMap input, ActionMap action, int event, AbstractAction command) {
        input.put(KeyStroke.getKeyStroke(event, 0, false), "key.command." + System.identityHashCode(command));
        action.put("key.command." + System.identityHashCode(command), command);
    }

    private class MoveCommand extends AbstractAction {
        
        private final GameField.Direction dir;

        public MoveCommand(GameField.Direction direction) {
           dir = direction;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            onMove(dir);
        }

    }
    
}
