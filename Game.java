import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;
import java.awt.*;

public class Game{
	
	private EButton[] buttons; //Array of all 81 buttons
	private Board theGame; //Instance of board for game
	private JFrame window; 
	private JPanel gameGrid; //Grid that holds frames
	private JPanel frameGrid; //Frames
	private JLabel statusBar, scoreIndicator; //Text indicators
	private boolean mode; //true = full view, false = subView
	private final int GAME_DIM = 600; //Game dimensions
	private boolean gameWon;
	private Scheme base, player1, player2;

	//GameOver variables
	private JFrame endGame;
	private JLabel endGameLabel;
	private JButton endGameMenu, endGamePlayAgain;
	private JPanel endGamePanel1, endGamePanel2;

	//MainMenu variables
	private JFrame mainMenu;
	private JLabel  mainMenuTitle, mainMenuColorCommands;
	private JButton mainMenuStart;
	private PButton mainMenuOneSelect, mainMenuTwoSelect;
	private TButton [] mainMenuThemes;
	private JPanel mainMenuPlayers, mainMenuColors;
	private boolean colorSelect;
	
	/** Buttons that hold information on which field and tile it is. */
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

	private class TButton extends JButton {
		private Scheme color;
		private int owner;
		private int location;
		public TButton(int l) {
			color = new Scheme(l);
			owner = 0;
			location = l;
		}
	}
	private class PButton extends JButton {
		private boolean owner;
		public PButton(String t, boolean i) {
			setText(t);
			owner = i;
		}
	}


	/**
	* Swaps the turn of the player and changes the turnIndocator to indicate who is playing.
	*/
	private void changeTurn() {
		theGame.changeActivePlayer();
		scoreIndicator.setText(player1.name + ": " + theGame.getP1Score() + 
			"       " + player2.name + ": " + theGame.getP2Score() +
			"       Fields left in play: " + theGame.getWinnableFields());
	}

	/**
	* Changes the text in the status bar.
	*/
	private void setStatus(String s){
		statusBar.setText(s);
	}

	/** Whenever a button is pressed, do this run a turn sequence */
	private class ButtonListener implements ActionListener{
		//What the button will do.
		public void actionPerformed(ActionEvent e){
			EButton butt = (EButton)e.getSource(); //Loads the button information with data values
			Field clickedField = theGame.getField(butt.parentField); //Gets the field in question
			Tile clickedTile = theGame.getTile(butt.parentField,butt.content); //Gets the tile in question
			
			/**THIS IS FOR TESTING ENDGAME. PLEASE KEEP COMMENTED OUT
			if (butt.index() == 0) {
				gameWon = true;
				gameOver();
			}*/
			
			
			setStatus("Please play in the highlighted field, " + (theGame.getActivePlayer()? player2.name : player1.name)); //Set owner); //Resets the Status text
			if(gameWon) {
				setStatus((theGame.getActivePlayer()? player2.name : player1.name) + " won!");
				return;
			}
			//If players could have chosen any field. 
			if(theGame.getFieldInPlay() == -1) {
				if(clickedTile.getOwner() == 0) { //The tile is free
					clickedTile.setOwner(theGame.getActivePlayer()? 2 : 1); //Set owner
					if (clickedField.checkIfWon()) { //Check if field is won
						theGame.incScore(theGame.getActivePlayer()); //Increases the score of the player who won
						if(theGame.checkIfWon()){ //Check if game is won if field is won
							gameOver();
							return;	
						} 
						setStatus((theGame.getActivePlayer()? player2.name : player1.name) + " won a field.");
					}
					else if (clickedField.isFull() && clickedField.getOwner() == 0) { //Check if field was catsgamed
						theGame.decWinnableFields(); //Decreases winnable fields due to cats game
						if(theGame.checkIfWon()){ //Check if game is won if field is won
							gameOver();
							return;
						} 
					}
					theGame.setLastIndex(butt.index());
					changeTurn();
					if (!(theGame.getField(butt.content).isFull())) { //Set the next field to play in to the previous player's tile
						theGame.setFieldInPlay(butt.content);                            
					}
					else { //The field the next player was sent is full
						setStatus("You can play anywhere, " + (theGame.getActivePlayer()? player2.name : player1.name));
						theGame.setFieldInPlay(-1);
					}
				}
				else { //Clicked Tile is not free
					setStatus("That's not a free tile, " + (theGame.getActivePlayer()? player2.name : player1.name));
				}
			}

			//Checks if the player clicked a button in the correct field
			else if(theGame.getFieldInPlay() == butt.parentField) {
				if(clickedTile.getOwner() == 0) { //The tile is free
					clickedTile.setOwner(theGame.getActivePlayer()? 2 : 1); //Set owner
					if (clickedField.checkIfWon()) { //Check if field is won
						theGame.incScore(theGame.getActivePlayer()); //Increases the score of the player who won
						if(theGame.checkIfWon()){ //Check if game is won if field is won
							gameOver();
							return;	
						} 
						setStatus((theGame.getActivePlayer()? player2.name : player1.name) + " won a field.");
					}
					else if (clickedField.isFull() && clickedField.getOwner() == 0) { //Check if field was catsgamed
						theGame.decWinnableFields(); //Decreases winnable fields due to cats game
						if(theGame.checkIfWon()){ //Check if game is won if field is won
							gameOver();
							return;
						} 
					}
					theGame.setLastIndex(butt.index());
					changeTurn();
					if (!(theGame.getField(butt.content).isFull())) { //Set the next field to play in to the previous player's tile
						theGame.setFieldInPlay(butt.content);                            
					}
					else { //The field the next player was sent is full
						setStatus("You can play anywhere, " + (theGame.getActivePlayer()? player2.name : player1.name));
						theGame.setFieldInPlay(-1);
					}
				}
				else { //Clicked Tile is not free
					setStatus("That's not a free tile, " + (theGame.getActivePlayer()? player2.name : player1.name));
				}
			}
			else {
				setStatus("That's not the right field, " + (theGame.getActivePlayer()? player2.name : player1.name));
			}
			recolor();
		}
	}
	
	/**
	* Function that does all duties associated with the end of the game. Allows
	* users to choose to quit and go to the main menu or play the game again
	*/
	public void gameOver(){
		//Prints winner to status of game
		setStatus((theGame.getActivePlayer()? player2.name : player1.name) + " won!");
		gameWon = true;
		
		//Initializes JFrames and JPanels for endGame pop-up
		endGame = new JFrame("Game Over");
		endGamePanel1 = new JPanel(new BorderLayout());
		endGamePanel2 = new JPanel(new FlowLayout());

		//Creates text for JLabel in endGame pop-up
		endGameLabel = new JLabel((theGame.getActivePlayer()? player2.name : player1.name) + " won! \n Would you like to play again?");
		endGameLabel.setFont(mainMenuTitle.getFont().deriveFont(24.0f));
		endGameLabel.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));
		
		//Initializes buttons for endGame pop-up
		endGameMenu = new JButton("   Menu   ");
		endGameMenu.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		endGameMenu.setFocusPainted(false);
		endGameMenu.setBackground(base.dark);
		endGamePlayAgain = new JButton("Play Again");
		endGamePlayAgain.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		endGamePlayAgain.setFocusPainted(false);
		endGamePlayAgain.setBackground(base.dark);


		//Adds components to panels for endGame pop-up
		endGamePanel1.add(endGameLabel);
		endGamePanel2.add(endGameMenu);
		endGamePanel2.add(endGamePlayAgain);
		
		//Creates a layout for endGame pop-up
		endGame.setLayout(new BoxLayout(endGame.getContentPane(),BoxLayout.Y_AXIS));
		endGame.add(endGamePanel1);
		endGame.add(endGamePanel2);
		
		//Sets dimensions and centers the endGame pop-up
		endGame.setSize(300,100);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		endGame.setLocation(dim.width/2-endGame.getSize().width/2, dim.height/2-endGame.getSize().height/2);
		endGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		endGame.setVisible(true);
		endGame.pack();
		
		//Action listeners for buttons of endGame pop-up
		endGameMenuClicked menuClicked = new endGameMenuClicked();
		endGameMenu.addActionListener(menuClicked);
		
		endGamePlayAgainClicked playAgainClicked = new endGamePlayAgainClicked();
		endGamePlayAgain.addActionListener(playAgainClicked);
		
		//Recolors tiles to default
		recolor();
	}

	/** ActionListener for endGameMenu button */
	public class endGameMenuClicked implements ActionListener{
		public void actionPerformed(ActionEvent e){
			window.setVisible(false);
			endGame.setVisible(false);
			theGame = new Board();
			gameWon = false;
			base = new Scheme();
			setupMainMenu();
		}
	}
	
	/** ActionListener for endGamePlayAgain button */
	public class endGamePlayAgainClicked implements ActionListener{
		public void actionPerformed(ActionEvent e){
			endGame.setVisible(false);
			window.setVisible(false);
			theGame = new Board();
			gameWon = false;
			base = new Scheme();
			initFrame();
		}
	}

	/*
	* Function that does all duties associated with the main menu of the game. Allows
	* users to choose to start the game or change their theme.
	*/
	public void setupMainMenu() {
		//Initalizes JFrame for mainMenu
		mainMenu = new JFrame("Main Menu");
		mainMenu.setLayout(new BoxLayout(mainMenu.getContentPane(),BoxLayout.Y_AXIS));
		mainMenu.setSize(300,400);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainMenu.setLocation(dim.width/2-mainMenu.getSize().width/2, dim.height/2-mainMenu.getSize().height/2);
		mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenu.setVisible(true);
		
		//Sets up the title
		mainMenuTitle = new JLabel("Hashtag The Game");
		mainMenuTitle.setFont(mainMenuTitle.getFont().deriveFont(24.0f));
		mainMenuTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainMenuTitle.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));
		mainMenu.add(mainMenuTitle);

		//Sets up the color choosing text
		mainMenuColorCommands = new JLabel(player1.name + " vs " + player2.name);
		mainMenuColorCommands.setFont(mainMenuColorCommands.getFont().deriveFont(18.0f));
		mainMenuColorCommands.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainMenuColorCommands.setBorder(BorderFactory.createEmptyBorder(10,0,10,10));
		mainMenu.add(mainMenuColorCommands);

		//Sets up the start button.
		mainMenuStart = new JButton("             Start             ");
		mainMenuStart.addActionListener(new mainMenuStartClicked());
		mainMenuStart.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainMenuStart.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		mainMenuStart.setFocusPainted(false);
		mainMenuStart.setBackground(base.dark);
		mainMenu.add(mainMenuStart);

		//Sets up the player color changing selection
		mainMenuPlayers = new JPanel(new FlowLayout());
		mainMenuPlayers.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		mainMenuPlayers.setAlignmentX(Component.CENTER_ALIGNMENT);
		//Sets up Selector for player 1
		mainMenuOneSelect = new PButton("Player One Color", false);
		mainMenuOneSelect.addActionListener(new mainMenuPlayerClicked());
		mainMenuOneSelect.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		mainMenuOneSelect.setFocusPainted(false);
		mainMenuOneSelect.setBackground(player1.light);
		mainMenuPlayers.add(mainMenuOneSelect);
		//Sets up Selector for player 2
		mainMenuTwoSelect = new PButton("Player Two Color", true);
		mainMenuTwoSelect.addActionListener(new mainMenuPlayerClicked());
		mainMenuTwoSelect.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		mainMenuTwoSelect.setFocusPainted(false);
		mainMenuTwoSelect.setBackground(player2.light);
		mainMenuPlayers.add(mainMenuTwoSelect);
		mainMenu.add(mainMenuPlayers);

		//Sets up the color selection
		themeButtons();
		mainMenuColors = new JPanel(new GridLayout(1,5,10,10));
		mainMenuColors.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
		for(int i = 0; i < 5; i++) {
			mainMenuColors.add(mainMenuThemes[i]);
		}
		mainMenu.add(mainMenuColors);

		//Packs it all in
		mainMenu.pack();
	} 

	//Button listener for the start button
	public class mainMenuStartClicked implements ActionListener{
		public void actionPerformed(ActionEvent e){
			mainMenu.setVisible(false);
			initFrame();
		}
	}
	//Button listener for the player selector
	public class mainMenuPlayerClicked implements ActionListener{
		public void actionPerformed(ActionEvent e){
			colorSelect = ((PButton)e.getSource()).owner;
			mainMenuOneSelect.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			mainMenuTwoSelect.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

			if(!colorSelect) {
				mainMenuOneSelect.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			}
			else {
				mainMenuTwoSelect.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			}
		}
	}
	//Button listener for the theme selector
	public class mainMenuThemeClicked implements ActionListener{
		public void actionPerformed(ActionEvent e){
			TButton clicked = (TButton)e.getSource();
			if(!colorSelect && clicked.color.name != player2.name) {
				player1 = clicked.color;
			}
			if(colorSelect && clicked.color.name != player1.name) {
				player2 = clicked.color;
			}
			for (int i = 0; i < 5; i++) {
				mainMenuThemes[i].setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
				if(mainMenuThemes[i].color.name == player1.name) {
					mainMenuThemes[i].setBorder(new LineBorder(player1.dark,10));
				}
				if(mainMenuThemes[i].color.name == player2.name) {
					mainMenuThemes[i].setBorder(new LineBorder(player2.dark,10));
				}
			}
			mainMenuOneSelect.setBackground(player1.light);
			mainMenuTwoSelect.setBackground(player2.light);
			mainMenuColorCommands.setText(player1.name + " vs " + player2.name);
		}
	}
	//Initalizes all the theme buttons.
	public void themeButtons() {
		mainMenuThemes = new TButton[5];
		for (int i = 0; i < 5; i++) {
			mainMenuThemes[i] = new TButton(i);
			mainMenuThemes[i].setBackground(mainMenuThemes[i].color.light);
			mainMenuThemes[i].setPreferredSize(new Dimension(50,50));
			mainMenuThemes[i].setFocusPainted(false);
			mainMenuThemes[i].setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			if(mainMenuThemes[i].color.name == player1.name) {
				mainMenuThemes[i].setBorder(new LineBorder(player1.dark,10));
			}
			if(mainMenuThemes[i].color.name == player2.name) {
				mainMenuThemes[i].setBorder(new LineBorder(player2.dark,10));
			}
			mainMenuThemes[i].addActionListener(new mainMenuThemeClicked());
		}
	}
	/**
	* Sets up all the Buttons assuming a 9x9 grid
	*/
	public void initButtons(){
		buttons = new EButton[81];
		for(int i = 0; i < 81; i++){
			buttons[i] = new EButton(i/9, i%9);
			buttons[i].addActionListener(new ButtonListener());
		}
	}

	public void initFrame(){
		initButtons();
		window = new JFrame();

		fullView();
		statusBar = new JLabel("Welcome to #TheGame! Press any square to start, " + player1.name);
		scoreIndicator = new JLabel(player1.name + ": " + theGame.getP1Score() + 
			"       " + player2.name + ": " + theGame.getP2Score() +
			"       Fields left in play: " + theGame.getWinnableFields());
			
		scoreIndicator.setAlignmentX(Component.CENTER_ALIGNMENT);
		statusBar.setAlignmentX(Component.CENTER_ALIGNMENT);
		scoreIndicator.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		statusBar.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		gameGrid.setAlignmentX(Component.CENTER_ALIGNMENT);
		window.add(statusBar);
		window.add(gameGrid);
		window.add(scoreIndicator);

		window.setLayout(new BoxLayout(window.getContentPane(),BoxLayout.Y_AXIS));
		window.pack();
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
		window.setVisible(true);
	}
	
	/**
	* Sets grid to 9x9 view
	*/
	public void fullView(){
		mode = true;
		gameGrid = new JPanel(new GridLayout(3,3,10,10));
		for(int i = 0; i < 9; i++) {
			frameGrid = new JPanel(new GridLayout(3,3,7,7));
			for(int j = 0; j < 9; j++) {
				frameGrid.add(buttons[i*9+j]);
			}
			gameGrid.add(frameGrid);
		}
		recolor();
		gameGrid.setPreferredSize(new Dimension(GAME_DIM, GAME_DIM));
	}
	
	///////////////////////////////////////
	public void subView(int f){
		mode = false;
	}
	///////////////////////////////////////
	
	/**
	* Recolors all buttons. Will recolor for either view
	*/
	private void recolor(){
		for(Component c : gameGrid.getComponents()) {
			c.setBackground(base.dark);
		}
		for(EButton e: buttons){
			e.setOpaque(true);
			//Color all blank
			e.setBackground(base.light);

			//Color the field in play
			if(e.parentField == theGame.getFieldInPlay() || theGame.getFieldInPlay() == -1) {
				e.getParent().setBackground(theGame.getActivePlayer()? player2.field : player1.field);
			}
			int t = theGame.getField(e.parentField).getTile(e.content).getOwner();
			//Color taken spaces
			if(t == 1) e.setBackground(player1.light);
			if(t == 2) e.setBackground(player2.light);
			//Color last played
			if(e.index() == theGame.getLastIndex()) {
				e.setBackground(theGame.getActivePlayer()? player1.dark : player2.dark);
			}
		}
		gameGrid.setBackground(base.dark);
	}
	
	///////////////////////////////////////
	public void endView() {} //End
	///////////////////////////////////////
	
	public Game(){
		theGame = new Board();
		gameWon = false;
		base = new Scheme();
		player1 = new Scheme(0);
		player2 = new Scheme(3);
	//	initFrame();
	}
	
	public static void main(String[] args){
		Game g = new Game();
		g.setupMainMenu();
	}
}
