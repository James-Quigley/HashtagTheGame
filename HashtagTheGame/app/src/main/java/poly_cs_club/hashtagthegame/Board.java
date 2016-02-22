package poly_cs_club.hashtagthegame;

/**
 * Class: ser215.hashtagthegameandroid.Board
 *
 * Represents the fullviewlayout board of #TheGame
 */
public class Board{
    private Field[][] m_Fields;	//Represents the fields of pla

    public Board()
    {
        m_Fields = new Field[3][3];
        setUpField();
    }

    /**
     * set Tile owner and fill
     * @param playerName
     */
    public void setTile(Location loc, String playerName)
    {
        m_Fields[loc.x_Field][loc.y_Field].setTile(loc.x_Tile,loc.y_Tile, playerName);
    }

    /**
     * mark Field Full
     * @param x x Value in Field
     * @param y y value in Field
     */
    public void fillField(int x, int y)
    {
        m_Fields[x][y].fillField();
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
}
