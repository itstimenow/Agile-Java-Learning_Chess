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
    
    private void blowsUp() {
        throw new SimpleException(EXCEPTION_MESSAGE);
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
    
    public void testWithProblems() {
        try {
          doSomething();
          fail("no exception");
        }
        catch (Exception success) {}
    }
    
    // Exception is a checked exception, must caught it in the method body, 
    // or declare it to be thrown
    void doSomething() throws Exception {
        throw new Exception("blah");
    }
    
    
    /*
    // compile, fail "caught wrong exception"
    public void testExceptionOrder1() {
        try {
            blowsUp();
            rethrows();
            fail("no exception");
        }
        catch (SimpleException yours) {
            fail("caught wrong exception");
        }
        catch (RuntimeException success) {
        }
    }
    
    // compile, fail "caught wrong exception"
    public void testExceptionOrder2() {
        try {
            rethrows();
            blowsUp();
            fail("no exception");
        }
        catch (SimpleException success) {
        }
        catch (RuntimeException failure) {
            fail("caught wrong exception");
        }
    }
    
    
    // not compile
    public void testExceptionOrder3() {
       try {
          blowsUp();
          rethrows();
          fail("no exception");
       }
       catch (RuntimeException success) {
       }
       catch (SimpleException yours) {
          fail("caught wrong exception");
       }
    }
    
    
    
    // not compile
    public void testExceptionOrder4() {
       try {
          blowsUp();
          rethrows();
          fail("no exception");
       }
       catch (RuntimeException fail) {
          fail("exception unacceptable");
       }
       catch (SimpleException yours) {
          fail("caught wrong exception");
       }
       finally {
          return;
       }
    }
    
    
    // compile, fail "caught wrong exception"
    public void testExceptionOrder5() {
       try {
          blowsUp();
          rethrows();
          fail("no exception");
       }
       catch (SimpleException yours) {
          fail("caught wrong exception");
       }
       catch (RuntimeException success) {
       }
    }
    
    // compile, pass
    public void testExceptionOrder6() {
       try {
          rethrows();
          blowsUp();
          fail("no exception");
       }
       catch (SimpleException yours) {
          fail("caught wrong exception");
       }
       catch (RuntimeException success) {
       }
    }
    
    // compile, fail "caught wrong exception"
    public void testExceptionOrder7() {
       try {
          rethrows();
          blowsUp();
          fail("no exception");
       }
       catch (SimpleException success) {
       }
       catch (RuntimeException fail) {
          fail("caught wrong exception");
       }
    }
    
    // compile, pass
    public void testErrorException1() {
       try {
          throw new RuntimeException("fail");
       }
       catch (Exception success) {
       }
    }
    
    // compile, pass
    public void testErrorException2() {
       try {
          new Dyer();
       }
       catch (Exception success) {
       }
    }
    
    // compile, err
    public void testErrorException3() {
       try {
          new Dyer();
       }
       catch (Error success) {
       }
    }
    
    // compile, pass
    public void testErrorException4() {
       try {
          new Dyer();
       }
       catch (Throwable success) {
       }
    }
    
    
    // not compile
    public void testErrorException5() {
       try {
          new Dyer();
       }
       catch (Throwable fail) {
          fail("caught exception in wrong place");
       }
       catch (Error success) {
       }
    }
    
    
    // compile, pass
    public void testErrorException6() {
       try {
          new Dyer();
       }
       catch (Error fail) {
          fail("caught exception in wrong place");
       }
       catch (Throwable success) {
       }
    }
    
    // compile, pass
    public void testErrorException7() {
       try {
          new Dyer();
       }
       catch (Error fail) {
          fail("caught exception in wrong place");
       }
       catch (Throwable success) {
       }
       finally {
          return;
       }
    }
    
    class Dyer {
       Dyer() {
          throw new RuntimeException("oops.");
       }
    }
    */
}

class SimpleException extends RuntimeException {
    SimpleException(String message) {
        super(message);
    }
}
