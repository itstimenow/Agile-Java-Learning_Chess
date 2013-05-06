package pieces;

import java.util.HashMap;
import junit.framework.TestCase;
import chess.Position;

public class PieceTest extends TestCase {
    
    public void testCreate() {
        verifyCreation(Piece.createWhitePawn(), Piece.createBlackPawn(), Pawn.class, 'p');
        verifyCreation(Piece.createWhiteRook(), Piece.createBlackRook(), Rook.class, 'r');
        verifyCreation(Piece.createWhiteKnight(), Piece.createBlackKnight(), Knight.class, 'n');
        verifyCreation(Piece.createWhiteBishop(), Piece.createBlackBishop(), Bishop.class, 'b');
        verifyCreation(Piece.createWhiteQueen(), Piece.createBlackQueen(), Queen.class, 'q');
        verifyCreation(Piece.createWhiteKing(), Piece.createBlackKing(), King.class, 'k');
        
        assertEquals('.', Piece.BLANK.getRepresentation());
    }
    
    private void verifyCreation(Piece whitePiece, Piece blackPiece, 
                                Class expectedClass, char expectedRepresentation) {
        assertTrue(whitePiece.isWhite());
        assertEquals(expectedRepresentation, whitePiece.getRepresentation());
        assertEquals(expectedClass, whitePiece.getClass());
        
        assertTrue(blackPiece.isBlack());
        assertEquals(Character.toUpperCase(expectedRepresentation), blackPiece.getRepresentation());
        assertEquals(expectedClass, blackPiece.getClass());
    }
    
    public void testCount() {
        Piece.resetCount();
        assertEquals(0, Piece.getBlackPieceCount());
        assertEquals(0, Piece.getWhitePieceCount());
        
        Piece.createBlackKing();
        assertEquals(1, Piece.getBlackPieceCount());
        assertEquals(0, Piece.getWhitePieceCount());
        
        Piece.createWhiteQueen();
        assertEquals(1, Piece.getBlackPieceCount());
        assertEquals(1, Piece.getWhitePieceCount());
        
        Piece.createWhitePawn();
        Piece.createWhitePawn();
        Piece.createBlackPawn();
        assertEquals(2, Piece.getBlackPieceCount());
        assertEquals(3, Piece.getWhitePieceCount());
    }
    
    public void testPiecePosition() {
        Piece piece = Piece.createBlackPawn();
        
        // One way to set position
        Position position = Position.at('d', 7);
        piece.setPosition(position);
        verifyPosition(piece, 'd', 7);
        
        // Another way to set position
        piece.setPosition('a', 5);
        verifyPosition(piece, 'a', 5);
    }
    
    private void verifyPosition(Piece piece, char expectedFile, int expectedRank) {
        Position position = piece.getPosition();
        assertEquals(expectedFile, position.getFile());
        assertEquals(expectedRank, position.getRank());
    }
}
