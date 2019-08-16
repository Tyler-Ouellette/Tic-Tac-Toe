import java.util.Scanner;

/**
 * Class to create a Human Player. Stuck on Make move method having to use the Gameboard to make a move.
 * Each player created increments ID. Still have to make it so there can't be more than 2 players total.
 */

public class HumanPlayer extends Player {

    /**
     * Overloaded Constructor, sends to parent class to set the information
     * @param playerName - set player name for human
     * @param Board -access for game board
     * @param playerType - player Type of 1 or -1 that is for X and O
     */
    HumanPlayer(String playerName, GameBoard Board, int playerType){
        super(playerName, Board, playerType);
    }

    /**
     * Implementing the Abstract method, capability to make a move. This move must be a valid move so that the tile isnt taken already or out of range
     * @param Board - must have the gameboard to make the move on
     */
    public void play(GameBoard Board){
        int move;
        boolean success;

        do {
            System.out.println("Please enter a number available to make your move: \n");
            Board.displayPlayerBoard();

            Scanner sc1 = new Scanner(System.in);
            move = sc1.nextInt();

            success = Board.makeMove(move, playerType); //Make move will return a true or false value if successful or not and if it is successful will set the move
            if (!success)
                System.out.println("Invalid move, please try again.");
        }while (!success);
    }


}
