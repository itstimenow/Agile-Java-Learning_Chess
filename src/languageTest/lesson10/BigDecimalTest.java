package languageTest.lesson10;

import java.math.*;
import junit.framework.*;


public class BigDecimalTest extends TestCase {
    
    public void testImmutability() {
        BigDecimal numberA = new BigDecimal("10.0");
        BigDecimal numberAPrime = new BigDecimal("10.0");
        numberA.add(new BigDecimal("5.0"));
        
        assertEquals(numberA, numberAPrime);
    }
    
    public void testArithmeticOperations() {
        BigDecimal a = new BigDecimal("10.00");
        BigDecimal b = new BigDecimal("1");
        assertFalse(a.equals(b));
        
        BigDecimal c = b.multiply(new BigDecimal("10.00"));
        assertEquals(c, a);
        
        BigDecimal divisor = new BigDecimal("10");
        int scale = 0;
        BigDecimal d = a.divide(divisor, scale, RoundingMode.HALF_UP);
        assertEquals(d, b);
    }
    
    public void testPrecision() {
        BigDecimal a = new BigDecimal("0.01");
        BigDecimal b = new BigDecimal("0.005").multiply(new BigDecimal("2.0"));
        assertFalse(a.equals(b));
        
        MathContext mathContext = new MathContext(1);
        a = new BigDecimal(a.toString(), mathContext);
        b = new BigDecimal(b.toString(), mathContext);
        assertEquals(a, b);
    }
}
