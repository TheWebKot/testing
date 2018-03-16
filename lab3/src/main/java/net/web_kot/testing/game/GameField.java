package net.web_kot.testing.game;

import org.apache.commons.lang3.tuple.Pair;

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

    /**
     * Enumeration with possible cells moving directions
     */
    public enum Direction { 
        
        UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);
        
        /**
         * Coordinate delta x when moving in this direction
         */
        public final int dx;
        
        /**
         * Coordinate delta y when moving in this direction
         */
        public final int dy;
        
        Direction(int _dx, int _dy) { dx = _dx; dy = _dy; }
        
    }
    
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
                if(field[i][j] == 0) result.add(Cell.at(i, j));
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

    /**
     * Moves all values in cells in specified direction 
     * and merges cells with same value
     * @param dir direction
     */
    public void move(Direction dir) {
        
    }

    /**
     * Returns farthest position on which current cell can be moved in given direction
     * Also returns next cell in given direction (or null if there is no such cell)
     * for merge possibility checking (for empty cells behavior undefined)
     * @param cell non-empty cell coordinates
     * @param dir direction
     * @return pair of position and next cell
     */
    public Pair<Cell, Cell> findFarthestPosition(Cell cell, Direction dir) {
        Cell next = nextCellAt(cell, dir);
        if(next == null || isCellOccupied(next)) return Pair.of(cell, next);
        
        Cell previous;
        do {
            previous = next;
            next = nextCellAt(next, dir);
        } while(next != null && !isCellOccupied(next));
        
        return Pair.of(previous, next);
    }

    /**
     * Returns next cell in given direction (or null if there is no such cell)
     * @param cell cell coordinates
     * @param dir direction
     * @return next cell position
     */
    public Cell nextCellAt(Cell cell, Direction dir) {
        try {
            return Cell.at(cell.getRow() + dir.dx, cell.getColumn() + dir.dy);
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
    }

}
