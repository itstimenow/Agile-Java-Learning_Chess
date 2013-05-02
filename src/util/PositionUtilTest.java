package util;

import junit.framework.TestCase;


public class PositionUtilTest extends TestCase {
    
    public void testConvertToColumn() {
        char file = 'a';
        assertEquals(0, PositionUtil.convertToColumn(file));
        
        file = 'd';
        assertEquals(3, PositionUtil.convertToColumn(file));
        
        file = 'h';
        assertEquals(7, PositionUtil.convertToColumn(file));
    }
    
    public void testConvertToRow() {
        int rank = 1;
        assertEquals(0, PositionUtil.convertToRow(rank));
        
        rank = 6;
        assertEquals(5, PositionUtil.convertToRow(rank));
        
        rank = 8;
        assertEquals(7, PositionUtil.convertToRow(rank));
    }
}
