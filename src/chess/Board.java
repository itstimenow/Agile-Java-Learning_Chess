package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import pieces.*;
import util.*;


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
        int column = PositionUtil.convertToColumn(file);
        int row = PositionUtil.convertToRow(rank);
        
        put(piece, column, row);
        
        piece.setPosition(file, rank);
        addToPieceCollection(piece);
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
    
    public int getNumberOfPieces(Piece.Color color, Class type) {
        int numberOfPieces = 0;
        
        for (Piece[] pieces : positionState) {
            for (Piece piece : pieces) {
                if (piece == Piece.BLANK) continue;
                if (piece.getColor() == color && piece.is(type))
                    ++numberOfPieces;
            }
        }
        
        return numberOfPieces;
    }
    
    public List<Piece> getBlackSidePieces() {
        return blackSidePieces;
    }
    
    public List<Piece> getWhiteSidePieces() {
        return whiteSidePieces;
    }
    
    public List<Piece> getPiecesOfColor(Piece.Color color) {
        if (color == Piece.Color.BLACK)
            return getBlackSidePieces();
        else
            return getWhiteSidePieces();
    }
    
    public Piece getPieceAt(char file, int rank) {
        int column = PositionUtil.convertToColumn(file);
        int row = PositionUtil.convertToRow(rank);
        return positionState[row][column];
    }
}
