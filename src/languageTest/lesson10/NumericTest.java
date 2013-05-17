package languageTest.lesson10;

import junit.framework.*;


public class NumericTest extends TestCase {
    
    public void testConversion() {
        int n = (int)Integer.decode("0xDEAD");
        assertEquals(57005, (int)Integer.decode("0xDEAD"));
        
        String octalStr = "0" + Integer.toOctalString(n);
        assertTrue(octalStr.equals("0157255"));
    }
}
