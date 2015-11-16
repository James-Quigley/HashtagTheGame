import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class TheGameGooey{
	private EButton[] buttons;
	private Board theGame;
	private JFrame window;
	private JPanel gameGrid;
    
	private JLabel statusBar, turnIndicator;
	private boolean mode; //true = full view, false = subView
	private final int GAME_DIM = 600;
	private class EButton extends JButton{

		private int parentField; //index of parent field in game (0-8)
		private int content; //index of tile in its parent frame (0-8)
		public EButton(int f, int t){
			parentField = f;
			content = t;
		}

	}

	private void changeTurn() {
		theGame.changeActivePlayer();

		if (theGame.getActivePlayer()) {
			turnIndicator.setBackground(new Color(109,208,247));
			//turnIndicator.setText("PLAYER 2");
		}
		else {
			turnIndicator.setBackground(new Color(246,152,157));
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

                if(theGame.getFieldInPlay() == -1) { //Freeplay field
                    if(clickedTile.getOwner() == 0) { //Clicked Tile is free
                        clickedTile.setOwner(theGame.getActivePlayer()? 2 : 1); //Set owner
                        theGame.setLastTile(butt.parentField*9+butt.content);

                        if (clickedField.checkIfWon()) { //Check if field is won
                            theGame.incScore(theGame.getActivePlayer()); //Increases the score of the player who won
                            if(theGame.checkIfWon()){
                            	theGame.reset();
                            	recolor();
                            	return;						 //Check if game is won if field was won
                            } 
                            statusBar.setText("Player " + (theGame.getActivePlayer()? 2 : 1) + " won a field.");
                        }
                        else if (clickedField.isFull() && clickedField.getOwner() == 0) { //Check if field was catsgamed
                      	    theGame.decWinnableFields(); //Decreases winnable fields due to cats game
                            if(theGame.checkIfWon()){
                            	theGame.reset();
                            	recolor();
                            	return;						 //Check if game is won if field was catsgamed
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
                        statusBar.setText("Tile already played on.");
                    }
                }
                else if(theGame.getFieldInPlay() == butt.parentField) { //Checks if the player clicked a button in the correct field
                    if(clickedTile.getOwner() == 0) {
                        clickedTile.setOwner(theGame.getActivePlayer()? 2 : 1); //Set owner
                        theGame.setLastTile(butt.parentField*9+butt.content);

                        if (clickedField.checkIfWon()) { //Check if field is won
                            theGame.incScore(theGame.getActivePlayer()); //Increases the score of the player who won
                            if(theGame.checkIfWon()){
                            	theGame.reset();
                            	recolor();
                            	return;						 //Check if game is won if field was won
                            } 
                            statusBar.setText("Player " + (theGame.getActivePlayer()? 2 : 1) + " won a field.");
                        }
                        else if (clickedField.isFull() && clickedField.getOwner() == 0) { //Check if field was catsgamed
                      	    theGame.decWinnableFields(); //Decreases winnable fields due to cats game
                            if(theGame.checkIfWon()){
                            	theGame.reset();
                            	recolor();
                            	return;					 //Check if game is won if field was catsgamed
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
                        statusBar.setText("Stop being so edgy and pick an open tile");
                    }
                }
				else {
                    statusBar.setText("You clicked the wrong field, punk.");
                }
                recolor();

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
		fullView();
		statusBar = new JLabel("           ");
		turnIndicator = new JLabel("                                   ");
		turnIndicator.setOpaque(true);

		window.add(gameGrid);
		window.add(statusBar);
		window.add(turnIndicator);
		/*
		initiate frame
		add buttons
		add status bar
		add turn indicator
		*/
		window.setLayout(new BoxLayout(window.getContentPane(),BoxLayout.Y_AXIS));
		window.pack();
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	public void fullView(){//sets grid to 9x9 view
		JPanel frameGrid;
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
			if(theGame.getLastTile() == e.parentField*9+e.content && !theGame.isFirstTurn()){
				Color c = e.getBackground();
				e.setBackground(new Color(c.getRed()-50,c.getGreen()-50,c.getBlue()-50));
			}
		}
		Color active = theGame.getActivePlayer()? new Color(109,208,247):new Color(246,152,157);
		
		gameGrid.setBackground(new Color(255,255,255));
		for(Component c : gameGrid.getComponents())
			c.setBackground(new Color(255,255,255));
		if(theGame.getFieldInPlay() != -1)
			gameGrid.getComponent(theGame.getFieldInPlay()).setBackground(active);
		else if(theGame.getFieldInPlay() == -1){
			for(Component c: gameGrid.getComponents())
				c.setBackground(active);
			gameGrid.setBackground(active);
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
