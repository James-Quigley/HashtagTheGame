import java.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MainMenu{
	private Game gameboard;
	private JFrame window;
	private JPanel grid;
	private Jpanel frameGrid;
	private JLabel title;


}
	private JFrame mainMenu;
	private JLabel  mainMenuTitle, mainMenuColorCommands;
	private JButton mainMenuStart, mainMenuOneSelect, mainMenuTwoSelect;
	private TButton [] mainMenuThemes;
	private JPanel mainMenuPanel1, mainMenuPanel2, mainMenuPanel3, mainMenuPanel4;
	private boolean colorSelect;




		mainMenuColorCommands = new JLabel("oky");

		mainMenuColorCommands.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainMenuColorCommands.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));


		//Initalizes buttons for main menu
		mainMenuStart = new JButton("Start");
		mainMenuStart.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainMenuOneSelect = new JButton();
		mainMenuTwoSelect = new JButton();
		themeButtons();

		//mainMenuPanel1.add(mainMenuTitle);
		//mainMenuPanel2.add(mainMenuStart);
		//mainMenuPanel3.add(mainMenuColorCommands);
		mainMenuPanel4.add(mainMenuOneSelect);
		mainMenuPanel4.add(mainMenuTwoSelect);
		for(int i = 0; i < 5; i++) {
			mainMenuPanel5.add(mainMenuThemes[i]);
		}
		mainMenuPanel5.setPreferredSize(new Dimension(300,70));
		mainMenuPanel5.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));



		mainMenu.add(mainMenuTitle);
		mainMenu.add(mainMenuStart);
	//	mainMenu.add(mainMenuColorCommands);
	//	mainMenu.add(mainMenuPanel4);
	//	mainMenu.add(mainMenuPanel5);
	//	mainMenu.setLayout(new BoxLayout(mainMenu.getContentPane(),BoxLayout.Y_AXIS));

		mainMenu.setSize(300,300);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainMenu.setLocation(dim.width/2-mainMenu.getSize().width/2, dim.height/2-mainMenu.getSize().height/2);
		mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenu.setVisible(true);
		mainMenu.pack();

		mainMenuStartClicked startClicked = new mainMenuStartClicked();