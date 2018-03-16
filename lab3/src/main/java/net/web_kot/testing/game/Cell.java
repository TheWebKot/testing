package net.web_kot.testing.game;

import java.util.Objects;

/**
 * Representing game field cell coordinates
 */
public class Cell {

    private static final Cell[][] cache = new Cell[GameField.SIZE][GameField.SIZE];
    private final int row, column;

    /**
     * Returns cell representation with given coordinates
     * @param r row number
     * @param c column number
     * @return cell with given coordinates
     */
    public static Cell at(int r, int c) {
        if(r < 0 || r >= GameField.SIZE) throw new IndexOutOfBoundsException();
        if(c < 0 || c >= GameField.SIZE) throw new IndexOutOfBoundsException();
        
        if(cache[r][c] == null) cache[r][c] = new Cell(r, c);
        return cache[r][c];
    }
    
    private Cell(int r, int c) {
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
    
    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }
    
}
