package pieces;

import junit.framework.TestCase;


public class KingTest extends TestCase {

    public void testCreate() {
        verifyCreation(new King(Piece.Color.WHITE), new King(Piece.Color.BLACK),
                       Piece.Type.KING, Piece.Type.KING.getCharacter());
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
