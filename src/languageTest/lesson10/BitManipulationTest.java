package languageTest.lesson10;

import junit.framework.*;


public class BitManipulationTest extends TestCase {
    
    public void testSwapUsingXor() {
        int a = 10;
        int b = 20;
        
        // swap a, b
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        
        assertEquals(20, a);
        assertEquals(10, b);
    }
    
    public void testBitSizeOfIntegrals() {
        assertEquals(8, getBitLengthOfByte());
        assertEquals(16, getBitLengthOfChar());
        assertEquals(16, getBitLengthOfShort());
        assertEquals(32, getBitLengthOfInt());
        assertEquals(64, getBitLengthOfLong());
    }
    
    private int getBitLengthOfByte() {
        byte n = 0x1;
        int length = 0;
        while (n != 0) {
            length++;
            n = (byte)(n << 1);
        }
        
        return length;
    }
    
    private int getBitLengthOfChar() {
        char n = 0x1;
        int length = 0;
        while (n != 0) {
            length++;
            n = (char)(n << 1);
        }
        
        return length;
    }
    
    private int getBitLengthOfShort() {
        short n = 0x1;
        int length = 0;
        while (n != 0) {
            length++;
            n = (short)(n << 1);
        }
        
        return length;
    }
    
    private int getBitLengthOfInt() {
        int n = 0x1;
        int length = 0;
        while (n != 0) {
            length++;
            n = n << 1;
        }
        
        return length;
    }
    
    private int getBitLengthOfLong() {
        long n = 0x1;
        int length = 0;
        while (n != 0) {
            length++;
            n = n << 1;
        }
        
        return length;
    }
}
