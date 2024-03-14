package com.example.jwassignment2.View;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* The following code generated automatically would create an error when using ViewBinding
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_main);  // we don't need this line anymore as we are using ViewBinding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ConstraintLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
         */

        binding = ActivityMainBinding.inflate(getLayoutInflater());  // ViewBinding setup
        setContentView(binding.getRoot());  // ViewBinding setup

        // bind imageView1 to imageView9 to the dropToken method
        int imageViewCount = 9; // 9 ImageViews
        for (int i = 0; i < imageViewCount; i++) {
            int imageViewId = getResources().getIdentifier("imageView" + (i + 1), "id", getPackageName());
            ImageView imageView = findViewById(imageViewId);
            imageView.setOnClickListener(view ->
                    dropToken(view) //view refers to the ImageView that was clicked
            );
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
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();
                    gameActive = false;
                }
            }
        }

        
    }
}