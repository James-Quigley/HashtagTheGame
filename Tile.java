/**
* Class: Tile
*
* Represents the nine tiles within each Field.java class
*/
public class Tile{
	
	private int owner; //0, 1, or 2 depending on who owns the tile
	
	/**
	* Default Constructor: Sets owner to 0
	*/
	public Tile(){
		owner = 0;
	}
	
	/**
	* Setter for owner of tile
	*/
	void setOwner(int owner){
		this.owner = owner;
	}
	
	/**
	* Getter for owner
	*/
	int getOwner(){
		return owner;
	}
}