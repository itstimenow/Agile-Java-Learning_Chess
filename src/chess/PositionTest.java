package chess;

import junit.framework.TestCase;
import util.*;


public class PositionTest extends TestCase {
    private Position position;
    private char file;
    private int rank;
    
    @Override
    public void setUp() {
        file = 'c';
        rank = 2;
        position = new Position(file, rank);
    }
    
    public void testCreate() {
        Position position = new Position(file, rank);
        verifyPosition(position, file, rank);
    }
    
    public void testLeft() {
        Position left = position.left();
        char expectedFile = (char)(file - 1);
        verifyPosition(left, expectedFile, rank);
        
        int offset = 2;
        left = position.left(offset);
        expectedFile = (char)(file - offset);
        verifyPosition(left, expectedFile, rank);
    }
    
    public void testRight() {
        Position right = position.right();
        char expectedFile = (char)(file + 1);
        verifyPosition(right, expectedFile, rank);
        
        int offset = 5;
        right = position.right(offset);
        expectedFile = (char)(file + offset);
        verifyPosition(right, expectedFile, rank);
    }
    
    public void testUp() {
        Position up = position.up();
        int expectedRank = rank + 1;
        verifyPosition(up, file, expectedRank);
        
        int offset = 3;
        up = position.up(offset);
        expectedRank = rank + offset;
        verifyPosition(up, file, expectedRank);
    }
    
    public void testDown() {
        Position down = position.down();
        int expectedRank = rank - 1;
        verifyPosition(down, file, expectedRank);
        
        int offset = 1;
        down = position.down(offset);
        expectedRank = rank - offset;
        verifyPosition(down, file, expectedRank);
    }    
    
    private void verifyPosition(Position position, char expectedFile, int expectedRank) {
        assertEquals(expectedFile, position.getFile());
        assertEquals(expectedRank, position.getRank());
        
        assertEquals(PositionUtil.convertToColumn(expectedFile), position.getColumn());
        assertEquals(PositionUtil.convertToRow(expectedRank), position.getRow());
    }
}
