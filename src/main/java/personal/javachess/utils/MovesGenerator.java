package personal.javachess.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import personal.javachess.data.Move;
import personal.javachess.data.Piece;
import personal.javachess.data.State;
import personal.javachess.enums.Color;
import personal.javachess.enums.PieceType;

@Component
public class MovesGenerator {

    @Autowired private MoveParser parser;
    
    public List<Move> generateMoves(State state, int rank, int file) {
        Piece[][] board = state.getBoard();
        Piece movePiece = board[rank][file];
        switch (movePiece.getType()) {
            case KING: return king(state, rank, file);
            case KNIGHT: return knight(state, rank, file);
            case BISHOP: return bishop(state, rank, file);
            case ROOK: return rook(state, rank, file);
            case QUEEN: return queen(state, rank, file);
            case PAWN: return pawn(state, rank, file);
        }
        return null;
    }

    private List<Move> king(State state, int rank, int file) {
        List<Move> moves = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!isWithinBounds(state, rank + i, file + j)) {
                    continue;
                }
                if (i == 0 && j == 0) {
                    continue;
                }
                Move move = parser.parse(state, rank, file, rank + i, file + j);
                Piece movePiece = move.getMovePiece();
                Piece takePiece = move.getTakePiece();
                if (takePiece != null && !canTakePiece(movePiece, takePiece)) {
                    continue;
                }
                moves.add(move);
            }
        }
        moves.addAll(castle(state, rank, file));
        return moves;
    }

    private List<Move> castle(State state, int rank, int file) {
        List<Move> moves = new ArrayList<>();
        Piece[][] board = state.getBoard();
        Piece movePiece = board[rank][file];

        // king side castle
        if (state.getKingCastlePossible()[movePiece.getColor().ordinal()]) {
            if (isEmpty(state, rank, file + 1, file + 2) && !isCheck(state, rank, file, file + 2, movePiece)) {
                Move move = parser.parse(state, rank, file, rank, file + 2);
                moves.add(move);
            }
        }
        // queen side castle
        if (state.getQueenCastlePossible()[movePiece.getColor().ordinal()]) {
            if (isEmpty(state, rank, file - 3, file - 1) && !isCheck(state, rank, file - 2, file, movePiece)) {
                Move move = parser.parse(state, rank, file, rank, file - 2);
                moves.add(move);
            }
        }

        return moves;
    }

    private boolean isEmpty(State state, int rank, int fromFile, int toFile) {
        Piece[][] board = state.getBoard();
        for (int j = 0; j <= toFile - fromFile; j++) {
            if (board[rank][fromFile + j] != null) {
                return false;
            }
        }
        return true;
    }

    private boolean isCheck(State state, int rank, int fromFile, int toFile, Piece king) {
        State newState = new State(state);
        Piece[][] board = newState.getBoard();
        for (int j = 0; j <= toFile - fromFile; j++) {
            board[rank][fromFile + j] = king;
        }
        Color opponent = king.getColor().equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Piece piece = board[i][j];
                if (piece != null && piece.getColor().equals(opponent)) {
                    List<Move> moves = generateMoves(newState, i, j);
                    for (Move move : moves) {
                        if (move.getTakePiece() != null && move.getTakePiece().equals(king)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private List<Move> knight(State state, int rank, int file) {
        List<Move> moves = new ArrayList<>();
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (!isWithinBounds(state, rank + i, file + j)) {
                    continue;
                }
                if (Math.abs(i) + Math.abs(j) != 3) {
                    continue;
                }
                Move move = parser.parse(state, rank, file, rank + i, file + j);
                Piece movePiece = move.getMovePiece();
                Piece takePiece = move.getTakePiece();
                if (takePiece != null && !canTakePiece(movePiece, takePiece)) {
                    continue;
                }
                moves.add(move);
            }
        }
        return moves;
    }

    private List<Move> bishop(State state, int rank, int file) {
        List<Move> moves = new ArrayList<>();
        for (int i = -1; i <= 1; i+=2) {
            for (int j = -1; j <= 1; j+=2) {
                for (int k = 1; true; k++) {
                    if (!isWithinBounds(state, rank + i*k, file + j*k)) {
                        break;
                    }
                    Move move = parser.parse(state, rank, file, rank + i*k, file + j*k);
                    Piece movePiece = move.getMovePiece();
                    Piece takePiece = move.getTakePiece();
                    if (takePiece != null) {
                        if (canTakePiece(movePiece, takePiece)) {
                            moves.add(move);
                        }
                        break;
                    }
                    moves.add(move);
                }
            }
        }
        return moves;
    }

    private List<Move> rook(State state, int rank, int file) {
        List<Move> moves = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0 || i != 0 && j != 0) {
                    continue;
                }
                for (int k = 1; true; k++) {
                    if (!isWithinBounds(state, rank + i*k, file + j*k)) {
                        break;
                    }
                    Move move = parser.parse(state, rank, file, rank + i*k, file + j*k);
                    Piece movePiece = move.getMovePiece();
                    Piece takePiece = move.getTakePiece();
                    if (takePiece != null) {
                        if (canTakePiece(movePiece, takePiece)) {
                            moves.add(move);
                        }
                        break;
                    }
                    moves.add(move);
                }
            }
        }
        return moves;
    }

    private List<Move> queen(State state, int rank, int file) {
        List<Move> moves = new ArrayList<>();
        moves.addAll(bishop(state, rank, file));
        moves.addAll(rook(state, rank, file));
        return moves;
    }

    private List<Move> pawn(State state, int rank, int file) {
        List<Move> moves = new ArrayList<>();
        Move singleMove = singleMove(state, rank, file);
        if (singleMove != null) {
            moves.add(singleMove);
        }
        Move doubleMove = doubleMove(state, rank, file);
        if (doubleMove != null) {
            moves.add(doubleMove);
        }
        moves.addAll(take(state, rank, file));
        return promote(state, moves);
    }

    private Move singleMove(State state, int rank, int file) {
        Piece[][] board = state.getBoard();
        Piece movePiece = board[rank][file];
        int i = movePiece.getColor().equals(Color.WHITE) ? -1 : 1;
        if (isWithinBounds(state, rank + i, file) && board[rank + i][file] == null) {
            return parser.parse(state, rank, file, rank + i, file);
        }
        return null;
    }

    private Move doubleMove(State state, int rank, int file) {
        Piece[][] board = state.getBoard();
        Piece movePiece = board[rank][file];
        int i = 1, homeRank = 1;
        if (movePiece.getColor().equals(Color.WHITE)) {
            i = -1;
            homeRank = 6;
        }
        if (rank == homeRank && isWithinBounds(state, rank + 2*i, file) && board[rank + i][file] == null && board[rank + 2*i][file] == null) {
            return parser.parse(state, rank, file, rank + 2*i, file);
        }
        return null;
    }

    private List<Move> take(State state, int rank, int file) {
        List<Move> moves = new ArrayList<>();
        Piece[][] board = state.getBoard();
        Piece movePiece = board[rank][file];
        int i = movePiece.getColor().equals(Color.WHITE) ? -1 : 1;
        for (int j = -1; j <= 1; j+=2) {
            if (!isWithinBounds(state, rank + i, file + j)) {
                continue;
            }
            Move move = parser.parse(state, rank, file, rank + i, file + j);
            Piece takePiece = move.getTakePiece();
            if (takePiece == null || !canTakePiece(movePiece, takePiece)) {
                continue;
            }
            if (!move.isEnPassant() || state.getEnpassantRank() == move.getToRank() && state.getEnpassantFile() == move.getToFile()) {
                moves.add(move);
            }
        }
        return moves;
    }

    private List<Move> promote(State state, List<Move> moves) {
        List<Move> promotedMoves = new ArrayList<>();
        PieceType[] promotions = new PieceType[]{PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN};
        for (Move move : moves) {
            int lastRank = move.getMovePiece().getColor().equals(Color.WHITE) ? 0 : 7;
            if (move.getToRank() == lastRank) {
                for (PieceType promotion : promotions) {
                    promotedMoves.add(parser.parse(state, move.getFromRank(), move.getFromFile(), move.getToRank(), move.getToFile(), promotion.name()));
                }
            } else {
                promotedMoves.add(move);
            }
        }
        return promotedMoves;
    }

    private boolean canTakePiece(Piece movePiece, Piece takePiece) {
        return !takePiece.getColor().equals(movePiece.getColor());
    }

    private boolean isWithinBounds(State state, int rank, int file) {
        Piece[][] board = state.getBoard();
        int ranks = board.length;
        int files = board[0].length;
        return rank >= 0 && rank < ranks && file >= 0 && file < files;
    }

}
