package pieces;

import junit.framework.TestCase;


public class PawnTest extends TestCase {

    public void testCreate() {
        verifyCreation(new Pawn(Piece.Color.WHITE), new Pawn(Piece.Color.BLACK),
                       Piece.Type.PAWN, Piece.Type.PAWN.getCharacter());
    }
    
    private void verifyCreation(Piece whitePiece, Piece blackPiece,
                                Piece.Type expectedType, char expectedRepresentation) {
        assertTrue(whitePiece.isWhite());
        assertEquals(expectedType, whitePiece.getType());
        assertEquals(expectedRepresentation, whitePiece.getRepresentation());
        
        assertTrue(blackPiece.isBlack());
        assertEquals(expectedType, blackPiece.getType());
        assertEquals(Character.toUpperCase(expectedRepresentation), blackPiece.getRepresentation());
    }
}
