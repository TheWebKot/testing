package net.web_kot.testing.game;

import java.util.ArrayList;
import java.util.Random;

/**
 * Representing a 512 game field
 */
public class GameField {

    /**
     * Game field width and height
     */
    public static final int SIZE = 4;
    
    private static final Random rand = new Random();
    private int[][] field = new int[SIZE][SIZE];
    
    /**
     * Set game field cell with 2^pow value
     * @param cell field cell
     * @param pow power value
     */
    public void setCellValue(Cell cell, int pow) {
        if(pow < 0) throw new IllegalArgumentException();
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

    /**
     * Returns list representing all empty cells on game field
     * @return list with cells coordinates
     */
    public ArrayList<Cell> getEmptyCells() {
        ArrayList<Cell> result = new ArrayList<>();
        for(int i = 0; i < SIZE; i++)
            for(int j = 0; j < SIZE; j++)
                if(field[i][j] == 0) result.add(new Cell(i, j));
        return result;
    }

    /**
     * Returns random cell from all empty cells on game field
     * @return cell coordinates
     */
    public Cell getRandomEmptyCell() {
        ArrayList<Cell> empty = getEmptyCells();
        return empty.get(rand.nextInt(empty.size()));
    }
    
}
