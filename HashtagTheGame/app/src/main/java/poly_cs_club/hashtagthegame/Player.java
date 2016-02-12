package poly_cs_club.hashtagthegame;

/**
 * Created by Josh on 2/12/2016.
 */
public class Player
{
    private String m_Name;
    private int m_Type;//0 Human, 1 CPU, 2 Human-Internet
    public Player()
    {
        m_Name = "Unkown";
        m_Type = 0;
    }
    public Player(String name, int type)
    {
        m_Name = name;
        m_Type = type;
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
}
