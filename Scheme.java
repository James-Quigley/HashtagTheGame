import java.awt.*;

public class Scheme {
	public Color board, tile, player1, player2, player1new, player2new, field;
	public String player1name, player2name;
	Scheme() {
		player1name = "Red";
		player2name = "Purple";
		
		board = new Color(137,137,137); //Dark gray
		tile = new Color(238,238,238); //Light gray
		player1 = new Color(189,59,59); //Red
		player2 = new Color(77,49,115); //Purple
		player1new = new Color(121,0,0); //Dark Red
		player2new = new Color(32,0,75); //Dark Purple
		field = new Color(124,197,118); //Light blue
	}
}