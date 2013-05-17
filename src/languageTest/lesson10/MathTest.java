package languageTest.lesson10;

import junit.framework.*;


public class MathTest extends TestCase {
    
    public void testRint() {
        assertEquals(2.0, Math.rint(1.9));
        assertEquals(2.0, Math.rint(1.5));
        assertEquals(2.0, Math.rint(2.5));
    }
    
    public void testIncrement() {
        int x = 5;
        int y = 10;
        assertEquals(42, x * 5 + y++ * 7 / 4);  // 25 + 10 * 7 / 4 = 25 + 70 / 4 = 25 + 17 = 42
        
        x = 5;
        y = 10;
        assertEquals(300, ++x * 5 * y++);
        
        x = 5;
        y = 10;
        assertEquals(true, x * 2 == y || ++y == 10);  // 10 == 10 || 
    }
    
    public void testBitShift() {
        assertEquals(34, 17 << 1);
        
        // Signed and unsigned right shift have no difference for positive numbers.
        assertEquals(10, 40 >> 2);
        assertEquals(10, 40 >>> 2);
        
        // Big difference for negative numbers
        assertEquals(-20, -40 >> 1);
        assertEquals(2147483628, -40 >>> 1);
    }
    
    public void testBitNot() {
        assertEquals(-1 - 1, ~1);   // 1 + ~1 = -1 (-1: 1111 1111 1111 1111)
        assertEquals(-1, 100 + ~100);
    }
}
