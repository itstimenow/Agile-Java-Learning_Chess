package pieces;

public class King extends Piece {
    
    protected King(Piece.Color color) {
        this.color = color;
        
        if (color == Piece.Color.WHITE)
            this.representation = 'k';
        else
            this.representation = 'K';
    }
    
    @Override
    public boolean is(Class type) {
        return type == King.class;
    }
}
