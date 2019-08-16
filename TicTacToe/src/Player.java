public abstract class Player {

    public int playerType;
    public GameBoard game;
    public String name;

    /**
     * Abstract method that MUST be implemented in any player created as a player must be able to make a move
     * @param board - player must have access to the game board to make a play on it
     */
    public abstract void play(GameBoard board);


    /**
     * Overloaded Constructor for player as the only option as the player absolutely needs this informatino
     * @param playerName - players name that is set
     * @param game1 - access to the gameboard so the player knows what moves to do and make
     * @param thePlayerType - This is a variable to determine if the player is an X or O that is taken from the global interface later
     */
    public Player(String playerName, GameBoard game1, int thePlayerType){
        name = playerName;
        game = game1;
        playerType = thePlayerType;
    }


    /**
     * To String method
     * @return the name of a player when called
     */
    @Override
    public String toString() {
        return name;
    }
}

