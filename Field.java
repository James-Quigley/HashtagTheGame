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
			if (Tiles[i].getOwner() == 1 && Tiles[i+1].getOwner() == 1 && Tiles[i+2].getOwner() == 1){
				setOwner(1);
				return;
			}
			else if (Tiles[i].getOwner() == 2 && Tiles[i+1].getOwner() == 2 && Tiles[i+2].getOwner() == 2){
				setOwner(2);
				return;
			}
		}
		for (int i = 0; i < 3; i++){
			if (Tiles[i].getOwner() == 1 && Tiles[i+3].getOwner() == 1 && Tiles[i+6].getOwner() == 1){
				setOwner(1);
				return;
			}
			else if (Tiles[i].getOwner() == 2 && Tiles[i+3].getOwner() == 2 && Tiles[i+6].getOwner() == 2){
				setOwner(2);
				return;
			}
		}
		if (Tiles[0].getOwner() == 1 && Tiles[4].getOwner() == 1 && Tiles[8].getOwner() == 1){
			setOwner(1);
			return;
		}
		else if (Tiles[0].getOwner() == 2 && Tiles[4].getOwner() == 2 && Tiles[8].getOwner() == 2){
			setOwner(2);
			return;
		}
		else if (Tiles[2].getOwner() == 1 && Tiles[4].getOwner() == 1 && Tiles[6].getOwner() == 1){
			setOwner(1);
			return;
		}
		else if (Tiles[2].getOwner() == 2 && Tiles[4].getOwner() == 2 && Tiles[6].getOwner() == 2){
			setOwner(2);
			return;
		}
	}
}