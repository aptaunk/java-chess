package personal.javachess.utils;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import personal.javachess.data.Move;
import personal.javachess.data.Piece;
import personal.javachess.data.State;
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
                switch(promotionStr) {
                    case "r": case "R": case "ROOK":
                        move.setPromote(new Piece(move.getPlayer(), PieceType.ROOK));
                        break;
                    case "n": case "N": case "k": case "K": case "KNIGHT":
                        move.setPromote(new Piece(move.getPlayer(), PieceType.KNIGHT));
                        break;
                    case "b": case "B": case "BISHOP":
                        move.setPromote(new Piece(move.getPlayer(), PieceType.BISHOP));
                        break;
                    case "q": case "Q": case "QUEEN":
                        move.setPromote(new Piece(move.getPlayer(), PieceType.QUEEN));
                        break;
                }
            }
    
            return move;
        } catch (Exception e) {
            return null;
        }
    }
        
}
