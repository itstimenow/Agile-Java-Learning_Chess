package chess;

import java.util.ArrayList;
import pieces.Piece;
import util.StringUtil;


/**
 * Represents a board in the chess.
 */
public class Board {
    private static final int ROW_COUNT = 8;
    private static final int COLUMN_COUNT = 8;
    
    private Piece[][] positionState = new Piece[ROW_COUNT][COLUMN_COUNT];
    
    
    public Board() {
        initialize();
    }
    
    private void initialize() {
        // Rank 1
        initializeWhiteKingRank();        
        
        // Rank 2
        initializeWhitePawnRank();
        
        // Rank 7
        initializeBlackPawnRank();
        
        // Rank 8
        initializeBlackKingRank();
    }
    
    /**
     * Initializes the white-side rank where the king is in, i.e. rank #1.
     */
    private void initializeWhiteKingRank() {
        initializeKingRank(Piece.WHITE_COLOR);
    }
    
    /**
     * Initializes the black-side rank where the king is in, i.e. rank #8.
     */
    private void initializeBlackKingRank() {
        initializeKingRank(Piece.BLACK_COLOR);
    }
    
    private void initializeKingRank(String color) {
        int rank = (color == Piece.WHITE_COLOR) ? 1 : 8;
        setPosition('a', rank, Piece.create(color, Piece.ROOK));
        setPosition('b', rank, Piece.create(color, Piece.KNIGHT));
        setPosition('c', rank, Piece.create(color, Piece.BISHOP));
        setPosition('d', rank, Piece.create(color, Piece.QUEEN));
        setPosition('e', rank, Piece.create(color, Piece.KING));
        setPosition('f', rank, Piece.create(color, Piece.BISHOP));
        setPosition('g', rank, Piece.create(color, Piece.KNIGHT));
        setPosition('h', rank, Piece.create(color, Piece.ROOK));
    }
    
    /**
     * Initialize the white-side rank where the pawn is in, i.e. rank #2.
     */
    private void initializeWhitePawnRank() {
        initializePawnRank(Piece.WHITE_COLOR);
    }
    
    /**
     * Initialize the black-side rank where the pawn is in, i.e. rank #7.
     */
    private void initializeBlackPawnRank() {
        initializePawnRank(Piece.BLACK_COLOR);
    }
    
    private void initializePawnRank(String color) {
        int row = (color == Piece.WHITE_COLOR) ? 2 : 7;
        for (int column = 1; column <= Board.COLUMN_COUNT; ++column) {
            setPosition(column, row,
                        Piece.create(color, Piece.PAWN));
        }
    }
    
    /**
     * @param file Letter of a to h
     * @param rank Number of 1 to 8
     */
    private void setPosition(char file, int rank, Piece piece) {
        int row = rank;
        
        char firstColumnLetter = 'a';
        int column = (int)file - (int)firstColumnLetter + 1;
        
        setPosition(column, row, piece);
    }
    
    /**
     * @param column Number of 1 to 8
     * @param row Number of 1 to 8
     */
    private void setPosition(int column, int row, Piece piece) {
        positionState[row-1][column-1] = piece;
    }
    
    public int getNumberOfPieces() {
        int numberOfPieces = 0;
                
        for (Piece[] pieces : positionState) {
            for (Piece piece : pieces) {
                if (piece != null)
                    ++numberOfPieces;
            }
        }
        
        return numberOfPieces;
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
                builder.append(piece.getSymbol());
            else
                builder.append('.');
        }
        return builder.toString();
    }
}
