package personal.javachess.enums;

public enum GameEndState {
    BLACK_WIN("Black Player Wins!"), 
    WHITE_WIN("White Player Wins!"), 
    DRAW("Its a draw.");

    private String message;

    private GameEndState(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static GameEndState state(Color winner) {
        if (winner == null) {
            return DRAW;
        }
        switch (winner) {
            case BLACK: return BLACK_WIN;
            case WHITE: return WHITE_WIN;
            default: return null;
        }
    }
}
