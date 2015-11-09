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
	private int turn;
	public int getTurn(){return turn;}
	public Field getField(int i){return fields[i];}
	public Tile getTile(int i){return fields[i/9].getTile(i%9);}
	public Tile getTile(int i, int j){return fields[i]
	    .getTile(j);}
	public int getLastTile(){return 0;}//returns index (0-80) of last tile
	/**
	* Default constructor for Board
	*/
	public Board(){
	    turn = 0;
		fieldInPlay = 0;
		p1Score = 0;
		p2Score = 0;
		winnableFields = 9;
		activePlayer = false;
		fields = new Field[9];
		for(int i = 0;i<9;i++)fields[i] = new Field();
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