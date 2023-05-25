package personal.javachess.utils;

import org.springframework.stereotype.Component;

import personal.javachess.data.State;
import personal.javachess.enums.Color;
import personal.javachess.enums.Piece;

@Component
public class BoardSetter {
    
    public void defaultStartingState(State state) {
        Piece[][] board = new Piece[8][];

        board[0] = new Piece[] {
            Piece.BLACK_ROOK,
            Piece.BLACK_KNIGHT,
            Piece.BLACK_BISHOP,
            Piece.BLACK_QUEEN,
            Piece.BLACK_KING,
            Piece.BLACK_BISHOP,
            Piece.BLACK_KNIGHT,
            Piece.BLACK_ROOK
        };
        board[1] = new Piece[] {
            Piece.BLACK_PAWN,
            Piece.BLACK_PAWN,
            Piece.BLACK_PAWN,
            Piece.BLACK_PAWN,
            Piece.BLACK_PAWN,
            Piece.BLACK_PAWN,
            Piece.BLACK_PAWN,
            Piece.BLACK_PAWN
        };

        board[2] = new Piece[8];
        board[3] = new Piece[8];
        board[4] = new Piece[8];
        board[5] = new Piece[8];

        board[6] = new Piece[] {
            Piece.WHITE_PAWN,
            Piece.WHITE_PAWN,
            Piece.WHITE_PAWN,
            Piece.WHITE_PAWN,
            Piece.WHITE_PAWN,
            Piece.WHITE_PAWN,
            Piece.WHITE_PAWN,
            Piece.WHITE_PAWN
        };
        board[7] = new Piece[] {
            Piece.WHITE_ROOK,
            Piece.WHITE_KNIGHT,
            Piece.WHITE_BISHOP,
            Piece.WHITE_QUEEN,
            Piece.WHITE_KING,
            Piece.WHITE_BISHOP,
            Piece.WHITE_KNIGHT,
            Piece.WHITE_ROOK
        };

        state.setBoard(board);
        state.setTurn(Color.WHITE);
    }

}
