package poly_cs_club.hashtagthegame;

public class Tile{

    private String m_Owner; //0, 1, or 2 depending on who owns the tile
    private boolean m_IsFull;

    /**
     * Default Constructor: Sets owner to 0
     */
    public Tile(){
        m_Owner = "Unknown";
        m_IsFull = false;
    }

    /**
     * Setter for owner of tile
     */
    void setOwner(String owner){
        this.m_Owner = owner;
        fill();
    }

    /**
     * Getter for owner
     */
    String getOwner(){
        return m_Owner;
    }
    /**
     * check is full
     */
    public boolean isFull()
    {
        return m_IsFull;
    }

    /**
     * mark full
     */
    public void fill()
    {
        m_IsFull = true;
    }
}
