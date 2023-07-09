package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

public class Blank extends Piece {
    protected Blank(Color color, Type type, Position position) {
        super(color, type, position);
    }

    @Override
    public Piece createMovedPiece(Position position) {
        return null;
    }

    @Override
    boolean verifyMovePosition(Position position) {
        return false;
    }

    public static Blank create(Position position) {
        return new Blank(Color.NOCOLOR, Type.NO_PIECE, position);
    }
}
