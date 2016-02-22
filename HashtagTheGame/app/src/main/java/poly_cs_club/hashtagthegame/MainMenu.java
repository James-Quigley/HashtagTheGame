package poly_cs_club.hashtagthegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void onClick(View view){
        Intent myIntent = new Intent(this, Game.class);
        if(view.getId() == R.id.singlePlayer) {
            myIntent.putExtra("gameType", "single");
        }
        else if (view.getId() == R.id.local){
            myIntent.putExtra("gameType", "local");
        }
        else if (view.getId() == R.id.online){
            myIntent.putExtra("gameType", "online");
        }
        this.startActivity(myIntent);

    }
}
