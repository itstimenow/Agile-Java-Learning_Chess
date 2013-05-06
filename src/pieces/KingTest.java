package pieces;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import chess.Board;
import chess.Position;


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
    
    public void testPossibleMoves() {
        Piece king = Piece.createBlackKing();
        king.setPosition('d', 3);
        
        List<Position> expectedPossibleMoves = new ArrayList<Position>();
        expectedPossibleMoves.add(Position.at('c', 4));
        expectedPossibleMoves.add(Position.at('d', 4));
        expectedPossibleMoves.add(Position.at('e', 4));
        expectedPossibleMoves.add(Position.at('c', 3));
        expectedPossibleMoves.add(Position.at('e', 3));
        expectedPossibleMoves.add(Position.at('c', 2));
        expectedPossibleMoves.add(Position.at('d', 2));
        expectedPossibleMoves.add(Position.at('e', 2));
        
        assertEquals(expectedPossibleMoves, king.getPossibleMoves());
    }
}
