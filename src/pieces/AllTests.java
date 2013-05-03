package pieces;

import junit.framework.TestSuite;

public class AllTests {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(PieceTest.class);
        suite.addTestSuite(KingTest.class);
        suite.addTestSuite(QueenTest.class);
        suite.addTestSuite(BishopTest.class);
        suite.addTestSuite(KnightTest.class);
        suite.addTestSuite(RookTest.class);
        suite.addTestSuite(PawnTest.class);
        return suite;
    }
}
