package net.web_kot.testing.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Cell")
public class CellTests {
    
    @Test
    @DisplayName("Class initialization")
    public void testClassInitialization() {
        new Cell(0, 0);
    }
    
}
