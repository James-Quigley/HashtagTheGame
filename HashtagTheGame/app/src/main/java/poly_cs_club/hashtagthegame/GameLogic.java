package poly_cs_club.hashtagthegame;

/**
 * Created by Josh on 2/12/2016.
 */
public class GameLogic
{
    private Board m_GameBoard;
    private Player m_PlayerOne;
    private Player m_PlayerTwo;
    private boolean m_Turn; // true player one, false player two.

    public GameLogic(int playerOneType, int playerTwoType)
    {
        m_GameBoard = new Board();
        m_PlayerOne = new Player("PlayerOne", playerOneType);
        m_PlayerTwo = new Player("PlayerTwo", playerTwoType);
        m_Turn = false;
    }

    public void takeTurn(int x_F, int y_F, int x, int y)
    {
        /*(m_GameBoard.getTile(x_F,y_F,x,y)
        {
            //Send Message to User tile is full;
        }else if(m_Turn)// player One Turn
        {

        }else// Player Two Turn
        {

        }*/
    }
}
