package chess;

import junit.framework.TestCase;
import util.StringUtil;
import pieces.Piece;

public class BoardTest extends TestCase {
    private Board board;
    
    public void setUp() {
        Piece.resetCount();
        board = new Board();
    }
    
    public void testCreate() {
        assertEquals(32, board.getNumberOfPieces());
        assertEquals(16, Piece.getBlackPieceCount());
        assertEquals(16, Piece.getWhitePieceCount());
        
        String newline = StringUtil.NEWLINE;
        String expectedBoardPrint = "RNBQKBNR" + newline
                                      + "PPPPPPPP" + newline
                                      + "........" + newline
                                      + "........" + newline
                                      + "........" + newline
                                      + "........" + newline
                                      + "pppppppp" + newline
                                      + "rnbqkbnr" + newline;
        assertEquals(expectedBoardPrint, board.getPrint());
    }
    
    public void testGetNumberOfPieces() {
        int whitePawnNumber = 
            board.getNumberOfPieces(Piece.Color.WHITE, Piece.Type.PAWN);
        assertEquals(8, whitePawnNumber);
        
        int blackBishopNumber = 
            board.getNumberOfPieces(Piece.Color.BLACK, Piece.Type.BISHOP);
        assertEquals(2, blackBishopNumber);
        
        // More tests ...
        
    }
    
    public void testRetrievePiece() {
        Piece piece = board.getPieceAt('a', 8);
        assertTrue(piece.isBlack());
        assertEquals(Piece.Type.ROOK, piece.getType());
        
        piece = board.getPieceAt('e', 1);
        assertTrue(piece.isWhite());
        assertEquals(Piece.Type.KING, piece.getType());
    }
}
