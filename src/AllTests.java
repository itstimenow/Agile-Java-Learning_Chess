import junit.framework.*;

public class AllTests {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(chess.AllTests.suite());
        suite.addTest(pieces.AllTests.suite());
        suite.addTest(util.AllTests.suite());
        suite.addTest(languageTest.AllTests.suite());
        return suite;
    }
}
