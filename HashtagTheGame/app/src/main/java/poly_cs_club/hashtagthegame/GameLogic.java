package poly_cs_club.hashtagthegame;

/**
 * Created by Josh on 2/12/2016.
 */
public class GameLogic
{
    private Board m_Board;
    private Player m_PlayerOne;
    private Player m_PlayerTwo;
    private boolean m_Turn; // true player one, false player two.

    public GameLogic(int playerOneType, int playerTwoType)
    {
        m_Board = new Board();
        m_PlayerOne = new Player("PlayerOne", playerOneType);
        m_PlayerTwo = new Player("PlayerTwo", playerTwoType);
        m_Turn = false;
    }

    public void takeTurn(Player player,Location selectedTile)
    {
        if(!m_Board.IsTileFull(selectedTile))
        {
            m_Board.setTileOwner(player, selectedTile);
            m_Turn = !m_Turn;
            if(ifFeildIsTaken(selectedTile))
            {
                m_Board.setFieldOwner(player, selectedTile);
                //update Field
                if(ifGameWon())
                {
                    //update Game Screen
                }
            }
        }else
        {
            //send message that tile is taken...
        }
    }
    private boolean ifFeildIsTaken(Location loc)
    {
       return m_Board.isFieldFull(loc);
    }
    private boolean ifGameWon()
    {
        return false;
    }
}
