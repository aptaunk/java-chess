package personal.javachess.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import personal.javachess.data.Move;
import personal.javachess.data.Piece;
import personal.javachess.data.State;
import personal.javachess.enums.Color;
import personal.javachess.enums.GameEndState;

@Component
public class GameEndDetector {

    @Autowired private MovesGenerator generator;
    @Autowired private MoveValidator validator;
    @Autowired private CheckFinder finder;

    public GameEndState getGameEndState(State state) {
        Piece[][] board = state.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Piece piece = board[i][j];
                if (piece != null && piece.getColor().equals(state.getTurn())) {
                    List<Move> moves = generator.generateMoves(state, i, j);
                    for (Move move : moves) {
                        if (validator.isValidMove(state, move)) {
                            return null;
                        }
                    }
                }
            }
        }
        Color opponent = state.getTurn().equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
        Color winner = finder.isInCheck(state) ? opponent : null;
        return GameEndState.state(winner);
    }
    
}
