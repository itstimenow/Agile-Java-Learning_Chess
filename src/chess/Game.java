package chess;

import java.util.List;
import pieces.Piece;
import util.StringUtil;


public class Game {
    private Board board;
    
    public Game() {
        board = Board.createEmptyBoard();
    }
    
    public Board getBoard() {
        return board;
    }
    
    public void initialize() {
        // Only need to initialize rank #1, #2, #7, #8, since all ranks are by default empty
        initializeRank01();
        initializeRank02();
        initializeRank07();
        initializeRank08();
    }
    
    /**
     * Initializes rank #1, the white-side rank where the king is in.
     */
    private void initializeRank01() {
        int rank = 1;
        board.put(Piece.createWhiteRook(),   'a', rank);
        board.put(Piece.createWhiteKnight(), 'b', rank);
        board.put(Piece.createWhiteBishop(), 'c', rank);
        board.put(Piece.createWhiteQueen(),  'd', rank);
        board.put(Piece.createWhiteKing(),   'e', rank);
        board.put(Piece.createWhiteBishop(), 'f', rank);
        board.put(Piece.createWhiteKnight(), 'g', rank);
        board.put(Piece.createWhiteRook(),   'h', rank);
    }
    
    /**
     * Initializes rank #2, the white-side rank where the pawn is in.
     */
    private void initializeRank02() {
        int rank = 2;
        for (char file : Board.FILES)
            board.put(Piece.createWhitePawn(), file, rank);
    }
    
    /**
     * Initializes rank #7, the black-side rank where the pawn is in.
     */
    private void initializeRank07() {
        int rank = 7;
        for (char file : Board.FILES)
            board.put(Piece.createBlackPawn(), file, rank);
    }
    
    /**
     * Initializes rank #8, the black-side rank where the king is in.
     */
    private void initializeRank08() {
        int rank = 8;
        board.put(Piece.createBlackRook(),   'a', rank);
        board.put(Piece.createBlackKnight(), 'b', rank);
        board.put(Piece.createBlackBishop(), 'c', rank);
        board.put(Piece.createBlackQueen(),  'd', rank);
        board.put(Piece.createBlackKing(),   'e', rank);
        board.put(Piece.createBlackBishop(), 'f', rank);
        board.put(Piece.createBlackKnight(), 'g', rank);
        board.put(Piece.createBlackRook(),   'h', rank);
    }
    
    public String getBoardPrint() {
        StringBuilder builder = new StringBuilder();
        
        // Loop through top rank to bottom rank, i.e. rank #8 to #1
        for (int index = Board.RANKS.length - 1; index >=0; index--) {
            int rank = Board.RANKS[index];
            
            for (char file : Board.FILES) {
                Piece piece = board.getPieceAt(file, rank);
                builder.append(piece.getRepresentation());
            }
            builder.append(StringUtil.NEWLINE);
        }
        
        return builder.toString();
    }
    
    public void moveLeft(Piece piece) {
        moveLeft(piece, 1);
    }
    
    /**
     * Moves a piece in the left horizontal direction.
     * @param squares The number of squares to move over
     */
    public void moveLeft(Piece piece, int squares) {
        Position originalPosition = piece.getPosition();
        
        board.put(Piece.BLANK, originalPosition);
        board.put(piece, originalPosition.left(squares));
    }
    
    public void moveRight(Piece piece) {
        moveRight(piece, 1);
    }
    
    /**
     * Moves a piece in the right horizontal direction.
     * @param squares The number of squares to move over
     */
    public void moveRight(Piece piece, int squares) {
        Position originalPosition = piece.getPosition();
        
        board.put(Piece.BLANK, originalPosition);
        board.put(piece, originalPosition.right(squares)); 
    }
    
    public void moveUp(Piece piece) {
        moveUp(piece, 1);
    }
    
    /**
     * Moves a piece in the up vertical direction.
     * @param squares The number of squares to move over
     */
    public void moveUp(Piece piece, int squares) {
        Position originalPosition = piece.getPosition();
        
        board.put(Piece.BLANK, originalPosition);
        board.put(piece, originalPosition.up(squares));
    }
    
    public void moveDown(Piece piece) {
        moveDown(piece, 1);
    }
    
    /**
     * Moves a piece in the down vertical direction.
     * @param squares The number of squares to move over
     */
    public void moveDown(Piece piece, int squares) {
        Position originalPosition = piece.getPosition();
        
        board.put(Piece.BLANK, originalPosition);
        board.put(piece, originalPosition.down(squares));
    }
    
    public double getBlackSideStrength() {
        double strength = 0.0;
        for (Piece piece : board.getBlackSidePieces())
            strength += getStrength(piece);
        
        return strength;
    }
    
    public double getWhiteSideStrength() {
        double strength = 0.0;
        for (Piece piece : board.getWhiteSidePieces())
            strength += getStrength(piece);
        
        return strength;
    }
    
    private double getStrength(Piece piece) {
        Piece.Type type = piece.getType();
        if (type != Piece.Type.PAWN)
            return piece.getType().getPoints();
        
        return getStrengthOfPawn(piece);
    }
    
    private double getStrengthOfPawn(Piece pawn) {
        Piece.Color color = pawn.getColor();
        char file = pawn.getPosition().getFile();
        boolean hasMultiplePawns = hasMultiplePawnsSatisfying(color, file);
        
        if (hasMultiplePawns)
            return 0.5;
        else
            return Piece.Type.PAWN.getPoints();
    }
    
    /**
     * Checks that if there are multiple pawns of the specified color in the specified file
     */
    private boolean hasMultiplePawnsSatisfying(Piece.Color color, char file) {
        List<Piece> pieces = board.getPiecesOfColor(color);
        
        int pawnCount = 0;
        for (Piece piece : pieces) {
            if (piece.getType() == Piece.Type.PAWN
                    && piece.getPosition().getFile() == file)
                pawnCount++;
        }
        
        if (pawnCount > 1)
            return true;
        else
            return false;
    }
}
