package ser215.hashtagthegameandroid;

/**
 * Class: ser215.hashtagthegameandroid.Board
 *
 * Represents the game board of #TheGame
 */
public class Board{

    private Field[] fields;	//Represents the fields of play
    private int fieldInPlay; //Reperesents the current field of play
    private int p1Score; //Player one's score
    private int p2Score; //Player two's score
    private int winnableFields; //Number of winnable fields left
    private int lastIndex; //Last tile played on
    private int activePlayer; //Active player: 1 = player 1 | 2 = player 2

    /**
     * Default constructor for ser215.hashtagthegameandroid.Board
     */
    public Board(){
        fieldInPlay = -1;
        p1Score = 0;
        p2Score = 0;
        winnableFields = 9;
        lastIndex = -1;
        activePlayer = 1;
        fields = new Field[9];

        for(int i = 0; i < 9; i++){
            fields[i] = new Field();
        }
    }
    //returns 0 if the play couldn't be made, 1 if the play was made and the field has transitioned to a new fieldinplay. returns 2 if the fieldinplay has transitioned to -1. returns 3 if game is won
    public int makePlayAt(int field, int tile){

        if(field != fieldInPlay || fields[field].getTile(tile).getOwner() != 0){//play not open
            return 0;
        }
        fields[field].getTile(tile).setOwner(getActivePlayer());
        if(fields[field].checkIfWon()){//if this play wins a field
            incScore(getActivePlayer());
        }
        if(fields[field].isFull() && fields[field].getOwner() == 0){//catsgame
            decWinnableFields();
        }

        if(checkIfWon()) return 3;//if the game is over, stop the game

        lastIndex = field*9 + tile;
        changeActivePlayer();//change player
        if(fields[tile].getOwner() != 0){//can't transition to a new field in play
            fieldInPlay = -1;
            return 2;
        }
        fieldInPlay = tile;//can transition to new field
        return 1;
    }
    //Decrements winnableFields. (Use for catsGame)
    private void decWinnableFields(){
        winnableFields--;
    }
    private int getWinnableFields() {
        return winnableFields;
    }

    /**
     * Increments pScore by one
     * Decrements winnableFields by one
     */
    private void incScore(int player){
        if (player == 1){
            p1Score++;
        }
        else{
            p2Score++;
        }
        winnableFields--;
    }

    /**
     * Getter for p1Score
     */
    private int getP1Score(){
        return p1Score;
    }

    /**
     * Getter for p2Score
     */
    private int getP2Score(){
        return p2Score;
    }

    /**
     * If player one is active player, switches to player 2
     * If player two is active player, switches to player 1
     */
    public void changeActivePlayer(){
        activePlayer = (activePlayer==1)?2:1;
    }

    /**
     * Getter for active player
     */
    public int getActivePlayer(){
        return activePlayer;
    }


    /**
     * Returns the field at the given index
     */
    public Field getField(int i){
        return fields[i];
    }
	
	/*
	public ser215.hashtagthegameandroid.Tile getTile(int i){
		return fields[i / 9].getTile(i % 9);
	}
	*/

    /**
     * Returns the tile at the give indices
     */
    public Tile getTile(int i, int j){
        return fields[i].getTile(j);
    }

    /**
     * Setter for fieldInPlay
     */
    public void setFieldInPlay(int fieldInPlay){
        this.fieldInPlay = fieldInPlay;
    }

    /**
     * Getter for fieldInPlay
     */
    public int getFieldInPlay(){
        return fieldInPlay;
    }

    public void setLastIndex(int i) {
        lastIndex = i;
    }

    public int getLastIndex() {
        return lastIndex;
    }
    /**
     * Returns whether or not the game has been one by some player
     */
    public boolean checkIfWon(){
        boolean won = false;

        if((p1Score > p2Score) && ((p2Score + winnableFields) < p1Score)){ //Player 1 wins
            //System.out.println("Player 1 Wins");
            won = true;
        }else if((p2Score > p1Score) && ((p1Score + winnableFields) < p2Score)){ //Player 2 wins
            //System.out.println("Player 2 Wins");
            won = true;
        }

        return won;
    }

    /**
     * Resets all variables for a new game situation
     */
    public void reset(){
        fieldInPlay = -1;
        p1Score = 0;
        p2Score = 0;
        winnableFields = 9;
        lastIndex = -1;
        activePlayer = 1;
        for (Field field: fields){
            field.reset();
        }
    }
}