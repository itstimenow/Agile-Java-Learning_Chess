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
    
    public void testLogCounter() {
        Logger logger = Logger.getAnonymousLogger();        
        LogCountHandler handler = new LogCountHandler();
        logger.addHandler(handler);
        
        // set level to ALL so that all messages will be logged
        logger.setLevel(Level.ALL);
        handler.setLevel(Level.ALL);
        
        logger.severe("");
        logger.warning("");
        logger.warning("");
        logger.warning("");
        logger.info("");
        logger.info("");
        logger.info("");
        logger.info("");
        logger.finest("");
        logger.finest("");
        
        assertEquals(1, handler.getCount(Level.SEVERE));
        assertEquals(3, handler.getCount(Level.WARNING));
        assertEquals(4, handler.getCount(Level.INFO));
        assertEquals(0, handler.getCount(Level.CONFIG));
        assertEquals(0, handler.getCount(Level.FINE));
        assertEquals(0, handler.getCount(Level.FINER));
        assertEquals(2, handler.getCount(Level.FINEST));
    }
    
}

class LogCountHandler extends Handler {
    
    private Map<Level, Integer> levelMessageCounter = new HashMap<Level, Integer>();
    
    @Override
    public void publish(LogRecord record) {
        Level level = record.getLevel();
        int originalCount = getCount(level);
        levelMessageCounter.put(level, originalCount + 1);
    }
    
    public int getCount(Level level) {
        if (levelMessageCounter.containsKey(level))
            return levelMessageCounter.get(level);
        return 0;
    }
    
    @Override
    public void flush() {}
    
    @Override
    public void close() {}
}
