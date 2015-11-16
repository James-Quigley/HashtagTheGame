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

			setStatus(""); //Resets the Status text

			//If players could have chosen any field. 
            if(theGame.getFieldInPlay() == -1) {
                if(clickedTile.getOwner() == 0) { //The tile is free
                    clickedTile.setOwner(theGame.getActivePlayer()? 2 : 1); //Set owner
                    if (clickedField.checkIfWon()) { //Check if field is won
                        theGame.incScore(theGame.getActivePlayer()); //Increases the score of the player who won
                        if(theGame.checkIfWon()){ //Check if game is won if field is won
                        	theGame.reset();
                        	recolor();
                        	return;	
                        } 
                        setStatus("Player " + (theGame.getActivePlayer()? 2 : 1) + " won a field.");
                    }
                    else if (clickedField.isFull() && clickedField.getOwner() == 0) { //Check if field was catsgamed
                  	    theGame.decWinnableFields(); //Decreases winnable fields due to cats game
                        if(theGame.checkIfWon()){ //Check if game is won if field is won
                        	theGame.reset();
                        	recolor();
                        	return;
                        } 
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
                        	theGame.reset();
                        	recolor();
                        	return;	
                        } 
                        setStatus("Player " + (theGame.getActivePlayer()? 2 : 1) + " won a field.");
                    }
                    else if (clickedField.isFull() && clickedField.getOwner() == 0) { //Check if field was catsgamed
                  	    theGame.decWinnableFields(); //Decreases winnable fields due to cats game
                        if(theGame.checkIfWon()){ //Check if game is won if field is won
                        	theGame.reset();
                        	recolor();
                        	return;
                        } 
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
		//window.setLayout(new BoxLayout(window,BoxLayout.Y_AXIS));

		window.setLayout(new FlowLayout());
		fullView();
		statusBar = new JLabel("");
		turnIndicator = new JLabel("PLAYER 1");

		window.add(gameGrid);
		window.add(statusBar);
		window.add(turnIndicator);
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
		for(EButton e: buttons){
			int t = theGame.getField(e.parentField).getTile(e.content).getOwner();
			e.setOpaque(true);
			if(t == 0) e.setBackground(new Color(137,137,137));
			if(t == 1) e.setBackground(new Color(246,152,157));
			if(t == 2) e.setBackground(new Color(109,208,247));
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
