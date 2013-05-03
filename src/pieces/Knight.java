package pieces;

public class Knight extends Piece {
    
    protected Knight(Piece.Color color) {
        this.color = color;
        
        if (color == Piece.Color.WHITE)
            this.representation = 'n';
        else
            this.representation = 'N';
    }
    
    @Override
    public boolean is(Class type) {
        return type == Knight.class;
    }
}
