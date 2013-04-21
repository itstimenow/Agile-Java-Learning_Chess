package chess;

import junit.framework.TestCase;
import util.StringUtil;
import pieces.Piece;

public class BoardTest extends TestCase {
    private Board board;
    
    public void setUp() {
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
}
