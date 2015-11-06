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
        private int parentFrame;
        private int content;
        public EButton(int f, int t){
            super();
            parentFrame = f;
            content = t;
        }
        
    }
    private class ButtonListener implements ActionListener{
        private EButton butt;
        public ButtonListener(EButton b){butt = b;}
        public void actionPerformed(ActionEvent e){
            /*
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
            buttons[i].addActionListener(new ButtonListener(buttons[i] ));
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
        grid = new JPanel(new GridLayout(9,9)); 
        for(int i = 0;i<81;i++)
            grid.add(buttons[i]);
    
    
    }
    public void subView(int frame){}
    
    
    public static void test(){
        TheGameGooey g = new TheGameGooey();
        g.initFrame();
        
        
    }
}