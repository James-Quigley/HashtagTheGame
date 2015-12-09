import java.awt.*;

public class Scheme {
	public Color light, dark, field;
	public String name;
	Scheme() {
		name = "";
		dark = new Color(137,137,137); //Dark gray
		light = new Color(238,238,238); //Light gray
		field = new Color(200,200,200); //Halfway inbetwen gray.. I can do better than that.
	}
	Scheme(int t) {
		setScheme(t);
	}
	public void setScheme(int t) {
		switch(t) {
			case(0):
				name = "Blue";
				dark = new Color(121,0,0); //change me
				light = new Color(75,181,227);
				field = new Color(124,197,118); //change me
				break;
			case(1):
				name = "Green";
				dark = new Color(121,0,0); //change me
				light = new Color(145,220,92);  
				field = new Color(124,197,118); //change me
				break;
			case(2):
				name = "Yellow";
				dark = new Color(121,0,0);  //change me
				light = new Color(225,225,96);  
				field = new Color(124,197,118); //change me
				break;
			case(3):
				name = "Red";
				dark = new Color(121,0,0);  
				light = new Color(224,109,94);  
				field = new Color(124,197,118); //change me
				break;
			case(4):
				name = "Purple";
				dark = new Color(121,0,0);  //change me
				light = new Color(118,112,226); 
				field = new Color(124,197,118); //change me
				break;
		}
	}
}