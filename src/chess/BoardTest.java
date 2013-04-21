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
        assertEquals(0, board.getNumberOfPieces());
        
        board.initialize();
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
        board.initialize();
        
        int whitePawnNumber = 
            board.getNumberOfPieces(Piece.Color.WHITE, Piece.Type.PAWN);
        assertEquals(8, whitePawnNumber);
        
        int blackBishopNumber = 
            board.getNumberOfPieces(Piece.Color.BLACK, Piece.Type.BISHOP);
        assertEquals(2, blackBishopNumber);
        
        // More tests ...
        
    }
    
    public void testRetrievePiece() {
        board.initialize();
        
        Piece piece = board.getPieceAt('a', 8);
        verifyPiece(piece, Piece.Color.BLACK, Piece.Type.ROOK);
        
        piece = board.getPieceAt('e', 1);
        verifyPiece(piece, Piece.Color.WHITE, Piece.Type.KING);
    }
    
    public void testPlacePiece() {
        
        board.placePiece('b', 6, Piece.createBlackKing());
        board.placePiece('b', 5, Piece.createBlackRook());
        board.placePiece('c', 4, Piece.createWhiteKing());
        
        Piece piece = board.getPieceAt('b', 6);
        verifyPiece(piece, Piece.Color.BLACK, Piece.Type.KING);
        
        piece = board.getPieceAt('b', 5);
        verifyPiece(piece, Piece.Color.BLACK, Piece.Type.ROOK);
        
        piece = board.getPieceAt('c', 4);
        verifyPiece(piece, Piece.Color.WHITE, Piece.Type.KING);
    }
    
    private void verifyPiece(Piece piece, Piece.Color color, Piece.Type type) {
        assertEquals(color, piece.getColor());
        assertEquals(type, piece.getType());
    }
}
