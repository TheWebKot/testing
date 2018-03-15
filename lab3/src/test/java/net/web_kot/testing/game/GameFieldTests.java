package net.web_kot.testing.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("Game field")
public class GameFieldTests {
    
    @Test
    @DisplayName("Class initialization")
    public void testClassInitialization() {
        new GameField();
    }
    
    @ParameterizedTest
    @MethodSource("cellValuesProvider")
    @DisplayName("Set cell value")
    public void testSetCellValue(int r, int c, int v) {
        Cell cell = new Cell(r, c);
        GameField field = new GameField();
        
        field.setCellValue(cell, v);
        Assertions.assertEquals(field.getCellValue(cell), v);
    }
    
    private static Stream<Arguments> cellValuesProvider() {
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
    @DisplayName("Cell occupation")
    public void testCellOccupation() {
        Assertions.assertEquals((new GameField()).isCellOccupied(new Cell(0, 0)), false);
    }
    
}
