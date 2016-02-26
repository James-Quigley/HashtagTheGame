package poly_cs_club.hashtagthegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends Activity {

    private Board m_Board;
    private Player m_PlayerOne;
    private Player m_PlayerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        String gameType = intent.getStringExtra("gameType");

        m_Board = new Board();
        m_PlayerOne = new Player("PlayerOne", 0); //Always Human
        m_PlayerOne.setTurn(true);

        switch(gameType){
            case("single"):
                m_PlayerTwo = new Player("PlayerTwo", 1); //P2 is a Bot
            case("local"):
                m_PlayerTwo = new Player("PlayerTwo", 0); //P2 is local human
            case("online"):
                m_PlayerTwo = new Player("PlayerTwo", 2); //P2 is online human
        }
        m_PlayerTwo.setTurn(false);

        //m_Turn = false;
        setContentView(R.layout.activity_game);
    }

    public void takeTurn(Player player,Location selectedTile)
    {
        System.out.println(getCurrentPlayer().getName()+ "took a turn.");

        if(!m_Board.IsTileFull(selectedTile))
        {
            m_Board.setTileOwner(player, selectedTile);
            String id = "field" + selectedTile.x_Field + "" + selectedTile.y_Field + "button" + selectedTile.x_Tile + "" + selectedTile.y_Tile;
            int resID = getResources().getIdentifier(id, "id", "poly_cs_club.hashtagthegame");
            Button tile = (Button) findViewById(resID);
            if (getCurrentPlayer().getName().equals("PlayerOne")){
                tile.setBackgroundColor(getResources().getColor(R.color.blue_900));
            }
            else{
                tile.setBackgroundColor(getResources().getColor(R.color.red_900));
            }
            switchTurns();
            if(ifFieldIsTaken(selectedTile))
            {
                //update Field
                if(ifGameWon())
                {
                    Toast.makeText(this, player.getName() + " won the game!",
                            Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(this, MainMenu.class);
                    this.startActivity(myIntent);
                }
            }

            Location nextField = new Location(selectedTile.x_Tile,selectedTile.y_Tile,-1,-1);
            if (m_Board.isFieldFull(nextField)){
                nextField = new Location(-1,-1,-1,-1);
            }
            m_Board.setFieldInPlay(nextField);
            updateAvailableTiles();
        }else
        {
            //send message that tile is taken...
        }
        TextView player1Score = (TextView) findViewById(R.id.p1Score);
        TextView player2Score = (TextView) findViewById(R.id.p2Score);
        TextView fieldsLeft = (TextView) findViewById(R.id.fieldsLeft);

        player1Score.setText(m_PlayerOne.getScore() + "");
        player2Score.setText(m_PlayerTwo.getScore() + "");
        fieldsLeft.setText(m_Board.getM_WinnableFields() + "");
    }

    private void updateAvailableTiles(){
        if (m_Board.getFieldInPlay().x_Field == -1){
            enableAllTiles();
        }
        else{
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    for (int k = 0; k < 3; k++){
                        for (int l = 0; l < 3; l++){
                            String id = "field" + i + "" + j + "button" + k + "" + l;
                            int resID = getResources().getIdentifier(id, "id", "poly_cs_club.hashtagthegame");
                            Button tile = (Button) findViewById(resID);
                            if (m_Board.getFieldInPlay().x_Field == i && m_Board.getFieldInPlay().y_Field == j) {
                                tile.setEnabled(true);
                            }
                            else{
                                tile.setEnabled(false);
                            }
                        }
                    }
                }
            }
        }
    }

    private void enableAllTiles(){
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 2; j++){
                for (int k = 0; k < 2; k++){
                    for (int l = 0; l < 2; l++){
                        String id = "field" + i + "" + j + "button" + k + "" + l;
                        int resID = getResources().getIdentifier(id, "id", "poly_cs_club.hashtagthegame");
                        Button tile = (Button) findViewById(resID);
                        tile.setEnabled(true);
                    }
                }
            }
        }
    }

    private boolean ifFieldIsTaken(Location loc)
    {
       return !m_Board.getField(loc).getOwner().equals("Unknown");
    }
    private boolean ifGameWon()
    {
        return m_Board.isGameWon(m_PlayerOne.getScore(), m_PlayerTwo.getScore());
    }
    private Player getCurrentPlayer() {
        if (m_PlayerOne.isTurn()) {
            return m_PlayerOne;
        } else {
            return m_PlayerTwo;
        }
    }
    private void switchTurns()
    {
        if(getCurrentPlayer().getName().equals("PlayerOne"))
        {
            m_PlayerOne.setTurn(false);
            m_PlayerTwo.setTurn(true);
        }else
        {
            m_PlayerTwo.setTurn(false);
            m_PlayerOne.setTurn(true);
        }
    }
    public void onTileClick(View view) {
        String idString = view.getTag().toString();
        String[] locationValues = idString.split(",");
        Integer[] locationIntValues = new Integer[locationValues.length];
        for (int i = 0; i < locationValues.length;i++){
            locationIntValues[i] = Integer.parseInt(locationValues[i]);
        }

        //Location of the clicked tile
        Location location = new Location(locationIntValues[0],locationIntValues[1],locationIntValues[2],locationIntValues[3]);
        System.out.println("Tile clicked! Location: " + location.toString());
        //switch colors
        if(m_Board.isFieldAvailable(location))
        {
            System.out.println("Field is Available");
            takeTurn(getCurrentPlayer(), location);
        }

    }
}
