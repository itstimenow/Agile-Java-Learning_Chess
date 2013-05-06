package chess;

import java.util.List;
import junit.framework.TestCase;
import util.StringUtil;
import pieces.*;

public class BoardTest extends TestCase {
    private Board board;
    
    public void setUp() {
        board = Board.createEmptyBoard();
    }
    
    public void testCreate() {
        board = Board.createEmptyBoard();
        assertEquals(0, board.getNumberOfPieces());
        
        // Verify that each square is blank
        for (char file : Board.FILES)
            for (int rank : Board.RANKS)
                assertEquals(Piece.BLANK, board.getPieceAt(file, rank));
    }
    
    public void testGetNumberOfPieces() {
        board.put(Piece.createWhitePawn(), 'c', 2);
        board.put(Piece.createWhitePawn(), 'c', 3);
        board.put(Piece.createWhitePawn(), 'c', 4);
        
        int whitePawnNumber = board.getNumberOfPieces(Piece.Color.WHITE, Pawn.class);
        assertEquals(3, whitePawnNumber);
        
        
        board.put(Piece.createBlackBishop(), 'a', 8);
        board.put(Piece.createBlackBishop(), 'b', 7);
        
        int blackBishopNumber = board.getNumberOfPieces(Piece.Color.BLACK, Bishop.class);
        assertEquals(2, blackBishopNumber);
    }
    
    public void testRetrievePiece() {
        char file = 'g';
        int rank = 5;
        board.put(Piece.createBlackRook(), file, rank);
        Piece blackRook = board.getPieceAt(file, rank);
        verifyPiece(blackRook, Piece.Color.BLACK, Rook.class);
        
        file = 'e';
        rank = 8;
        board.put(Piece.createWhiteQueen(), file, rank);
        Piece whiteQueen = board.getPieceAt(file, rank);
        verifyPiece(whiteQueen, Piece.Color.WHITE, Queen.class);
    }
    
    public void testRetrievePieceCollection() {
        board.put(Piece.createBlackKing(),  'a', 7);
        board.put(Piece.createBlackQueen(), 'b', 8);
        board.put(Piece.createWhitePawn(),  'c', 2);
        board.put(Piece.createWhitePawn(),  'c', 3);
        board.put(Piece.createWhiteRook(),  'c', 4);
        
        List<Piece> allPieces = board.getAllPieces();
        assertEquals(5, allPieces.size());
        
        List<Piece> blackSidePieces = board.getBlackSidePieces();
        assertEquals(2, blackSidePieces.size());
        
        blackSidePieces = board.getPiecesOfColor(Piece.Color.BLACK);
        assertEquals(2, blackSidePieces.size());
        
        List<Piece> whiteSidePieces = board.getWhiteSidePieces();
        assertEquals(3, whiteSidePieces.size());
        
        whiteSidePieces = board.getPiecesOfColor(Piece.Color.WHITE);
        assertEquals(3, whiteSidePieces.size());
    }
    
    public void testPutPiece01() {
        board = Board.createEmptyBoard();
        
        board.put(Piece.createBlackKing(),  'b', 6);
        board.put(Piece.createBlackRook(),  'b', 5);
        board.put(Piece.createWhiteKing(),  'c', 4);
        
        Piece piece = board.getPieceAt('b', 6);
        verifyPiece(piece, Piece.Color.BLACK, King.class);
        
        piece = board.getPieceAt('b', 5);
        verifyPiece(piece, Piece.Color.BLACK, Rook.class);
        
        piece = board.getPieceAt('c', 4);
        verifyPiece(piece, Piece.Color.WHITE, King.class);
    }
    
    public void testPutPiece02() {
        board = Board.createEmptyBoard();
        
        Position position = Position.at('b', 6);
        board.put(Piece.createBlackKing(), position);
        
        position = Position.at('c', 4);
        board.put(Piece.createWhiteBishop(), position);
        
        Piece piece = board.getPieceAt('b', 6);
        verifyPiece(piece, Piece.Color.BLACK, King.class);
        
        piece = board.getPieceAt('c', 4);
        verifyPiece(piece, Piece.Color.WHITE, Bishop.class);
    }
    
    private void verifyPiece(Piece piece, Piece.Color expectedColor, Class expectedClass) {
        assertEquals(expectedColor, piece.getColor());
        assertEquals(expectedClass, piece.getClass());
    }
}
