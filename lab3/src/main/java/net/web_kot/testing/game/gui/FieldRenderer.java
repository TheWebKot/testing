package net.web_kot.testing.game.gui;

import net.web_kot.testing.game.Cell;
import net.web_kot.testing.game.GameField;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

public class FieldRenderer extends JPanel {
    
    public static final int SIZE = 500;
    
    private static final Font font = new Font("ClearSans", Font.BOLD, 45);
    
    private static final int CELL_SIZE = 107;
    private final Game game;
    
    public FieldRenderer(Game parent) {
        game = parent;
    }
    
    @Override
    public void paint(Graphics graphics) {
        Graphics2D g = (Graphics2D)graphics;
        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHints(hints);
        
        g.setColor(Color.decode("#bbada0"));
        RoundRectangle2D.Double bubble = new RoundRectangle2D.Double(0, 0, SIZE, SIZE, 14, 14);
        g.fill(bubble);
        
        for(int i = 0; i < GameField.SIZE; i++)
            for(int j = 0; j < GameField.SIZE; j++) {
                int x = 15 + (CELL_SIZE + 14) * j;
                int y = 15 + (CELL_SIZE + 14) * i;
                
                int value = game.getField().getCellValue(Cell.at(i, j));
                
                Cell latest = game.getLatestAdded();
                if(latest != null && latest.getRow() == i && latest.getColumn() == j) {
                    g.setColor(Color.decode("#444444"));
                    RoundRectangle2D.Double cell = new RoundRectangle2D.Double(
                            x - 1, y - 1, CELL_SIZE + 2, CELL_SIZE + 2, 8, 8);
                    g.fill(cell);
                }
                
                g.setColor(Color.decode(getCellColor(value)));
                RoundRectangle2D.Double cell = new RoundRectangle2D.Double(x, y, CELL_SIZE, CELL_SIZE, 8, 8);
                g.fill(cell);
                
                if(value != 0) {
                    String str = (1 << value) + "";
                    
                    Font f = font.deriveFont(1.2F * getFontSize(value));
                    int w = g.getFontMetrics(f).stringWidth(str);
                    float h = 0.78f * g.getFontMetrics(f).getAscent();
                    
                    g.setFont(f);
                    g.setColor(Color.decode(getCellForeground(value)));
                    g.drawString(str, x + (CELL_SIZE - w) / 2, y + CELL_SIZE - (CELL_SIZE - h) / 2);
                }
            }
            
        if(game.isGameOver()) {
            g.setColor(new Color(255, 255, 255, 170));
            g.fillRect(0, 0, SIZE, SIZE);

            g.setColor(Color.decode("#776e65"));
            String str = "Game over!";

            Font f = font.deriveFont(64F);
            int w = g.getFontMetrics(f).stringWidth(str);

            g.setFont(f);
            g.drawString(str, (SIZE - w) / 2, 212);
        }
    }
    
    private String getCellColor(int value) {
        switch(value) {
            case 0: return "#cdc1b4";
            case 1: return "#eee4da";
            case 2: return "#ede0c8";
            case 3: return "#f2b179";
            case 4: return "#f59563";
            case 5: return "#f67c5f";
            case 6: return "#f65e3b";
            case 7: return "#edcf72";
            case 8: return "#edcc61";
            case 9: return "#edc850";
            case 10: return "#edc53f";
            case 11: return "#edc22e";
            default: return "#3c3a32";
        }
    }
    
    private String getCellForeground(int value) {
        if(value <= 2) return "#776e65";
        return "#f9f6f2";
    }
    
    private int getFontSize(int value) {
        switch(value) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return 55;
            case 7:
            case 8:
            case 9:
                return 45;
            case 10:
            case 11:
                return 35;
            default:
                return 30;
        }
    }
    
}
