package pieces;

import java.util.List;
import java.util.ArrayList;
import chess.Position;


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
    
    @Override
    public List<Position> getPossibleMoves() {
        List<Position> possibleMoves = new ArrayList<Position>();
        
        Position position = getPosition();
        possibleMoves.add(position.up().left());
        possibleMoves.add(position.up());
        possibleMoves.add(position.up().right());
        possibleMoves.add(position.left());
        possibleMoves.add(position.right());
        possibleMoves.add(position.down().left());
        possibleMoves.add(position.down());
        possibleMoves.add(position.down().right());
        
        return possibleMoves;
    }
}
