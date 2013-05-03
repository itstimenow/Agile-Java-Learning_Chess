package pieces;

import junit.framework.TestCase;


public class PawnTest extends TestCase {

    public void testCreate() {
        Pawn whitePawn = new Pawn(Piece.Color.WHITE);
        Pawn blackPawn = new Pawn(Piece.Color.BLACK);
        char expectedRepresentation = 'p';
        Class expectedClass = Pawn.class;
        verifyCreation(whitePawn, blackPawn, expectedRepresentation, expectedClass);
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
        assertTrue(piece.is(Pawn.class));
        
        assertFalse(piece.is(King.class));
        assertFalse(piece.is(Queen.class));
        assertFalse(piece.is(Bishop.class));
        assertFalse(piece.is(Knight.class));
        assertFalse(piece.is(Rook.class));
    }
}
