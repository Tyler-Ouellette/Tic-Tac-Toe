public class GameBoard implements global {

    public BoardTile board[][];
    private int gameState = 0;

    /**
     * Default constructor of GameBoard. When a gameboard is created it creates a 2D array 3x3 of boardTiles
     */
    public GameBoard(){
        board = new BoardTile[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new BoardTile();
            }
        }
    }

    public void ClearBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setTileState(0);
            }
        }
        System.out.println("\nGame Board Cleared!\n");
    }

    /**
     * method to return the Game State rather than the tile state, this is done by calling updateWinningState method which will update if win, or draw or unfinished
      * @return
     */
    public int getGameState() {
        return updateWinningState();
    }


    /**
     * method to update the winning state is it is 1 that means player X wins if it is -1 then player O wins
     * if it is neither of those options, call isDraw method. Is draw will either return a 3 which means the game is a draw
     * or it will return a 4 which means the game is still in progress
     * @param
     */
    public int updateWinningState(){
        if (isWin() != 4) {
            gameState = isWin();
        }
        else {
            gameState = isDraw();
        }
        return gameState;
    }

    /**
     * Method to determine if there is a win. since X = 1 when you add them up it will == 3 for a win
     * Since O = -1, adding them up will give you -3 for a win
     * @return
     */
    public int isWin(){
        if (board[0][0].getTileState() + board[0][1].getTileState() + board[0][2].getTileState() == 3 ||    //row 1 win
            board[1][0].getTileState() + board[1][1].getTileState() + board[1][2].getTileState() == 3 ||    //row 2 win
            board[2][0].getTileState() + board[2][1].getTileState() + board[2][2].getTileState() == 3 ||    //row 3 win
            board[0][0].getTileState() + board[1][0].getTileState() + board[2][0].getTileState() == 3 ||    //column 1 win
            board[0][1].getTileState() + board[1][1].getTileState() + board[2][1].getTileState() == 3 ||    //column 2 win
            board[0][2].getTileState() + board[1][2].getTileState() + board[2][2].getTileState() == 3 ||    //column 3 win
            board[0][0].getTileState() + board[1][1].getTileState() + board[2][2].getTileState() == 3 ||    //diagonal win left to right (top down)
            board[2][0].getTileState() + board[1][1].getTileState() + board[0][2].getTileState() == 3)      //diagonal win right to left (top down)
            return 1; //player X wins from global interface value
        else if (board[0][0].getTileState() + board[0][1].getTileState() + board[0][2].getTileState() == -3 ||    //row 1 win
                board[1][0].getTileState() + board[1][1].getTileState() + board[1][2].getTileState() == -3 ||    //row 2 win
                board[2][0].getTileState() + board[2][1].getTileState() + board[2][2].getTileState() == -3 ||    //row 3 win
                board[0][0].getTileState() + board[1][0].getTileState() + board[2][0].getTileState() == -3 ||    //column 1 win
                board[0][1].getTileState() + board[1][1].getTileState() + board[2][1].getTileState() == -3 ||    //column 2 win
                board[0][2].getTileState() + board[1][2].getTileState() + board[2][2].getTileState() == -3 ||    //column 3 win
                board[0][0].getTileState() + board[1][1].getTileState() + board[2][2].getTileState() == -3 ||    //diagonal win left to right (top down)
                board[2][0].getTileState() + board[1][1].getTileState() + board[0][2].getTileState() == -3)
            return 2; //player O wins from global interface value
        else
            return 4; //return game incomplete that is checked in isDraw to see if it is a draw
    }

    /**
     * Method to check to see if the game is a draw. If there is not a win seen in method above it will return a value of 4. If isWin doesn't equal 4
     * @return
     */
    public int isDraw() {
        if (isWin() == 4) { // 4 means incomplete from global
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j].isValidPlayState())
                        return 4; //slot is empty from global interface and not a draw
                }
            }
        }
        return 3; //return a draw which is value 3 from interface
    }

    /**
     * This is a method to display the Tic Tac Toe Game As a regular game would look with X's and O's
     */
    public void displayBoard(){
        System.out.println("" + board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
        System.out.println("------");
        System.out.println("" + board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
        System.out.println("------");
        System.out.println("" + board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
    }

    /**
     * This is a method to display the game board when the user is prompted for a move. This shows the user which slots are available and what number to enter to play in it
     * it also displays the previously entered moves according to player indicating by X and Os.
     */
    public void displayPlayerBoard(){
        int spaceCounter = 1;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (board[i][j].isValidPlayState()) { //if there is no move made yet, display a number for the user to enter (space counter) to make a move in that location
                    System.out.print(spaceCounter); //couldn't use println as it would print a new line each time
                }
                else if (!board[i][j].isValidPlayState()){ //if the board slot is not empty. Display what is inside that block (either an X or O)
                    System.out.print(board[i][j]);
                }
                if (spaceCounter % 3 != 0) //this is to make sure it makes the board look like a board but not boxed in
                    System.out.print("|");
                spaceCounter++; //used to label the squares in Tic Tac Toe as number from 1-9 to tell the user what number to enter for that location to make a move
            }
            if (i != 2) {
                System.out.println("\n------"); //needed this to make it stay in proper format as seen above
            }
            else {
                System.out.println();
            }
        }

    }

    /**
     * method to make a move on the board to be used by the player. The method will take in the selection value that the player wants to enter
     * The method will also receive which player number is playing that move corresponding to the X or O value in global
     * The selection will then be checked to see if it is a valid Move that can be played (depending on if the locaiton is empty or not) and actually set the state and return a value true for success
     * If the board is not empty, and the spot is taken, it will return a false value that will be used to tell the user that it is not allowed
     * @param selection - location on the game board must be within 1-9 and must be checked for if it is a valid state to play
     * @param playerType - this is the players ID in a sense that will identify X or O
     * @return - true or false value depending on if the move was valid. If it was valid the move will proceed and return a true value else false
     */
    public boolean makeMove(int selection, int playerType){
        int a = 0, b = 0; //variables uses for 2D array location

        switch (selection){
            case 1: //player selection 1, array 0,0
                a = 0;
                b = 0;
                break;

            case 2:  //player selection 2, array 0,1
                a = 0;
                b = 1;
                break;

            case 3:  //player selection 3, array 0,3
                a = 0;
                b = 2;
                break;
            case 4:  //player selection 4, array 1,0
                a = 1;
                b = 0;
                break;

            case 5:  //player selection 5, array 1,1
                a = 1;
                b = 1;
                break;

            case 6:  //player selection 6, array 1,2
                a = 1;
                b = 2;
                break;

            case 7:  //player selection 7, array 2,0
                a = 2;
                b = 0;
                break;

            case 8:  //player selection 8, array 2,1
                a = 2;
                b = 1;
                break;

            case 9:  //player selection 9, array 2,2
                a = 2;
                b = 2;
                break;

            default: //if it is not a number between 1 and 9 return false as it is not valid
                return false;
        }

        if (board[a][b].isValidPlayState()){    //check the location of the game board and see if it is available to play
            board[a][b].setTileState(playerType);    //if it is, set the state to the value of the player
            return true;
        }
        return false;
    }
}
