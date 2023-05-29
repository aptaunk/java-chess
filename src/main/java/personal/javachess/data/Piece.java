package personal.javachess.data;

import personal.javachess.enums.Color;
import personal.javachess.enums.PieceType;

public class Piece {

    private final Color color;
    private final PieceType type;

    public Piece(Color color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }
}
