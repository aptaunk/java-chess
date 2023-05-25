package personal.javachess.data;

import org.springframework.stereotype.Component;

import personal.javachess.enums.Color;
import personal.javachess.enums.GameEndState;
import personal.javachess.enums.Piece;

@Component
public class State {
    
    private Color turn;
    private Piece[][] board;
    private GameEndState gameEndState;
    private boolean[] kingMoved = new boolean[Color.values().length];
    private boolean[] kingRookMoved = new boolean[Color.values().length];
    private boolean[] queenRookMoved = new boolean[Color.values().length];
    private int enpassantRank = -1;
    private int enpassantFile = -1;

    public State() {
    }

    public State(State other) {
        this.turn = other.turn;
        this.gameEndState = other.gameEndState;
        this.enpassantRank = other.enpassantRank;
        this.enpassantFile = other.enpassantFile;
        for (int i = 0; i < Color.values().length; i++) {
            this.kingMoved[i] = other.kingMoved[i];
            this.kingRookMoved[i] = other.kingRookMoved[i];
            this.queenRookMoved[i] = other.queenRookMoved[i];
        }
        this.board = new Piece[other.board.length][other.board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                this.board[i][j] = other.board[i][j];
            }
        }
    }

    public void setTurn(Color turn) {
        this.turn = turn;
    }

    public Color getTurn() {
        return turn;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public GameEndState getGameEndState() {
        return gameEndState;
    }

    public void setGameEndState(GameEndState gameEndState) {
        this.gameEndState = gameEndState;
    }

    public boolean[] getKingMoved() {
        return kingMoved;
    }

    public void setKingMoved(boolean[] kingMoved) {
        this.kingMoved = kingMoved;
    }

    public boolean[] getKingRookMoved() {
        return kingRookMoved;
    }

    public void setKingRookMoved(boolean[] kingRookMoved) {
        this.kingRookMoved = kingRookMoved;
    }

    public boolean[] getQueenRookMoved() {
        return queenRookMoved;
    }

    public void setQueenRookMoved(boolean[] queenRookMoved) {
        this.queenRookMoved = queenRookMoved;
    }

    public int getEnpassantRank() {
        return enpassantRank;
    }

    public void setEnpassantRank(int enpassantRank) {
        this.enpassantRank = enpassantRank;
    }

    public int getEnpassantFile() {
        return enpassantFile;
    }

    public void setEnpassantFile(int enpassantFile) {
        this.enpassantFile = enpassantFile;
    }

}
