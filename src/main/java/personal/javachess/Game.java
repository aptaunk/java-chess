package personal.javachess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import personal.javachess.data.Move;
import personal.javachess.data.State;
import personal.javachess.enums.GameEndState;
import personal.javachess.utils.BoardPrinter;
import personal.javachess.utils.BoardSetter;
import personal.javachess.utils.GameEndDetector;
import personal.javachess.utils.MoveParser;
import personal.javachess.utils.MoveValidator;
import personal.javachess.utils.StateUpdater;

import java.util.Scanner;

@Component
public class Game {

    @Autowired private State state;
    @Autowired private BoardSetter setter;
    @Autowired private BoardPrinter printer;
    @Autowired private MoveParser parser;
    @Autowired private StateUpdater updater;
    @Autowired private MoveValidator validator;
    @Autowired private GameEndDetector detector;

    private Scanner sc = new Scanner(System.in);
    
    public void run() {
        
        // set the pieces
        setter.defaultStartingState(state);

        // keep playing till the game ends
        GameEndState gameEndState = null;
        while (gameEndState == null) {

            // display the board
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(printer.displayBoard(state));

            Move move = null;
            boolean validMove = false;
            
            // keep repeating till player enters a valid move
            while (!validMove) {

                // ask player for next move
                System.out.println("Please enter "+state.getTurn()+" player's move:");
                String moveStr = sc.nextLine();
                move = parser.parse(state, moveStr);

                // validate the move
                if (move != null) {
                    validMove = validator.isValidMove(state, move);
                }
                if (!validMove) {
                    System.out.println("Invalid move. Try again.");
                    System.out.println();
                }
            }

            // update the state
            updater.updateState(state, move);

            // check if the game is over
            gameEndState = detector.getGameEndState(state);
        }

        // game over
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(printer.displayBoard(state));
        System.out.println("Game Over. "+gameEndState.getMessage());
    }

}
