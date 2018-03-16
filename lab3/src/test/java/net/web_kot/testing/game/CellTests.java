package net.web_kot.testing.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("Cell")
public class CellTests {
    
    @Test
    @DisplayName("Class initialization")
    public void testClassInitialization() {
        Cell.at(0, 0);
    }
    
    @ParameterizedTest
    @MethodSource("boundsProvider")
    @DisplayName("Bounds checking")
    public void testInitializationBounds(int r, int c) {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> Cell.at(r, c));
    }
    
    private static Stream<Arguments> boundsProvider() {
        return Stream.of(
            Arguments.of(GameField.SIZE + 1, 0),      
            Arguments.of(2, GameField.SIZE),      
            Arguments.of(-1, 1),      
            Arguments.of(3, -3)      
        );
    }
    
    @ParameterizedTest
    @MethodSource("coordinatesProvider")
    @DisplayName("Storage checking")
    public void testStorage(int r, int c) {
        Cell cell = Cell.at(r, c);
        Assertions.assertEquals(r, cell.getRow());
        Assertions.assertEquals(c, cell.getColumn());
    }
    
    private static Stream<Arguments> coordinatesProvider() {
        return Stream.of(
            Arguments.of(0, 0),
            Arguments.of(1, 3),
            Arguments.of(2, 0)    
        );
    }
    
}
