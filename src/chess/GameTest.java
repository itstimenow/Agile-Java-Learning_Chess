package chess;

import java.util.*;
import junit.framework.TestCase;
import util.StringUtil;
import pieces.*;


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
    
    public void testMoveQueen() {
        Piece queen = Piece.createWhiteQueen();
        board.put(queen, 'c', 2);
        
        game.moveLeft(queen, 1);
        assertEquals(Piece.BLANK, board.getPieceAt('c', 2));
        assertEquals(queen, board.getPieceAt('b', 2));
        
        game.moveUp(queen, 5);
        assertEquals(Piece.BLANK, board.getPieceAt('b', 2));
        assertEquals(queen, board.getPieceAt('b', 7));
        
        game.moveRight(queen, 6);
        assertEquals(Piece.BLANK, board.getPieceAt('b', 7));
        assertEquals(queen, board.getPieceAt('h', 7));
        
        game.moveDown(queen, 3);
        assertEquals(Piece.BLANK, board.getPieceAt('h', 7));
        assertEquals(queen, board.getPieceAt('h', 4));
    }
    
    /**
     * Some basic tests on strength
     */
    public void testStrength01() {
        // Verify strength of a fresh game
        game = new Game();
        assertEquals(0.0, game.getBlackSideStrength());
        assertEquals(0.0, game.getWhiteSideStrength());
        
        // Verify strength of an initialized game
        game.initialize();
        assertEquals(38.0, game.getBlackSideStrength());
        assertEquals(38.0, game.getWhiteSideStrength());
        
        // Verify strength of each type of piece separately
        verifySinglePieceStrength(Pawn.class,   1.0);
        verifySinglePieceStrength(Rook.class,   5.0);
        verifySinglePieceStrength(Knight.class, 2.5);
        verifySinglePieceStrength(Bishop.class, 3.0);
        verifySinglePieceStrength(Queen.class,  9.0);
        verifySinglePieceStrength(King.class,   0.0);
    }
    
    private void verifySinglePieceStrength(Class type, double expectedStrength) {
        Position position = new Position('a', 1);
        List<Piece> piecePair = createPiecesOfType(type);
        
        game = new Game();
        board = game.getBoard();
        Piece blackPiece = piecePair.get(0);
        board.put(blackPiece, position);
        assertEquals(expectedStrength, game.getBlackSideStrength());
        
        game = new Game();
        board = game.getBoard();
        Piece whitePiece = piecePair.get(1);
        board.put(whitePiece, position);
        assertEquals(expectedStrength, game.getWhiteSideStrength());
    }
    
    /**
     * Creates a pair of pieces of the specified type, the first one is black, the second white
     */
    private List<Piece> createPiecesOfType(Class type) {
        Piece blackPiece, whitePiece;
        
        if (type == King.class) {
            blackPiece = Piece.createBlackKing();
            whitePiece = Piece.createWhiteKing();
        } else if (type == Queen.class) {
            blackPiece = Piece.createBlackQueen();
            whitePiece = Piece.createWhiteQueen();
        } else if (type == Bishop.class) {
            blackPiece = Piece.createBlackBishop();
            whitePiece = Piece.createWhiteBishop();
        } else if (type == Knight.class) {
            blackPiece = Piece.createBlackKnight();
            whitePiece = Piece.createWhiteKnight();
        } else if (type == Rook.class) {
            blackPiece = Piece.createBlackRook();
            whitePiece = Piece.createWhiteRook();
        } else if (type == Pawn.class) {
            blackPiece = Piece.createBlackPawn();
            whitePiece = Piece.createWhitePawn();
        } else {
            fail("Unsupported piece type!");
            return null;
        }
        
        ArrayList<Piece> pieces = new ArrayList<Piece>();
        pieces.add(blackPiece);
        pieces.add(whitePiece);
        return pieces;
    }
    
    /**
     * More complex tests on strength
     */
    public void testStrength02() {
        game = new Game();
        board = game.getBoard();
        
        // Black side
        board.put(Piece.createBlackPawn(),      'b', 6);
        board.put(Piece.createBlackQueen(),     'e', 6);
        board.put(Piece.createBlackPawn(),      'g', 6);
        board.put(Piece.createBlackPawn(),      'a', 7);
        board.put(Piece.createBlackPawn(),      'c', 7);
        board.put(Piece.createBlackBishop(),    'd', 7);
        board.put(Piece.createBlackKing(),      'b', 8);
        board.put(Piece.createBlackRook(),      'c', 8);
        
        // White side
        board.put(Piece.createWhiteKnight(),    'f', 4);
        board.put(Piece.createWhiteQueen(),     'g', 4);
        board.put(Piece.createWhitePawn(),      'f', 3);
        board.put(Piece.createWhitePawn(),      'h', 3);
        board.put(Piece.createWhitePawn(),      'f', 2);
        board.put(Piece.createWhitePawn(),      'g', 2);
        board.put(Piece.createWhiteRook(),      'e', 1);
        board.put(Piece.createWhiteKing(),      'f', 1);
        
        // Verify strength
        assertEquals(21.0, game.getBlackSideStrength());
        assertEquals(19.5, game.getWhiteSideStrength());
    }
}
