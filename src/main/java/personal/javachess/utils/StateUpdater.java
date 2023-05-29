package personal.javachess.utils;

import org.springframework.stereotype.Component;

import personal.javachess.data.Move;
import personal.javachess.data.Piece;
import personal.javachess.data.State;
import personal.javachess.enums.Color;
import personal.javachess.enums.PieceType;

@Component
public class StateUpdater {
    
    public void updateState(State state, Move move) {
        // remove taken piece
        take(state, move);

        // move piece to new location
        move(state, move);
        
        // move rook if castling
        castle(state, move);

        // promote pawn
        promote(state, move);

        // set enpassant
        enpassant(state, move);

        // next turn
        nextTurn(state);
    }

    private void take(State state, Move move) {
        if (move.getTakeRank() >= 0) {
            Piece[][] board = state.getBoard();
            board[move.getTakeRank()][move.getTakeFile()] = null;
        }
    }

    private void move(State state, Move move) {
        Piece[][] board = state.getBoard();
        Piece movePiece = move.getMovePiece();
        board[move.getFromRank()][move.getFromFile()] = null;
        board[move.getToRank()][move.getToFile()] = movePiece;
        boolean[] isCastlePossible;
        switch (movePiece.getType()) {
            case KING:
                isCastlePossible = state.getKingCastlePossible();
                isCastlePossible[movePiece.getColor().ordinal()] = false;
                isCastlePossible = state.getQueenCastlePossible();
                isCastlePossible[movePiece.getColor().ordinal()] = false;
                break;
            case ROOK:
                if (move.getFromFile() == 0) {
                    isCastlePossible = state.getQueenCastlePossible();
                } else {
                    isCastlePossible = state.getKingCastlePossible();
                }
                isCastlePossible[movePiece.getColor().ordinal()] = true;
                break;
            default:
                break;
        }
    }

    private void castle(State state, Move move) {
        Piece[][] board = state.getBoard();
        if (move.getCastleFromRank() >= 0) {
            Piece rook = board[move.getCastleFromRank()][move.getCastleFromFile()];
            board[move.getCastleFromRank()][move.getCastleFromFile()] = null;
            board[move.getCastleToRank()][move.getCastleToFile()] = rook;
        }
    }

    private void promote(State state, Move move) {
        if (move.getPromote() != null) {
            Piece[][] board = state.getBoard();
            board[move.getToRank()][move.getToFile()] = move.getPromote();
        }
    }

    private void enpassant(State state, Move move) {
        state.setEnpassantRank(-1);
        state.setEnpassantFile(-1);
        if (move.getMovePiece().getType().equals(PieceType.PAWN) && Math.abs(move.getFromRank() - move.getToRank()) > 1) {
            state.setEnpassantRank((move.getFromRank() + move.getToRank()) / 2);
            state.setEnpassantFile(move.getToFile());
        }
    }

    public void nextTurn(State state) {
        switch(state.getTurn()) {
            case WHITE:
                state.setTurn(Color.BLACK);
                break;
            case BLACK:
                state.setTurn(Color.WHITE);
                break;
        }
    }

}
