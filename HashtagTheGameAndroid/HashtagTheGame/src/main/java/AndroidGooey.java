public class AndroidGooey{
    private EButton[] buttons;
    private Board theGame;
    //private JFrame window;
    //private JPanel grid;
    //private JLabel statusBar, turnIndicator;
    private final int GAME_DIM = 600;
    private class EButton{
        private int parentField; //index of parent field in game (0-8)
        private int content; //index of tile in its parent frame (0-8)
        public EButton(int f, int t){
            parentField = f;
            content = t;
        }

    }



    private class ButtonListener{
        public void actionPerformed() {
            EButton butt = new EButton(0, 0);//(EButton)e.getSource(); //Loads the button information with data values
            Field clickedField = theGame.getField(butt.parentField); //Gets the field in question
            Tile clickedTile = theGame.getTile(butt.parentField, butt.content); //Gets the tile in question

            boolean gameWon = theGame.checkIfWon();
            int fip = theGame.getFieldInPlay();
            //end conditions
            if (fip == -1) {//if in full view
                if (!clickedField.isFull()) {
                    subView(butt.parentField);
                } else {
                }//alert user that the field is full
            } else {//already in a subview
                int move = theGame.makePlayAt(butt.parentField, butt.content);

                if (move == 0) {
                    //alert user that the tile isn't available
                }
                if (move == 1) {
                    subView(theGame.getFieldInPlay());
                }
                if (move == 2) {
                    fullView();
                }
                if (move == 3) {
                    endView();
                }
            }
        }
    }
    public void initButtons(){
        buttons = new EButton[81];
        for(int i = 0;i<81;i++){
            buttons[i] = new EButton(i/9,i%9);
            //buttons[i].addActionListener(new ButtonListener());
        }

    }
    public void initFrame(){
        initButtons();
        //window = new JFrame();


        //window.setLayout(new FlowLayout());
        fullView();
        //statusBar = new JLabel("EMPTY");
        //turnIndicator = new JLabel("PLAYER 1");

        //window.add(grid);
        //window.add(statusBar);
        //window.add(turnIndicator);
		/*
		initiate frame
		add buttons
		add status bar
		add turn indicator
		*/
        //window.pack();
        //window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //window.setVisible(true);
    }
    public void fullView(){//sets grid to 9x9 view
        //grid = new JPanel(new GridLayout(9,9));

        for(int i = 0;i<3;i++)
            for(int j = 0;j<3;j++)
                for(int k = 0;k<3;k++)
                    k = k;
                    //grid.add(buttons[(i*3)+(j*9)+k]);
        for(int i = 9;i<12;i++)
            for(int j = 0;j<3;j++)
                for(int k = 0;k<3;k++)
                    k=k;
                    //grid.add(buttons[(i*3)+(j*9)+k]);
        for(int i = 18;i<21;i++)
            for(int j = 0;j<3;j++)
                for(int k = 0;k<3;k++)
                    k=k;
                    //grid.add(buttons[(i*3)+(j*9)+k]);
        //for(int i = 0;i<81;i++) grid.add(buttons[i]);
        recolor();
        //grid.setPreferredSize(new Dimension(GAME_DIM, GAME_DIM));

    }
    public void subView(int f){
    }
    private void recolor(){//recolors all buttons. will recolor for either view
        for(EButton e: buttons){
            int t = theGame.getField(e.parentField).getTile(e.content).getOwner();
            //e.setOpaque(true);
            //if(t == 0) e.setBackground(Color.WHITE);
            //if(t == 1) e.setBackground(Color.ORANGE);
            //if(t == 2) e.setBackground(Color.BLUE);
            if(theGame.getLastIndex() == e.parentField*9+e.content){}
                //e.setBackground(Color.RED);
        }

    }
    public void endView() {} //End
    public AndroidGooey(){
        theGame = new Board();
        initFrame();
    }
    public static void main(String[] args){
        AndroidGooey g = new AndroidGooey();
    }
}
