package personal.javachess.enums;

public enum Piece {
    BLACK_ROOK(Color.BLACK, PieceType.ROOK, "♖"),
    BLACK_KNIGHT(Color.BLACK, PieceType.KNIGHT, "♘"),
    BLACK_BISHOP(Color.BLACK, PieceType.BISHOP, "♗"),
    BLACK_QUEEN(Color.BLACK, PieceType.QUEEN, "♕"),
    BLACK_KING(Color.BLACK, PieceType.KING, "♔"),
    BLACK_PAWN(Color.BLACK, PieceType.PAWN, "♙"),
    WHITE_ROOK(Color.WHITE, PieceType.ROOK, "♜"),
    WHITE_KNIGHT(Color.WHITE, PieceType.KNIGHT, "♞"),
    WHITE_BISHOP(Color.WHITE, PieceType.BISHOP, "♝"),
    WHITE_QUEEN(Color.WHITE, PieceType.QUEEN, "♛"),
    WHITE_KING(Color.WHITE, PieceType.KING, "♚"),
    WHITE_PAWN(Color.WHITE, PieceType.PAWN, "♟︎");

    private Color color;
    private PieceType type;
    private String symbol;

    private Piece(Color color, PieceType type, String symbol) {
        this.color = color;
        this.type = type;
        this.symbol = symbol;
    }

    public Color getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public String getSymbol() {
        return symbol;
    }
}
