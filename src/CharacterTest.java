import junit.framework.*;

public class CharacterTest extends TestCase {
    public void testWhitespace() {
        assertTrue(Character.isWhitespace(' '));
        assertTrue(Character.isWhitespace('\n'));
        assertTrue(Character.isWhitespace('\t'));
        assertTrue(Character.isWhitespace('\r'));
        
        assertFalse(Character.isWhitespace('a'));
        assertFalse(Character.isWhitespace('_'));
    }
    
    public void testJavaIdentifierStart() {
        char[] acceptableChars = new char[] {
            'C', '$', 'Ⅰ', '\u2161', '_'
        };
        for (char ch : acceptableChars)
            assertTrue(Character.isJavaIdentifierStart(ch));
        
        
        char[] unacceptableChars = new char[] {
            '5', '^', '*', '\\', '\t'
        };
        for (char ch : unacceptableChars)
            assertFalse(Character.isJavaIdentifierStart(ch));
    }
    
    public void testJavaIdentifierPart() {
        char[] acceptableChars = new char[] {
            's', '$', '_', '8', 
            '\u2161',   // Roman numeral character
            '\u0300',   // Connecting character
            '\u0010'    // Ignorable character
        };
        for (char ch : acceptableChars) {
            assertTrue(Character.isJavaIdentifierPart(ch));
        }
                
        char[] unacceptableChars = new char[] {
            '*', '-', ' ', '\t', '^'            
        };
        for (char ch : unacceptableChars) {
            assertFalse(Character.isJavaIdentifierPart(ch));
        }
        
        
        int[] acceptableCodePoints = new int[] {
            0x2167, 0x0024, 0x0072
        };
        for (int codePoint : acceptableCodePoints) {
            assertTrue(Character.isJavaIdentifierPart(codePoint));
        }
    }
}
