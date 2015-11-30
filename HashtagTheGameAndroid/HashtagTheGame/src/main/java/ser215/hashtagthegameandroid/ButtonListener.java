package ser215.hashtagthegameandroid;
import android.content.*;
import android.view.*;
import android.app.*;
/**
 * Created by Kirevikyn on 11/23/2015.
 */
public class ButtonListener implements View.OnClickListener {
    public void onClick(View v){
        EBuotton butt = (EBuotton)v;
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

            if (move == 0) {//invalid move
            }
            if (move == 1) {//valid move made, new field in play
            }
            if (move == 2) {//valid move, choose field in play
            }
            if (move == 3) {//game over
                endView();
            }
        }
        //do stuff lel
        //do some maths to determine which button was pressed
        // go fokin 'am m8
    }
}
