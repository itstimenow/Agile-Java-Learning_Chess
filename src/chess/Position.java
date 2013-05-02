package chess;
import util.*;

public class Position {
    private char file;
    private int rank;
    private int column;
    private int row;
    
    public Position(char file, int rank) {
        this.file = file;
        this.rank = rank;
        
        this.column = PositionUtil.convertToColumn(file);
        this.row = PositionUtil.convertToRow(rank);
    }
    
    public char getFile() {
        return file;
    }
    
    public int getRank() {
        return rank;
    }
    
    public int getColumn() {
        return column;
    }
    
    public int getRow() {
        return row;
    }
    
    public Position left() {
        char leftFile = (char)(this.file - 1);
        return new Position(leftFile, rank);
    }
    
    public Position right() {
        char rightFile = (char)(this.file + 1);
        return new Position(rightFile, rank);
    }
    
    public Position up() {
        int upRank = rank + 1;
        return new Position(file, upRank);
    }
    
    public Position down() {
        int downRank = rank - 1;
        return new Position(file, downRank);
    }
}
