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
	private boolean activePlayer; //Active player: false = player 1 | true = player 2
	private boolean firstTurn; //Boolean representing if the turn is the first move or not
	
	/**
	* Default constructor for Board
	*/
	public Board(){
	    firstTurn = true;
		fieldInPlay = -1;
		p1Score = 0;
		p2Score = 0;
		winnableFields = 9;
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
	
	/**
	* Increments pScore by one
	* Decrements winnableFields by one
	*/
	public void incScore(int player){
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
	public int getP1Score(){
		return p1Score;
	}
	
	/**
	* Getter for p2Score
	*/
	public int getp2Score(){
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
	* Returns whether or not the current turn is the first turn
	*/
	public boolean isFirstTurn(){
		return firstTurn;
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
	* Returns index of the last tile (0 - 80)
	*/	
	public int getLastTile(){
		return 0;
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
	
	/**
	* Returns whether or not the game has been one by some player
	*/
	public boolean checkIfWon(){
		boolean won = false;
		
		if((p1Score > p2Score) && ((p2Score + winnableFields) < p1Score)){ //Player 1 wins
			won = true;
		}else if((p2Score > p1Score) && ((p1Score + winnableFields) < p2Score)){ //Player 2 wins
			won = true;
		}
		
		return won;
	}
	
	/**
	* Resets all variables for a new game situation
	*/
	public void reset(){
		fieldInPlay = 0;
		p1Score = 0;
		p2Score = 0;
		winnableFields = 9;
		activePlayer = false;
	}
}