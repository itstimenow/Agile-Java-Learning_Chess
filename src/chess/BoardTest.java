package chess;

import java.util.List;
import junit.framework.TestCase;
import util.StringUtil;
import pieces.Piece;

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
        
        int whitePawnNumber = board.getNumberOfPieces(Piece.Color.WHITE, Piece.Type.PAWN);
        assertEquals(3, whitePawnNumber);
        
        
        board.put(Piece.createBlackBishop(), 'a', 8);
        board.put(Piece.createBlackBishop(), 'b', 7);
        
        int blackBishopNumber = board.getNumberOfPieces(Piece.Color.BLACK, Piece.Type.BISHOP);
        assertEquals(2, blackBishopNumber);
    }
    
    public void testRetrievePiece() {
        char file = 'g';
        int rank = 5;
        board.put(Piece.createBlackRook(), file, rank);
        Piece blackRook = board.getPieceAt(file, rank);
        verifyPiece(blackRook, Piece.Color.BLACK, Piece.Type.ROOK);
        
        file = 'e';
        rank = 8;
        board.put(Piece.createWhiteQueen(), file, rank);
        Piece whiteQueen = board.getPieceAt(file, rank);
        verifyPiece(whiteQueen, Piece.Color.WHITE, Piece.Type.QUEEN);
    }
    
    public void testRetrievePieceCollection() {
        board.put(Piece.createBlackKing(),  'a', 7);
        board.put(Piece.createBlackQueen(), 'b', 8);
        board.put(Piece.createWhitePawn(),  'c', 2);
        board.put(Piece.createWhitePawn(),  'c', 3);
        board.put(Piece.createWhiteRook(),  'c', 4);
        
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
        verifyPiece(piece, Piece.Color.BLACK, Piece.Type.KING);
        
        piece = board.getPieceAt('b', 5);
        verifyPiece(piece, Piece.Color.BLACK, Piece.Type.ROOK);
        
        piece = board.getPieceAt('c', 4);
        verifyPiece(piece, Piece.Color.WHITE, Piece.Type.KING);
    }
    
    public void testPutPiece02() {
        board = Board.createEmptyBoard();
        
        Position position = new Position('b', 6);
        board.put(Piece.createBlackKing(), position);
        
        position = new Position('c', 4);
        board.put(Piece.createWhiteBishop(), position);
        
        Piece piece = board.getPieceAt('b', 6);
        verifyPiece(piece, Piece.Color.BLACK, Piece.Type.KING);
        
        piece = board.getPieceAt('c', 4);
        verifyPiece(piece, Piece.Color.WHITE, Piece.Type.BISHOP);
    }
    
    private void verifyPiece(Piece piece, Piece.Color color, Piece.Type type) {
        assertEquals(color, piece.getColor());
        assertEquals(type, piece.getType());
    }
}
