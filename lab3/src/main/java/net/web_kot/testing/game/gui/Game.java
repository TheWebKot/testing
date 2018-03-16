package net.web_kot.testing.game.gui;

import net.web_kot.testing.game.Cell;
import net.web_kot.testing.game.GameField;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;

public class Game extends JFrame {
    
    private GameField field;
    
    public static void main(String[] args) throws Exception {
        (new Game()).setVisible(true);
    }
    
    private Game() throws Exception {
        super("512");
        this.setContentPane(new JPanel());
        this.setLayout(null);
        
        this.getContentPane().setPreferredSize(new Dimension(FieldRenderer.SIZE + 40, FieldRenderer.SIZE + 40));
        this.pack();
        this.setLocationRelativeTo(null);
        
        this.setIconImage(ImageIO.read(Game.class.getResourceAsStream("/favicon.png")));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        this.getContentPane().setBackground(Color.decode("#faf8ef"));
        
        field = new GameField();
        field.setCellValue(Cell.at(1, 1), 3);
        field.setCellValue(Cell.at(0, 0), 3);
        field.setCellValue(Cell.at(2, 2), 3);
        field.setCellValue(Cell.at(3, 3), 3);
        
        FieldRenderer renderer = new FieldRenderer(this);
        renderer.setBounds(20, 20, FieldRenderer.SIZE, FieldRenderer.SIZE);
        this.add(renderer);
        
        this.registerKeys();
    }
    
    public GameField getField() {
        return field;
    }
    
    private void onMove(GameField.Direction dir) {
        field.move(dir);
        this.repaint();
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
