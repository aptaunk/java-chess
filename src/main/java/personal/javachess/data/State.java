package personal.javachess.data;

import org.springframework.stereotype.Component;

import personal.javachess.enums.Color;

@Component
public class State {
    
    private Color turn;
    private Piece[][] board;
    private boolean[] kingCastlePossible = new boolean[Color.values().length];
    private boolean[] queenCastlePossible = new boolean[Color.values().length];
    private int enpassantRank = -1;
    private int enpassantFile = -1;

    public State() {
    }

    public State(State other) {
        this.turn = other.turn;
        this.enpassantRank = other.enpassantRank;
        this.enpassantFile = other.enpassantFile;
        for (int i = 0; i < Color.values().length; i++) {
            this.kingCastlePossible[i] = other.kingCastlePossible[i];
            this.queenCastlePossible[i] = other.queenCastlePossible[i];
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

    public boolean[] getKingCastlePossible() {
        return kingCastlePossible;
    }

    public void setKingCastlePossible(boolean[] kingCastlePossible) {
        this.kingCastlePossible = kingCastlePossible;
    }

    public boolean[] getQueenCastlePossible() {
        return queenCastlePossible;
    }

    public void setQueenCastlePossible(boolean[] queenCastlePossible) {
        this.queenCastlePossible = queenCastlePossible;
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
