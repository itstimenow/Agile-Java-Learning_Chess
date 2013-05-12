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
