package pieces;

import junit.framework.TestCase;


public class KingTest extends TestCase {

    public void testCreate() {
        King whiteKing = new King(Piece.Color.WHITE);
        King blackKing = new King(Piece.Color.BLACK);
        char expectedRepresentation = 'k';
        Class expectedClass = King.class;
        verifyCreation(whiteKing, blackKing, expectedRepresentation);
    }
    
    private void verifyCreation(Piece whitePiece, Piece blackPiece, char expectedRepresentation) {
        assertTrue(whitePiece.isWhite());
        assertEquals(expectedRepresentation, whitePiece.getRepresentation());
        verifyType(whitePiece);
        
        assertTrue(blackPiece.isBlack());
        assertEquals(Character.toUpperCase(expectedRepresentation), blackPiece.getRepresentation());
        verifyType(blackPiece);
    }
    
    private void verifyType(Piece piece) {
        assertTrue(piece.is(King.class));
        
        assertFalse(piece.is(Queen.class));
        assertFalse(piece.is(Bishop.class));
        assertFalse(piece.is(Knight.class));
        assertFalse(piece.is(Rook.class));
        assertFalse(piece.is(Pawn.class));
    }
}
