package util;

public class StringUtil {
    public static final String NEWLINE = System.getProperty("line.separator");
    
    private StringUtil() {}
    
    /**
     * Concatenates newline string to the end of the specified string. 
     */
    public static String appendNewline(String str) {
        return str + NEWLINE;
    }
}
