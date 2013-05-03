package pieces;

public class Bishop extends Piece {
    
    protected Bishop(Piece.Color color) {
        this.color = color;
        
        if (color == Piece.Color.WHITE)
            this.representation = 'b';
        else
            this.representation = 'B';
    }
    
    @Override
    public boolean is(Class type) {
        return type == Bishop.class;
    }
}
