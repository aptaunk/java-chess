package personal.javachess.utils;

import org.springframework.stereotype.Component;

import personal.javachess.data.State;
import personal.javachess.enums.Color;
import personal.javachess.enums.Piece;
import personal.javachess.enums.PieceType;

@Component
public class BoardPrinter {
    
    public String displayBoard(State state) {
        Piece[][] board = state.getBoard();
        int ranks = board.length;
        int files = board[0].length;

        String border = "  ├" + "───┼".repeat(files-1) + "───┤\n";
        String[] rankStr = new String[ranks];
        for (int i = 0; i < ranks; i++) {
            char rank = (char)('8' - i);
            rankStr[i] = rank + " ";
            for (int j = 0; j < files; j++) {
                String pieceSymbol = " ";
                Piece piece = board[i][j];
                if (piece != null) {
                    pieceSymbol = getSymbol(piece.getColor(), piece.getType());
                }
                rankStr[i] += "│ " + pieceSymbol + " ";
            }
            rankStr[i] += "│\n";
        }


        String boardStr = "  ┌" + "───┬".repeat(files-1) + "───┐\n";
        boardStr += String.join(border, rankStr);
        boardStr += "  └" + "───┴".repeat(files-1) + "───┘\n";
        boardStr += "  ";
        for (int i = 0; i < files; i++) {
            char file = (char)('a' + i);
            boardStr += "  " + file + " ";
        }
        boardStr += "\n";

        return boardStr;
    }

    private String getSymbol(Color color, PieceType type) {
        switch (color) {
            case WHITE: {
                switch (type) {
                    case ROOK: return "♖";
                    case KNIGHT: return "♘";
                    case BISHOP: return "♗";
                    case QUEEN: return "♕";
                    case KING: return "♔";
                    case PAWN: return "♙";
                }
            }
            case BLACK: {
                switch (type) {
                    case ROOK: return "♜";
                    case KNIGHT: return "♞";
                    case BISHOP: return "♝";
                    case QUEEN: return "♛";
                    case KING: return "♚";
                    case PAWN: return "♟︎";
                }
            }
        }
        return null;
    }

}
