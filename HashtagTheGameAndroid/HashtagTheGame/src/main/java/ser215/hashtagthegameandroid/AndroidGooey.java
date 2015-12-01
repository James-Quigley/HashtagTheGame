package ser215.hashtagthegameandroid;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AndroidGooey extends AppCompatActivity {
    private Button[] buttons = new Button[81];
    private Board theGame;
    //private JFrame window;
    //private JPanel grid;
    //private JLabel statusBar, turnIndicator;
    private final int GAME_DIM = 600;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullviewlayout);
        theGame = new Board();
        for (int i = 0; i < 81; i++) {
            String buttonID = "button"+(i+1);
            int id = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = ((Button) findViewById(id));
            buttons[i].setText(i + "");
            buttons[i].setTextColor(Color.TRANSPARENT);
        }
        recolor();
    }

    public void createToast(String message){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    public void buttonClicked(View view){
        Button button = (Button)view;

        int buttonID = Integer.parseInt(button.getText().toString());
        //Field clickedField = theGame.getField((int)buttonID/9); //Gets the field in question
        //Tile clickedTile = theGame.getTile((int)buttonID/9, buttonID%9); //Gets the tile in question
        //boolean gameWon = theGame.checkIfWon();
        //int fip = theGame.getFieldInPlay();
        //end conditions
        int move = theGame.makePlayAt((int)buttonID/9, buttonID%9);
        if (move == 0) {//invalid move
            createToast("Invalid Move");
        }
        if (move == 1) {//valid move made, new field in play
        }
        if (move == 2) {//valid move, choose field in play
        }
        if (move == 3) {//game over
            endView();
        }

        recolor();
    }

    protected void recolor(){//recolors all buttons. will recolor for either view
        for(int i = 0; i < 81; i++){
            int t = theGame.getField((int)i/9).getTile(i%9).getOwner();
            if(t == 0) buttons[i].setBackgroundColor(0xFFFFFFFF); // 0xAARRGGBB
            if(t == 1) buttons[i].setBackgroundColor(0xFFC34C1D);
            if(t == 2) buttons[i].setBackgroundColor(0xFF1F42F0);
            if(theGame.getLastIndex() == i) {
                buttons[i].setBackgroundColor(0xFF98291E);
            }
        }

    }
    public void endView() {
        theGame.reset();
    }

}
