/**
 * Class board Tile used to create each individual tile object in the GameBoard
 * Still need to Replace EMPTY with an underscore potentially or maybe just space even
 */

public class BoardTile implements global{

    private int tileState;

    /**
     * default constructors sets the tileState to 0
     */
    public BoardTile(){
        tileState = 0;
    }


    //get method for state for the tile
    public int getTileState() {
        return tileState;
    }

    /**
     * Method to set state
     * @param playerType takes in a player that is first checked if isValidState, then check to see if it is and X or O and if it is set it
     */
    public boolean setTileState(int playerType) {
        if (isValidPlayState() && playerType == 1 || playerType == -1 || playerType == 0){
            tileState = playerType;
            return true;
        }
        else
            return false;
    }

    /**
     * Method to see if the actual tile is a valid state. If the slot is not empty it can't be played
     * @return true or false depending on if it is valid
     */
    public boolean isValidPlayState(){
        if (getTileState() == 0)
            return true;
        else
            return false;
    }


    /**
     * ToString method to return what is in the tile. this will be used to display the board display an X O or a space signifying empty
     * @return
     */
    @Override
    public String toString() {
        if (getTileState() == 1)
            return "X";
        if (getTileState() == -1)
            return "O";
        else
            return " ";
    }

}
