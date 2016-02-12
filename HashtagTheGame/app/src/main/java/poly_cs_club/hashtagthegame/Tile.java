package poly_cs_club.hashtagthegame;

/**
 * Created by Josh on 2/12/2016.
 */
public class Tile{

    private int owner; //0, 1, or 2 depending on who owns the tile
    private String color;

    /**
     * Default Constructor: Sets owner to 0
     */
    public Tile(){
        owner = 0;
    }

    /**
     * Setter for owner of tile
     */
    void setOwner(int owner){
        this.owner = owner;
    }

    /**
     * Getter for owner
     */
    int getOwner(){
        return owner;
    }
}
