/**
* Class: Field
*
* Represents the nine playing fields within the Board.java class
*/
public class Field{
	
	private Tile tiles[][] = new Tile[3][3]; //Represents the 9 tic-tac-toe tiles
	private int owner; //Who owns the field
	private boolean full; //Represents the status of the field being full
	
	/**
	* Default constructor for Field
	*/
	public Field(){
		owner = 0;
		full = false;
	}
	
	/**
	* Setter for owner
	*/
	public void setOwner(int owner){
		this.owner = owner;
	}
	
	/**
	* Getter for owner
	*/
	public int getOwner(){
		return owner;
	}

	/**
	* Setter for full
	*/
	public void setFull(boolean full){
		this.full = full;
	}
	
	/**
	* Getter for full
	*/
	public boolean isFull(){
		return full;
	}
}