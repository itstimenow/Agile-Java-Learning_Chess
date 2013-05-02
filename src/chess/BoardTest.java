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
        
        int whitePawnNumber = 
            board.getNumberOfPieces(Piece.Color.WHITE, Piece.Type.PAWN);
        assertEquals(3, whitePawnNumber);
        
        
        board.put(Piece.createBlackBishop(), 'a', 8);
        board.put(Piece.createBlackBishop(), 'b', 7);
        
        int blackBishopNumber = 
            board.getNumberOfPieces(Piece.Color.BLACK, Piece.Type.BISHOP);
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
    
    public void testStrength() {
        board = board.createEmptyBoard();
        
        // Black side
        board.put(Piece.createBlackPawn(),  'b', 6);
        assertEquals(1.0, board.getBlackSideStrength());        
        board.put(Piece.createBlackQueen(), 'e', 6);
        assertEquals(10.0, board.getBlackSideStrength());
        
        board.put(Piece.createBlackPawn(),   'a', 7);
        board.put(Piece.createBlackPawn(),   'c', 7);
        board.put(Piece.createBlackBishop(), 'd', 7);
        board.put(Piece.createBlackKing(),   'b', 8);
        board.put(Piece.createBlackRook(),   'c', 8);
        assertEquals(20.0, board.getBlackSideStrength());
        
        // White side
        board.put(Piece.createWhiteKnight(), 'f', 4);
        board.put(Piece.createWhiteQueen(),  'g', 4);
        board.put(Piece.createWhitePawn(),   'f', 3);
        board.put(Piece.createWhitePawn(),   'h', 3);
        assertEquals(13.5, board.getWhiteSideStrength());
        
        board.put(Piece.createWhitePawn(), 'f', 2);
        board.put(Piece.createWhitePawn(), 'g', 2);
        board.put(Piece.createWhiteRook(), 'e', 1);
        board.put(Piece.createWhiteKing(), 'f', 1);
        assertEquals(19.5, board.getWhiteSideStrength());
        
        // Verify that strength of black side keep unchanged
        assertEquals(20.0, board.getBlackSideStrength());
        
        
        // Verify collection of pieces for each side
        List<Piece> blackSidePieces = board.getBlackSidePieces();
        assertEquals(7, blackSidePieces.size());
        assertEquals(9.0, blackSidePieces.get(0).getStrength());
        assertEquals(5.0, blackSidePieces.get(1).getStrength());
        assertEquals(3.0, blackSidePieces.get(2).getStrength());
        assertEquals(1.0, blackSidePieces.get(3).getStrength());
        assertEquals(1.0, blackSidePieces.get(4).getStrength());
        assertEquals(1.0, blackSidePieces.get(5).getStrength());
        assertEquals(0.0, blackSidePieces.get(6).getStrength());
        
        List<Piece> whiteSidePieces = board.getWhiteSidePieces();
        assertEquals(8, whiteSidePieces.size());
        assertEquals(9.0, whiteSidePieces.get(0).getStrength());
        assertEquals(5.0, whiteSidePieces.get(1).getStrength());
        assertEquals(2.5, whiteSidePieces.get(2).getStrength());
        assertEquals(1.0, whiteSidePieces.get(3).getStrength());
        assertEquals(1.0, whiteSidePieces.get(4).getStrength());
        assertEquals(0.5, whiteSidePieces.get(5).getStrength());
        assertEquals(0.5, whiteSidePieces.get(6).getStrength());
        assertEquals(0.0, whiteSidePieces.get(7).getStrength());
    }
}
