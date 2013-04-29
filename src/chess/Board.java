package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import pieces.Piece;
import util.StringUtil;


/**
 * Represents a board in the chess.
 */
public class Board {
    
    public static final int ROW_COUNT = 8;
    public static final int COLUMN_COUNT = 8;
    
    public static final char[] FILES = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
    public static final int[] RANKS = { 1, 2, 3, 4, 5, 6, 7, 8 };
    
    
    private Piece[][] positionState = new Piece[ROW_COUNT][COLUMN_COUNT];
    
    private List<Piece> blackSidePieces = new ArrayList<Piece>();
    private List<Piece> whiteSidePieces = new ArrayList<Piece>();
    
    private List<Piece> allPawns = new ArrayList<Piece>();
    private int[] blackPawnCountInColumn = new int[COLUMN_COUNT];
    private int[] whitePawnCountInColumn = new int[COLUMN_COUNT];
    
    
    public static Board createEmptyBoard() {
        Board board = new Board();
        for (char file : FILES)
            for (int rank : RANKS)
                board.put(Piece.BLANK, file, rank);
        
        return board;
    }
    
    private Board() {}
    
    public void put(Piece piece, Position position) {
        char file = position.getFile();
        int rank = position.getRank();
        put(piece, file, rank);
    }
    
    /**
     * @param file Letter of a to h
     * @param rank Number of 1 to 8
     */
    public void put(Piece piece, char file, int rank) {
        int row = rank - 1;
        
        char firstColumnLetter = 'a';
        int column = Character.getNumericValue(file) 
            - Character.getNumericValue(firstColumnLetter);
        
        put(piece, column, row);
        
        piece.setPosition(file, rank);
        addToPieceCollection(piece);
        addToPawnCollection(piece);
        incrementPawnCount(piece, column);
        setStrength(piece);
    }
    
    /**
     * @param column Number of 0 to 7, corresponding to file 'a' to 'h'
     * @param row Number of 0 to 7, corresponding to rank 1 to 8
     */
    private void put(Piece piece, int column, int row) {
        positionState[row][column] = piece;
    }
    
    private void addToPieceCollection(Piece piece) {
        if (piece.isBlack())
            blackSidePieces.add(piece);
        if (piece.isWhite())
            whiteSidePieces.add(piece);
    }
    
    private void addToPawnCollection(Piece piece) {
        if (piece.getType() == Piece.Type.PAWN)
            allPawns.add(piece);
    }
    
    private void incrementPawnCount(Piece piece, int column) {
        if (isBlackPawn(piece)) {
            ++blackPawnCountInColumn[column];
        }
        
        if (isWhitePawn(piece)) {
            ++whitePawnCountInColumn[column];
        }
    }
    
    private boolean isBlackPawn(Piece piece) {
        return piece.isBlack() && piece.getType() == Piece.Type.PAWN;
    }
    
    private boolean isWhitePawn(Piece piece) {
        return piece.isWhite() && piece.getType() == Piece.Type.PAWN;
    }
    
    private void setStrength(Piece piece) {
        if (piece.getType() != Piece.Type.PAWN) {
            double strength = calculateStrengthForNonPawnPiece(piece);
            piece.setStrength(strength);
        } else {
            setStrengthForPawn(piece);
        }
    }
    
    private double calculateStrengthForNonPawnPiece(Piece piece) {
        return piece.getType().getPoints();
    }
    
    private void setStrengthForPawn(Piece pawn) {
        double strength = calculateStrengthForPawn(pawn);
        
        for (Piece piece : allPawns) {
            if (piece.getColor() == pawn.getColor()
                    && piece.getPositionFile() == pawn.getPositionFile())
                piece.setStrength(strength);
        }
    }
    
    private double calculateStrengthForPawn(Piece pawn) {
        int pawnCount = 0;
        if (pawn.isBlack())
            pawnCount = getBlackPawnCountInSameColumnAs(pawn);
        else
            pawnCount = getWhitePawnCountInSameColumnAs(pawn);
        
        // if there are multiple pawns in the same column, strength is 0.5
        if (pawnCount > 1)
            return 0.5;
        else
            return 1.0;
    }
    
    private int getBlackPawnCountInSameColumnAs(Piece pawn) {
        int column = pawn.getPositionColumn();
        return blackPawnCountInColumn[column];
    }
    
    private int getWhitePawnCountInSameColumnAs(Piece pawn) {
        int column = pawn.getPositionColumn();
        return whitePawnCountInColumn[column];
    }
    
    
    public int getNumberOfPieces() {
        int numberOfPieces = 0;
                
        for (Piece[] pieces : positionState) {
            for (Piece piece : pieces) {
                if (piece != Piece.BLANK)
                    ++numberOfPieces;
            }
        }
        
        return numberOfPieces;
    }
    
    public int getNumberOfPieces(Piece.Color color, Piece.Type type) {
        int numberOfPieces = 0;
        
        for (Piece[] pieces : positionState) {
            for (Piece piece : pieces) {
                if (piece == null) continue;
                if (piece.getColor() == color && piece.getType() == type)
                    ++numberOfPieces;
            }
        }
        
        return numberOfPieces;
    }
    
    public List<Piece> getBlackSidePieces() {
        Collections.sort(blackSidePieces);
        Collections.reverse(blackSidePieces);
        return blackSidePieces;
    }
    
    public List<Piece> getWhiteSidePieces() {
        Collections.sort(whiteSidePieces);
        Collections.reverse(whiteSidePieces);
        return whiteSidePieces;
    }
    
    public String getPrint() {
        StringBuilder builder = new StringBuilder();
        
        // Since for-loop iterate through from rank #1 to #8, while the print 
        // needs to show the board from rank #8 to rank #1, following approach
        // is used to make it print correct result.
        for (Piece[] rank : positionState) {
            builder.insert(0, StringUtil.NEWLINE);
            builder.insert(0, getRankPrint(rank));
        }
        
        return builder.toString();
    }
    
    private String getRankPrint(Piece[] rank) {
        StringBuilder builder = new StringBuilder();
        for (Piece piece : rank) {
            if (piece != null)
                builder.append(piece.getRepresentation());
            else
                builder.append('.');
        }
        return builder.toString();
    }
    
    public Piece getPieceAt(char file, int rank) {
        int rowIndex = rank - 1;
        
        char firstColumnLetter = 'a';
        int columnIndex = Character.getNumericValue(file) 
            - Character.getNumericValue(firstColumnLetter);
        
        return positionState[rowIndex][columnIndex];
    }
    
    public double getBlackSideStrength() {
        return getStrengthOf(blackSidePieces);
    }
    
    public double getWhiteSideStrength() {
        return getStrengthOf(whiteSidePieces);
    }
    
    private double getStrengthOf(List<Piece> pieces) {
        double strength = 0.0;
        for (Piece piece : pieces)
            strength += piece.getStrength();
        return strength;
    }
    
    public void moveKingLeft(Piece king) {
        int originalRow = king.getPositionRow();
        int originalColumn = king.getPositionColumn();
        
        king.moveLeft();
        positionState[originalRow][originalColumn] = null;
        positionState[originalRow][originalColumn - 1] = king;
    }
    
    public void moveKingRight(Piece king) {
        int originalRow = king.getPositionRow();
        int originalColumn = king.getPositionColumn();
        
        king.moveRight();
        positionState[originalRow][originalColumn] = null;
        positionState[originalRow][originalColumn + 1] = king;
    }
    
    public void moveKingUp(Piece king) {
        int originalRow = king.getPositionRow();
        int originalColumn = king.getPositionColumn();
        
        king.moveUp();
        positionState[originalRow][originalColumn] = null;
        positionState[originalRow + 1][originalColumn] = king;
    }
    
    public void moveKingDown(Piece king) {
        int originalRow = king.getPositionRow();
        int originalColumn = king.getPositionColumn();
        
        king.moveDown();
        positionState[originalRow][originalColumn] = null;
        positionState[originalRow - 1][originalColumn] = king;
    }
}
