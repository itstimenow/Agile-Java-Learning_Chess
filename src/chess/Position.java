package chess;
import util.*;

public class Position {
    
    private static Position[][] allPositions = new Position[Board.ROW_COUNT][Board.COLUMN_COUNT];
    
    private char file;
    private int rank;
    private int column;
    private int row;
    
    
    private Position(char file, int rank) {
        this.file = file;
        this.rank = rank;
        
        this.column = PositionUtil.convertToColumn(file);
        this.row = PositionUtil.convertToRow(rank);
    }
    
    
    public static Position at(char file, int rank) {
        int row = PositionUtil.convertToRow(rank);
        int column = PositionUtil.convertToColumn(file);
        
        if (allPositions[row][column] == null)
            allPositions[row][column] = new Position(file, rank);
        return allPositions[row][column];
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
        return Position.at(leftFile, rank);
    }
    
    public Position right() {
        return right(1);
    }
    
    public Position right(int offset) {
        char rightFile = (char)(this.file + offset);
        return Position.at(rightFile, rank);
    }
    
    public Position up() {
        return up(1);
    }
    
    public Position up(int offset) {
        int upRank = rank + offset;
        return Position.at(file, upRank);
    }
    
    public Position down() {
        return down(1);
    }
    
    public Position down(int offset) {
        int downRank = rank - offset;
        return Position.at(file, downRank);
    }
}
