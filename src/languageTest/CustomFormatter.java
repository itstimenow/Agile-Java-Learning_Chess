package languageTest;

import java.util.logging.*;


public class CustomFormatter extends Formatter {
    
    private CountingLogHandler handler;
    
    public CustomFormatter() {
    }
    
    public CustomFormatter(CountingLogHandler handler) {
        this.handler = handler;
    }
    
    @ Override
    public String format(LogRecord record) {
        Level level = record.getLevel();
        String message = record.getMessage();
        
        if (handler == null)
            return String.format("%s: %s\n", level, message);
        
        return String.format("%s: %s (%s total = %d\n)", 
                             level, message, level, handler.getCount(level));
    }
}
