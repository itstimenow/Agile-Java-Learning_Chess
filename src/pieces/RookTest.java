package pieces;

import junit.framework.TestCase;


public class RookTest extends TestCase {

    public void testCreate() {
        Rook whiteRook = new Rook(Piece.Color.WHITE);
        Rook blackRook = new Rook(Piece.Color.BLACK);
        char expectedRepresentation = 'r';
        Class expectedClass = Rook.class;
        verifyCreation(whiteRook, blackRook, expectedRepresentation, expectedClass);
    }
    
    private void verifyCreation(Piece whitePiece, Piece blackPiece, 
                                char expectedRepresentation, Class expectedClass) {
        assertTrue(whitePiece.isWhite());
        assertEquals(expectedRepresentation, whitePiece.getRepresentation());
        verifyType(whitePiece);
        
        assertTrue(blackPiece.isBlack());
        assertEquals(Character.toUpperCase(expectedRepresentation), blackPiece.getRepresentation());
        verifyType(blackPiece);
    }
    
    private void verifyType(Piece piece) {
        assertTrue(piece.is(Rook.class));
        
        assertFalse(piece.is(King.class));
        assertFalse(piece.is(Queen.class));
        assertFalse(piece.is(Bishop.class));
        assertFalse(piece.is(Knight.class));
        assertFalse(piece.is(Pawn.class));
    }
}
