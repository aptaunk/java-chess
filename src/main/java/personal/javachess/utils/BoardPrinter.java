package personal.javachess.utils;

import org.springframework.stereotype.Component;

import personal.javachess.data.State;
import personal.javachess.enums.Piece;

@Component
public class BoardPrinter {
    
    public String displayBoard(State state) {
        Piece[][] board = state.getBoard();
        int ranks = board.length;
        int files = board[0].length;

        String border = "  " + "+---".repeat(files) + "+\n";
        String boardStr = border;

        for (int i = 0; i < ranks; i++) {
            char rank = (char)('8' - i);
            boardStr += rank + " ";
            for (int j = 0; j < files; j++) {
                String pieceSymbol = " ";
                Piece piece = board[i][j];
                if (piece != null) {
                    pieceSymbol = piece.getSymbol();
                }
                boardStr += "| " + pieceSymbol + " ";
            }
            boardStr += "|\n" + border;
        }
        boardStr += "  ";
        for (int i = 0; i < files; i++) {
            char file = (char)('a' + i);
            boardStr += "  " + file + " ";
        }
        boardStr += "\n";

        return boardStr;
    }

}
