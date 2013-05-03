package pieces;

public class Rook extends Piece {
    
    protected Rook(Piece.Color color) {
        this.color = color;
        
        if (color == Piece.Color.WHITE)
            this.representation = 'r';
        else
            this.representation = 'R';
    }
    
    @Override
    public Type getType() {
        return Piece.Type.ROOK;
    }
}
