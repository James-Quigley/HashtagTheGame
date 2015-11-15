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

	private void setStatus(String s){
        statusBar.setText(s);
    }
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			EButton butt = (EButton)e.getSource();
			Field clickedField = theGame.getField(butt.parentField);
			Tile clickedTile = theGame.getTile(butt.parentField,butt.content);
		/*	if (mode) {
				if(!clickedField.isFull()){
					subView((butt).parentField);
				}
				else {
					setStatus("Field Full. Do something derek!");
				}
			}
			else { */
                if(theGame.getFieldInPlay() == -1) { //Freeplay field
                    if(clickedTile.getOwner() == 0) { //Clicked Tile is free
                        clickedTile.setOwner(theGame.getActivePlayer()? 2 : 1); //Set owner
                        // System.out.println(butt.content); 
                        // System.out.println(butt.parentField);
                        if (clickedField.checkIfWon()) { //Check if field is won
                            theGame.incScore(theGame.getActivePlayer()); //Increases the score of the player who won
                            theGame.checkIfWon(); //Check if game is won if field was won
                            System.out.println("Player " + (theGame.getActivePlayer()? 2 : 1) + " won a field.");
                        }
                        else if (clickedField.isFull() && clickedField.getOwner() == 0) { //Check if field was catsgamed
                      	    theGame.decWinnableFields(); //Decreases winnable fields due to cats game
                            theGame.checkIfWon(); //Check if game is won if field was catsgamed
                        }
                        changeTurn();
                        if (!(theGame.getField(butt.content).isFull())) { //Set the next field to play in to the previous player's tile
                            theGame.setFieldInPlay(butt.content);                            
                        }
                        else { //The field the next player was sent is full
                            theGame.setFieldInPlay(-1);
                        }
                    }
                    else { //Clicked Tile is not free
                        System.out.println("Tile already played on. Derek do Something");
                    }
                }
                else if(theGame.getFieldInPlay() == butt.parentField) { //Checks if the player clicked a button in the correct field
                    if(clickedTile.getOwner() == 0) {
                        clickedTile.setOwner(theGame.getActivePlayer()? 2 : 1); //Set owner
                        // System.out.println(butt.content); 
                        // System.out.println(butt.parentField);

                        if (clickedField.checkIfWon()) { //Check if field is won
                            theGame.incScore(theGame.getActivePlayer()); //Increases the score of the player who won
                            theGame.checkIfWon(); //Check if game is won if field was won
                            System.out.println("Player " + (theGame.getActivePlayer()? 2 : 1) + " won a field.");
                        }
                        else if (clickedField.isFull() && clickedField.getOwner() == 0) { //Check if field was catsgamed
                      	    theGame.decWinnableFields(); //Decreases winnable fields due to cats game
                            theGame.checkIfWon(); //Check if game is won if field was catsgamed
                        }
                        changeTurn();
                        if (!(theGame.getField(butt.content).isFull())) { //Set the next field to play in to the previous player's tile
                            theGame.setFieldInPlay(butt.content);                            
                        }
                        else { //The field the next player was sent is full
                            theGame.setFieldInPlay(-1);
                        }
                    }
                    else { //Clicked Tile is not free
                        System.out.println("Tile already played on. Derek do Something");
                    }
                }
				else {
                    System.out.println("You clicked the wrong field, punk.");
                }
                recolor();
			//}

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
		window.pack();
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
			if(theGame.getLastTile() == e.parentField*9+e.content && !theGame.isFirstTurn()) e.setBackground(Color.RED);
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
