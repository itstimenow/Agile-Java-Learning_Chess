package pieces;

import java.util.HashMap;
import junit.framework.TestCase;

public class PieceTest extends TestCase {
    public void testCreate() {
        // white pieces
        String white = Piece.WHITE_COLOR;
        Piece whiteKing = Piece.create(white, Piece.KING);
        checkPiece(whiteKing, white, Piece.KING, "k");
        
        Piece whiteQueen = Piece.create(white, Piece.QUEEN);
        checkPiece(whiteQueen, white, Piece.QUEEN, "q");
        
        Piece whiteRook = Piece.create(white, Piece.ROOK);
        checkPiece(whiteRook, white, Piece.ROOK, "r");
        
        Piece whiteBishop = Piece.create(white, Piece.BISHOP);
        checkPiece(whiteBishop, white, Piece.BISHOP, "b");
        
        Piece whiteKnight = Piece.create(white, Piece.KNIGHT);
        checkPiece(whiteKnight, white, Piece.KNIGHT, "n");
        
        Piece whitePawn = Piece.create(white, Piece.PAWN);
        checkPiece(whitePawn, white, Piece.PAWN, "p");
        
        // black pieces
        String black = Piece.BLACK_COLOR;
        Piece blackKing = Piece.create(black, Piece.KING);
        checkPiece(blackKing, black, Piece.KING, "K");
        
        Piece blackQueen = Piece.create(black, Piece.QUEEN);
        checkPiece(blackQueen, black, Piece.QUEEN, "Q");
        
        Piece blackRook = Piece.create(black, Piece.ROOK);
        checkPiece(blackRook, black, Piece.ROOK, "R");
        
        Piece blackBishop = Piece.create(black, Piece.BISHOP);
        checkPiece(blackBishop, black, Piece.BISHOP, "B");
        
        Piece blackKnight = Piece.create(black, Piece.KNIGHT);
        checkPiece(blackKnight, black, Piece.KNIGHT, "N");
        
        Piece blackPawn = Piece.create(black, Piece.PAWN);
        checkPiece(blackPawn, black, Piece.PAWN, "P");
    }
    
    private void checkPiece(Piece piece, String expectedColor, 
                            String expectedName, String expectedSymbol) {
        assertEquals(expectedColor, piece.getColor());
        assertEquals(expectedColor == Piece.WHITE_COLOR, piece.isWhite());
        assertEquals(expectedColor == Piece.BLACK_COLOR, piece.isBlack());
        assertEquals(expectedName, piece.getName());
        assertEquals(expectedSymbol, piece.getSymbol());
    }    
    
    public void testCount() {
        Piece.resetCount();
        assertEquals(0, Piece.getBlackPieceCount());
        assertEquals(0, Piece.getWhitePieceCount());
        
        Piece.create(Piece.BLACK_COLOR, Piece.KING);
        assertEquals(1, Piece.getBlackPieceCount());
        assertEquals(0, Piece.getWhitePieceCount());
        
        Piece.create(Piece.WHITE_COLOR, Piece.QUEEN);
        assertEquals(1, Piece.getBlackPieceCount());
        assertEquals(1, Piece.getWhitePieceCount());
        
        Piece.create(Piece.WHITE_COLOR, Piece.PAWN);
        Piece.create(Piece.WHITE_COLOR, Piece.PAWN);
        Piece.create(Piece.BLACK_COLOR, Piece.PAWN);
        assertEquals(2, Piece.getBlackPieceCount());
        assertEquals(3, Piece.getWhitePieceCount());
    }
}
