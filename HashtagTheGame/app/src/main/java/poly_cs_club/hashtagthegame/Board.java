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
        player.setScore(player.getScore()+1);
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
            System.out.println("Field " + loc.x_Field + ", " + loc.y_Field + " owner set to " + player.getName());
        }
    }
    public boolean threeInARow(String name, Location loc)
    {
        for(int i = 0; i < 3; i++){
            if (m_Fields[loc.x_Field][loc.y_Field].getTile(i,0).getOwner().equals(name) && m_Fields[loc.x_Field][loc.y_Field].getTile(i,0).getOwner().equals(m_Fields[loc.x_Field][loc.y_Field].getTile(i,1).getOwner()) && m_Fields[loc.x_Field][loc.y_Field].getTile(i,0).getOwner().equals(m_Fields[loc.x_Field][loc.y_Field].getTile(i,2).getOwner())){
                System.out.println("3 Across");
                return true;
            }
        }
        for (int i = 0; i < 3; i++){
            if(m_Fields[loc.x_Field][loc.y_Field].getTile(0,i).getOwner().equals(name) && m_Fields[loc.x_Field][loc.y_Field].getTile(0,i).getOwner().equals(m_Fields[loc.x_Field][loc.y_Field].getTile(1,i).getOwner()) && m_Fields[loc.x_Field][loc.y_Field].getTile(0,i).getOwner().equals(m_Fields[loc.x_Field][loc.y_Field].getTile(2,i).getOwner())){
                System.out.println("3 Down");
                return true;
            }
        }
        if (m_Fields[loc.x_Field][loc.y_Field].getTile(0,0).getOwner().equals(name) && m_Fields[loc.x_Field][loc.y_Field].getTile(0,0).getOwner().equals(m_Fields[loc.x_Field][loc.y_Field].getTile(1,1).getOwner()) && m_Fields[loc.x_Field][loc.y_Field].getTile(0,0).getOwner().equals(m_Fields[loc.x_Field][loc.y_Field].getTile(2,2).getOwner())){
            System.out.println("3 diagonal");
            return true;
        }
        if (m_Fields[loc.x_Field][loc.y_Field].getTile(0,2).getOwner().equals(name) && m_Fields[loc.x_Field][loc.y_Field].getTile(0,2).getOwner().equals(m_Fields[loc.x_Field][loc.y_Field].getTile(1,1).getOwner()) && m_Fields[loc.x_Field][loc.y_Field].getTile(0,2).getOwner().equals(m_Fields[loc.x_Field][loc.y_Field].getTile(2,0).getOwner())){
            System.out.println("3 diagonal");
            return true;
        }
        return false;
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
        if(loc.x_Field != -1)
        {
            for(int i = 0; i < 3; i++)
            {
                for(int j = 0; j < 3; j++)
                    if (m_Fields[loc.x_Field][loc.y_Field].getTile(i,j).getOwner().equals("Unknown")) {
                        return false;
                    }
            }
        }else
        {
            return false;
        }

        return true;
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
    public Field getField(Location loc){
        return m_Fields[loc.x_Field][loc.y_Field];
    }

    public int getM_WinnableFields(){
        return m_WinnableFields;
    }
}