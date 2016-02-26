package poly_cs_club.hashtagthegame;

/**
 * Created by Josh on 2/12/2016.
 */
public class Player
{
    private String m_Name;
    private int m_Type;//0 Human, 1 CPU, 2 Human-Internet
    private int m_Score;
    private boolean m_MyTurn; // true player one, false player two.
    public Player()
    {
        m_Name = "Unknown";
        m_Type = 0;
        m_Score = 0;
        m_MyTurn = false;
    }
    public Player(String name, int type)
    {
        m_Name = name;
        m_Type = type;
        m_Score = 0;
    }

    public int getType() {
        return m_Type;
    }
    public String getName()
    {
        return m_Name;
    }
    public void setName(String name)
    {
        m_Name = name;
    }
    public void setType(int type)
    {
        m_Type = type;
    }
    public int getScore()
    {
        return m_Score;
    }
    public void setScore(int score)
    {
        m_Score = score;
    }
    public boolean isTurn()
    {
        return m_MyTurn;
    }
    public void setTurn(boolean turn)
    {
        m_MyTurn = turn;
    }
}
