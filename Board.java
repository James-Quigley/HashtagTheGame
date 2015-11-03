/**
* Class: Board
*
* Represents the game board of #TheGame
*/
public class Board{
	
	private Field fields[][] = new Field[3][3];	//Represents the fields of play
	private int fieldInPlay; //Reperesents the current field of play
	private int p1Score; //Player one's score
	private int p2Score; //Player two's score
	private int winnableFields; //Number of winnable fields left
	private boolean activePlayer; //Active player: false = player 1 | true = player 2
	
	/**
	* Default constructor for Board
	*/
	public Board(){
		fieldInPlay = 0;
		p1Score = 0;
		p2Score = 0;
		winnableFields = 9;
		activePlayer = false;
	}
	
	/**
	* Increments p1Score by one
	*/
	public void incP1Score(){
		p1Score++;
	}
	
	/**
	* Getter for p1Score
	*/
	public int getP1Score(){
		return p1Score;
	}
	
	/**
	* Increments p2Score
	*/
	public void incP2Score(){
		p2Score++;
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