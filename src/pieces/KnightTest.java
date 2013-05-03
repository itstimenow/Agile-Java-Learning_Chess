package pieces;

import junit.framework.TestCase;


public class KnightTest extends TestCase {

    public void testCreate() {
        Knight whiteKnight = new Knight(Piece.Color.WHITE);
        Knight blackKnight = new Knight(Piece.Color.BLACK);
        char expectedRepresentation = 'n';
        Class expectedClass = Knight.class;
        verifyCreation(whiteKnight, blackKnight, expectedRepresentation, expectedClass);
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
        assertTrue(piece.is(Knight.class));
        
        assertFalse(piece.is(King.class));
        assertFalse(piece.is(Queen.class));
        assertFalse(piece.is(Bishop.class));
        assertFalse(piece.is(Rook.class));
        assertFalse(piece.is(Pawn.class));
    }
}
