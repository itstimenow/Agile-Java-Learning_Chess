package languageTest;

import java.util.*;
import java.util.logging.*;
import java.util.logging.Formatter;
import junit.framework.TestCase;


public class LoggingTest extends TestCase {
    
    public void testLogging() {
        try {
            throwException();
            fail("Expected an exception was thrown from throwException()");
        }
        catch (Exception expectedException) {
            //log(expectedException);
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
    
    public void testCountingLog() {
        Logger logger = Logger.getAnonymousLogger();        
        CountingLogHandler handler = new CountingLogHandler();
        logger.addHandler(handler);
        
        // set level to ALL so that all messages will be logged
        logger.setLevel(Level.ALL);
        handler.setLevel(Level.ALL);
        
        logger.severe("severe event");
        logger.warning("warning event");
        logger.warning("warning event");
        logger.warning("warning event");
        logger.info("info event");
        logger.info("info event");
        logger.info("info event");
        logger.info("info event");
        logger.finest("finest event");
        logger.finest("finest event");
        
        assertEquals(1, handler.getCount(Level.SEVERE));
        assertEquals(3, handler.getCount(Level.WARNING));
        assertEquals(4, handler.getCount(Level.INFO));
        assertEquals(0, handler.getCount(Level.CONFIG));
        assertEquals(0, handler.getCount(Level.FINE));
        assertEquals(0, handler.getCount(Level.FINER));
        assertEquals(2, handler.getCount(Level.FINEST));
    }
    
    public void testCountingLogFormatter() {
        CountingLogHandler handler = new CountingLogHandler();
        assertTrue(handler.getFormatter() instanceof CustomFormatter);
    }
    
    public void testCountingLogHandlerLoggingSummary() {
        CountingLogHandler handler = new CountingLogHandler();
        CustomFormatter formatter = new CustomFormatter(handler);
        StringBuilder summaryBuilder = new StringBuilder();
        
        LogRecord record = new LogRecord(Level.INFO, "info message blah blah...");
        for (int i = 0; i < 4; i++) {
            handler.publish(record);
            summaryBuilder.append(formatter.format(record) + "\n");
        }
        
        record = new LogRecord(Level.WARNING, "warning message blah blah...");
        for (int i = 0; i < 2; i++) {
            handler.publish(record);
            summaryBuilder.append(formatter.format(record) + "\n");
        }
        
        String expectedSummary = summaryBuilder.toString();
        assertEquals(expectedSummary, handler.getLoggingSummary());
    }
    
    public void testCustomFormatter() {
        Formatter formatter = new CustomFormatter();
        
        Level level = Level.WARNING;
        String message = "A warning message...";
        LogRecord record = new LogRecord(level, message);
        
        String formattedMessage = formatter.format(record);
        String expectedFormattedMessage = String.format("%s: %s\n", level, message);
        assertEquals(expectedFormattedMessage, formattedMessage);
        
        
        CountingLogHandler handler = new CountingLogHandler();
        handler.publish(record);
        handler.publish(record);
        formatter = new CustomFormatter(handler);
        formattedMessage = formatter.format(record);
        expectedFormattedMessage = String.format("%s: %s (%s total = %d\n)", level, message, level,
                                                 ((CountingLogHandler)handler).getCount(level));
        assertEquals(expectedFormattedMessage, formattedMessage);
    }
}

class CountingLogHandler extends Handler {
    
    private Map<Level, Integer> levelMessageCounter = new HashMap<Level, Integer>();
    private StringBuilder summaryBuilder = new StringBuilder();
    
    
    public CountingLogHandler() {
        Formatter formatter = new CustomFormatter(this);
        setFormatter(formatter);
    }
    
    
    @Override
    public void publish(LogRecord record) {
        Level level = record.getLevel();
        int originalCount = getCount(level);
        levelMessageCounter.put(level, originalCount + 1);
        
        Formatter formatter = getFormatter();
        summaryBuilder.append(formatter.format(record) + "\n");
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
    
    public String getLoggingSummary() {
        return summaryBuilder.toString();
    }
}
