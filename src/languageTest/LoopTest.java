package languageTest;

import junit.framework.TestCase;

public class LoopTest extends TestCase {
    
    public void testFactorialUsingWhile() {
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
    
    public void testFactorialUsingFor() {
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
    
    public void testFactorialUsingDo() {
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
}
