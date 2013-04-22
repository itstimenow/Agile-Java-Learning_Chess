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
    private static final int ROW_COUNT = 8;
    private static final int COLUMN_COUNT = 8;
    
    private Piece[][] positionState = new Piece[ROW_COUNT][COLUMN_COUNT];
    
    private List<Piece> blackSidePieces;
    private List<Piece> whiteSidePieces;
    
    
    public static Board createEmptyBoard() {
        return new Board();
    }
    
    public static Board createInitializedBoard() {
        Board board = new Board();
        board.initialize();
        return board;
    }
    
    private Board() {}
        
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
    
    public void placePiece(char file, int rank, Piece piece) {
        setPosition(file, rank, piece);
    }
    
    public double getBlackSideStrength() {
        return getStrengthOfSide(Piece.Color.BLACK);
    }
    
    public double getWhiteSideStrength() {
        return getStrengthOfSide(Piece.Color.WHITE);
    }
    
    private double getStrengthOfSide(Piece.Color color) {
        scanBoard();
        
        double strength = 0.0;
        if (color == Piece.Color.BLACK) {
            for (Piece piece : blackSidePieces)
                strength += piece.getStrength();
        } else if (color == Piece.Color.WHITE) {
            for (Piece piece : whiteSidePieces)
                strength += piece.getStrength();
        }
        
        return strength;
    }
    
    private void scanBoard() {
        blackSidePieces = new ArrayList<Piece>();
        whiteSidePieces = new ArrayList<Piece>();
        for (Piece[] rank : positionState) {
            for (int columnIndex = 0; columnIndex < Board.COLUMN_COUNT; 
                 ++columnIndex) {
                Piece piece = rank[columnIndex];
                if (piece == null) continue;
                if (piece.isBlack()) blackSidePieces.add(piece);
                if (piece.isWhite()) whiteSidePieces.add(piece);
                
                piece.setStrength(calculateStrength(piece, columnIndex));
            }
        }
    }
    
    
    private double calculateStrength(Piece piece, int fileIndex) {
        if (piece.getType() == Piece.Type.QUEEN) return 9.0;
        if (piece.getType() == Piece.Type.ROOK) return 5.0;
        if (piece.getType() == Piece.Type.BISHOP) return 3.0;
        if (piece.getType() == Piece.Type.KNIGHT) return 2.5;
        
        if (piece.getType() == Piece.Type.PAWN)
            return calculateStrengthForPawn(piece, fileIndex);
        
        return 0.0;
    }
    
    private double calculateStrengthForPawn(Piece piece, int columnIndex) {
        if (hasMultiplePawnsInColumn(columnIndex, piece.getColor()))
            return 0.5;
        else
            return 1.0;
    }
    
    private boolean hasMultiplePawnsInColumn(int columnIndex, 
                                            Piece.Color color) {
        int pawnCount = 0;
        for (int rowIndex = 0; rowIndex < Board.ROW_COUNT; ++rowIndex) {
            Piece piece = positionState[rowIndex][columnIndex];
            if (piece == null) continue;
            
            if (piece.getType() == Piece.Type.PAWN 
                    && piece.getColor() == color)
                ++pawnCount;
        }
        
        return pawnCount > 1;
    }
}
