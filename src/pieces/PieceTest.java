package pieces;

import java.util.HashMap;
import junit.framework.TestCase;

public class PieceTest extends TestCase {
    public void testCreate() {
        verifyCreation(Piece.createWhitePawn(), Piece.createBlackPawn(),
                       Piece.Type.PAWN, Piece.PAWN_REPRESENTATION);
        verifyCreation(Piece.createWhiteRook(), Piece.createBlackRook(),
                       Piece.Type.ROOK, Piece.ROOK_REPRESENTATION);
        verifyCreation(Piece.createWhiteKnight(), Piece.createBlackKnight(),
                       Piece.Type.KNIGHT, Piece.KNIGHT_REPRESENTATION);
        verifyCreation(Piece.createWhiteBishop(), Piece.createBlackBishop(),
                       Piece.Type.BISHOP, Piece.BISHOP_REPRESENTATION);
        verifyCreation(Piece.createWhiteQueen(), Piece.createBlackQueen(),
                       Piece.Type.QUEEN, Piece.QUEEN_REPRESENTATION);
        verifyCreation(Piece.createWhiteKing(), Piece.createBlackKing(),
                       Piece.Type.KING, Piece.KING_REPRESENTATION);
        
        Piece blank = Piece.noPiece();
        assertEquals('.', blank.getRepresentation());
        assertEquals(Piece.Type.NO_PIECE, blank.getType());
    }
    
    private void verifyCreation(Piece whitePiece, Piece blackPiece,
                                Piece.Type type, char representation) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());
        assertEquals(representation, whitePiece.getRepresentation());
        
        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
        assertEquals(Character.toUpperCase(representation),
                     blackPiece.getRepresentation());
    }
    
    public void testCount() {
        Piece.resetCount();
        assertEquals(0, Piece.getBlackPieceCount());
        assertEquals(0, Piece.getWhitePieceCount());
        
        Piece.createBlackKing();
        assertEquals(1, Piece.getBlackPieceCount());
        assertEquals(0, Piece.getWhitePieceCount());
        
        Piece.createWhiteQueen();
        assertEquals(1, Piece.getBlackPieceCount());
        assertEquals(1, Piece.getWhitePieceCount());
        
        Piece.createWhitePawn();
        Piece.createWhitePawn();
        Piece.createBlackPawn();
        assertEquals(2, Piece.getBlackPieceCount());
        assertEquals(3, Piece.getWhitePieceCount());
    }
}
