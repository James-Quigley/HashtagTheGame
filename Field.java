/**
* Class: Field
*
* Represents the nine playing fields within the Board.java class
*/
public class Field{
	private Tile[] tiles; //Represents the 9 tic-tac-toe tiles
	private int owner; //Who owns the field
	private boolean full; //Represents the status of the field being full
	public Tile getTile(int i){return tiles[i];}
	/**
	* Default constructor for Field
	*/
	public Field(){
	    tiles = new Tile[9];
	    for(int i = 0;i<9;i++) tiles[i] = new Tile();
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

	public void checkIfWon(){
		for (int i = 0; i < 7; i += 3){
			for (int j = 0; j < 2; j++){
				if (Tiles[i].getOwner() == j && Tiles[i+1].getOwner() == j && Tiles[i+2].getOwner() == j){
					setOwner(j);
					return;
				}
			}
		}
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 2; j++){
				if (Tiles[i].getOwner() == j && Tiles[i+3].getOwner() == j && Tiles[i+6].getOwner() == j){
					setOwner(j);
					return;
				}
			}
		}
		for (int i = 0; i < 2; i++){
			if (Tiles[0].getOwner() == i && Tiles[4].getOwner() == i && Tiles[8].getOwner() == i){
				setOwner(i);
				return;
			}
		}
		for (int i = 0; i < 2; i++){
			else if (Tiles[2].getOwner() == i && Tiles[4].getOwner() == i && Tiles[6].getOwner() == i){
				setOwner(i);
				return;
			}
		}
	}
}