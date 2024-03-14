package com.example.jwassignment2.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jwassignment2.R;
import com.example.jwassignment2.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;  // ViewBinding setup
    public int activePlayer = 0; //0 for yellow, 1 for red, 2 for empty(initial state); This represents the current player
    boolean gameActive = true; //game is active, for checking if the game is still active

    //record the condition of the grid, index 0-8. 2 means unplayed(empty state), 0 means occupied by yellow, 1 means occupied by red
    public int gridState[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    //winning positions records the winning positions of the game
    public int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    boolean gameIsOver = false; //assume the game is not over (there is still empty position to play

    public String playerOne;
    public String playerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        playerOne = getIntent().getStringExtra("playerOne");
        playerTwo = getIntent().getStringExtra("playerTwo");


        binding = ActivityMainBinding.inflate(getLayoutInflater());  // ViewBinding setup
        setContentView(binding.getRoot());  // ViewBinding setup

        // bind imageView1 to imageView9 to the dropToken method
        for (int i = 0; i < binding.gridLayout.getChildCount(); i++) { //loop through the gridLayout children
            //bind the gridLayout children to the dropToken method
            binding.gridLayout.getChildAt(i).setOnClickListener(view -> dropToken(view));
        }

    }

    public void dropToken(View view) {
        ImageView counts = (ImageView) view; //cast the view to an ImageView
//        Toast.makeText(this, counts.getTag().toString(), Toast.LENGTH_SHORT).show(); //show the tag of the ImageView clicked

        //get the tag of the ImageView clicked so we can know which position has been clicked
        int tappedCounter = Integer.parseInt(counts.getTag().toString()); //tag is set to 0-8 in the xml file

        //check if the game is still active and the position has not been occupied by any player
        if(gameActive && gridState[tappedCounter] == 2){
            counts.setTranslationY(-1500f); //set the starting point of animation
            gridState[tappedCounter] = activePlayer; //store the value of the activePlayer in the gridState array
        /* if someone play first, activePlayer will be 0 (yellow), and drop the token at imageView1 (tag 0), then gridState[0]
        will be 0, means that that position has been played by yellow, the first player, and so on.
        */

            //drop the token(animation) and change the token color to yellow or red
            if (activePlayer == 0) {
                counts.setImageResource(R.drawable.yellow); //select yellow token
                activePlayer = 1; //change the activePlayer to 1 (change to red for the next player)
            } else {
                counts.setImageResource(R.drawable.red); //select red token
                activePlayer = 0; //change the activePlayer to 1 (change to red for the next player)
            }
            counts.animate().translationYBy(1500f).rotation(3600).setDuration(300); //animation: drop the token

            //check if someone has won
            //loop through the winning positions. One row at a time through the winningPositions 2D array
            for (int[] winningPosition : winningPositions) { //take one array at a time, first one is {0, 1, 2}, and so on..
                if (gridState[winningPosition[0]] == gridState[winningPosition[1]] &&
                        gridState[winningPosition[1]] == gridState[winningPosition[2]] &&
                        gridState[winningPosition[0]] != 2) {
                    // Someone has won!

                    String winner = "";
                    if (activePlayer == 1) { //yellow wins
                        winner = playerOne;
                    } else { //red wins
                        winner = playerTwo;
                    }
//                    Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();
                    gameActive = false;

                    //show the winner
                    binding.winnerTextView.setText("Congrats "+ winner + "!");
                    gameIsOverMsgDisplay(view);
                }
            }
            //check if it's a draw
            // if there is no winner and all the positions have been played
            if(gameActive) {
                gameIsOver = true; //assume the game is over
                for (int counterState : gridState) {
                    if (counterState == 2) {
                        gameIsOver = false; //if there is still empty position, the game is not over
                        break;
                    }
                }
                if (gameIsOver) {
//                    Toast.makeText(this, "It's a draw!", Toast.LENGTH_SHORT).show();
                    gameActive = false;

                    //show the draw message
                    binding.winnerTextView.setText("It's a draw!");
                    gameIsOverMsgDisplay(view);
                }
            }
        }
    }

    public void gameIsOverMsgDisplay(View view){
        binding.winnerTextView.setVisibility(View.VISIBLE);

        //show the play again button & quit button
        binding.playAgainBtn.setOnClickListener(v -> playAgain(v));
        binding.playAgainBtn.setVisibility(View.VISIBLE);

        binding.quitBtn.setOnClickListener(v -> quitGame(v));
        binding.quitBtn.setVisibility(View.VISIBLE);
    }

    public void playAgain(View view){
        //hide the winnerTextView, playAgainBtn, and quitBtn
        binding.winnerTextView.setVisibility(View.INVISIBLE);
        binding.playAgainBtn.setVisibility(View.INVISIBLE);
        binding.quitBtn.setVisibility(View.INVISIBLE);

        //reset the gameActive to true
        gameActive = true;

        //reset the activePlayer to 0
        activePlayer = 0;

        //reset the gridState to 2
        for (int i = 0; i < gridState.length; i++) {
            gridState[i] = 2;
        }

        //reset the token images to empty
        for (int i = 0; i < binding.gridLayout.getChildCount(); i++) {
            ((ImageView) binding.gridLayout.getChildAt(i)).setImageResource(0); //0 means empty
        }

    }

    public void quitGame(View view){
        // go back to the PlayerActivity
        Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
        startActivity(intent);
    }
}