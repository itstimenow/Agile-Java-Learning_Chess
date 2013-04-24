package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Map;
import java.util.EnumMap;
import pieces.Piece;
import util.StringUtil;


/**
 * Represents a board in the chess.
 */
public class Board {
    private static final int ROW_COUNT = 8;
    private static final int COLUMN_COUNT = 8;
    
    
    private Piece[][] positionState = new Piece[ROW_COUNT][COLUMN_COUNT];
    
    private List<Piece> blackSidePieces = new ArrayList<Piece>();
    private List<Piece> whiteSidePieces = new ArrayList<Piece>();
    
    private List<Piece> allPawns = new ArrayList<Piece>();
    private int[] blackPawnCountInColumn = new int[COLUMN_COUNT];
    private int[] whitePawnCountInColumn = new int[COLUMN_COUNT];
    
    private Map<Piece.Type, Double> baseStrengthValues;
    
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
        placePiece('a', rank, Piece.createWhiteRook());
        placePiece('b', rank, Piece.createWhiteKnight());
        placePiece('c', rank, Piece.createWhiteBishop());
        placePiece('d', rank, Piece.createWhiteQueen());
        placePiece('e', rank, Piece.createWhiteKing());
        placePiece('f', rank, Piece.createWhiteBishop());
        placePiece('g', rank, Piece.createWhiteKnight());
        placePiece('h', rank, Piece.createWhiteRook());
    }
    
    /**
     * Initializes the black-side rank where the king is in, i.e. rank #8.
     */
    private void initializeBlackKingRank() {
        int rank = 8;
        placePiece('a', rank, Piece.createBlackRook());
        placePiece('b', rank, Piece.createBlackKnight());
        placePiece('c', rank, Piece.createBlackBishop());
        placePiece('d', rank, Piece.createBlackQueen());
        placePiece('e', rank, Piece.createBlackKing());
        placePiece('f', rank, Piece.createBlackBishop());
        placePiece('g', rank, Piece.createBlackKnight());
        placePiece('h', rank, Piece.createBlackRook());
    }
    
    /**
     * Initialize the white-side rank where the pawn is in, i.e. rank #2.
     */
    private void initializeWhitePawnRank() {
        int row = 1;
        for (int column = 0; column < Board.COLUMN_COUNT; ++column)
            placePiece(column, row, Piece.createWhitePawn());
    }
    
    /**
     * Initialize the black-side rank where the pawn is in, i.e. rank #7.
     */
    private void initializeBlackPawnRank() {
        int row = 6;
        for (int column = 0; column < Board.COLUMN_COUNT; ++column)
            placePiece(column, row, Piece.createBlackPawn());
    }
    
    /**
     * @param file Letter of a to h
     * @param rank Number of 1 to 8
     */
    public void placePiece(char file, int rank, Piece piece) {
        int row = rank - 1;
        
        char firstColumnLetter = 'a';
        int column = Character.getNumericValue(file) 
            - Character.getNumericValue(firstColumnLetter);
        
        placePiece(column, row, piece);
        
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
    private void placePiece(int column, int row, Piece piece) {
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
        return getBaseStrengthValues().get(piece.getType());
    }
    
    private Map<Piece.Type, Double> getBaseStrengthValues() {
        if (baseStrengthValues == null)
            loadBaseStrengthValues();
        return baseStrengthValues;
    }
    
    private void loadBaseStrengthValues() {
        baseStrengthValues = new EnumMap<Piece.Type, Double>(Piece.Type.class);
        baseStrengthValues.put(Piece.Type.QUEEN, 9.0);
        baseStrengthValues.put(Piece.Type.ROOK, 5.0);
        baseStrengthValues.put(Piece.Type.BISHOP, 3.0);
        baseStrengthValues.put(Piece.Type.KNIGHT, 2.5);
        baseStrengthValues.put(Piece.Type.PAWN, 1.0);
        baseStrengthValues.put(Piece.Type.KING, 0.0);
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
}
