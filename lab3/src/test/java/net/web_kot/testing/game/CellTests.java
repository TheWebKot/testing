package net.web_kot.testing.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Cell")
public class CellTests {
    
    @Test
    @DisplayName("Class initialization")
    public void testClassInitialization() {
        new Cell(0, 0);
    }

    @Nested
    @DisplayName("Bounds checking")
    public class TestInitializationBounds {
        
        @Test
        @DisplayName("Row excess")
        public void testRowExcess() {
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> new Cell(GameField.SIZE + 1, 0));
        }

        @Test
        @DisplayName("Column excess")
        public void testColumnExcess() {
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> new Cell(2, GameField.SIZE));
        }

        @Test
        @DisplayName("Row negative value")
        public void testRowNegative() {
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> new Cell(-1, 1));
        }

        @Test
        @DisplayName("Column negative value")
        public void testColumnNegative() {
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> new Cell(3, -3));
        }
        
    }
    
}
