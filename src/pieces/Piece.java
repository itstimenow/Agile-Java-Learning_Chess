package pieces;

import java.util.HashMap;

public class Piece implements Comparable<Piece> {
    public enum Type {
        NO_PIECE, PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING
    }
    public enum Color { WHITE, BLACK }
    
    
    public static final char PAWN_REPRESENTATION = 'p';
    public static final char ROOK_REPRESENTATION = 'r';
    public static final char KNIGHT_REPRESENTATION = 'n';
    public static final char BISHOP_REPRESENTATION = 'b';
    public static final char QUEEN_REPRESENTATION = 'q';
    public static final char KING_REPRESENTATION = 'k';
    public static final char NO_PIECE_REPRESENTATION = '.';
        
    private static int blackPieceCount = 0;
    private static int whitePieceCount = 0;
    
    private static Piece noPiece;
        
    private Color color;
    private Type type;
    private char representation;
    private double strength;
    
    
    private Piece() {}
    
    public static Piece createWhitePawn() {
        return create(Color.WHITE, Type.PAWN, PAWN_REPRESENTATION);
    }
    
    public static Piece createWhiteRook() {
        return create(Color.WHITE, Type.ROOK, ROOK_REPRESENTATION);
    }
    
    public static Piece createWhiteKnight() {
        return create(Color.WHITE, Type.KNIGHT, KNIGHT_REPRESENTATION);
    }
    
    public static Piece createWhiteBishop() {
        return create(Color.WHITE, Type.BISHOP, BISHOP_REPRESENTATION);
    }
    
    public static Piece createWhiteQueen() {
        return create(Color.WHITE, Type.QUEEN, QUEEN_REPRESENTATION);
    }
    
    public static Piece createWhiteKing() {
        return create(Color.WHITE, Type.KING, KING_REPRESENTATION);
    }
    
    public static Piece createBlackPawn() {
        return create(Color.BLACK, Type.PAWN, 
                      Character.toUpperCase(PAWN_REPRESENTATION));
    }
    
    public static Piece createBlackRook() {
        return create(Color.BLACK, Type.ROOK, 
                      Character.toUpperCase(ROOK_REPRESENTATION));
    }
    
    public static Piece createBlackKnight() {
        return create(Color.BLACK, Type.KNIGHT, 
                      Character.toUpperCase(KNIGHT_REPRESENTATION));
    }
    
    public static Piece createBlackBishop() {
        return create(Color.BLACK, Type.BISHOP, 
                      Character.toUpperCase(BISHOP_REPRESENTATION));
    }
    
    public static Piece createBlackQueen() {
        return create(Color.BLACK, Type.QUEEN, 
                      Character.toUpperCase(QUEEN_REPRESENTATION));
    }
    
    public static Piece createBlackKing() {
        return create(Color.BLACK, Type.KING, 
                      Character.toUpperCase(KING_REPRESENTATION));
    }
    
    private static Piece create(Color color, Type type, char representation) {
        Piece piece = new Piece();
        piece.color = color;
        piece.type = type;
        piece.representation = representation;
                
        if (color == Color.WHITE) ++whitePieceCount;
        if (color == Color.BLACK) ++blackPieceCount;
        
        return piece;
    }
    
    public static Piece noPiece() {
        if (noPiece != null)
            return noPiece;
        
        noPiece = new Piece();
        noPiece.type = Type.NO_PIECE;
        noPiece.representation = NO_PIECE_REPRESENTATION;
        return noPiece;
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
}
