package net.web_kot.testing.game;

/**
 * Representing a 512 game field
 */
public class GameField {

    /**
     * Game field width and height
     */
    public static final int SIZE = 4;
    
    private int[][] field = new int[SIZE][SIZE];
    
    /**
     * Set game field cell with 2^pow value
     * @param cell field cell
     * @param pow power value
     */
    public void setCellValue(Cell cell, int pow) {
        field[cell.getRow()][cell.getColumn()] = pow;
    }

    /**
     * Returns log2 of value stored in cell
     * @param cell field cell
     * @return log2(value)
     */
    public int getCellValue(Cell cell) {
        return field[cell.getRow()][cell.getColumn()];
    }

    /**
     * Return whether cell occupied by any number
     * @param cell cell for checking
     * @return cell occupied or not 
     */
    public boolean isCellOccupied(Cell cell) {
        return field[cell.getRow()][cell.getColumn()] != 0;
    }
    
}
