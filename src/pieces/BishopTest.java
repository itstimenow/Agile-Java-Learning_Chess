package pieces;

import junit.framework.TestCase;


public class BishopTest extends TestCase {

    public void testCreate() {
        Bishop whiteBishop = new Bishop(Piece.Color.WHITE);
        Bishop blackBishop = new Bishop(Piece.Color.BLACK);
        char expectedRepresentation = 'b';
        Class expectedClass = Bishop.class;
        verifyCreation(whiteBishop, blackBishop, expectedRepresentation, expectedClass);
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
        assertTrue(piece.is(Bishop.class));
        
        assertFalse(piece.is(King.class));
        assertFalse(piece.is(Queen.class));
        assertFalse(piece.is(Knight.class));
        assertFalse(piece.is(Rook.class));
        assertFalse(piece.is(Pawn.class));
    }
}
