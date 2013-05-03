package pieces;

import junit.framework.TestCase;


public class QueenTest extends TestCase {
    
    public void testCreate() {
        Queen whiteQueen = new Queen(Piece.Color.WHITE);
        Queen blackQueen = new Queen(Piece.Color.BLACK);
        char expectedRepresentation = 'q';
        Class expectedClass = Queen.class;
        verifyCreation(whiteQueen, blackQueen, expectedRepresentation, expectedClass);
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
        assertTrue(piece.is(Queen.class));
        
        assertFalse(piece.is(King.class));
        assertFalse(piece.is(Bishop.class));
        assertFalse(piece.is(Knight.class));
        assertFalse(piece.is(Rook.class));
        assertFalse(piece.is(Pawn.class));
    }
}
