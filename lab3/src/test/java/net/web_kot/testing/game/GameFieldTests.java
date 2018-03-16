package net.web_kot.testing.game;

import org.apache.commons.lang3.tuple.Pair;
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
            Cell cell = Cell.at(r, c);
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
            Cell c1 = Cell.at(1, 0), c2 = Cell.at(0, 3);
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
                    () -> (new GameField()).setCellValue(Cell.at(0, 0), -1)
            );
        }
        
    }
    
    @Nested
    @DisplayName("Cell occupation")
    public class CellOccupation {
        
        @Test
        @DisplayName("Empty cell")
        public void testCellOccupation() {
            Assertions.assertEquals(false, (new GameField()).isCellOccupied(Cell.at(0, 0)));
        }
        
        @Test
        @DisplayName("Occupied cell")
        public void testCellOccupation2() {
            Cell cell = Cell.at(2, 1);
            GameField field = new GameField();
            
            field.setCellValue(cell, 3);
            Assertions.assertEquals(true, field.isCellOccupied(cell));
        }
        
    }
    
    @Test
    @DisplayName("Empty cells check")
    public void testEmptyCells() {
        Cell c1 = Cell.at(0, 0), c2 = Cell.at(2, 3);
        GameField field = new GameField();
        
        field.setCellValue(c1, 8);
        field.setCellValue(c2, 3);
        
        ArrayList<Cell> i = field.getEmptyCells();
        Assertions.assertAll(
                () -> Assertions.assertEquals(false, i.contains(c1)),
                () -> Assertions.assertEquals(false, i.contains(c2)),
                
                () -> Assertions.assertEquals(true, i.contains(Cell.at(3, 2))),
                () -> Assertions.assertEquals(true, i.contains(Cell.at(1, 0)))
        );
    }
    
    @RepeatedTest(10)
    @DisplayName("Random empty cell")
    public void testRandomEmptyCell() {
        Cell c1 = Cell.at(2, 2), c2 = Cell.at(3, 0);
        GameField field = new GameField();
        
        field.setCellValue(c1, 1);
        field.setCellValue(c2, 2);
        
        Cell rand = field.getRandomEmptyCell();
        Assertions.assertAll(
                () -> Assertions.assertNotEquals(c1, rand),
                () -> Assertions.assertNotEquals(c2, rand)
        );
    }
    
    @ParameterizedTest
    @MethodSource("movementProvider")
    @DisplayName("Movement")
    public void testMovement(GameField.Direction dir, int[][] from, int[][] to) {
        GameField field = new GameField();
        for(int i = 0; i < from.length; i++)
            for(int j = 0; j < from[i].length; j++)
                field.setCellValue(Cell.at(i, j), from[i][j]);
        
        field.move(dir);
        
        for(int i = 0; i < from.length; i++) {
            for(int j = 0; j < from[i].length; j++)
                Assertions.assertEquals(to[i][j], field.getCellValue(Cell.at(i, j)));
        }
    }
    
    private static Stream<Arguments> movementProvider() {
        return Stream.of(
                Arguments.of(
                        GameField.Direction.LEFT, 
                        convertMatrix(new int[][] {
                                { 2, 0, 0, 2 }, 
                                { 0, 4, 0, 2 }, 
                                { 8, 0, 4, 2 }, 
                                { 2, 2, 2, 2 }
                        }), convertMatrix(new int[][] {
                                { 4, 0, 0, 0 }, 
                                { 4, 2, 0, 0 }, 
                                { 8, 4, 2, 0 }, 
                                { 4, 4, 0, 0 }
                        })),
                Arguments.of(
                        GameField.Direction.LEFT,
                        convertMatrix(new int[][] {
                                { 0, 0, 0, 0 },
                                { 2, 0, 2, 4 },
                                { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 }
                        }), convertMatrix(new int[][] {
                                { 0, 0, 0, 0 },
                                { 4, 4, 0, 0 },
                                { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 }
                        })),
                Arguments.of(
                        GameField.Direction.RIGHT,
                        convertMatrix(new int[][] {
                                { 2, 0, 0, 2 },
                                { 0, 4, 0, 2 },
                                { 8, 0, 4, 2 },
                                { 4, 0, 2, 2 }
                        }), convertMatrix(new int[][] {
                                { 0, 0, 0, 4 },
                                { 0, 0, 4, 2 },
                                { 0, 8, 4, 2 },
                                { 0, 0, 4, 4 }
                        })),
                Arguments.of(
                        GameField.Direction.UP,
                        convertMatrix(new int[][] {
                                { 2, 0, 0, 2 },
                                { 0, 4, 0, 2 },
                                { 8, 0, 4, 2 },
                                { 4, 0, 2, 2 }
                        }), convertMatrix(new int[][] {
                                { 2, 4, 4, 4 },
                                { 8, 0, 2, 4 },
                                { 4, 0, 0, 0 },
                                { 0, 0, 0, 0 }
                        })),
                Arguments.of(
                        GameField.Direction.DOWN,
                        convertMatrix(new int[][] {
                                { 2, 0, 0, 2 },
                                { 0, 4, 0, 2 },
                                { 8, 0, 4, 2 },
                                { 4, 0, 2, 2 }
                        }), convertMatrix(new int[][] {
                                { 0, 0, 0, 0 },
                                { 2, 0, 0, 0 },
                                { 8, 0, 4, 4 },
                                { 4, 4, 2, 4 }
                        }))
        );
    }
    
    private static int[][] convertMatrix(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++)
            for(int j = 0; j < matrix[i].length; j++)
                if(matrix[i][j] == 0)
                    result[i][j] = 0;
                else
                    result[i][j] = Integer.numberOfTrailingZeros(Integer.highestOneBit(matrix[i][j]));
        return result;
    }
    
    @ParameterizedTest
    @MethodSource("positionsProvider")
    @DisplayName("Find farthest position")
    public void testFindPosition(GameField.Direction dir, Cell cell, Cell expected, Cell next) {
        int[][] from = new int[][] {
                { 2, 4, 0, 4 },
                { 8, 0, 2, 2 },
                { 0, 4, 2, 0 },
                { 0, 2, 0, 8 }
        };

        GameField field = new GameField();
        for(int i = 0; i < from.length; i++)
            for(int j = 0; j < from[i].length; j++)
                field.setCellValue(Cell.at(i, j), from[i][j]);
        
        Pair<Cell, Cell> pair = field.findFarthestPosition(cell, dir);
        Assertions.assertAll(
                () -> Assertions.assertEquals(expected, pair.getLeft()),
                () -> Assertions.assertEquals(next, pair.getRight())
        );
    }
    
    private static Stream<Arguments> positionsProvider() {
        return Stream.of(
                Arguments.of(GameField.Direction.LEFT, Cell.at(0, 3), Cell.at(0, 2), Cell.at(0, 1)),       
                Arguments.of(GameField.Direction.LEFT, Cell.at(3, 1), Cell.at(3, 0), null),
               
                Arguments.of(GameField.Direction.RIGHT, Cell.at(1, 2), Cell.at(1, 2), Cell.at(1, 3)), 
                Arguments.of(GameField.Direction.UP, Cell.at(2, 1), Cell.at(1, 1), Cell.at(0, 1)),
                Arguments.of(GameField.Direction.DOWN, Cell.at(1, 0), Cell.at(3, 0), null)
        );
    }
    
    @ParameterizedTest
    @MethodSource("nextPositionsProvider")
    @DisplayName("Next cell in given direction")
    public void testNextCell(GameField.Direction dir, Cell cell, Cell expected) {
        Assertions.assertEquals(expected, (new GameField()).nextCellAt(cell, dir));
    }
    
    private static Stream<Arguments> nextPositionsProvider() {
        return Stream.of(
                Arguments.of(GameField.Direction.LEFT, Cell.at(1, 1), Cell.at(1, 0)),
                Arguments.of(GameField.Direction.UP, Cell.at(0, 3), null),
                Arguments.of(GameField.Direction.DOWN, Cell.at(0, 3), Cell.at(1, 3)),
                Arguments.of(GameField.Direction.RIGHT, Cell.at(2, 2), Cell.at(2, 3))
        );
    }
    
    @Nested
    @DisplayName("Random tile")
    public class RandomTile {

        @Test
        @DisplayName("Add random tile")
        public void testAddRandomTile() {
            GameField field = new GameField();
            field.addRandomTile();

            boolean flag = false;
            for(int i = 0; i < GameField.SIZE; i++)
                for(int j = 0; j < GameField.SIZE; j++)
                    if(field.getCellValue(Cell.at(i, j)) != 0) {
                        Assertions.assertEquals(false, flag);
                        flag = true;
                    }

            Assertions.assertEquals(true, flag);
        }

        @Test
        @DisplayName("Random tile value")
        public void testRandomTileValue() {
            GameField field = new GameField();
            field.addRandomTile();
            
            for(int i = 0; i < GameField.SIZE; i++)
                for(int j = 0; j < GameField.SIZE; j++) {
                    int value = field.getCellValue(Cell.at(i, j));
                    if(value > 2) Assertions.fail("Value larger then expected");
                }
        }
        
        @Test
        @DisplayName("Return value")
        public void testTileReturn() {
            GameField field = new GameField();
            Cell cell = field.addRandomTile();
            Assertions.assertNotEquals(0, field.getCellValue(cell));
        }
        
    }

    @ParameterizedTest
    @MethodSource("movementReturnProvider")
    @DisplayName("Movement return value")
    public void testMovementReturn(GameField.Direction dir, int[][] from, boolean ret) {
        GameField field = new GameField();
        for(int i = 0; i < from.length; i++)
            for(int j = 0; j < from[i].length; j++)
                field.setCellValue(Cell.at(i, j), from[i][j]);

        Assertions.assertEquals(ret, field.move(dir));
    }

    private static Stream<Arguments> movementReturnProvider() {
        return Stream.of(
                Arguments.of(
                        GameField.Direction.LEFT,
                        convertMatrix(new int[][] {
                                { 2, 0, 0, 2 },
                                { 0, 4, 0, 2 },
                                { 8, 0, 4, 2 },
                                { 2, 2, 2, 2 }
                        }), true),
                Arguments.of(
                        GameField.Direction.UP,
                        convertMatrix(new int[][] {
                                { 2, 4, 8, 16 },
                                { 4, 8, 16, 2 },
                                { 8, 16, 2, 4 },
                                { 16, 2, 4, 8 }
                        }), false)
        );
    }
    
    @ParameterizedTest
    @MethodSource("canMoveProvider")
    @DisplayName("Can move")
    public void testCanMove(int[][] from, boolean expected) {
        GameField field = new GameField();
        for(int i = 0; i < from.length; i++)
            for(int j = 0; j < from[i].length; j++)
                field.setCellValue(Cell.at(i, j), from[i][j]);
        
        Assertions.assertEquals(expected, field.canMove());
    }
    
    private static Stream<Arguments> canMoveProvider() {
        return Stream.of(
                Arguments.of(convertMatrix(new int[][] {
                        { 2, 0, 0, 2 },
                        { 0, 4, 0, 2 },
                        { 8, 0, 4, 2 },
                        { 2, 2, 2, 2 }
                }), true),
                Arguments.of(convertMatrix(new int[][] {
                        { 2, 4, 8, 16 },
                        { 4, 8, 16, 2 },
                        { 8, 16, 2, 4 },
                        { 16, 2, 4, 8 }
                }), false)
        );
    }
    
}
