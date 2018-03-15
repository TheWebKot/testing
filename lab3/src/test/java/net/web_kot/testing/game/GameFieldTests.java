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
            Assertions.assertEquals(field.getCellValue(cell), v);
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

            Assertions.assertEquals(field.getCellValue(c1), 2);
            Assertions.assertEquals(field.getCellValue(c2), 8);
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
            Assertions.assertEquals((new GameField()).isCellOccupied(new Cell(0, 0)), false);
        }
        
        @Test
        @DisplayName("Occupied cell")
        public void testCellOccupation2() {
            Cell cell = new Cell(2, 1);
            GameField field = new GameField();
            
            field.setCellValue(cell, 3);
            Assertions.assertEquals(field.isCellOccupied(cell), true);
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
                () -> Assertions.assertEquals(i.contains(c1), false),
                () -> Assertions.assertEquals(i.contains(c2), false),
                
                () -> Assertions.assertEquals(i.contains(new Cell(3, 2)), true),
                () -> Assertions.assertEquals(i.contains(new Cell(1, 0)), true)
        );
    }
    
}
