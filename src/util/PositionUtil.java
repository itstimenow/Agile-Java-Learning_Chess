package util;

public class PositionUtil {

    private PositionUtil() {}

    public static int convertToColumn(char file) {
        char firstFileLetter = 'a';
        int column = Character.getNumericValue(file) - Character.getNumericValue(firstFileLetter);
        return column;
    }
    
    public static int convertToRow(int rank) {
        return rank - 1;
    }
}
