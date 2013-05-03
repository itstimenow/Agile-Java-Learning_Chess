package pieces;

import junit.framework.TestCase;


public class RookTest extends TestCase {

    public void testCreate() {
        verifyCreation(new Rook(Piece.Color.WHITE), new Rook(Piece.Color.BLACK),
                       Piece.Type.ROOK, Piece.Type.ROOK.getCharacter());
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
