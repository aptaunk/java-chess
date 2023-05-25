package personal.javachess.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import personal.javachess.data.Move;
import personal.javachess.data.State;
import personal.javachess.enums.Color;
import personal.javachess.enums.Piece;
import personal.javachess.enums.PieceType;

@Component
public class CheckDetector {

    @Autowired private MovesGenerator generator;
    
    public boolean isInCheck(State state, Color player) {
        Piece[][] board = state.getBoard();
        Color opponent = player.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Piece piece = board[i][j];
                if (piece != null && piece.getColor().equals(opponent)) {
                    List<Move> moves = generator.generateMoves(state, i, j);
                    for (Move move : moves) {
                        if (move.getTakePiece() != null && move.getTakePiece().getType().equals(PieceType.KING)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
