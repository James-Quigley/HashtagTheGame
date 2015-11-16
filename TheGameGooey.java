import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class TheGameGooey{
	private EButton[] buttons; //Array of all 81 buttons
	private Board theGame; //Instance of board for game
	private JFrame window; 
	private JPanel gameGrid; //Grid that holds frames
	private JPanel frameGrid; //Frames
	private JLabel statusBar, turnIndicator; //Text indicators
	private boolean mode; //true = full view, false = subView
	private final int GAME_DIM = 600; //Game dimensions
	private boolean gameWon;

	//Buttons that hold information on which field and tile it is. 
	private class EButton extends JButton{
		private int parentField; //index of parent field in game from 0 to 9
		private int content; //index of tile in its parent frame from 0 to 9
		public EButton(int f, int t){
			parentField = f;
			content = t;
		}
		public int index() { //Gives the index on a 9x9 grid of the button. 
			return (parentField * 9 + content);
		}
	}

	//Swaps the turn of the player and changes the turnIndocator to indicate who is playing.
	private void changeTurn() {
		theGame.changeActivePlayer();

		if (theGame.getActivePlayer()) {
			turnIndicator.setText("PLAYER 2");
		}
		else {
			turnIndicator.setText("PLAYER 1");
		}
	}

	//Changes the text in the status bar.
	private void setStatus(String s){
		statusBar.setText(s);
	}

	//Whenever a button is pressed, do this run a turn sequence .
	private class ButtonListener implements ActionListener{
		//What the button will do.
		public void actionPerformed(ActionEvent e){
			EButton butt = (EButton)e.getSource(); //Loads the button information with data values
			Field clickedField = theGame.getField(butt.parentField); //Gets the field in question
			Tile clickedTile = theGame.getTile(butt.parentField,butt.content); //Gets the tile in question

			setStatus("Please play in the green field"); //Resets the Status text
			if(gameWon) {
				theGame.reset();
				gameWon = false;
				setStatus("Welcome to #TheGame");
				recolor();
				return;
			}
			//If players could have chosen any field. 
			if(theGame.getFieldInPlay() == -1) {
				if(clickedTile.getOwner() == 0) { //The tile is free
					clickedTile.setOwner(theGame.getActivePlayer()? 2 : 1); //Set owner
					if (clickedField.checkIfWon()) { //Check if field is won
						theGame.incScore(theGame.getActivePlayer()); //Increases the score of the player who won
						if(theGame.checkIfWon()){ //Check if game is won if field is won
							setStatus("Player " + (theGame.getActivePlayer()? 2 : 1) + " won! Press any button to play again");
							gameWon = true;
							recolor();
							return;	
						} 
						setStatus("Player " + (theGame.getActivePlayer()? 2 : 1) + " won a field.");
					}
					else if (clickedField.isFull() && clickedField.getOwner() == 0) { //Check if field was catsgamed
						theGame.decWinnableFields(); //Decreases winnable fields due to cats game
						if(theGame.checkIfWon()){ //Check if game is won if field is won
							setStatus("Player " + (theGame.getActivePlayer()? 2 : 1) + " won! Press any button to play again");
							gameWon = true;
							recolor();
							return;
						} 
					}
					theGame.setLastIndex(butt.index());
					changeTurn();
					if (!(theGame.getField(butt.content).isFull())) { //Set the next field to play in to the previous player's tile
						theGame.setFieldInPlay(butt.content);                            
					}
					else { //The field the next player was sent is full
						setStatus("You can play anywhere, Player " + (theGame.getActivePlayer()? 2 : 1));
						theGame.setFieldInPlay(-1);
					}
				}
				else { //Clicked Tile is not free
					setStatus("That's not a free tile, Player " + (theGame.getActivePlayer()? 2 : 1));
				}
			}

			//Checks if the player clicked a button in the correct field
			else if(theGame.getFieldInPlay() == butt.parentField) {
				if(clickedTile.getOwner() == 0) { //The tile is free
					clickedTile.setOwner(theGame.getActivePlayer()? 2 : 1); //Set owner
					if (clickedField.checkIfWon()) { //Check if field is won
						theGame.incScore(theGame.getActivePlayer()); //Increases the score of the player who won
						if(theGame.checkIfWon()){ //Check if game is won if field is won
							setStatus("Player " + (theGame.getActivePlayer()? 2 : 1) + " won! Press any button to play again");
							gameWon = true;
							recolor();
							return;	
						} 
						setStatus("Player " + (theGame.getActivePlayer()? 2 : 1) + " won a field.");
					}
					else if (clickedField.isFull() && clickedField.getOwner() == 0) { //Check if field was catsgamed
						theGame.decWinnableFields(); //Decreases winnable fields due to cats game
						if(theGame.checkIfWon()){ //Check if game is won if field is won
							setStatus("Player " + (theGame.getActivePlayer()? 2 : 1) + " won! Press any button to play again");
							gameWon = true;
							recolor();
							return;
						} 
					}
					theGame.setLastIndex(butt.index());
					changeTurn();

					if (!(theGame.getField(butt.content).isFull())) { //Set the next field to play in to the previous player's tile
						theGame.setFieldInPlay(butt.content);                            
					}
					else { //The field the next player was sent is full
						setStatus("You can play anywhere, Player " + (theGame.getActivePlayer()? 2 : 1));
						theGame.setFieldInPlay(-1);
					}
				}
				else { //Clicked Tile is not free
					setStatus("That's not a free tile, Player " + (theGame.getActivePlayer()? 2 : 1));
				}
			}
			else {
				setStatus("That's not the right field, Player " + (theGame.getActivePlayer()? 2 : 1));
			}
			recolor();
		}
	}

	//Sets up all the Buttons assuming aa 9x9 grid
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

		fullView();
		statusBar = new JLabel("Welcome to #TheGame");
		turnIndicator = new JLabel("PLAYER 1");

		window.add(statusBar);
		window.add(gameGrid);
		window.add(turnIndicator);

		window.setLayout(new BoxLayout(window.getContentPane(),BoxLayout.Y_AXIS));
		window.pack();
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	public void fullView(){//sets grid to 9x9 view
		mode = true;
		gameGrid = new JPanel(new GridLayout(3,3,10,10));
		for(int i = 0; i < 9; i++) {
			frameGrid = new JPanel(new GridLayout(3,3,5,5));
			for(int j = 0; j < 9; j++) {
				frameGrid.add(buttons[i*9+j]);
			}
			gameGrid.add(frameGrid);
		}
		recolor();
		gameGrid.setPreferredSize(new Dimension(GAME_DIM, GAME_DIM));

	}
	public void subView(int f){
		mode = false;
	}
	private void recolor(){//recolors all buttons. will recolor for either view
		Scheme base = new Scheme();
		for(EButton e: buttons){
			e.setOpaque(true);
			//Color all blank
			e.setBackground(base.blank);
			//Color the field in play
			if(e.parentField == theGame.getFieldInPlay() || theGame.getFieldInPlay() == -1) {
				e.setBackground(base.field);
			}
			int t = theGame.getField(e.parentField).getTile(e.content).getOwner();
			//Color taken spaces
			if(t == 1) e.setBackground(base.player1);
			if(t == 2) e.setBackground(base.player2);
			//Color last played
			if(e.index() == theGame.getLastIndex()) {
				e.setBackground(theGame.getActivePlayer()? base.player1new : base.player2new);
			}
		}

	}
	public void endView() {} //End
	public TheGameGooey(){
		theGame = new Board();
		gameWon = false;
		initFrame();
	}
	public static void main(String[] args){
		TheGameGooey g = new TheGameGooey();



	}
}
