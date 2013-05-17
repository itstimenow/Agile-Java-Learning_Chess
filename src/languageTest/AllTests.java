package languageTest;

import junit.framework.TestSuite;

public class AllTests {
    
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(CharacterTest.class);
        suite.addTestSuite(LoopTest.class);
        suite.addTestSuite(ExceptionTest.class);
        suite.addTestSuite(LoggingTest.class);
        suite.addTestSuite(MapTest.class);
        suite.addTest(languageTest.lesson10.AllTests.suite());
        return suite;
    }
}
