package languageTest;

import junit.framework.TestSuite;

public class AllTests {
    
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(CharacterTest.class);
        suite.addTestSuite(LoopTest.class);
        suite.addTestSuite(ExceptionTest.class);
        return suite;
    }
}
