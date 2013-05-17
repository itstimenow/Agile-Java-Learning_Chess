package languageTest.lesson10;

import junit.framework.*;


public class NumericTest extends TestCase {
    
    public void testConversion() {
        int n = (int)Integer.decode("0xDEAD");
        assertEquals(57005, (int)Integer.decode("0xDEAD"));
        
        String octalStr = "0" + Integer.toOctalString(n);
        assertTrue(octalStr.equals("0157255"));
    }
    
    public void testNaN() {
        isNaN(Double.NaN);
        isNaN(0.0 / 0.0);
        isNaN(0.0 / 0);
        isNaN(0 / 0.0);
        isNaN(0 % 0.0);
        isNaN(Double.NaN + Double.NaN);
        isNaN(Math.sqrt(-1));
        isNaN(Double.POSITIVE_INFINITY + Double.NEGATIVE_INFINITY);
        isNaN(Double.NEGATIVE_INFINITY / Double.POSITIVE_INFINITY);
        isNaN(Double.POSITIVE_INFINITY % 10);
    }
    
    private void isNaN(double value) {
        assertTrue(Double.isNaN(value));
    }
    
    public void testInfinite() {
        isInfinite(1.0 / 0);
        isNegativeInfinite(Double.NEGATIVE_INFINITY);
        isNegativeInfinite(Double.NEGATIVE_INFINITY * Double.POSITIVE_INFINITY);
        isPositiveInfinite(Double.NEGATIVE_INFINITY * Double.NEGATIVE_INFINITY);
    }
    
    private void isInfinite(double value) {
        assertTrue(Double.isInfinite(value));
    }
    
    private void isPositiveInfinite(double value) {
        assertTrue(value == Double.POSITIVE_INFINITY);
    }
    
    private void isNegativeInfinite(double value) {
        assertTrue(value == Double.NEGATIVE_INFINITY);
    }
}
