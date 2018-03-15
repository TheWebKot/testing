package net.web_kot.testing.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Game field")
public class GameFieldTests {
    
    @Test
    @DisplayName("Class initialization")
    public void testClassInitialization() {
        new GameField();
    }
    
}
