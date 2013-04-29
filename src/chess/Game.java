package chess;

import pieces.Piece;
import util.StringUtil;


public class Game {
    private Board board;
    
    public Game() {
        board = Board.createEmptyBoard();
    }
    
    public Board getBoard() {
        return board;
    }
    
    public void initialize() {
        // Only need to initialize rank #1, #2, #7, #8, since all ranks are by default empty
        initializeRank1();
        initializeRank2();
        initializeRank7();
        initializeRank8();
    }
    
    /**
     * Initializes rank #1, the white-side rank where the king is in.
     */
    private void initializeRank1() {
        int rank = 1;
        board.put(Piece.createWhiteRook(),   'a', rank);
        board.put(Piece.createWhiteKnight(), 'b', rank);
        board.put(Piece.createWhiteBishop(), 'c', rank);
        board.put(Piece.createWhiteQueen(),  'd', rank);
        board.put(Piece.createWhiteKing(),   'e', rank);
        board.put(Piece.createWhiteBishop(), 'f', rank);
        board.put(Piece.createWhiteKnight(), 'g', rank);
        board.put(Piece.createWhiteRook(),   'h', rank);
    }
    
    /**
     * Initializes rank #2, the white-side rank where the pawn is in.
     */
    private void initializeRank2() {
        int rank = 2;
        for (char file : Board.FILES)
            board.put(Piece.createWhitePawn(), file, rank);
    }
    
    /**
     * Initializes rank #7, the black-side rank where the pawn is in.
     */
    private void initializeRank7() {
        int rank = 7;
        for (char file : Board.FILES)
            board.put(Piece.createBlackPawn(), file, rank);
    }
    
    /**
     * Initializes rank #8, the black-side rank where the king is in.
     */
    private void initializeRank8() {
        int rank = 8;
        board.put(Piece.createBlackRook(),   'a', rank);
        board.put(Piece.createBlackKnight(), 'b', rank);
        board.put(Piece.createBlackBishop(), 'c', rank);
        board.put(Piece.createBlackQueen(),  'd', rank);
        board.put(Piece.createBlackKing(),   'e', rank);
        board.put(Piece.createBlackBishop(), 'f', rank);
        board.put(Piece.createBlackKnight(), 'g', rank);
        board.put(Piece.createBlackRook(),   'h', rank);
    }
    
    public String getBoardPrint() {
        StringBuilder builder = new StringBuilder();
        
        // Loop through top rank to bottom rank, i.e. rank #8 to #1
        for (int index = Board.RANKS.length - 1; index >=0; index--) {
            int rank = Board.RANKS[index];
            
            for (char file : Board.FILES) {
                Piece piece = board.getPieceAt(file, rank);
                builder.append(piece.getRepresentation());
            }
            builder.append(StringUtil.NEWLINE);
        }
        
        return builder.toString();
    }
    
    public void moveLeft(Piece piece) {
        Position originalPosition = piece.getPosition();
        
        board.put(Piece.BLANK, originalPosition);
        board.put(piece, originalPosition.left());
    }
    
    public void moveRight(Piece piece) {
        Position originalPosition = piece.getPosition();
        
        board.put(Piece.BLANK, originalPosition);
        board.put(piece, originalPosition.right()); 
    }
    
    public void moveUp(Piece piece) {
        Position originalPosition = piece.getPosition();
        
        board.put(Piece.BLANK, originalPosition);
        board.put(piece, originalPosition.up());
    }
    
    public void moveDown(Piece piece) {
        Position originalPosition = piece.getPosition();
        
        board.put(Piece.BLANK, originalPosition);
        board.put(piece, originalPosition.down());
    }
}
