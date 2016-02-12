package poly_cs_club.hashtagthegame;

/**
 *
 */
public class Field{
    private Tile[][] m_Tiles; //Represents the 9 tic-tac-toe tiles
    private String owner; //Who owns the field
    private boolean m_IsFull; //Represents the status of the field being full

    /**
     * Default constructor for ser215.hashtagthegameandroid.Field
     */
    public Field(){
        m_Tiles = new Tile[3][3];
        owner = "Unkown";
        m_IsFull = false;
        setUpField();
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
        return m_IsFull;
    }

    /**
     * Returns the tile at the given index
     */
    public Tile getTile(int x, int y){
        return m_Tiles[x][y];
    }

    /**
     * setup the field with tiles
     */
    public void setUpField()
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
     * fills the Field
     */
    public void fillField()
    {
        m_IsFull = true;
    }
    /**
     * sets the tile.
     */
    public void setTile(int x, int y, String playerName)
    {
        m_Tiles[x][y].setOwner(playerName);
        m_Tiles[x][y].fill();
    }
}
