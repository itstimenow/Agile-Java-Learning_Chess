package languageTest;

import junit.framework.*;


public class ExceptionTest extends TestCase {
    
    private static final String EXCEPTION_MESSAGE = "exception occurs...";
    
    public void testException() {
        try {
            blowsUp();
            fail("Expected blowsUp() throw SimpleException");
        }
        catch (SimpleException e) {
            assertEquals(RuntimeException.class, e.getClass().getSuperclass());
        }
        catch (Exception e) {
            fail("Expected SimpleException, but was " + e.getClass().getName());
        }
    }
    
    public void testRethrowException() {
        try {
            rethrows();
        }
        catch (RuntimeException e) {
            Throwable cause = e.getCause();
            assertTrue(cause instanceof SimpleException);
            assertEquals(EXCEPTION_MESSAGE, cause.getMessage());
        }
    }
    
    private void rethrows() {
        try {
            blowsUp();
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void blowsUp() {
        throw new SimpleException(EXCEPTION_MESSAGE);
    }
}

class SimpleException extends RuntimeException {
    SimpleException(String message) {
        super(message);
    }
}
