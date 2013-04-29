package pieces;

import java.util.HashMap;
import chess.Position;

public class Piece implements Comparable<Piece> {
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
    
    
    private Color color;
    private Type type;
    private char representation;
    private double strength;
    
    private Position position;
    
    
    private Piece() {}
    
    public static Piece createWhitePawn() {
        return create(Color.WHITE, Type.PAWN);
    }
    
    public static Piece createWhiteRook() {
        return create(Color.WHITE, Type.ROOK);
    }
    
    public static Piece createWhiteKnight() {
        return create(Color.WHITE, Type.KNIGHT);
    }
    
    public static Piece createWhiteBishop() {
        return create(Color.WHITE, Type.BISHOP);
    }
    
    public static Piece createWhiteQueen() {
        return create(Color.WHITE, Type.QUEEN);
    }
    
    public static Piece createWhiteKing() {
        return create(Color.WHITE, Type.KING);
    }
    
    public static Piece createBlackPawn() {
        return create(Color.BLACK, Type.PAWN);
    }
    
    public static Piece createBlackRook() {
        return create(Color.BLACK, Type.ROOK);
    }
    
    public static Piece createBlackKnight() {
        return create(Color.BLACK, Type.KNIGHT);
    }
    
    public static Piece createBlackBishop() {
        return create(Color.BLACK, Type.BISHOP);
    }
    
    public static Piece createBlackQueen() {
        return create(Color.BLACK, Type.QUEEN);
    }
    
    public static Piece createBlackKing() {
        return create(Color.BLACK, Type.KING);
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
    
    public Color getColor() {
        return color;
    }
        
    public Type getType() {
        return type;
    }
    
    public char getRepresentation() {
        return representation;
    }
    
    public double getStrength() {
        return strength;
    }
    
    public void setStrength(double strength) {
        this.strength = strength;
    }
    
    public Position getPosition() {
        return position;
    }
    
    public int getPositionRank() {
        return position.getRank();
    }
    
    public char getPositionFile() {
        return position.getFile();
    }
    
    public int getPositionRow() {
        return position.getRow();
    }
    
    public int getPositionColumn() {
        return position.getColumn();
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
    
    public int compareTo(Piece that) {
        double result = this.getStrength() * 10 - that.getStrength() * 10;
        return (int)result;
    }
    
    public void moveLeft() {
        position = position.left();
    }
    
    public void moveRight() {
        position = position.right();
    }
    
    public void moveUp() {
        position = position.up();
    }
    
    public void moveDown() {
        position = position.down();
    }
}
