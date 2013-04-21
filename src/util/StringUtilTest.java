package util;

import junit.framework.TestCase;

public class StringUtilTest extends TestCase {
    public void testAppendNewline() {
        String str = "ABC";
        assertEquals(str + StringUtil.NEWLINE,
                     StringUtil.appendNewline(str));
    }
}
