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
        if(m_Fields[loc.x_Field][loc.y_Field].getOwner().equals("Unknown") && threeInARow(player.getName(), loc))
        {
            setFieldOwner(player, loc);
        }
    }
    public boolean threeInARow(String name, Location loc)
    {
        String owner = name, acrossOwner = "", downOwner = "", diaOwner = "";
        int across = 0;
        int down = 0;
        int dia = 0;

        for(int x = 0; x < 3; x++)
        {
            across = 0;
            down = 0;

            for (int y = 0,d = 0; y < 3; y++, d++)
            {
                acrossOwner = m_Fields[loc.x_Field][loc.y_Field].getTile(x, y).getOwner();
                //downOwner = m_Fields[loc.x_Field][loc.y_Field].getTile(y, x).getOwner();
                //diaOwner = m_Fields[loc.x_Field][loc.y_Field].getTile(y,d).getOwner();
                if (acrossOwner.equals(owner))
                {
                    System.out.println("Across");
                    across++;
                }
                if (downOwner.equals(owner))
                {
                    //System.out.println("DOWN");
                    //down++;
                }
                if(diaOwner.equals(owner))
                {
                   // System.out.println("DIA");
                    //dia++;
                }
            }
        }
        if(across == 3 || down == 3) {
            System.out.println(name +  " " + "three In a Row");
            return true;
        }else
        {
            return false;
        }
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
    public Location getFieldInPlay()
    {
        return fieldInPlay;
    }
    public boolean isFieldAvailable(Location loc)
    {
        if(getFieldInPlay().x_Field == -1 && getFieldInPlay().y_Field == -1)
        {
            return true;
        }else
        {
            return getFieldInPlay().fieldEquals(loc);
        }
    }
}
