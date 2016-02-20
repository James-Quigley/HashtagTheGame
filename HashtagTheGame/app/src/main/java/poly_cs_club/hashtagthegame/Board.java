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
     * @param x_F
     * @param y_F
     * @param x
     * @param y
     * @param playerName
     */
    public void setTile(int x_F, int y_F,int x, int y, String playerName)
    {
        m_Fields[x_F][y_F].setTile(x, y, playerName);
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
     * @param x_F x value of Field
     * @param y_F y value of Field
     * @param x x value of Tile
     * @param y y Value of Tile
     */
    public void fillTile(int x_F, int y_F,int x,int y)
    {
        m_Fields[x_F][y_F].getTile(x,y).fill();
    }

    public boolean IsTileFull(Location loc)
    {
        return m_Fields[loc.x_Field][loc.y_Field].getTile(loc.x_Tile, loc.y_Tile).isFull();
    }

    /**
     * set Field Onwer
     * @param x Field x value
     * @param y Field y Value
     * @param playerName Name of Player Owner
     */
    public void setFieldOwner(int x, int y,String playerName)
    {
        m_Fields[x][y].setOwner(playerName);
    }

    /**
     * set Tile Owner
     **/
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
    public void getTile(int x_F,int y_F, int x, int y)
    {
        m_Fields[x_F][y_F].getTile(x, y);
    }
}
