package languageTest;

import java.util.Vector;
import java.util.Enumeration;
import java.util.Iterator;
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
    
    public void testVector() {
        String sequence = sequence(12);
        Vector<String> result = breakString(sequence);
        
        assertEquals("1", result.get(0));
        assertEquals("2", result.get(1));
        assertEquals("3", result.get(2));
        assertEquals("4", result.get(3));
        assertEquals("5*", result.get(4));
        assertEquals("6", result.get(5));
        assertEquals("7", result.get(6));
        assertEquals("8", result.get(7));
        assertEquals("9", result.get(8));
        assertEquals("10*", result.get(9));
        assertEquals("11", result.get(10));
        assertEquals("12", result.get(11));
        
        StringBuilder builder = new StringBuilder();
        Iterator<String> it = result.iterator();
        while (it.hasNext()) {
            builder.append(it.next());
            if (it.hasNext())
                builder.append(' ');
        }
        assertEquals(sequence, builder.toString());
    }
    
    private Vector<String> breakString(String str) {
        Vector<String> result = new Vector<String>();
        String[] tokens = str.split(" ");
        for (String token : tokens)
            result.add(token);
        return result;
    }
}
