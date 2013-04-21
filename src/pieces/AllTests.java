package pieces;

import junit.framework.TestSuite;

public class AllTests {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(PieceTest.class);
        return suite;
    }
}
