package pieces;

import java.util.HashMap;
import junit.framework.TestCase;
import chess.Position;

public class PieceTest extends TestCase {
    public void testCreate() {
        verifyCreation(Piece.createWhitePawn(), Piece.createBlackPawn(),
                       Piece.Type.PAWN, Piece.Type.PAWN.getCharacter());
        verifyCreation(Piece.createWhiteRook(), Piece.createBlackRook(),
                       Piece.Type.ROOK, Piece.Type.ROOK.getCharacter());
        verifyCreation(Piece.createWhiteKnight(), Piece.createBlackKnight(),
                       Piece.Type.KNIGHT, Piece.Type.KNIGHT.getCharacter());
        verifyCreation(Piece.createWhiteBishop(), Piece.createBlackBishop(),
                       Piece.Type.BISHOP, Piece.Type.BISHOP.getCharacter());
        verifyCreation(Piece.createWhiteQueen(), Piece.createBlackQueen(),
                       Piece.Type.QUEEN, Piece.Type.QUEEN.getCharacter());
        verifyCreation(Piece.createWhiteKing(), Piece.createBlackKing(),
                       Piece.Type.KING, Piece.Type.KING.getCharacter());
        
        Piece blank = Piece.BLANK;
        assertEquals(Piece.Type.NO_PIECE.getCharacter(), blank.getRepresentation());
        assertEquals(Piece.Type.NO_PIECE, blank.getType());
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
    
    public void testPieceTypeEnum() {
        assertEquals(0.0, Piece.Type.NO_PIECE.getPoints());
        assertEquals('.', Piece.Type.NO_PIECE.getCharacter());
        
        assertEquals(1.0, Piece.Type.PAWN.getPoints());
        assertEquals('p', Piece.Type.PAWN.getCharacter());
        
        assertEquals(5.0, Piece.Type.ROOK.getPoints());
        assertEquals('r', Piece.Type.ROOK.getCharacter());
        
        assertEquals(2.5, Piece.Type.KNIGHT.getPoints());
        assertEquals('n', Piece.Type.KNIGHT.getCharacter());
        
        assertEquals(3.0, Piece.Type.BISHOP.getPoints());
        assertEquals('b', Piece.Type.BISHOP.getCharacter());
        
        assertEquals(9.0, Piece.Type.QUEEN.getPoints());
        assertEquals('q', Piece.Type.QUEEN.getCharacter());
        
        assertEquals(0.0, Piece.Type.KING.getPoints());
        assertEquals('k', Piece.Type.KING.getCharacter());
    }
    
    public void testPiecePosition() {
        Piece piece = Piece.createBlackPawn();
        
        // One way to set position
        Position position = new Position('d', 7);
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
