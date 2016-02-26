package poly_cs_club.hashtagthegame;

/**
 * Class: ser215.hashtagthegameandroid.Board
 *
 * Represents the fullviewlayout board of #TheGame
 */
public class Board{
    private Field[][] m_Fields;	//Represents the fields of pla
    private boolean m_GameWon;
    private int m_WinnableFields;
    private Location fieldInPlay;

    public Board()
    {
        m_Fields = new Field[3][3];
        m_GameWon = false;
        m_WinnableFields = 9;
        fieldInPlay = new Location(-1,-1,-1,-1);
        setUpField();
    }

    /**
     * set Tile owner and fill
     * @param playerName
     */
    public void setTile(Location loc, String playerName)
    {
        m_Fields[loc.x_Field][loc.y_Field].setTile(loc.x_Tile, loc.y_Tile, playerName);
    }

    /**
     * mark Field Full
     * @param x x Value in Field
     * @param y y value in Field
     */
    public void fillField(int x, int y)
    {
        m_Fields[x][y].fillField();
        m_WinnableFields -= 1;
    }

    /**
     * mark tile full
     */
    public void fillTile(Location loc)
    {
        m_Fields[loc.x_Field][loc.y_Field].getTile(loc.x_Tile, loc.y_Tile).fill();
    }

    public boolean IsTileFull(Location loc)
    {
        return m_Fields[loc.x_Field][loc.y_Field].getTile(loc.x_Tile, loc.y_Tile).isFull();
    }

    /**
     * set Field Onwer
     * @param player
     * @param loc
     */
    public void setFieldOwner(Player player, Location loc)
    {
        m_Fields[loc.x_Field][loc.y_Field].setOwner(player.getName());
        m_WinnableFields -= 1;
    }

    /**
     * set Tile Owner
     * @param player
     * @param loc
     */
    public void setTileOwner(Player player, Location loc)
    {
        m_Fields[loc.x_Field][loc.y_Field].getTile(loc.x_Tile, loc.y_Tile).setOwner(player.getName());
    }
    public void setUpField()
    {
        for(int x = 0; x < 3; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                m_Fields[x][y] = new Field();
            }
        }
    }
    public void getTile(Location loc)
    {
        m_Fields[loc.x_Field][loc.y_Field].getTile(loc.x_Tile, loc.y_Tile);
    }
    public boolean isFieldFull(Location loc)
    {
        return m_Fields[loc.x_Field][loc.y_Field].isFull();
    }

    public boolean isGameWon(int p1Score, int p2Score)
    {
        if((p1Score > p2Score) && ((p2Score + m_WinnableFields) < p1Score)){ //Player 1 wins
            m_GameWon = true;

        }else if((p2Score > p1Score) && ((p1Score + m_WinnableFields) < p2Score)){ //Player 2 wins
            m_GameWon = true;
        }
        return m_GameWon;
    }

    public void setFieldInPlay (Location location){
        fieldInPlay = location;
    }

}
