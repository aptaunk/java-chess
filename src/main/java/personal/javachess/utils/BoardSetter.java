package personal.javachess.utils;

import org.springframework.stereotype.Component;

import personal.javachess.data.Piece;
import personal.javachess.data.State;
import personal.javachess.enums.Color;
import personal.javachess.enums.PieceType;

@Component
public class BoardSetter {
    
    public void defaultStartingState(State state) {
        Piece[][] board = new Piece[8][];

        board[0] = new Piece[] {
            new Piece(Color.BLACK, PieceType.ROOK),
            new Piece(Color.BLACK, PieceType.KNIGHT),
            new Piece(Color.BLACK, PieceType.BISHOP),
            new Piece(Color.BLACK, PieceType.QUEEN),
            new Piece(Color.BLACK, PieceType.KING),
            new Piece(Color.BLACK, PieceType.BISHOP),
            new Piece(Color.BLACK, PieceType.KNIGHT),
            new Piece(Color.BLACK, PieceType.ROOK)
        };
        board[1] = new Piece[] {
            new Piece(Color.BLACK, PieceType.PAWN),
            new Piece(Color.BLACK, PieceType.PAWN),
            new Piece(Color.BLACK, PieceType.PAWN),
            new Piece(Color.BLACK, PieceType.PAWN),
            new Piece(Color.BLACK, PieceType.PAWN),
            new Piece(Color.BLACK, PieceType.PAWN),
            new Piece(Color.BLACK, PieceType.PAWN),
            new Piece(Color.BLACK, PieceType.PAWN)
        };

        board[2] = new Piece[8];
        board[3] = new Piece[8];
        board[4] = new Piece[8];
        board[5] = new Piece[8];

        board[6] = new Piece[] {
            new Piece(Color.WHITE, PieceType.PAWN),
            new Piece(Color.WHITE, PieceType.PAWN),
            new Piece(Color.WHITE, PieceType.PAWN),
            new Piece(Color.WHITE, PieceType.PAWN),
            new Piece(Color.WHITE, PieceType.PAWN),
            new Piece(Color.WHITE, PieceType.PAWN),
            new Piece(Color.WHITE, PieceType.PAWN),
            new Piece(Color.WHITE, PieceType.PAWN)
        };
        board[7] = new Piece[] {
            new Piece(Color.WHITE, PieceType.ROOK),
            new Piece(Color.WHITE, PieceType.KNIGHT),
            new Piece(Color.WHITE, PieceType.BISHOP),
            new Piece(Color.WHITE, PieceType.QUEEN),
            new Piece(Color.WHITE, PieceType.KING),
            new Piece(Color.WHITE, PieceType.BISHOP),
            new Piece(Color.WHITE, PieceType.KNIGHT),
            new Piece(Color.WHITE, PieceType.ROOK)
        };

        state.setBoard(board);
        state.setTurn(Color.WHITE);
        state.setKingCastlePossible(new boolean[]{true, true});
        state.setQueenCastlePossible(new boolean[]{true, true});
    }

}
