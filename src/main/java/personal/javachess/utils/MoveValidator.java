package personal.javachess.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import personal.javachess.data.Move;
import personal.javachess.data.Piece;
import personal.javachess.data.State;

@Component
public class MoveValidator {
    
    @Autowired private MovesGenerator generator;
    @Autowired private StateUpdater updater;
    @Autowired private CheckFinder finder;

    public boolean isValidMove(State state, Move move) {
        Piece[][] board = state.getBoard();

        // check its the correct player's turn
        if (!state.getTurn().equals(move.getPlayer())) {
            return false;
        }

        // check the from-coordinates are within the bounds
        if (!isWithinBounds(state, move.getFromRank(), move.getFromFile())) {
            return false;
        }

        // check that the movePiece belongs to the correct player
        Piece movePiece = board[move.getFromRank()][move.getFromFile()];
        if (!movePiece.getColor().equals(move.getPlayer())) {
            return false;
        }
        
        // check that the move is something that the piece can do
        List<Move> moves = generator.generateMoves(state, move.getFromRank(), move.getFromFile());
        if (!moves.contains(move)) {
            return false;
        }

        // make sure king is not in check as a result of this move
        State newState = newState(state, move);
        return !finder.isInCheck(newState);
    }

    private boolean isWithinBounds(State state, int rank, int file) {
        Piece[][] board = state.getBoard();
        int ranks = board.length;
        int files = board[0].length;
        return rank >= 0 && rank < ranks && file >= 0 && file < files;
    }

    private State newState(State state, Move move) {
        State newState = new State(state);
        updater.updateState(newState, move);
        updater.nextTurn(newState);
        return newState;
    }
}
