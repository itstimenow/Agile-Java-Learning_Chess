package chess;

import junit.framework.TestSuite;

public class AllTests {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(BoardTest.class);
        suite.addTestSuite(GameTest.class);
        suite.addTestSuite(PositionTest.class);
        return suite;
    }
}
