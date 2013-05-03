package pieces;

import junit.framework.TestCase;


public class KnightTest extends TestCase {

    public void testCreate() {
        verifyCreation(new Knight(Piece.Color.WHITE), new Knight(Piece.Color.BLACK),
                       Piece.Type.KNIGHT, Piece.Type.KNIGHT.getCharacter());
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
