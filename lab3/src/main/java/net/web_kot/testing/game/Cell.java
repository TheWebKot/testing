package net.web_kot.testing.game;

import java.util.Objects;

/**
 * Representing game field cell coordinates
 */
public class Cell {
    
    private final int row, column;
    
    /**
     * Creates new field cell representation
     * @param r row number
     * @param c column number
     */
    public Cell(int r, int c) {
        if(r < 0 || r >= GameField.SIZE) throw new IndexOutOfBoundsException();
        if(c < 0 || c >= GameField.SIZE) throw new IndexOutOfBoundsException();
        
        row = r; column = c;
    }

    /**
     * Returns current cell row number
     * @return row number
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns current cell column number
     * @return column number
     */
    public int getColumn() {
        return column;
    }
    
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Cell)) return false;
        Cell c = (Cell)other;
        return c.row == row && c.column == column;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
    
}
