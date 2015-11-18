/**
 * Class: Field
 *
 * Represents the nine playing fields within the Board.java class
 */
public class Field{
    private Tile[] tiles; //Represents the 9 tic-tac-toe tiles
    private int owner; //Who owns the field
    private boolean full; //Represents the status of the field being full

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
     * Getter for full
     */
    public boolean isFull(){
        for(Tile t:tiles)
            if(t.getOwner() == 0) return false;
        return true;
    }

    /**
     * Returns the tile at the given index
     */
    public Tile getTile(int index){
        return tiles[index];
    }

    /**
     * Checks if field has been won. Returns true if player has just won a field or false if there is no change in ownership.
     */
    public boolean checkIfWon(){
        if(owner != 0) return false;
        for (int i = 0; i < 7; i += 3){
            for (int j = 1; j <= 2; j++){
                if (tiles[i].getOwner() == j && tiles[i+1].getOwner() == j && tiles[i+2].getOwner() == j){
                    setOwner(j);
                    return true;
                }
            }
        }
        for (int i = 0; i < 3; i++){
            for (int j = 1; j <= 2; j++){
                if (tiles[i].getOwner() == j && tiles[i+3].getOwner() == j && tiles[i+6].getOwner() == j){
                    setOwner(j);
                    return true;
                }
            }
        }
        for (int i = 1; i <= 2; i++){
            if (tiles[0].getOwner() == i && tiles[4].getOwner() == i && tiles[8].getOwner() == i){
                setOwner(i);
                return true;
            }
        }
        for (int i = 1; i <= 2; i++){
            if (tiles[2].getOwner() == i && tiles[4].getOwner() == i && tiles[6].getOwner() == i){
                setOwner(i);
                return true;
            }
        }

        return false;
    }

    public void reset(){
        setOwner(0);
        for (Tile tile: tiles){
            tile.setOwner(0);
        }
    }
}