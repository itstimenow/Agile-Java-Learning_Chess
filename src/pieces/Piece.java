package pieces;

import java.util.HashMap;

public class Piece {
    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";
    public static final String KING =   "king";
    public static final String QUEEN =  "queen";
    public static final String ROOK =   "rook";
    public static final String BISHOP = "bishop";
    public static final String KNIGHT = "knight";
    public static final String PAWN =   "pawn";   
         
    private static HashMap<String, String> pieceSymbolMap;
    static {
        pieceSymbolMap = new HashMap<String, String>();
        pieceSymbolMap.put(WHITE_COLOR + KING,      "k");
        pieceSymbolMap.put(WHITE_COLOR + QUEEN,     "q");
        pieceSymbolMap.put(WHITE_COLOR + ROOK,      "r");
        pieceSymbolMap.put(WHITE_COLOR + BISHOP,    "b");
        pieceSymbolMap.put(WHITE_COLOR + KNIGHT,    "n");
        pieceSymbolMap.put(WHITE_COLOR + PAWN,      "p");
        pieceSymbolMap.put(BLACK_COLOR + KING,      "K");
        pieceSymbolMap.put(BLACK_COLOR + QUEEN,     "Q");
        pieceSymbolMap.put(BLACK_COLOR + ROOK,      "R");
        pieceSymbolMap.put(BLACK_COLOR + BISHOP,    "B");
        pieceSymbolMap.put(BLACK_COLOR + KNIGHT,    "N");
        pieceSymbolMap.put(BLACK_COLOR + PAWN,      "P");
    }
    
    private static int blackPieceCount = 0;
    private static int whitePieceCount = 0;
        
    private String color;
    private String name;
    private String symbol;
    
    
    private Piece() {}
    
    public static Piece create(String color, String name) {
        Piece piece = new Piece();
        piece.color = color;
        piece.name = name;
        piece.symbol = chooseSymbol(color, name);
        
        if (color == BLACK_COLOR)
            ++blackPieceCount;
        
        if (color == WHITE_COLOR)
            ++whitePieceCount;
        
        return piece;
    }
    
    private static String chooseSymbol(String color, String name) {
        return pieceSymbolMap.get(color + name);
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
    
    public String getColor() {
        return color;
    }
    
    public String getName() {
        return name;
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public boolean isBlack() {
        return color == Piece.BLACK_COLOR;
    }
    
    public boolean isWhite() {
        return color == Piece.WHITE_COLOR;
    }
}
