/**
* Class: Board
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
	private boolean activePlayer; //Active player: false = player 1 | true = player 2
	
	/**
	* Default constructor for Board
	*/
	public Board(){
		fieldInPlay = -1;
		p1Score = 0;
		p2Score = 0;
		winnableFields = 9;
		lastIndex = -1;
		activePlayer = false;
		fields = new Field[9];
		
		for(int i = 0; i < 9; i++){
			fields[i] = new Field();
		}
	}

	//Decrements winnableFields. (Use for catsGame)
	public void decWinnableFields(){
		winnableFields--;
	}
	public int getWinnableFields() {
		return winnableFields;
	}

	/**
	* Increments pScore by one
	* Decrements winnableFields by one
	*/
	public void incScore(boolean player){
		if (!player){
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
	public int getP1Score(){
		return p1Score;
	}
	
	/**
	* Getter for p2Score
	*/
	public int getP2Score(){
		return p2Score;
	}
	
	/**
	* If player one is active player, switches to player 2
	* If player two is active player, switches to player 1
	*/
	public void changeActivePlayer(){
		if(activePlayer == false){
			activePlayer = true;
		}else{
			activePlayer = false;
		}
	}
	
	/**
	* Getter for active player
	*/
	public boolean getActivePlayer(){
		return activePlayer;
	}
	
	
	/**
	* Returns the field at the given index
	*/
	public Field getField(int i){
		return fields[i];
	}
	
	/*
	public Tile getTile(int i){
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
			System.out.println("Player 1 Wins");
			won = true;
		}else if((p2Score > p1Score) && ((p1Score + winnableFields) < p2Score)){ //Player 2 wins
			System.out.println("Player 2 Wins");
			won = true;
		}
		
		return won;
	}
	
	/**
	* Resets all variables for a new game situation
	*/
	public void reset(){
		activePlayer = false;
		fieldInPlay = -1;
		p1Score = 0;
		p2Score = 0;
		winnableFields = 9;
		lastIndex = -1;
		activePlayer = false;
		for (Field field: fields){
			field.reset();
		}
	}
}