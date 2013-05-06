package languageTest;

import junit.framework.TestCase;

public class LoopTest extends TestCase {
    
    public void testWhile() {
        assertEquals(1, factorialUsingWhile(0));
        assertEquals(1, factorialUsingWhile(1));
        assertEquals(2, factorialUsingWhile(2));
        assertEquals(120, factorialUsingWhile(5));
        assertEquals(39916800, factorialUsingWhile(11));
    }
    
    private long factorialUsingWhile(int n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return 1;
        
        long fac = 1;
        int i = 2;
        while (true) {
            fac *= i;
            i++;
            
            if (i > n)
                break;
        }
        
        return fac;
    }
    
    public void testFor() {
        assertEquals(1, factorialUsingFor(0));
        assertEquals(1, factorialUsingFor(1));
        assertEquals(2, factorialUsingFor(2));
        assertEquals(120, factorialUsingFor(5));
        assertEquals(39916800, factorialUsingFor(11));
    }
    
    private long factorialUsingFor(int n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return 1;
        
        long fac = 1;
        for (int i = 2; i <= n; i++)
            fac *= i;
        
        return fac;
    }
    
    public void testDoWhile() {
        assertEquals(1, factorialUsingDoWhile(0));
        assertEquals(1, factorialUsingDoWhile(1));
        assertEquals(2, factorialUsingDoWhile(2));
        assertEquals(120, factorialUsingDoWhile(5));
        assertEquals(39916800, factorialUsingDoWhile(11));
    }
    
    private long factorialUsingDoWhile(int n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return 1;
        
        long fac = 1;
        int i = 2;
        do {
            fac *= i;
            i++;
        } while(i <= n);
        
        return fac;
    }
    
    public void testContinue() {
        assertEquals("1 2 3 4 5* 6 7 8 9 10* 11 12", sequence(12));
    }
    
    private String sequence(int n) {
        if (n < 1)
            return "";
        
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            builder.append(i);
            if (i % 5 == 0)
                builder.append('*');
            if (i != n)
                builder.append(' ');
        }
        return builder.toString();
    }
}
