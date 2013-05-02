package util;

import junit.framework.TestSuite;

public class AllTests {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(StringUtilTest.class);
        suite.addTestSuite(PositionUtilTest.class);
        return suite;
    }
}
