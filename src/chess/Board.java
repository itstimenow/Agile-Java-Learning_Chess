package chess;

import java.util.ArrayList;
import java.util.Arrays;
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
        int rank = 1;
        setPosition('a', rank, Piece.createWhiteRook());
        setPosition('b', rank, Piece.createWhiteKnight());
        setPosition('c', rank, Piece.createWhiteBishop());
        setPosition('d', rank, Piece.createWhiteQueen());
        setPosition('e', rank, Piece.createWhiteKing());
        setPosition('f', rank, Piece.createWhiteBishop());
        setPosition('g', rank, Piece.createWhiteKnight());
        setPosition('h', rank, Piece.createWhiteRook());
    }
    
    /**
     * Initializes the black-side rank where the king is in, i.e. rank #8.
     */
    private void initializeBlackKingRank() {
        int rank = 8;
        setPosition('a', rank, Piece.createBlackRook());
        setPosition('b', rank, Piece.createBlackKnight());
        setPosition('c', rank, Piece.createBlackBishop());
        setPosition('d', rank, Piece.createBlackQueen());
        setPosition('e', rank, Piece.createBlackKing());
        setPosition('f', rank, Piece.createBlackBishop());
        setPosition('g', rank, Piece.createBlackKnight());
        setPosition('h', rank, Piece.createBlackRook());
    }
    
    /**
     * Initialize the white-side rank where the pawn is in, i.e. rank #2.
     */
    private void initializeWhitePawnRank() {
        int row = 2;
        for (int column = 1; column <= Board.COLUMN_COUNT; ++column)
            setPosition(column, row, Piece.createWhitePawn());
    }
    
    /**
     * Initialize the black-side rank where the pawn is in, i.e. rank #7.
     */
    private void initializeBlackPawnRank() {
        int row = 7;
        for (int column = 1; column <= Board.COLUMN_COUNT; ++column)
            setPosition(column, row, Piece.createBlackPawn());
    }
    
    /**
     * @param file Letter of a to h
     * @param rank Number of 1 to 8
     */
    private void setPosition(char file, int rank, Piece piece) {
        int row = rank;
        
        char firstColumnLetter = 'a';
        int column = Character.getNumericValue(file) 
            - Character.getNumericValue(firstColumnLetter) + 1;
        
        setPosition(column, row, piece);
    }
    
    /**
     * @param column Number of 1 to 8
     * @param row Number of 1 to 8
     */
    private void setPosition(int column, int row, Piece piece) {
        positionState[row - 1][column - 1] = piece;
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
}
