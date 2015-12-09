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
				dark = new Color(0x01579B);
				light = new Color(0x03A9F4);
				field = new Color(0x0288D1);
				break;
			case(1):
				name = "Green";
				dark = new Color(0x1B5E20); 
				light = new Color(0x4CAF50);  
				field = new Color(0x388E3C); 
				break;
			case(2):
				name = "Yellow";
				dark = new Color(0xF57F17); 
				light = new Color(0xFFEB3B);  
				field = new Color(0xFBC02D); 
				break;
			case(3):
				name = "Red";
				dark = new Color(0xB71C1C);  
				light = new Color(0xF44336);  
				field = new Color(0xD32F2F);
				break;
			case(4):
				name = "Purple";
				dark = new Color(0x311B92); 
				light = new Color(0x673AB7); 
				field = new Color(0x512DA8);
				break;
		}
	}
}