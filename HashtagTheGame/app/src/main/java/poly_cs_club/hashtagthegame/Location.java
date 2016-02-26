package poly_cs_club.hashtagthegame;

/**
 * Created by Joshua on 2/16/2016.
 */
public class Location {
    public int x_Field, y_Field, x_Tile,y_Tile;

    public Location(int x_F, int y_F, int x_T,int y_T)
   {
       x_Field = x_F;
       y_Field = y_F;
       x_Tile = x_T;
       y_Tile = y_T;
   }

    public String toString(){
        return this.x_Field + ", " + this.y_Field + ", " + this.x_Tile + ", " + this.y_Tile;
    }

    public boolean fieldEquals(Location loc)
    {
        return (loc.x_Field == this.x_Field) && (loc.y_Field == this.y_Field);
    }

}
