package languageTest.lesson10;

import java.util.*;
import junit.framework.*;


public class RandomTest extends TestCase {

    private Random random = new Random();
    
    public void testCustomRandom() {
        final int start = 1;
        final int end = 50;
        
        for (int i = 0; i < 50; i++) {
            int value = random(start, end);
            assertTrue(start <= value && value <= end);
        }
    }
    
    private int random(int start, int end) {
        if (start > end)
            throw new IllegalArgumentException("start should be less than or equal to end");
        if (start == end)
            return start;
        
        double increase = Math.random() * (end - start);
        int increaseInt = (int)Math.round(increase);
        return start + increaseInt;
    }
    
    
    public void testRandomSwap() {
        // Initialize numbers
        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 1; i <= 100; i++)
            numbers.add(i);
        
        Verifier verifier = new Verifier(numbers);
        
        // Swap 100 times and test
        for (int i = 0; i < 100; i++) {
            randomSwap(numbers);
            verifier.verifySwapping(numbers);
        }
    }
    
    private void randomSwap(List<Integer> numbers) {
        int bound = numbers.size();
        int indexA = random.nextInt(bound);
        int indexB = indexA;
        while (indexA == indexB)
            indexB = random.nextInt(bound);
        
        int temp = numbers.get(indexA);
        numbers.set(indexA, numbers.get(indexB));
        numbers.set(indexB, temp);
    }
    
    class Verifier {
        
        private List<Integer> oldNumberList;
        
        Verifier(List<Integer> numbers) {
            oldNumberList = new ArrayList<Integer>(numbers);
        }
        
        void verifySwapping(List<Integer> newNumberList) {
            assertEquals(oldNumberList.size(), newNumberList.size());
            
            List<Integer> indexesOfDifferences = new ArrayList<Integer>();
            for (int i = 0; i < oldNumberList.size(); i++) {
                if (oldNumberList.get(i) != newNumberList.get(i))
                    indexesOfDifferences.add(i);
            }
            
            assertEquals(2, indexesOfDifferences.size());
            
            int indexA = indexesOfDifferences.get(0);
            int indexB = indexesOfDifferences.get(1);
            assertEquals(oldNumberList.get(indexA), newNumberList.get(indexB));
            assertEquals(oldNumberList.get(indexB), newNumberList.get(indexA));
            
            updateOldNumberList(indexA, indexB);
        }
        
        private void updateOldNumberList(int indexA, int indexB) {
            int temp = oldNumberList.get(indexA);
            oldNumberList.set(indexA, oldNumberList.get(indexB));
            oldNumberList.set(indexB, temp);
        }
    }
}
