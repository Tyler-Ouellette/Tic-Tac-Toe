import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game implements global {

    GameBoard game;
    boolean success = false;
    Player PlayerX;
    Player PlayerO;
    int playerXWcount = 0;
    int playerOWcount = 0;
    int level;
    //public int turnCount = 0;


    Scanner scName = new Scanner(System.in);

    /**
     * Default Constructor for Game. Every Time game is created it will display the menu and then prompt user for what game mode they want
     */
    public Game(){
        String name1 = "Player 1", name2 = "Player 2";
        Scanner scName = new Scanner(System.in);
        game = new GameBoard();

        System.out.println("******************************");
        System.out.println("******************************");
        System.out.println();
        System.out.println("    Welcome to TicTacToe    ");
        System.out.println("  Created by Tyler Ouellette    ");
        System.out.println();
        System.out.println("******************************");
        System.out.println("******************************");
        System.out.println();

        int mode = 0;
        Scanner modeSC = new Scanner(System.in);
        boolean done = false;

    while(!success) {
        while (!done) {
            try {
                System.out.println("Please select a game mode by pressing the number keys listed next to the game mode: "); //had to enter this as it apparently wasn't fool proof enough
                System.out.println("Human vs. Human [1]");
                System.out.println("Human vs AI [2]");
                System.out.println("AI vs. AI [3]");
                System.out.println("EXPERTBOT vs SMART BOT [4]");
                System.out.print("GAME MODE: ");
                mode = modeSC.nextInt();
                if (mode == 1 || mode == 2 || mode == 3 || mode == 4)
                    done = true;
                else
                    System.out.println("Invalid Entry, please try again");
            } catch (InputMismatchException e) {
                modeSC.nextLine();
                System.out.println("Invalid entry, please try again");
            }
        }

        /**
         * Switch statement to run depending on user input
         */
        switch (mode) {
            case 1:
                System.out.println("Enter name for player 1: ");
                name1 = scName.next();
                System.out.println("Enter name for player 2: ");
                name2 = scName.next();
                success = true;
                break;
            case 2:
                System.out.println("Enter name for player 1: ");
                name1 = scName.next();
                Scanner scLevel = new Scanner(System.in);
                boolean validLevelType = false;
                while (!validLevelType) {
                    try {
                        System.out.println("What level would you like to play against? [1] Easy [2] Challenging [3] Impossible ");
                        level = scLevel.nextInt();
                        if (level == 1 || level == 2 || level == 3)
                            validLevelType = true;
                        else
                            System.out.println("Invalid entry, please try again,");
                    } catch (InputMismatchException e) {
                        scName.nextLine();
                        System.out.println("Invalid entry, please try again");
                    }
                }
                if (level == 1) {
                    System.out.println("You are up against BOT1, difficulty is super easy mode.");
                    name2 = "NOOB BOT";
                    System.out.println();
                    success = true;
                    break;
                }
                if (level == 2) {
                    System.out.println("You are up against SMART BOT, difficulty level is hard.");
                    name2 = "SMART BOT";
                    System.out.println();
                    success = true;
                    break;
                }
                else{
                    System.out.println("You are up against EXPERT BOT, he doesn't lose.");
                    name2 = "EXPERT BOT";
                    System.out.println();
                    success = true;
                    break;
                }
            case 3:
                System.out.println("I can't believe you want to see 2 bots play");
                name1 = "NOOB BOT";
                name2 = "SMART BOT";
                System.out.println("NOOB BOT (X) vs SMART BOT (O)");
                success = true;
                break;
            case 4:
                System.out.println("EXPERT BOT vs SMART BOT");
                name1 = "EXPERT BOT";
                name2 = "SMART BOT";
                success = true;
                break;
            default:
                System.out.println("Invalid Entry");

            }
        }

        /**
         * Depending on the mode entered by user, create players depending on if they want P v P, P v AI, AI vs AI
         */
        if (mode == 1){
            PlayerX = new HumanPlayer(name1, game, 1);
            PlayerO = new HumanPlayer(name2, game, -1);
        }
        else if (mode == 2){
            if (level == 1) {
                PlayerX = new HumanPlayer(name1, game, 1);
                PlayerO = new AIPlayer(name2, game, -1);
            }
            if (level == 2) { //potentially (if level == 2) and else would be 3 for ExpertAI
                PlayerX = new HumanPlayer(name1, game, 1);
                PlayerO = new SmartAIPlayer(name2, game, -1);
            }
            if (level == 3) {
                PlayerX = new HumanPlayer(name1, game, 1);
                PlayerO = new ExpertAIPlayer(name2, game, -1 );
            }
        }
        if (mode == 3){
            PlayerX = new AIPlayer(name1, game, 1);
            PlayerO = new SmartAIPlayer(name2, game, -1);
        }
        else {
            PlayerX = new ExpertAIPlayer(name1, game, 1 );
            PlayerO = new SmartAIPlayer(name2,game,-1 );
        }
    }

    /**
     * Method to start the game used in main.
     */
    public void start(){
    Random randStart = new Random();
    int playerTurn;
    int winner = 0;

    playerTurn = (randStart.nextInt(2) + 1); //bound 2 = 0-1 + 1 makes it either 1 or 2

    System.out.println("*******************");
    System.out.println("The game is starting!");
    System.out.println("*******************");

    System.out.println();
    if (playerTurn == 1) {
        System.out.println("" + PlayerX.name + " was randomly selected and will start.");
    }
    else {
        System.out.println("" + PlayerO.name + " was randomly selected and will start.");
    }

    /**
     * This do while loop is what is actually running the game itself. Controlling player turns and checking the game state
     */
    do {
        if (playerTurn == 1) {
            System.out.println("\n" + PlayerX.name + " it is your turn. (X)");
            PlayerX.play(game);
        }
        else{
            System.out.println("\n" + PlayerO.name + " it is your turn. (O)");
            PlayerO.play(game);
            //turnCount++; //was planning on counting turns to use for Expert AI but ran out of time
        }

        if (game.getGameState() != 4)  //if it is 1, -1, 3 it will mean either one of the players have won, or its a draw
            winner = 1;
        if (winner == 0){
            playerTurn = swap(playerTurn);
        }
    }while (winner == 0);

    //once a winner is determined above
    game.displayBoard(); //display the regular board with X and O on it

    //Print statements for who wins or tie game, also increment the Win count for the player if they won
    if (game.getGameState() == 3) {
        System.out.println("\nCats Game, its a draw.\n");
    }
    else if(game.getGameState() == 1) {
        System.out.println("\n" + PlayerX.name + " is the winner!\n");
        playerXWcount++;
    }
    else {
        System.out.println("\n" + PlayerO.name + " is the winner!\n");
        playerOWcount++;
    }

        /**
         * Call the method Scoreboard to display the score, send the scoreboard the Win counts and the names of the players
         */
    Scoreboard(playerXWcount, playerOWcount, PlayerX.name , PlayerO.name);


        /**
         * Would like to implement this feature however, because you create the gameboard in the game default constructor
         * and also create the players in the default constructor and pass the players that board. using the start(); method will
         * just use the same board as last game.
         *
         * UPDATE* Came up with a solution. Created a method called ClearBoard that would clear all the board tiles and reset them to 0.
         * However to do this, I had to change my method setTileSTate in BoardTile to allow for 0 as an entry.
         * This should not change anything as setTile state is only used in makeMove and it sets the tile state to the Player type of 1 or -1 and now also clear board for 0
         */

        boolean donePlaying = false;
        String again = "N";

        /**
         * This part of the code asks the user if they want to play again, throws exceptions to handle wrong input
         */
        while (!donePlaying) {
            try {
                System.out.println("Would you like to play again? (Y/N)");
                 again = scName.next();
                 if (again.equalsIgnoreCase("y") || again.equalsIgnoreCase("n"))
                    donePlaying = true;
                 else
                     System.out.println("Invalid entry, please try again,");
            } catch (InputMismatchException e) {
                scName.nextInt();
                System.out.println("Invalid entry, please try again");
            }
        }


        /**
         * Check to see if the user wants to play again, if they do, clear the board and start over using the same players
         */
        if (again.equalsIgnoreCase("y")){
            game.ClearBoard();
            start();
        }

}

    /**
     * Just a method to toggle player turns
     * @param turn takes int variable that is either 1 or 2 and swaps it
     * @return new value
     */
    public int swap(int turn){
    if (turn == 1)
        turn = 2;
    else
        turn = 1;

    return turn;
    }

    /**
     * A simple scoreboard display for the users of the Game Count
     * @param playerXWcount - Win count for player X
     * @param playerOWcount - win count for Player O
     * @param name1 - Name of Player 1 to be displayed
     * @param name2 - Name of Player 2 to be displayed
     */
    private void Scoreboard(int playerXWcount, int playerOWcount, String name1, String name2){
        System.out.println("" + name1 + "'s Wins \t\t" + "" + name2 + "'s Wins");
        System.out.println("----------------------------------------");
        System.out.println("\t" + playerXWcount + "\t\t\t\t\t\t\t" + playerOWcount);
        System.out.println();
    }

}

