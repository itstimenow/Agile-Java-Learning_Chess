package chess;

import junit.framework.TestCase;

public class PositionTest extends TestCase {
    private Position position;
    
    @Override
    public void setUp() {
        position = new Position('c', 2);
    }
    
    public void testCreate() {
        char file = 'c';
        int rank = 2;
        Position position = new Position(file, rank);
        verifyPosition(position, file, rank);
    }
    
    public void testRelativePosition() {
        char file = position.getFile();
        int rank = position.getRank();
        
        Position left = position.left();
        char expectedFile = (char)(file - 1);
        verifyPosition(left, expectedFile, rank);
        
        Position right = position.right();
        expectedFile = (char)(file + 1);
        verifyPosition(right, expectedFile, rank);
        
        Position up = position.up();
        int expectedRank = rank + 1;
        verifyPosition(up, file, expectedRank);
        
        Position down = position.down();
        expectedRank = rank - 1;
        verifyPosition(down, file, expectedRank);
        
        Position rightUp = position.right().up();
        expectedFile = (char)(file + 1);
        expectedRank = rank + 1;
        verifyPosition(rightUp, expectedFile, expectedRank);
    }
    
    private void verifyPosition(Position position, char expectedFile, int expectedRank) {
        assertEquals(expectedFile, position.getFile());
        assertEquals(expectedRank, position.getRank());
        
        assertEquals(convertToColumn(expectedFile), position.getColumn());
        assertEquals(convertToRow(expectedRank), position.getRow());
    }
    
    private int convertToColumn(char file) {
        char firstFileLetter = 'a';
        int column = Character.getNumericValue(file) - Character.getNumericValue(firstFileLetter);
        return column;
    }
    
    private int convertToRow(int rank) {
        return rank - 1;
    }
}
