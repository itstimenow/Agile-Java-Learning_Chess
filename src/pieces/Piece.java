package pieces;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import chess.Position;

public class Piece {
    
    public enum Color { WHITE, BLACK }
    
    
    public static final Piece BLANK;
    static {
        BLANK = new Piece();
        BLANK.representation = '.';
    }
    
    private static int blackPieceCount = 0;
    private static int whitePieceCount = 0;
    
    
    protected Color color;
    protected char representation;
    
    private Position position;
    
    
    protected Piece() {}
    
    public static Piece createWhitePawn() {
        incrementWhitePieceCount();
        return new Pawn(Color.WHITE);
    }
    
    public static Piece createWhiteRook() {
        incrementWhitePieceCount();
        return new Rook(Color.WHITE);
    }
    
    public static Piece createWhiteKnight() {
        whitePieceCount++;
        return new Knight(Color.WHITE);
    }
    
    public static Piece createWhiteBishop() {
        incrementWhitePieceCount();
        return new Bishop(Color.WHITE);
    }
    
    public static Piece createWhiteQueen() {
        incrementWhitePieceCount();
        return new Queen(Color.WHITE);
    }
    
    public static Piece createWhiteKing() {
        incrementWhitePieceCount();
        return new King(Color.WHITE);
    }
    
    public static Piece createBlackPawn() {
        incrementBlackPieceCount();
        return new Pawn(Color.BLACK);
    }
    
    public static Piece createBlackRook() {
        incrementBlackPieceCount();
        return new Rook(Color.BLACK);
    }
    
    public static Piece createBlackKnight() {
        incrementBlackPieceCount();
        return new Knight(Color.BLACK);
    }
    
    public static Piece createBlackBishop() {
        incrementBlackPieceCount();
        return new Bishop(Color.BLACK);
    }
    
    public static Piece createBlackQueen() {
        incrementBlackPieceCount();
        return new Queen(Color.BLACK);
    }
    
    public static Piece createBlackKing() {
        incrementBlackPieceCount();
        return new King(Color.BLACK);
    }
    
    public static int getBlackPieceCount() {
        return blackPieceCount;
    }
    
    public static int getWhitePieceCount() {
        return whitePieceCount;
    }
    
    public static void resetCount() {
        blackPieceCount = 0;
        whitePieceCount = 0;
    }
    
    private static void incrementBlackPieceCount() {
        blackPieceCount++;
    }
    
    private static void incrementWhitePieceCount() {
        whitePieceCount++;
    }
    
    public Color getColor() {
        return color;
    }
    
    public char getRepresentation() {
        return representation;
    }
    
    public Position getPosition() {
        return position;
    }
    
    public void setPosition(char file, int rank) {
        position = new Position(file, rank);
    }
    
    public void setPosition(Position position) {
        this.position = position;
    }
    
    public boolean isBlack() {
        return color == Color.BLACK;
    }
    
    public boolean isWhite() {
        return color == Color.WHITE;
    }
    
    /**
     * Tests if the instance is of one specified type.
     */
    public boolean is(Class type) {
        return type == Piece.class;
    }
    
    public List<String> getPossibleMoves() {
        return new ArrayList<String>();
    }
}
