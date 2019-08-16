import java.util.Random;

public class AIPlayer extends Player {
    Random rand = new Random();

    public AIPlayer(String name, GameBoard game, int playerType){
        super(name, game, playerType);
    }

    /**
     * Method to find a legal move, store it in an array and then randomly choose one of those moves
     * Create an array of moves that are available and since it is a 2D array you know first row is 0-2
     * Ex. Say you want slot 5. Well that is position 1,1 in the 2D array. 3*1 = 3 + 1 + j(1) = 5
     * Ex2. Slot 7, row 2,0. so 3*2 = 6 + 1 + 0 = 7.
     * This created array will help give a data set of available moves for the bot to choose a random move at
     * @return - a random move
     */
    private int randomLegalMove(){
        int counter = 0; //used for parsing the array
        int tiles[] = new int[9];

        Random randMove = new Random();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.board[i][j].isValidPlayState()) {
                    tiles[(counter++)] = (3 * i + 1 + j);
                }
            }
        }
        return tiles[randMove.nextInt(counter)];
    }

    /**
     * Use the random number generated above to make a move, let the user know where the move was made
     */
    public void play(GameBoard Board){
        int move = 0;
        boolean success = false;
        move = randomLegalMove();

        do {
            success = Board.makeMove(move, playerType); //Make move will return a true or false value if successful or not and if it is successful will set the move
        }while(!success);

        System.out.println("I will play at tile " + move + ".\n");
    }

}
