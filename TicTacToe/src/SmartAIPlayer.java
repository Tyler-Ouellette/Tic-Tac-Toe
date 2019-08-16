import java.util.Random;

public class SmartAIPlayer extends AIPlayer {

    Random rand = new Random();

    public SmartAIPlayer(String name, GameBoard game, int playerType) {
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

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.board[i][j].getTileState() == 0) {
                    tiles[(counter++)] = (3 * i + 1 + j);
                }
            }
        }
        return tiles[rand.nextInt(counter)];
    }

    /**
     * Method to iterate through every location in the board and check to see if there is a possible way for the bot to win
     * It starts by checking the rows of the board, and looking to see if the bot has 2 spots in a row in accordance to that tile it is currently searching
     * If there is no way to win in rows, check the columns next. Then if the location of the tile is within one of the diagonals, check for diagonals.
     * However if it is one of the slots on the middle edges (2,4,6,8) notice even numbers, it isn't a part of the diagonal chances
     * @return
     */
    private int senseWin() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.board[i][j].getTileState() == 0) {
                    int count = 0;
                    //Check rows 0,1,2 of array and count how many the bot has in that column. if two, return the value of slot (1-9) to play in
                    if (game.board[i][0].getTileState() == playerType) count++;
                    if (game.board[i][1].getTileState() == playerType) count++;
                    if (game.board[i][2].getTileState() == playerType) count++;
                    if (count == 2) {
                        return 3 * i + 1 + j;
                    }

                    //Check Columns 0,1,2 of array and count how many the bot has in that column. if two, return the value of slot (1-9) to play in
                    count = 0; //reset the count
                    if (game.board[0][j].getTileState() == playerType) count++;
                    if (game.board[1][j].getTileState() == playerType) count++;
                    if (game.board[2][j].getTileState() == playerType) count++;
                    if (count == 2) {
                        return 3 * i + 1 + j; //return the numerical value of the slot (1-9) to play
                    }
                    count = 0; //reset count
                    //Check Diagonal Top Left to Bottom Right
                    if (((i == 0) && (j == 0)) || ((i == 1) && (j == 1)) || ((i == 2) && (j == 2))) // Check to see if it is an odd number, if it is, it is part of the diagonal search if even it is an edge and don't check
                    {
                        {
                            if (game.board[0][0].getTileState() == playerType) count++;
                            if (game.board[1][1].getTileState() == playerType) count++;
                            if (game.board[2][2].getTileState() == playerType) count++;
                            if (count == 2) {
                                return 3 * i + 1 + j; //return the numerical value of the slot (1-9) to play
                            }
                        }
                        count = 0; // reset count
                        //Check Diagonal Top Right to Bottom Left
                        if (((i == 0) && (j == 2)) || ((i == 1) && (j == 1)) || ((i == 2) && (j == 0))) // Check to see if it is an odd number, if it is, it is part of the diagonal search if even it is an edge and don't check
                        {
                            if (game.board[0][2].getTileState() == playerType) count++;
                            if (game.board[1][1].getTileState() == playerType) count++;
                            if (game.board[2][0].getTileState() == playerType) count++;
                            if (count == 2) {
                                return 3 * i + 1 + j;
                            }
                        }

                    }
                }
            }
        }
        return 0; //return 0 if there is no way to win
    }

    /**
     * Method to check to see if the bot can lose within the next turn. This is done by parsing the 2D array and checking to see if there is a row, column or diagonal
     * that the opposing player has 2/3 spots with an empty spot remaining. If that is the case, return the integer of that spot to play and block it
     * @return TILE that the bot will lose at to be used to make a move and play in
     */
    private int senseLoss() {
        int oppositePlayer;

        if (playerType == 1) {
            oppositePlayer = -1;
        } else {
            oppositePlayer = 1;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.board[i][j].getTileState() == 0) {
                    int count = 0;
                    //Check rows 0,1,2 of array and count how many the bot has in that column. if two, return the value of slot (1-9) to play in
                    if (game.board[i][0].getTileState() == oppositePlayer) count++;
                    if (game.board[i][1].getTileState() == oppositePlayer) count++;
                    if (game.board[i][2].getTileState() == oppositePlayer) count++;
                    if (count == 2) {
                        return 3 * i + 1 + j;
                    }

                    //Check Columns 0,1,2 of array and count how many the bot has in that column. if two, return the value of slot (1-9) to play in
                    count = 0; //reset the count
                    if (game.board[0][j].getTileState() == oppositePlayer) count++;
                    if (game.board[1][j].getTileState() == oppositePlayer) count++;
                    if (game.board[2][j].getTileState() == oppositePlayer) count++;
                    if (count == 2) {
                        return 3 * i + 1 + j; //return the numerical value of the slot (1-9) to play
                    }
                    count = 0; //reset count
                    //Check Diagonal Top Left to Bottom Right
                    if (((i == 0) && (j == 0)) || ((i == 1) && (j == 1)) || ((i == 2) && (j == 2))){
                        if (game.board[0][0].getTileState() == oppositePlayer) count++;
                        if (game.board[1][1].getTileState() == oppositePlayer) count++;
                        if (game.board[2][2].getTileState() == oppositePlayer) count++;
                        if (count == 2) {
                            return 3 * i + 1 + j; //return the numerical value of the slot (1-9) to play
                        }
                    }
                    count = 0; // reset count
                    //Check Diagonal Top Right to Bottom Left
                    if (((i == 0) && (j == 2)) || ((i == 1) && (j == 1)) || ((i == 2) && (j == 0))){
                        if (game.board[0][2].getTileState() == oppositePlayer) count++;
                        if (game.board[1][1].getTileState() == oppositePlayer) count++;
                        if (game.board[2][0].getTileState() == oppositePlayer) count++;
                        if (count == 2) {
                            return 3 * i + 1 + j;
                        }
                    }
                }
            }
        }
        return 0; //return 0 if there is no way to Lose
    }

    /**
     * implementing the required make move method
     * @param board
     */
    public void play(GameBoard board){
        int move = 0;
        boolean success;

        move = senseWin();

        if (move != 0) {
            board.makeMove(move, playerType);
            System.out.println("I will WIN at tile " + move + ".\n");
            return;
        }

        move = senseLoss();
        if (move != 0) {
            board.makeMove(move, playerType);
            System.out.println("I will play DEFENSE at tile " + move + ".\n");
            return;
        }

        move = randomLegalMove();
        do {
            success = board.makeMove(move, playerType); //Make move will return a true or false value if successful or not and if it is successful will set the move
        }while(!success);
        System.out.println("I will play at tile " + move + ".\n");
        return;
    }
}
