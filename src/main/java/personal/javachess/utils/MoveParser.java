package personal.javachess.utils;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import personal.javachess.data.Move;
import personal.javachess.data.State;
import personal.javachess.enums.Piece;
import personal.javachess.enums.PieceType;

@Component
public class MoveParser {

    public Move parse(State state, String moveStr) {
        try (Scanner sc = new Scanner(moveStr)) {
            final String movePattern = "[a-hA-H][1-8]";

            String from = sc.findInLine(movePattern);
            int fromRank = '8' - from.charAt(1);
            int fromFile = from.charAt(0) - 'a';
    
            String to = sc.findInLine(movePattern);
            int toRank = '8' - to.charAt(1);
            int toFile = to.charAt(0) - 'a';

            String promotionStr = sc.findInLine("[rbnqkRBNQK]");

            return parse(state, fromRank, fromFile, toRank, toFile, promotionStr);
        } catch (Exception e) {
            return null;
        }
    }

    public Move parse(State state, int fromRank, int fromFile, int toRank, int toFile) { 
        return parse(state, fromRank, fromFile, toRank, toFile, null);
    }
    
    public Move parse(State state, int fromRank, int fromFile, int toRank, int toFile, String promotionStr) {
        try {
            Piece[][] board = state.getBoard();
            Move move = new Move();
    
            move.setPlayer(state.getTurn());
    
            move.setFromRank(fromRank);
            move.setFromFile(fromFile);
            move.setToRank(toRank);
            move.setToFile(toFile);
    
            Piece movePiece = board[fromRank][fromFile];
            move.setMovePiece(movePiece);
    
            Piece takePiece = board[toRank][toFile];
            move.setTakePiece(takePiece);
            if (takePiece != null) {
                move.setTakeRank(toRank);
                move.setTakeFile(toFile);
            } else if (movePiece.getType() == PieceType.PAWN && toFile != fromFile) {
                move.setTakeRank(fromRank);
                move.setTakeFile(toFile);
                move.setTakePiece(board[fromRank][toFile]);
                move.setEnPassant(true);
            }
    
            if (movePiece.getType() == PieceType.KING) {
                if (toFile - fromFile > 1) {
                    move.setCastleFromRank(fromRank);
                    move.setCastleFromFile(7);
                    move.setCastleToRank(toRank);
                    move.setCastleToFile(5);
                } else if (fromFile - toFile > 1) {
                    move.setCastleFromRank(fromRank);
                    move.setCastleFromFile(0);
                    move.setCastleToRank(toRank);
                    move.setCastleToFile(3);
                }
            }
    
            if (promotionStr != null) {
                Piece rook = null, bishop = null, knight = null, queen = null;
                switch(move.getPlayer()) {
                    case WHITE:
                        rook = Piece.WHITE_ROOK;
                        bishop = Piece.WHITE_BISHOP;
                        knight = Piece.WHITE_KNIGHT;
                        queen = Piece.WHITE_QUEEN;
                        break;
                    case BLACK:
                        rook = Piece.BLACK_ROOK;
                        bishop = Piece.BLACK_BISHOP;
                        knight = Piece.BLACK_KNIGHT;
                        queen = Piece.BLACK_QUEEN;
                        break;
                }
                switch(promotionStr) {
                    case "r": case "R": case "ROOK":
                        move.setPromote(rook);
                        break;
                    case "b": case "B": case "BISHOP":
                        move.setPromote(bishop);
                        break;
                    case "n": case "N": case "k": case "K": case "KNIGHT":
                        move.setPromote(knight);
                        break;
                    case "q": case "Q": case "QUEEN":
                        move.setPromote(queen);
                        break;
                }
            }
    
            return move;
        } catch (Exception e) {
            return null;
        }
    }
        
}
