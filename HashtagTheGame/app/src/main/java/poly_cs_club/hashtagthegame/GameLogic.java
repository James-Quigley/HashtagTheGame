package poly_cs_club.hashtagthegame;

/**
 * Created by Josh on 2/12/2016.
 */
public class GameLogic
{
    private GameBoard m_GameBoard;
    private Player m_PlayerOne;
    private Player m_PlayerTwo;
    private boolean m_Turn; // true player one, false player two.

    public GameLogic(int playerOneType, int playerTwoType)
    {
        m_GameBoard = new GameBoard();
        m_PlayerOne = new Player("PlayerOne", playerOneType);
        m_PlayerTwo = new Player("PlayerTwo", playerTwoType);
        m_Turn = false;
    }

    public void takeTurn(Player player,Location selectedTile)
    {
        if(!m_GameBoard.IsTileFull(selectedTile))
        m_GameBoard.setTileOwner(player, selectedTile);
    }
}
