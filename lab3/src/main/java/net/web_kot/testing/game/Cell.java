package net.web_kot.testing.game;

/**
 * Representing game field cell coordinates
 */
public class Cell {

    /**
     * Creates new field cell representation
     * @param r row number
     * @param c column number
     */
    public Cell(int r, int c) {
        if(r < 0 || r >= GameField.SIZE) throw new IndexOutOfBoundsException();
        if(c < 0 || c >= GameField.SIZE) throw new IndexOutOfBoundsException();
    }

    /**
     * Returns current cell row number
     * @return row number
     */
    public int getRow() {
        return 0;
    }

    /**
     * Returns current cell column number
     * @return column number
     */
    public int getColumn() {
        return 0;
    }
    
}
