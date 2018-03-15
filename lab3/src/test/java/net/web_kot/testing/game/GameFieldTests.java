package net.web_kot.testing.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Game field")
public class GameFieldTests {
    
    @Test
    @DisplayName("Class initialization")
    public void testClassInitialization() {
        new GameField();
    }
    
    @Test
    @DisplayName("Set cell value")
    public void testSetCellValue() {
        Cell cell = new Cell(0, 0);
        GameField field = new GameField();
        
        field.setCellValue(cell, 1);
        Assertions.assertEquals(field.getCellValue(cell), 1);
    }
    
}
