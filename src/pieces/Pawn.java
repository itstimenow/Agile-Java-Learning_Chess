package pieces;

public class Pawn extends Piece {
    
    protected Pawn(Piece.Color color) {
        this.color = color;
        
        if (color == Piece.Color.WHITE)
            this.representation = 'p';
        else
            this.representation = 'P';
    }
    
    @Override
    public boolean is(Class type) {
        return type == Pawn.class;
    }
}
