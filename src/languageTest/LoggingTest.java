package languageTest;

import java.util.*;
import java.util.logging.*;
import junit.framework.TestCase;


public class LoggingTest extends TestCase {
    
    public void testLogging() {
        try {
            throwException();
            fail("Expected an exception was thrown from throwException()");
        }
        catch (Exception expectedException) {
            log(expectedException);
        }
    }
    
    private void throwException() {
        throw new RuntimeException("lalala... a runtime exception arise");
    }
    
    private void log(Exception e) {
        List<StackTraceElement> traceElements = Arrays.asList(e.getStackTrace());
        ListIterator<StackTraceElement> li = traceElements.listIterator(traceElements.size());
        
        StringBuilder exceptionMessageBuilder = new StringBuilder();
        exceptionMessageBuilder.append(e.getMessage() + "\n");
        while (li.hasPrevious()) {
            StackTraceElement trace = li.previous();
            exceptionMessageBuilder.append(trace.toString() + "\n");
        }
        
        Logger logger = Logger.getAnonymousLogger();
        logger.warning(exceptionMessageBuilder.toString());
    }
}
