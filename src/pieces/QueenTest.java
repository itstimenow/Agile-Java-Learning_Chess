package pieces;

import junit.framework.TestCase;


public class QueenTest extends TestCase {
    
    public void testCreate() {
        verifyCreation(new Queen(Piece.Color.WHITE), new Queen(Piece.Color.BLACK),
                       Piece.Type.QUEEN, Piece.Type.QUEEN.getCharacter());
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
