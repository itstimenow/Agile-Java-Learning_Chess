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
    
    public void testStrength() {
        // Verify strength of a fresh game
        game = new Game();
        assertEquals(0.0, game.getBlackSideStrength());
        assertEquals(0.0, game.getWhiteSideStrength());
        
        // Verify strength of an initialized game
        game.initialize();
        assertEquals(38.0, game.getBlackSideStrength());
        assertEquals(38.0, game.getWhiteSideStrength());
        
        // Verify strength of each type of piece separately
        verifySinglePieceStrength(Piece.Type.PAWN,   1.0);
        verifySinglePieceStrength(Piece.Type.ROOK,   5.0);
        verifySinglePieceStrength(Piece.Type.KNIGHT, 2.5);
        verifySinglePieceStrength(Piece.Type.BISHOP, 3.0);
        verifySinglePieceStrength(Piece.Type.QUEEN,  9.0);
        verifySinglePieceStrength(Piece.Type.KING,   0.0);
        
        // ..............
        game = new Game();
        board = game.getBoard();
        
        // Black side
        board.put(Piece.createBlackPawn(),      'b', 6);
        board.put(Piece.createBlackQueen(),     'e', 6);
        board.put(Piece.createBlackPawn(),      'a', 7);
        board.put(Piece.createBlackPawn(),      'c', 7);
        board.put(Piece.createBlackBishop(),    'd', 7);
        board.put(Piece.createBlackKing(),      'b', 8);
        board.put(Piece.createBlackRook(),      'c', 8);
        assertEquals(20.0, game.getBlackSideStrength());
        
        // White side
        board.put(Piece.createWhiteKnight(),    'f', 4);
        board.put(Piece.createWhiteQueen(),     'g', 4);
        board.put(Piece.createWhitePawn(),      'f', 3);
        board.put(Piece.createWhitePawn(),      'h', 3);
        board.put(Piece.createWhitePawn(),      'f', 2);
        board.put(Piece.createWhitePawn(),      'g', 2);
        board.put(Piece.createWhiteRook(),      'e', 1);
        board.put(Piece.createWhiteKing(),      'f', 1);
        assertEquals(19.5, game.getWhiteSideStrength());
        
        // Verify that strength of black side keep unchanged
        assertEquals(20.0, board.getBlackSideStrength());
    }
    
    private void verifySinglePieceStrength(Piece.Type type, double expectedStrength) {
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
    private List<Piece> createPiecesOfType(Piece.Type type) {
        Piece blackPiece, whitePiece;
        
        switch (type) {
            case PAWN:
                blackPiece = Piece.createBlackPawn();
                whitePiece = Piece.createWhitePawn();
                break;
                
            case ROOK:
                blackPiece = Piece.createBlackRook();
                whitePiece = Piece.createWhiteRook();
                break;
                
            case KNIGHT:
                blackPiece = Piece.createBlackKnight();
                whitePiece = Piece.createWhiteKnight();
                break;
                
            case BISHOP:
                blackPiece = Piece.createBlackBishop();
                whitePiece = Piece.createWhiteBishop();
                break;
                
            case QUEEN:
                blackPiece = Piece.createBlackQueen();
                whitePiece = Piece.createWhiteQueen();
                break;
                
            case KING:
                blackPiece = Piece.createBlackKing();
                whitePiece = Piece.createWhiteKing();
                break;
                
            default:
                fail("Unsupported piece type!");
                return null;
        }
        
        ArrayList<Piece> pieces = new ArrayList<Piece>();
        pieces.add(blackPiece);
        pieces.add(whitePiece);
        return pieces;
    }
}
