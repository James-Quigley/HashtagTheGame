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
    }

    /**
     * sets a tile, at a location x,y.
     *
     */
    public void setTile(int x, int y, String playerName)
    {
        m_Field[x][y].setOwner(playerName);
    }
}
