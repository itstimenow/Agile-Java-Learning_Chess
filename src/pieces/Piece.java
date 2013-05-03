package pieces;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import chess.Position;

public class Piece {
    
    public enum Type {
        NO_PIECE('.', 0.0),
        PAWN('p', 1.0),
        ROOK('r', 5.0),
        KNIGHT('n', 2.5),
        BISHOP('b', 3.0),
        QUEEN('q', 9.0),
        KING('k', 0.0);
        
        private char character;
        private double points;
        
        private Type(char character, double points) {
            this.character = character;
            this.points = points;
        }
        
        public double getPoints() {
            return points;
        }
        
        public char getCharacter() {
            return character;
        }
    }
    
    public enum Color { WHITE, BLACK }
    
    
    public static final Piece BLANK;
    static {
        BLANK = new Piece();
        BLANK.type = Type.NO_PIECE;
        BLANK.representation = Type.NO_PIECE.getCharacter();
    }
    
    private static int blackPieceCount = 0;
    private static int whitePieceCount = 0;
    
    
    protected Color color;
    private Type type;
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
    
    private static Piece create(Color color, Type type) {
        Piece piece = new Piece();
        piece.color = color;
        piece.type = type;
                
        if (color == Color.WHITE) {
            piece.representation = type.getCharacter();
            ++whitePieceCount;
        }
        if (color == Color.BLACK) {
            piece.representation = Character.toUpperCase(type.getCharacter());
            ++blackPieceCount;
        }
        
        return piece;
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
        
    public Type getType() {
        return type;
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
    
    public List<String> getPossibleMoves() {
        return new ArrayList<String>();
    }
}
