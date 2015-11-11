import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class TheGameGooey{
	private EButton[] buttons;
	private Board theGame;
	private JFrame window;
	private JPanel grid;
	private JLabel statusBar, turnIndicator;
	private boolean mode; //true = full view, false = subView
	private final int GAME_DIM = 600;
	private class EButton extends JButton{
		/*
		private Frame parentFrame;
		private Tile content;
		public EButton(Frame f, Tile t){
			super();
			parentFrame = f;
			content = t;
		}
		*/
		private int parentField; //index of parent field in game (0-8)
		private int content; //index of tile in its parent frame (0-8)
		public EButton(int f, int t){
			super(f*9 + t+ "");
			parentField = f;
			content = t;
		}

	}

	private void changeTurn() {
		theGame.changeActivePlayer();
		if (theGame.getActivePlayer()) {
			turnIndicator.setText("PLAYER 2");
		}
		else {
			turnIndicator.setText("PLAYER 1");
		}
	}

	private void setStatus(String s){}
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			EButton butt = (EButton)e.getSource();
			Field clickedField = theGame.getField(butt.parentField);
			Tile clickedTile = theGame.getTile(butt.parentField,butt.content);
			if (mode) {
				if(!clickedField.isFull()){
					subView((butt).parentField);
				}
				else {
					setStatus("Field Full. Do something derek!");
				}
			}
			else {
				if(clickedTile.getOwner() != 0) {
					clickedTile.setOwner(theGame.getActivePlayer()? 2 : 1);
					//Check for win conditions here
					suvView(butt.content);
					changeTurn();
				}
				else {
					setStatus("Tile already played on. Derek do something!");
				}
			}

			/*
			Derek's comments on what I should do ^
			If in fullview,
				if this button's frame isn't full
					move to subview of this button's frame
				else
					add something in status bar, handle accordingly
			If in subView,
				if this button's tile isn't taken
					take the tile for player
					check to see if any end conditions
					go into fullView
					change mode
					edit turn indicator
					change the button's color
					etc
				else
					indicate in status bar that a different move must be made
			*/
		}
	}
	public void initButtons(){
		buttons = new EButton[81];
		for(int i = 0;i<81;i++){
			buttons[i] = new EButton(i/9,i%9);
			buttons[i].addActionListener(new ButtonListener());
		}

	}
	public void initFrame(){
		initButtons();
		window = new JFrame();
		//window.setLayout(new BoxLayout(window,BoxLayout.Y_AXIS));
		window.setLayout(new FlowLayout());
		fullView();
		statusBar = new JLabel("EMPTY");
		turnIndicator = new JLabel("PLAYER 1");

		window.add(grid);
		window.add(statusBar);
		window.add(turnIndicator);
		/*
		initiate frame
		add buttons
		add status bar
		add turn indicator
		*/
		window.setVisible(true);
	}
	public void fullView(){//sets grid to 9x9 view
		mode = true;
		grid = new JPanel(new GridLayout(9,9));

		for(int i = 0;i<3;i++)
			for(int j = 0;j<3;j++)
				for(int k = 0;k<3;k++)
					grid.add(buttons[(i*3)+(j*9)+k]);
		for(int i = 9;i<12;i++)
			for(int j = 0;j<3;j++)
				for(int k = 0;k<3;k++)
					grid.add(buttons[(i*3)+(j*9)+k]);
		for(int i = 18;i<21;i++)
			for(int j = 0;j<3;j++)
				for(int k = 0;k<3;k++)
					grid.add(buttons[(i*3)+(j*9)+k]);
		//for(int i = 0;i<81;i++) grid.add(buttons[i]);
		recolor();
		grid.setPreferredSize(new Dimension(GAME_DIM, GAME_DIM));

	}
	public void subView(int f){
		mode = false;
	}
	private void recolor(){//recolors all buttons. will recolor for either view
		for(EButton e: buttons){
			int t = theGame.getField(e.parentField).getTile(e.content).getOwner();
			e.setOpaque(true);
			if(t == 0) e.setBackground(Color.WHITE);
			if(t == 1) e.setBackground(Color.ORANGE);
			if(t == 2) e.setBackground(Color.BLUE);
			if(theGame.getLastTile() == e.parentField*9+e.content && theGame.getTurn() > 1) e.setBackground(Color.RED);
		}

	}
	public void endView() {} //End
	public TheGameGooey(){
		theGame = new Board();
		initFrame();
	}
	public static void main(String[] args){
		TheGameGooey g = new TheGameGooey();



	}
}
