package net.web_kot.testing.game;

/**
 * Representing game field cell coordinates
 */
public class Cell {

    /**
     * Creates new field cell representation
     * @param r row number
     * @param c col number
     */
    public Cell(int r, int c) {
        if(r < 0 || r >= GameField.SIZE) throw new IndexOutOfBoundsException();
        if(c < 0 || c >= GameField.SIZE) throw new IndexOutOfBoundsException();
    }
    
}
