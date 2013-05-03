package pieces;

import junit.framework.TestCase;


public class BishopTest extends TestCase {

    public void testCreate() {
        verifyCreation(new Bishop(Piece.Color.WHITE), new Bishop(Piece.Color.BLACK),
                       Piece.Type.BISHOP, Piece.Type.BISHOP.getCharacter());
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
