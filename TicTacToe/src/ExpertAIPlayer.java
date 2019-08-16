import java.util.Random;

public class ExpertAIPlayer extends AIPlayer {

    Random rand = new Random();

    /**
     * Overloaded Constructor for Expert AI
     * @param name - player name
     * @param game - game board
     * @param playerType - int either 1 or -1 to indiciate X or O
     */
    public ExpertAIPlayer(String name, GameBoard game, int playerType) {
        super(name, game, playerType);
    }

    /**
     * Method to check if the entire board is empty. If it is empty count will remain 1 telling the bot has first move
     * If the board Tile is not empty, increment move by 1 and for each tile do that.
     * Ex. Board is empty, Bot has first move
     * Ex2. Board has 2 X's and 3 O's, it is the bots turn, he has move number 6
     * @return true of false
     */
    private int moveNumberCheck() {
        int count = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.board[i][j].getTileState() != 0)
                    count++;
            }
        }
        return count;
    }

    /**
     * Method to iterate through every location in the board and check to see if there is a possible way for the bot to win
     * It starts by checking the rows of the board, and looking to see if the bot has 2 spots in a row in accordance to that tile it is currently searching
     * If there is no way to win in rows, check the columns next. Then if the location of the tile is within one of the diagonals, check for diagonals.
     * However if it is one of the slots on the middle edges (2,4,6,8) notice even numbers, it isn't a part of the diagonal chances
     * @return the numerical value of the move to play to win
     */
    private int senseWin() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.board[i][j].isValidPlayState()) {
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
                    if (((i == 0) && (j == 0)) || ((i == 1) && (j == 1)) || ((i == 2) && (j == 2))) {
                            if (game.board[0][0].getTileState() == playerType) count++;
                            if (game.board[1][1].getTileState() == playerType) count++;
                            if (game.board[2][2].getTileState() == playerType) count++;
                            if (count == 2) {
                                return 3 * i + 1 + j; //return the numerical value of the slot (1-9) to play
                            }
                        }
                    count = 0; // reset count
                    //Check Diagonal Top Right to Bottom Left
                    if (((i == 0) && (j == 2)) || ((i == 1) && (j == 1)) || ((i == 2) && (j == 0))) {
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
        return 0; //return 0 if there is no way to win
    }

    /**
     * Method to set and calculate opposite player status
     * @return
     */
    private int checkPlayerStatus(){
        int oppositePlayer;

        if (playerType == 1) {
            oppositePlayer = -1;
        } else {
            oppositePlayer = 1;
        }
        return oppositePlayer;
    }

    /**
     * Method to check to see if the bot can lose within the next turn. This is done by parsing the 2D array and checking to see if there is a row, column or diagonal
     * that the opposing player has 2/3 spots with an empty spot remaining. If that is the case, return the integer of that spot to play and block it
     * @return TILE that the bot will lose at to be used to make a move and play in
     */
    private int senseLoss() {

        int oppositePlayer = checkPlayerStatus();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.board[i][j].isValidPlayState()) {
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
     * A Boolean method that checks the corner tiles, if the opponent took a corner return true. This method is pretty much only used for the 3rd move of the bot.
     * If bot has first move, takes a corner, if the opponent took a corner, that will trigger the bot to take another corner instead of the middle and force a win
     * @return true or false depending on if the opponent took a corner
     */
    private boolean opponentTookCorner(){
        int oppositePlayer = checkPlayerStatus();
        if (game.board[0][0].getTileState() == oppositePlayer || game.board[0][2].getTileState() == oppositePlayer)
            return true;
        if (game.board[2][0].getTileState() == oppositePlayer || game.board[2][2].getTileState() == oppositePlayer)
            return true;
        else
            return false;
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
                if (game.board[i][j].isValidPlayState()) {
                    tiles[(counter++)] = (3 * i + 1 + j);
                }
            }
        }
        return tiles[rand.nextInt(counter)];
    }

    /**
     * Method to choose a random corner for the first move every time
     * @return - either 1, 3, 5, 7 a random corner
     */
    private int firstTurn(){
        int moves[] = new int [] {1,3,7,9};
        return moves[rand.nextInt(moves.length)];
    }


    /**
     * method for the bots second Turn
     * If it is the bots first move and the moveNumberCheck is 2, don't check if they picked a corner, since the bot has to take middle immediately
     * IF it is the bots second move (3rd Move or up for total moves of the game) check to see if the opponent took a corner, if they did take another random corner
     * Middle is available. if it is, take it, else take another corner
     *
     * Important to Note. moveNumberCheck method != 2 must be != 2 as for moveNumber 5, must check to see if the opponent took a corner. If they did play a corner.
     * LOTS of coding involved here. Thing is when you return a number it goes to make move and tries to play and if it is valid success, else it gets stuck in the try block
     * It never gets adjusted. So instead, had to put checks in for implementation of choosing Random corner
     * Select a random corner, if its available return it to be used for make move. If it's not, try a random corner from a list without that spot. Repeat until only 1 option left.
     * Do this for every possible option for 1,3,7,9
     * @return either a corner or middle
     */
    private int secondTurn() {
        int moves[] = new int[]{1, 3, 7, 9};
        int move, move2, move3;
        if (moveNumberCheck() != 2){
            if (opponentTookCorner()) {                         //if the opponent played a corner then take another corner
                move = moves[rand.nextInt(moves.length)];
                if (move == 1){
                    if (game.board[0][0].getTileState() == 0) {
                        return move;
                    }
                    else{
                        int moveOption2[] = new int[] {3,7,9};
                        move2 = moveOption2[rand.nextInt(moveOption2.length)];
                        if (move2 == 3){
                            if (game.board[0][2].getTileState() == 0) {
                                return move2;
                            }
                            else{
                                int moveOption3[] = new int[] {7,9};
                                move3 = moveOption3[rand.nextInt(moveOption3.length)];
                                if (move3 == 7){
                                    if (game.board[2][0].getTileState() == 0) {
                                        return move3;
                                    }
                                    else {
                                        return 9;
                                    }
                                }
                            }
                        }
                        if (move2 == 7){
                            if (game.board[2][0].getTileState() == 0) {
                                return move2;
                            }
                            else{
                                int moveOption3[] = new int[]{3,9};
                                move3 = moveOption3[rand.nextInt(moveOption3.length)];
                                if (move3 == 3){
                                    if (game.board[0][2].getTileState() == 0) {
                                        return move3;
                                    }
                                    else {
                                        return 9;
                                    }
                                }
                            }
                        }
                        if (move2 == 9){
                            if (game.board[2][2].getTileState() == 0) {
                                return move2;
                            }
                            else{
                                int moveOption3[] = new int[] {3,7};
                                move3 = moveOption3[rand.nextInt(moveOption3.length)];
                                if (move3 == 3){
                                    if (game.board[0][2].isValidPlayState()) {
                                        return move3;
                                    }
                                    else {
                                        return 7;
                                    }
                                }
                            }
                        }
                    }
                }
                if (move == 3){
                    if (game.board[0][2].isValidPlayState()) {
                        return move;
                    }
                    else{
                        int moveOption2[] = new int[] {1,7,9};
                        move2 = moveOption2[rand.nextInt(moveOption2.length)];
                        if (move2 == 1){
                            if (game.board[0][0].isValidPlayState()) {
                                return move2;
                            }
                            else{
                                int moveOption3[] = new int[] {7,9};
                                move3 = moveOption3[rand.nextInt(moveOption3.length)];
                                if (move3 == 7){
                                    if (game.board[2][0].isValidPlayState()) {
                                        return move3;
                                    }
                                    else {
                                        return 9;
                                    }
                                }
                            }
                        }
                        if (move2 == 7){
                            if (game.board[2][0].isValidPlayState()) {
                                return move2;
                            }
                            else{
                                int moveOption3[] = new int[] {1,9};
                                move3 = moveOption3[rand.nextInt(moveOption3.length)];
                                if (move3 == 1){
                                    if (game.board[0][0].isValidPlayState()) {
                                        return move3;
                                    }
                                    else {
                                        return 9;
                                    }
                                }
                            }
                        }
                        if (move2 == 9){
                            if (game.board[2][2].isValidPlayState()) {
                                return move2;
                            }
                            else{
                                int moveOption3[] = new int[] {1,7};
                                move3 = moveOption3[rand.nextInt(moveOption3.length)];
                                if (move3 == 1){
                                    if (game.board[0][0].isValidPlayState()) {
                                        return move3;
                                    }
                                    else {
                                        return 7;
                                    }
                                }
                            }
                        }
                    }
                }
                if (move == 7){
                    if (game.board[2][0].isValidPlayState()) {
                        return move;
                    }
                    else{
                        int moveOption2[] = new int[] {1, 3, 9};
                        move2 = moveOption2[rand.nextInt(moveOption2.length)];
                        if (move2 == 1){
                            if (game.board[0][0].isValidPlayState()) {
                                return move2;
                            }
                            else{
                                int moveOption3[] = new int[] {3,9};
                                move3 = moveOption3[rand.nextInt(moveOption3.length)];
                                if (move3 == 3){
                                    if (game.board[0][2].isValidPlayState()) {
                                        return move3;
                                    }
                                    else {
                                        return 9;
                                    }
                                }
                            }
                        }
                        if (move2 == 3){
                            if (game.board[0][2].isValidPlayState()) {
                                return move2;
                            }
                            else{
                                int moveOption3[] = new int[] {1,9};
                                move3 = moveOption3[rand.nextInt(moveOption3.length)];
                                if (move3 == 1){
                                    if (game.board[0][0].isValidPlayState()) {
                                        return move3;
                                    }
                                    else {
                                        return 9;
                                    }
                                }
                            }
                        }
                        if (move2 == 9){
                            if (game.board[2][2].isValidPlayState()) {
                                return move2;
                            }
                            else{
                                int moveOption3[] = new int[] {1,3};
                                move3 = moveOption3[rand.nextInt(moveOption3.length)];
                                if (move3 == 3){
                                    if (game.board[0][2].isValidPlayState()) {
                                        return move3;
                                    }
                                    else {
                                        return 1;
                                    }
                                }
                            }
                        }
                    }
                }
                else if (move == 9){
                    if (game.board[2][2].isValidPlayState())
                        return move;
                    else{
                        int moveOption2[] = new int[] {1, 3, 7};
                        move2 = moveOption2[rand.nextInt(moveOption2.length)];
                        if (move2 == 1){
                            if (game.board[0][0].getTileState() == 0) {
                                return move2;
                            }
                            else{
                                int moveOption3[] = new int[] {3, 7};
                                move3 = moveOption3[rand.nextInt(moveOption3.length)];
                                if (move3 == 3){
                                    if (game.board[0][2].getTileState() == 0) {
                                        return move3;
                                    }
                                    else {
                                        return 7;
                                    }
                                }
                            }
                        }
                        if (move2 == 3){
                            if (game.board[0][2].getTileState() == 0) {
                                return move2;
                            }
                            else{
                                int moveOption3[] = new int[] {1,7};
                                move3 = moveOption3[rand.nextInt(moveOption3.length)];
                                if (move3 == 1){
                                    if (game.board[0][0].getTileState() == 0) {
                                        return move3;
                                    }
                                    else {
                                        return 7;
                                    }
                                }
                            }
                        }
                        if (move2 == 7){
                            if (game.board[2][0].getTileState() == 0) {
                                return move2;
                            }
                            else{
                                int moveOption3[] = new int[] {1,3};
                                move3 = moveOption3[rand.nextInt(moveOption3.length)];
                                if (move3 == 1){
                                    if (game.board[0][0].getTileState() == 0) {
                                        return move3;
                                    }
                                    else {
                                        return 3;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (game.board[1][1].isValidPlayState()) {              //if the opponent didn't take a corner, check if middle is available, if it is take it. Opponent played an edge
            return 5;
        }
        else {                                                  //opponent didn't take a corner, however they took middle so take a corner
            move = moves[rand.nextInt(moves.length)];
            if (move == 1){
                if (game.board[0][0].isValidPlayState()) {
                    return move;
                }
                else{
                    int moveOption2[] = new int[] {3,7,9};
                    move2 = moveOption2[rand.nextInt(moveOption2.length)];
                    if (move2 == 3){
                        if (game.board[0][2].isValidPlayState()) {
                            return move2;
                        }
                        else{
                            int moveOption3[] = new int[] {7,9};
                            move3 = moveOption3[rand.nextInt(moveOption3.length)];
                            if (move3 == 7){
                                if (game.board[2][0].isValidPlayState()) {
                                    return move3;
                                }
                                else {
                                    return 9;
                                }
                            }
                        }
                    }
                    if (move2 == 7){
                        if (game.board[2][0].isValidPlayState()) {
                            return move2;
                        }
                        else{
                            int moveOption3[] = new int[] {3,9};
                            move3 = moveOption3[rand.nextInt(moveOption3.length)];
                            if (move3 == 3){
                                if (game.board[0][2].isValidPlayState()) {
                                    return move3;
                                }
                                else {
                                    return 9;
                                }
                            }
                        }
                    }
                    if (move2 == 9){
                        if (game.board[2][2].isValidPlayState()) {
                            return move2;
                        }
                        else{
                            int moveOption3[] = new int[] {3,7};
                            move3 = moveOption3[rand.nextInt(moveOption3.length)];
                            if (move3 == 3){
                                if (game.board[0][2].isValidPlayState()) {
                                    return move3;
                                }
                                else {
                                    return 7;
                                }
                            }
                        }
                    }
                }
            }
            if (move == 3){
                if (game.board[0][2].isValidPlayState()) {
                    return move;
                }
                else{
                    int moveOption2[] = new int[] {1,7,9};
                    move2 = moveOption2[rand.nextInt(moveOption2.length)];
                    if (move2 == 1){
                        if (game.board[0][0].isValidPlayState()) {
                            return move2;
                        }
                        else{
                            int moveOption3[] = new int[] {7,9};
                            move3 = moveOption3[rand.nextInt(moveOption3.length)];
                            if (move3 == 7){
                                if (game.board[2][0].isValidPlayState()) {
                                    return move3;
                                }
                                else {
                                    return 9;
                                }
                            }
                        }
                    }
                    if (move2 == 7){
                        if (game.board[2][0].isValidPlayState()) {
                            return move2;
                        }
                        else{
                            int moveOption3[] = new int[] {1,9};
                            move3 = moveOption3[rand.nextInt(moveOption3.length)];
                            if (move3 == 1){
                                if (game.board[0][0].isValidPlayState()) {
                                    return move3;
                                }
                                else {
                                    return 9;
                                }
                            }
                        }
                    }
                    if (move2 == 9){
                        if (game.board[2][2].isValidPlayState()) {
                            return move2;
                        }
                        else{
                            int moveOption3[] = new int[] {1,7};
                            move3 = moveOption3[rand.nextInt(moveOption3.length)];
                            if (move3 == 1){
                                if (game.board[0][0].isValidPlayState()) {
                                    return move3;
                                }
                                else {
                                    return 7;
                                }
                            }
                        }
                    }
                }
            }
            if (move == 7){
                if (game.board[2][0].isValidPlayState()) {
                    return move;
                }
                else{
                    int moveOption2[] = new int[] {1, 3, 9};
                    move2 = moveOption2[rand.nextInt(moveOption2.length)];
                    if (move2 == 1){
                        if (game.board[0][0].isValidPlayState()) {
                            return move2;
                        }
                        else{
                            int moveOption3[] = new int[] {3,9};
                            move3 = moveOption3[rand.nextInt(moveOption3.length)];
                            if (move3 == 3){
                                if (game.board[0][2].isValidPlayState()) {
                                    return move3;
                                }
                                else {
                                    return 9;
                                }
                            }
                        }
                    }
                    if (move2 == 3){
                        if (game.board[0][2].isValidPlayState()) {
                            return move2;
                        }
                        else{
                            int moveOption3[] = new int[] {1,9};
                            move3 = moveOption3[rand.nextInt(moveOption3.length)];
                            if (move3 == 1){
                                if (game.board[0][0].isValidPlayState()) {
                                    return move3;
                                }
                                else {
                                    return 9;
                                }
                            }
                        }
                    }
                    if (move2 == 9){
                        if (game.board[2][2].isValidPlayState()) {
                            return move2;
                        }
                        else{
                            int moveOption3[] = new int[] {1,3};
                            move3 = moveOption3[rand.nextInt(moveOption3.length)];
                            if (move3 == 3){
                                if (game.board[0][2].isValidPlayState()) {
                                    return move3;
                                }
                                else {
                                    return 1;
                                }
                            }
                        }
                    }
                }
            }
            else{
                if (game.board[2][2].isValidPlayState()) {
                    return move;
                }
                else{
                    int moveOption2[] = new int[] {1, 3,7};
                    move2 = moveOption2[rand.nextInt(moveOption2.length)];
                    if (move2 == 1){
                        if (game.board[0][0].isValidPlayState()) {
                            return move2;
                        }
                        else{
                            int moveOption3[] = new int[] {3, 7};
                            move3 = moveOption3[rand.nextInt(moveOption3.length)];
                            if (move3 == 3){
                                if (game.board[0][2].isValidPlayState()) {
                                    return move3;
                                }
                                else {
                                    return 7;
                                }
                            }
                        }
                    }
                    if (move2 == 3){
                        if (game.board[0][2].isValidPlayState()) {
                            return move2;
                        }
                        else{
                            int moveOption3[] = new int[] {1,7};
                            move3 = moveOption3[rand.nextInt(moveOption3.length)];
                            if (move3 == 1){
                                if (game.board[0][0].isValidPlayState()) {
                                    return move3;
                                }
                                else {
                                    return 7;
                                }
                            }
                        }
                    }
                    if (move2 == 7){
                        if (game.board[2][0].isValidPlayState()) {
                            return move2;
                        }
                        else{
                            int moveOption3[] = new int[] {1,3};
                            move3 = moveOption3[rand.nextInt(moveOption3.length)];
                            if (move3 == 1){
                                if (game.board[0][0].isValidPlayState()) {
                                    return move3;
                                }
                                else {
                                    return 3;
                                }
                            }
                        }
                    }
                }
            }
        }
        return firstTurn();
    }



    /**
     * Method for the third turn. Based on the Bot's plays, if it reaches the need to check for a case 5, the bot will only need to play in the corner and then the next move is a win
     * @return one of the free corners available
     */
    private int thirdTurn(){
        if (game.board[0][0].isValidPlayState()) {
            return 1;
        }
        if (game.board[0][2].isValidPlayState()) {
            return 3;
        }
        if (game.board[2][0].isValidPlayState()) {
            return 7;
        }
        else {
            return 9;
        }
    }

    /**
     * Method specifically only for turn 4 of the game when the bot has second move. Most the time the bot will be defending already with isLoss.
     * However, there is a certain sequence of moves that don't allow for just a random move to be made for move 4 if the bot is not winning or defending
     * I found that if First player plays a sequence of positions (1,6), (2,7), (3,8), (4,9) and the bot takes middle every time for Bot's move (2nd of Game)
     *      there is 2 locations for each ordered pair that can cause a loss, and also an optimal choice that leaves a slight random chance of winning instead
     * EX. If Player 1 takes 1, Bot takes 5, player 1 takes 6 (1,6). The bot sense loss method see's not immediate threat.
     *      If the bot chose (4 || 7) this would allow player 1 to play slot 3 with a double win set up not blockable. (wins in row 1 using slot 2, or column 3 using slot 9)
     *
     *
     * (Potential Ordered pairs) --> (corresponding Loss Moves) --> (next move to cause double win set up)
     * These ordered pairs coincide with edge corner
     * (1,6) --> Losses (4,7) --> 3 : 2
     * (2,7) --> Losses (8,9) --> 1 : 4
     * (3,8) --> Losses (1,2) --> 9 : 6
     * (4,9) --> Losses (3,6) --> 7 : 8
     * and there Permutations
     *
     * Second Set of Ordered Pairs that can cause a loss. Edge and edge. If you don't take the corner in between, you can lose
     * (4,8) --> Play 7, 1 or 9
     * (2,4) --> Play 1, 3 or 7
     * (2,6) --> Play 1, 3 or 9
     * (6,8) --> Play 3, 7 or 9
     *
     * Last Set of Ordered Pairs, include the double corner move, stop it by playing an edge
     *(1,9) --> Play edge not corner
     *(3,7) --> Play edge not corner
     * @return Optimal answer or SecondTurn
     */
    private int turnFour(){
        int move;
        /**
         * These are All the Edge Corner Permutations

         * Case of (1,6)
         */
        if (game.board[0][0].getTileState() == checkPlayerStatus() && game.board[1][2].getTileState() == checkPlayerStatus()) {
            int moves[] = new int[]{2, 3, 9};
            move = moves[rand.nextInt(moves.length)];
            if (move == 2){
                if (game.board[0][1].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {3,9};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 3){
                        if (game.board[0][2].isValidPlayState())
                            return move2;
                        else
                            return 9;
                    }
                }
            }
            if (move == 3){
                if (game.board[0][2].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {2,9};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 2){
                        if (game.board[0][1].isValidPlayState())
                            return move2;
                        else
                            return 9;
                    }
                }
            }
            if (move == 9){
                if (game.board[2][2].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {2,3};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 3){
                        if (game.board[0][2].isValidPlayState())
                            return move2;
                        else
                            return 2;
                    }
                }
            }
        }
        /**
         * Case of (1,8)
         */
        if (game.board[0][0].getTileState() == checkPlayerStatus() && game.board[2][1].getTileState() == checkPlayerStatus()){
            int moves[] = new int[]{4,7,9};
            move = moves[rand.nextInt(moves.length)];
            if (move == 4){
                if (game.board[1][0].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {7,9};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 7){
                        if (game.board[2][0].isValidPlayState())
                            return move2;
                        else
                            return 9;
                    }
                }
            }
            if (move == 7){
                if (game.board[2][0].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {4,9};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 4){
                        if (game.board[1][0].isValidPlayState())
                            return move2;
                        else
                            return 9;
                    }
                }
            }
            if (move == 9){
                if (game.board[2][2].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {4,7};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 4){
                        if (game.board[1][0].isValidPlayState())
                            return move2;
                        else
                            return 7;
                    }
                }
            }
        }
        /**
         * Case of (2,7)
         */
        if (game.board[0][1].getTileState() == checkPlayerStatus() && game.board[2][0].getTileState() == checkPlayerStatus()) { //2,7
            int moves[] = new int[]{1, 3, 4};
            move = moves[rand.nextInt(moves.length)];
            if (move == 1){
                if (game.board[0][0].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {3,4};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 3){
                        if (game.board[0][2].isValidPlayState())
                            return move2;
                        else
                            return 4;
                    }
                }
            }
            if (move == 3){
                if (game.board[0][2].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {1,4};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 1){
                        if (game.board[0][0].isValidPlayState())
                            return move2;
                        else
                            return 4;
                    }
                }
            }
            if (move == 4){
                if (game.board[1][0].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {1,3};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 1){
                        if (game.board[0][0].isValidPlayState())
                            return move2;
                        else
                            return 3;
                    }
                }
            }
        }
        /**
         * Case of (2,9)
         */
        if (game.board[0][1].getTileState() == checkPlayerStatus() && game.board[2][2].getTileState() == checkPlayerStatus()){
            int moves[] = new int[]{1,3,6};
            move = moves[rand.nextInt(moves.length)];
            if (move == 1){
                if (game.board[0][0].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {3,6};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 3){
                        if (game.board[0][2].isValidPlayState())
                            return move2;
                        else
                            return 6;
                    }
                }
            }
            if (move == 3){
                if (game.board[0][2].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {1,6};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 1){
                        if (game.board[0][0].isValidPlayState())
                            return move2;
                        else
                            return 6;
                    }
                }
            }
            if (move == 6){
                if (game.board[1][2].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {1,3};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 1){
                        if (game.board[0][0].isValidPlayState())
                            return move2;
                        else
                            return 3;
                    }
                }
            }
        }
        /**
         * Case of (3,8)
         */
        if (game.board[0][2].getTileState() == checkPlayerStatus() && game.board[2][1].getTileState() == checkPlayerStatus()) { //3,8
            int moves[] = new int[]{6,7,9};
            move = moves[rand.nextInt(moves.length)];
            if (move == 6){
                if (game.board[1][2].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {7,9};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 7){
                        if (game.board[2][0].isValidPlayState())
                            return move2;
                        else
                            return 9;
                    }
                }
            }
            if (move == 7){
                if (game.board[2][0].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {6,9};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 6){
                        if (game.board[1][2].isValidPlayState())
                            return move2;
                        else
                            return 9;
                    }
                }
            }
            if (move == 9){
                if (game.board[2][2].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {6,7};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 6){
                        if (game.board[1][2].isValidPlayState())
                            return move2;
                        else
                            return 7;
                    }
                }
            }
        }
        /**
         * Case of (3,4)
         */
        if (game.board[0][2].getTileState() == checkPlayerStatus() && game.board[1][0].getTileState() == checkPlayerStatus()) { //3,4
            int moves[] = new int[]{1,2,7};
            move = moves[rand.nextInt(moves.length)];
            if (move == 1){
                if (game.board[0][0].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {2,7};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 2){
                        if (game.board[0][1].isValidPlayState())
                            return move2;
                        else
                            return 7;
                    }
                }
            }
            if (move == 2){
                if (game.board[0][1].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {1,7};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 1){
                        if (game.board[0][0].isValidPlayState())
                            return move2;
                        else
                            return 7;
                    }
                }
            }
            if (move == 7){
                if (game.board[2][0].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {1,2};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 1){
                        if (game.board[0][0].isValidPlayState())
                            return move2;
                        else
                            return 2;
                    }
                }
            }
        }
        /**
         * Case of (4,9)
         */
        if (game.board[1][0].getTileState() == checkPlayerStatus() && game.board[2][2].getTileState() == checkPlayerStatus()) {
            int moves[] = new int[]{1,7,8};
            move = moves[rand.nextInt(moves.length)];
            if (move == 1){
                if (game.board[0][0].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {7,8};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 7){
                        if (game.board[2][0].isValidPlayState())
                            return move2;
                        else
                            return 8;
                    }
                }
            }
            if (move == 7){
                if (game.board[2][0].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {1,8};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 1){
                        if (game.board[0][0].isValidPlayState())
                            return move2;
                        else
                            return 8;
                    }
                }
            }
            if (move == 8){
                if (game.board[2][1].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {1,7};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 1){
                        if (game.board[0][0].isValidPlayState())
                            return move2;
                        else
                            return 7;
                    }
                }
            }
        }

        /**
         * Case of (6,7)
         */
        if (game.board[1][2].getTileState() == checkPlayerStatus() && game.board[2][0].getTileState() == checkPlayerStatus()) {
            int moves[] = new int[]{3,8,9};
            move = moves[rand.nextInt(moves.length)];
            if (move == 3){
                if (game.board[0][2].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {8,9};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 8){
                        if (game.board[2][1].isValidPlayState())
                            return move2;
                        else
                            return 9;
                    }
                }
            }
            if (move == 8){
                if (game.board[2][1].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {3,9};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 3){
                        if (game.board[0][2].isValidPlayState())
                            return move2;
                        else
                            return 9;
                    }
                }
            }
            if (move == 9){
                if (game.board[2][2].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {3,8};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 8){
                        if (game.board[2][1].isValidPlayState())
                            return move2;
                        else
                            return 3;
                    }
                }
            }
        }

        /**
         * These are the Edge-Edge Permutations
         */

        /**
         * Case of (4,8)
         */
        if (game.board[1][0].getTileState() == checkPlayerStatus() && game.board[2][1].getTileState() == checkPlayerStatus()) {
            int moves[] = new int[]{1, 7, 9};
            move = moves[rand.nextInt(moves.length)];

            if (move == 1){
                if (game.board[0][0].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {7,9};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 7){
                        if (game.board[2][0].isValidPlayState())
                            return move2;
                        else
                            return 9;
                    }
                }
            }
            if (move == 7){
                if (game.board[2][0].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {1,9};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 1){
                        if (game.board[0][0].isValidPlayState())
                            return move2;
                        else
                            return 9;
                    }
                }
            }
            if (move == 9){
                if (game.board[2][2].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {1,7};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 1){
                        if (game.board[0][0].isValidPlayState())
                            return move2;
                        else
                            return 7;
                    }
                }
            }
        }
        /**
         * Case of (2,4)
         */
        if (game.board[0][1].getTileState() == checkPlayerStatus() && game.board[1][0].getTileState() == checkPlayerStatus()){
            int moves[] = new int[]{1, 3, 7};
            move = moves[rand.nextInt(moves.length)];
            if (move == 1){
                if (game.board[0][0].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {3,7};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 3){
                        if (game.board[0][2].isValidPlayState())
                            return move2;
                        else
                            return 7;
                    }
                }
            }
            if (move == 3){
                if (game.board[0][2].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {1,7};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 1){
                        if (game.board[0][0].isValidPlayState())
                            return move2;
                        else
                            return 7;
                    }
                }
            }
            if (move == 7){
                if (game.board[2][0].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {1,3};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 1){
                        if (game.board[0][0].isValidPlayState())
                            return move2;
                        else
                            return 3;
                    }
                }
            }
        }
        /**
         * Case of (2,6)
         */
        if (game.board[0][1].getTileState() == checkPlayerStatus() && game.board[1][2].getTileState() == checkPlayerStatus()) {//2,6
            int moves[] = new int[]{1, 3, 9};
            move = moves[rand.nextInt(moves.length)];
            if (move == 1){
                if (game.board[0][0].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {3,9};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 3){
                        if (game.board[0][2].isValidPlayState())
                            return move2;
                        else
                            return 9;
                    }
                }
            }
            if (move == 3){
                if (game.board[0][2].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {1,9};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 1){
                        if (game.board[0][0].isValidPlayState())
                            return move2;
                        else
                            return 9;
                    }
                }
            }
            if (move == 9){
                if (game.board[2][2].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {1,3};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 1){
                        if (game.board[0][0].isValidPlayState())
                            return move2;
                        else
                            return 3;
                    }
                }
            }
        }
        /**
         * Case of (6,8)
         */
        if (game.board[1][2].getTileState() == checkPlayerStatus() && game.board[2][1].getTileState() == checkPlayerStatus()) { //6,8
            int moves[] = new int[]{3, 7, 9};
            move = moves[rand.nextInt(moves.length)];
            if (move == 3){
                if (game.board[0][2].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {7,9};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 7){
                        if (game.board[2][0].isValidPlayState())
                            return move2;
                        else
                            return 9;
                    }
                }
            }
            if (move == 7){
                if (game.board[2][0].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {3,9};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 3){
                        if (game.board[0][2].isValidPlayState())
                            return move2;
                        else
                            return 9;
                    }
                }
            }
            if (move == 9){
                if (game.board[2][2].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {3,7};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 3){
                        if (game.board[0][2].isValidPlayState())
                            return move2;
                        else
                            return 7;
                    }
                }
            }
        }

        /**
         * This is the cross corner permuation
         */

        /**
         * Case of (1,9)
         */
        if (game.board[0][0].getTileState() == checkPlayerStatus() && game.board[2][2].getTileState() == checkPlayerStatus()){
            int moves[] = new int[] {2,4,6,8};
            move = moves[rand.nextInt(moves.length)];
            if (move == 2){
                if (game.board[0][1].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {4,6,8};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 4){
                        if (game.board[1][0].isValidPlayState())
                            return move2;
                        else {
                            int movesOption3[] = new int[] {6,8};
                            int move3 = movesOption3[rand.nextInt(movesOption3.length)];
                            if (move3 == 6){
                                if (game.board[1][2].isValidPlayState())
                                    return 6;
                                else{
                                    return 8;
                                }
                            }
                        }
                    }
                }
            }
            if (move == 4){
                if (game.board[1][0].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {2,6,8};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 2){
                        if (game.board[0][1].isValidPlayState())
                            return move2;
                        else {
                            int movesOption3[] = new int[] {6,8};
                            int move3 = movesOption3[rand.nextInt(movesOption3.length)];
                            if (move3 == 6){
                                if (game.board[1][2].isValidPlayState())
                                    return move3;
                                else{
                                    return 8;
                                }
                            }
                        }
                    }
                }
            }
            if (move == 6){
                if (game.board[1][2].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {2,4,8};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 2){
                        if (game.board[0][1].isValidPlayState())
                            return move2;
                        else {
                            int movesOption3[] = new int[] {4,8};
                            int move3 = movesOption3[rand.nextInt(movesOption3.length)];
                            if (move3 == 4){
                                if (game.board[1][0].isValidPlayState())
                                    return move3;
                                else{
                                    return 8;
                                }
                            }
                        }
                    }
                }
            }
            if (move == 8){
                if (game.board[2][1].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {2,4,6};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 2){
                        if (game.board[0][1].isValidPlayState())
                            return move2;
                        else {
                            int movesOption3[] = new int[] {4,6};
                            int move3 = movesOption3[rand.nextInt(movesOption3.length)];
                            if (move3 == 4){
                                if (game.board[1][0].isValidPlayState())
                                    return move3;
                                else{
                                    return 6;
                                }
                            }
                        }
                    }
                }
            }
        }
        /**
         * Case of (3,7)
         */
        if (game.board[0][2].getTileState() == checkPlayerStatus() && game.board[2][0].getTileState() == checkPlayerStatus()) {
            int moves[] = new int[] {2,4,6,8};
            move = moves[rand.nextInt(moves.length)];
            if (move == 2){
                if (game.board[0][1].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {4,6,8};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 4){
                        if (game.board[1][0].isValidPlayState())
                            return move2;
                        else {
                            int movesOption3[] = new int[] {6,8};
                            int move3 = movesOption3[rand.nextInt(movesOption3.length)];
                            if (move3 == 6){
                                if (game.board[1][2].isValidPlayState())
                                    return 6;
                                else{
                                    return 8;
                                }
                            }
                        }
                    }
                }
            }
            if (move == 4){
                if (game.board[1][0].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {2,6,8};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 2){
                        if (game.board[0][1].isValidPlayState())
                            return move2;
                        else {
                            int movesOption3[] = new int[] {6,8};
                            int move3 = movesOption3[rand.nextInt(movesOption3.length)];
                            if (move3 == 6){
                                if (game.board[1][2].isValidPlayState())
                                    return move3;
                                else{
                                    return 8;
                                }
                            }
                        }
                    }
                }
            }
            if (move == 6){
                if (game.board[1][2].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {2,4,8};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 2){
                        if (game.board[0][1].isValidPlayState())
                            return move2;
                        else {
                            int movesOption3[] = new int[] {4,8};
                            int move3 = movesOption3[rand.nextInt(movesOption3.length)];
                            if (move3 == 4){
                                if (game.board[1][0].isValidPlayState())
                                    return move3;
                                else{
                                    return 8;
                                }
                            }
                        }
                    }
                }
            }
            if (move == 8){
                if (game.board[2][1].isValidPlayState())
                    return move;
                else{
                    int movesOption2[] = new int[] {2,4,6};
                    int move2 = movesOption2[rand.nextInt(movesOption2.length)];
                    if (move2 == 2){
                        if (game.board[0][1].isValidPlayState())
                            return move2;
                        else {
                            int movesOption3[] = new int[] {4,6};
                            int move3 = movesOption3[rand.nextInt(movesOption3.length)];
                            if (move3 == 4){
                                if (game.board[1][0].isValidPlayState())
                                    return move3;
                                else{
                                    return 6;
                                }
                            }
                        }
                    }
                }
            }
        }
        return secondTurn();
    }

    /**
     * Turn 6 will check for the Edge-Edge Locations and if there is that option to block it, else refer to turn 4
     */
    private int turnSix(){
        /**
         * Case of (4,8)
         */
        if (game.board[1][0].getTileState() == checkPlayerStatus() && game.board[2][1].getTileState() == checkPlayerStatus()) {
            if (game.board[2][0].isValidPlayState())
                return 7;
            else {
                int moves[] = new int[]{1, 9};
                int move = moves[rand.nextInt(moves.length)];
                if (move == 1) {
                    if (game.board[0][0].isValidPlayState()) {
                        return move;
                    } else {
                        return 9;
                    }
                }
            }
        }
        /**
         * Case of (2,4)
         */
        if (game.board[0][1].getTileState() == checkPlayerStatus() && game.board[1][0].getTileState() == checkPlayerStatus()){
            if (game.board[0][0].isValidPlayState())
                return 1;
            else {
                int moves[] = new int[]{3, 7};
                int move = moves[rand.nextInt(moves.length)];
                if (move == 3) {
                    if (game.board[0][2].isValidPlayState()) {
                        return move;
                    } else {
                        return 7;
                    }
                }
            }
        }
        /**
         * Case of (2,6)
         */
        if (game.board[0][1].getTileState() == checkPlayerStatus() && game.board[1][2].getTileState() == checkPlayerStatus()) {
            if (game.board[0][2].isValidPlayState())
                return 3;
            else {
                int moves[] = new int[]{1, 9};
                int move = moves[rand.nextInt(moves.length)];
                if (move == 1){
                    if (game.board[0][0].isValidPlayState()){
                        return move;
                    }
                    else{
                        return 9;
                    }
                }
            }
        }
        /**
         * Case of (6,8)
         */
        if (game.board[1][2].getTileState() == checkPlayerStatus() && game.board[2][1].getTileState() == checkPlayerStatus()) {
            if (game.board[2][2].isValidPlayState())
                return 9;
            else{
                int moves[] = new int[]{3, 7};
                int move = moves[rand.nextInt(moves.length)];
                if (move == 3){
                    if (game.board[0][2].isValidPlayState()){
                        return move;
                    }
                    else{
                        return 7;
                    }
                }
            }
        }
        return turnFour();
    }


    /**
     * implementing the required make move method
     * This method is for the bot to make a play. The nice thing about TicTacToe is that turns rotate so therefore the bot will only play on Even number turns or Odd number turns
     * This thought process is used for the switch statement. This bot is programmed to win consistently or tie, should never lose
     * Every Turn the bot will first check if the bot can win. If so take that move. If not, then check if the bot will lose.
     * If there is a move that will lose, make sure to block that move. If neither of those are found, use the moveNumberCheck() method to let the bot know what turn it is.
     * Then implement the switch statement, depending on the what move number it is will determine how the bot plays. However each time it still checks win then loss first.
     *
     * The bot will only play on Odd Moves if he gets first play. IF the bot is first play, he will play to win. If the bot gets a corner then middle he wins.
     * If the bot has second turn, the main objective is to Tie the game but if there is a win available take it. This is done by immediately taking middle if available.
     *      If the middle is taken, take a corner and then defend all game by checking for a loss.
     * @param board - the game board so that the bot knows what moves are available
     */
    public void play(GameBoard board){
        int move;
        boolean success;
        int moveCount;

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

        //moveCount is set to what current move is it which determines if the bot is first player or not see javadoc above
        moveCount = moveNumberCheck();

        /**
         * Case 1 - Run the execution of FirstTurn which will take a random corner
         * Case 2 and Case 3 are the same, this is because if the bot has first turn, the bot takes a corner then turn 3 bot wants middle if possible else another corner
         * If the bot has second turn, the bot is immediately trying to take the middle spot and if that is not available and the other player took middle, take a corner
         * Case 4 - Run turnFour which has a BUNCH of checks to try and stop potential double wins
         * case 5 - thirdTurn
         *
         */

        switch (moveCount){
            case 1:
                move = firstTurn();
                break;
            case 2:
                move = secondTurn();
                break;
            case 3:
                move = secondTurn();
                break;
            case 4:
                move = turnFour();
                break;
            case 5:
                if (opponentTookCorner()) {
                    move = thirdTurn();
                }
                else {
                    move = secondTurn();
                }
                break;
            case 6:
                move = turnSix();
                break;
            default:
                move = randomLegalMove();
                break;
        }

        do {
            success = board.makeMove(move, playerType); //Make move will return a true or false value if successful or not and if it is successful will set the move
        }while(!success);
        System.out.println("I will play at tile " + move + ".\n");
        return;
    }





}
