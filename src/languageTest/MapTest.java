package languageTest;

import java.util.*;
import junit.framework.TestCase;


public class MapTest extends TestCase {
    
    public void testWordCount() {
        final String input = "Create a String literal using the first two sentences of this " 
                             + "exercise. You will create a WordCount class to parse through the "
                             + "text and count the number of instances of each word.";
        WordCount counter = new WordCount(input);
        Set<String> result = counter.getResult();
        
        Set<String> expectedResult = countWords("Create", "a", "String", "literal", "using", "the",
                                                "first", "two", "sentences", "of", "this", 
                                                "exercise", "You", "will", "create", "a", 
                                                "WordCount", "class", "to", "parse", "through", 
                                                "the", "text", "and", "count", "the", "number",
                                                "of", "instances", "of", "each", "word");
        assertEquals(expectedResult, result);
    }
    
    private Set<String> countWords(String... words) {
        Map<String, Integer> wordCounter = new HashMap<String, Integer>();
        for (String word : words) {
            String lowercaseWord = word.toLowerCase();
            int count = wordCounter.containsKey(lowercaseWord)
                        ? (wordCounter.get(lowercaseWord) + 1) : 1;
            wordCounter.put(lowercaseWord, count);
        }
        
        Set<String> result = new HashSet<String>();
        for (Map.Entry<String, Integer> entry : wordCounter.entrySet())
            result.add(entry.getKey() + ":" + entry.getValue());
        return result;
    }
    
    public void testNameEquality() {
        Name eq1 = new Name("Eric");
        Name eq2 = new Name("Eric");
        Name eq3 = new Name("Eric");
        Name neq = new Name("Bruce");
        
        // reflexivity
        assertEquals(eq1, eq1);
        
        // symmetry
        Name nameAPrime1 = new Name("Eric");
        assertEquals(eq1, eq2);
        assertEquals(eq2, eq1);
        
        // transitivity
        assertEquals(eq2, eq3);
        assertEquals(eq1, eq3);
        
        // consistency
        assertEquals(eq1, eq2);
        
        // comparison to null
        assertFalse(eq1.equals(null));
        assertFalse(neq.equals(null));
        
        // unequality
        assertFalse(eq1.equals(neq));
    }
    
    public void testNameSet() {
        Set<Name> names = new HashSet<Name>();
        names.add(new Name("AAA"));
        names.add(new Name("Foo"));
        names.add(new Name("BBB"));
        
        assertTrue(names.contains(new Name("AAA")));
        assertTrue(names.contains(new Name("Foo")));
        assertTrue(names.contains(new Name("BBB")));
    }
}


class WordCount {
    
    private Map<String, Integer> countData = new HashMap<String, Integer>();
    
    public WordCount(String input) {
        String[] words = input.split("\\W+");
        for (String word : words) {
            String lowercaseWord = word.toLowerCase();
            int count = countData.containsKey(lowercaseWord) 
                        ? (countData.get(lowercaseWord) + 1) : 1;
            countData.put(lowercaseWord, count);
        }
    }
    
    public Set<String> getResult() {
        Set<String> result = new HashSet<String>();
        for (Map.Entry<String, Integer> entry : countData.entrySet())
            result.add(entry.getKey() + ":" + entry.getValue());
        return result;
    }
}


class Name {
    
    private String name;
    
    public Name(String name) {
        this.name = name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj.getClass() != this.getClass())
            return false;
        
        Name that = (Name)obj;
        if (that.name == this.name)
            return true;
        
        return false;
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
