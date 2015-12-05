package ser215.hashtagthegameandroid;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Gravity;
import android.widget.Button;
import android.widget.Toast;
import android.widget.GridLayout;
import android.widget.TextView;

public class AndroidGooey extends AppCompatActivity {
    private Button[] buttons = new Button[81];
    private GridLayout[] fields = new GridLayout[9];
    private Board theGame;
    //private JFrame window;
    //private JPanel grid;
    //private JLabel statusBar, turnIndicator;
    private final int GAME_DIM = 600;
    private TextView p1Score, p2Score;


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
            buttons[i].setHeight(20);
            buttons[i].setWidth(20);
        }
        for(int i = 0;i<9;i++) {
            String gridID = "grid" + i;
            int id = getResources().getIdentifier(gridID, "id", getPackageName());
            fields[i] = (GridLayout)findViewById(id);
        }
        p1Score = (TextView)findViewById(getResources().getIdentifier("textView","id",getPackageName()));
        p2Score = (TextView)findViewById(getResources().getIdentifier("textView2", "id", getPackageName()));
        p1Score.setBackgroundColor(0xFFff3939);
        p2Score.setBackgroundColor(0xFF1F42F0);
        p1Score.setText("0");
        p2Score.setText("0");
        //p1Score.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        p1Score.setGravity(Gravity.CENTER_VERTICAL);
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
            createToast("Player " + theGame.getWinner() + " wins!");
            endView();
        }
        p1Score.setText(theGame.getP1Score()+"");
        p2Score.setText(theGame.getP2Score()+"");
        recolor();
    }

    protected void recolor(){//recolors all buttons. will recolor for either view
        for(int i = 0; i < 81; i++){
            int t = theGame.getField((int)i/9).getTile(i%9).getOwner();
            if(t == 0) buttons[i].setBackgroundColor(0xFFffffff); // 0xAARRGGBB
            if(t == 1) buttons[i].setBackgroundColor(0xFFff3939);
            if(t == 2) buttons[i].setBackgroundColor(0xFF1F42F0);
            if(theGame.getLastIndex() == i) {
                if(t == 1) buttons[i].setBackgroundColor(0xFF98291E);
                else buttons[i].setBackgroundColor(0xFF031948);
            }
        }
        for(int i = 0;i<9;i++){
            int fip = theGame.getFieldInPlay();
            int color = (theGame.getActivePlayer() == 1)? 0xFFffa6a6 : 0xFFa6e2ff;
            if(fip == -1 || i == fip)
                fields[i].setBackgroundColor(color);
            else
                fields[i].setBackgroundColor(0xFFFFFFFF);
            //10DBF9 = light blue
            //FF00B4 = pink

        }
    }
    public void endView() {
        theGame.reset();
    }

}
