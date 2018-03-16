package net.web_kot.testing.game;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

@DisplayName("Game field")
public class GameFieldTests {
    
    @Test
    @DisplayName("Class initialization")
    public void testClassInitialization() {
        new GameField();
    }
    
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @SuppressWarnings("JUnit5MalformedParameterized")
    @DisplayName("Cells values")
    public class CellsValues {
        
        @ParameterizedTest
        @MethodSource("cellValuesProvider")
        @DisplayName("Set cell value")
        public void testSetCellValue(int r, int c, int v) {
            Cell cell = new Cell(r, c);
            GameField field = new GameField();

            field.setCellValue(cell, v);
            Assertions.assertEquals(v, field.getCellValue(cell));
        }

        private Stream<Arguments> cellValuesProvider() {
            return Stream.of(
                    Arguments.of(0, 0, 1),
                    Arguments.of(1, 2, 3),
                    Arguments.of(3, 3, 8)
            );
        }

        @Test
        @DisplayName("Set multiple cells values")
        public void testMultipleCellsValues() {
            Cell c1 = new Cell(1, 0), c2 = new Cell(0, 3);
            GameField field = new GameField();

            field.setCellValue(c1, 2);
            field.setCellValue(c2, 8);

            Assertions.assertEquals(2, field.getCellValue(c1));
            Assertions.assertEquals(8, field.getCellValue(c2));
        }

        @Test
        @DisplayName("Negative cells values")
        public void testNegativeCellsValues() {
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> (new GameField()).setCellValue(new Cell(0, 0), -1)
            );
        }
        
    }
    
    @Nested
    @DisplayName("Cell occupation")
    public class CellOccupation {
        
        @Test
        @DisplayName("Empty cell")
        public void testCellOccupation() {
            Assertions.assertEquals(false, (new GameField()).isCellOccupied(new Cell(0, 0)));
        }
        
        @Test
        @DisplayName("Occupied cell")
        public void testCellOccupation2() {
            Cell cell = new Cell(2, 1);
            GameField field = new GameField();
            
            field.setCellValue(cell, 3);
            Assertions.assertEquals(true, field.isCellOccupied(cell));
        }
        
    }
    
    @Test
    @DisplayName("Empty cells check")
    public void testEmptyCells() {
        Cell c1 = new Cell(0, 0), c2 = new Cell(2, 3);
        GameField field = new GameField();
        
        field.setCellValue(c1, 8);
        field.setCellValue(c2, 3);
        
        ArrayList<Cell> i = field.getEmptyCells();
        Assertions.assertAll(
                () -> Assertions.assertEquals(false, i.contains(c1)),
                () -> Assertions.assertEquals(false, i.contains(c2)),
                
                () -> Assertions.assertEquals(true, i.contains(new Cell(3, 2))),
                () -> Assertions.assertEquals(true, i.contains(new Cell(1, 0)))
        );
    }
    
    @RepeatedTest(10)
    @DisplayName("Random empty cell")
    public void testRandomEmptyCell() {
        Cell c1 = new Cell(2, 2), c2 = new Cell(3, 0);
        GameField field = new GameField();
        
        field.setCellValue(c1, 1);
        field.setCellValue(c2, 2);
        
        Cell rand = field.getRandomEmptyCell();
        Assertions.assertAll(
                () -> Assertions.assertNotEquals(c1, rand),
                () -> Assertions.assertNotEquals(c2, rand)
        );
    }
    
    @Test
    @DisplayName("Movement test")
    public void testMovement() {
        int[][] from = new int[][] {
                { 2, 0, 0, 2 },      
                { 0, 4, 0, 2 },      
                { 8, 0, 4, 2 },      
                { 2, 2, 2, 2 }      
        };
        
        int[][] to = new int[][] {
                { 4, 0, 0, 0 },
                { 4, 2, 0, 0 },
                { 8, 4, 2, 0 },
                { 4, 4, 0, 0 }
        };
        
        GameField field = new GameField();
        for(int i = 0; i < from.length; i++)
            for(int j = 0; j < from[i].length; j++)
                field.setCellValue(new Cell(i, j), from[i][j]);
        
        field.move(GameField.Direction.LEFT);
        
        for(int i = 0; i < from.length; i++) {
            for(int j = 0; j < from[i].length; j++)
                Assertions.assertEquals(to[i][j], field.getCellValue(new Cell(i, j)));
        }
    }
    
}
