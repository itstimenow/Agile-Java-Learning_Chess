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
        return left(1);
    }
    
    public Position left(int offset) {
        char leftFile = (char)(this.file - offset);
        return new Position(leftFile, rank);
    }
    
    public Position right() {
        return right(1);
    }
    
    public Position right(int offset) {
        char rightFile = (char)(this.file + offset);
        return new Position(rightFile, rank);
    }
    
    public Position up() {
        return up(1);
    }
    
    public Position up(int offset) {
        int upRank = rank + offset;
        return new Position(file, upRank);
    }
    
    public Position down() {
        return down(1);
    }
    
    public Position down(int offset) {
        int downRank = rank - offset;
        return new Position(file, downRank);
    }
}
