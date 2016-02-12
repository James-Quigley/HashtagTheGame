package poly_cs_club.hashtagthegame;

/**
 *
 */
public class Field{
    private Tile[][] m_Tiles; //Represents the 9 tic-tac-toe tiles
    private String owner; //Who owns the field
    private boolean full; //Represents the status of the field being full

    /**
     * Default constructor for ser215.hashtagthegameandroid.Field
     */
    public Field(){
        m_Tiles = new Tile[3][3];
        owner = "Unkown";
        full = false;
        fillField();
    }

    /**
     * Setter for owner
     */
    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    /**
     * Getter for owner
     */
    public String getOwner(){
        return owner;
    }


    /**
     * Getter for full
     */
    public boolean isFull()
    {
        return true;
    }

    /**
     * Returns the tile at the given index
     */
    public Tile getTile(int x, int y){
        return m_Tiles[x][y];
    }

    /**
     * fills the field with tiles
     */
    public void fillField()
    {
        for(int x = 0; x < 3; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                m_Tiles[x][y] = new Tile();
            }
        }
    }

    /**
     * Checks if field has been won. Returns true if player has just won a field or false if there is no change in ownership.
     */
    /*public boolean checkIfWon(){
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
    }*/
}
