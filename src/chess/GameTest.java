package chess;

import java.util.*;
import junit.framework.TestCase;
import util.StringUtil;
import pieces.Piece;


public class GameTest extends TestCase {
    
    private Game game;
    private Board board;
        
    @Override
    public void setUp() {
        game = new Game();
        board = game.getBoard();
    }
    
    public void testCreate() {
        // Verify that game holds empty board at the beginning
        Game game = new Game();
        Board board = game.getBoard();
        assertEquals(0, board.getNumberOfPieces());
        
        // Initialize and then verify
        game.initialize();
        assertEquals(32, board.getNumberOfPieces());
        
        String newline = StringUtil.NEWLINE;
        String expectedBoardPrint = "RNBQKBNR" + newline
                                     + "PPPPPPPP" + newline
                                     + "........" + newline
                                     + "........" + newline
                                     + "........" + newline
                                     + "........" + newline
                                     + "pppppppp" + newline
                                     + "rnbqkbnr" + newline;
        assertEquals(expectedBoardPrint, game.getBoardPrint());
    }
    
    public void testMoveKing() {
        Piece king = Piece.createWhiteKing();
        board.put(king, 'c', 2);
        
        game.moveLeft(king);
        assertEquals(Piece.BLANK, board.getPieceAt('c', 2));
        assertEquals(king, board.getPieceAt('b', 2));
        
        game.moveUp(king);
        assertEquals(Piece.BLANK, board.getPieceAt('b', 2));
        assertEquals(king, board.getPieceAt('b', 3));
        
        game.moveRight(king);
        assertEquals(Piece.BLANK, board.getPieceAt('b', 3));
        assertEquals(king, board.getPieceAt('c', 3));
        
        game.moveDown(king);
        assertEquals(Piece.BLANK, board.getPieceAt('c', 3));
        assertEquals(king, board.getPieceAt('c', 2));
    }
}
