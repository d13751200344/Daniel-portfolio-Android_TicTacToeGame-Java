package com.example.jwassignment2.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jwassignment2.Model.Player;
import com.example.jwassignment2.R;
import com.example.jwassignment2.databinding.ActivityMainBinding;
import com.example.jwassignment2.databinding.ActivityPlayerBinding;

public class PlayerActivity extends AppCompatActivity {

    private ActivityPlayerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // set on click listener for play button
        binding.playBtn.setOnClickListener(v -> {
            if (binding.playerOne.getText().toString().isEmpty() && binding.playerTwo.getText().toString().isEmpty()){
                binding.playerOne.setError("Please enter player one's name");
                binding.playerTwo.setError("Please enter player two's name");
            } else if (binding.playerOne.getText().toString().isEmpty()){
                binding.playerOne.setError("Please enter player one's name");
            } else if (binding.playerTwo.getText().toString().isEmpty()){
                binding.playerTwo.setError("Please enter player two's name");
            } else {
                // send the player names to the model
                Player playerOne = new Player(binding.playerOne.getText().toString());
                Player playerTwo = new Player(binding.playerTwo.getText().toString());

                // use getter to get players' name, sending them to MainActivity by Intent and start the MainActivity
                Intent intent = new Intent(PlayerActivity.this, MainActivity.class);
                intent.putExtra("playerOne", playerOne.getName());
                intent.putExtra("playerTwo", playerTwo.getName());
                startActivity(intent);
            }
        });
    }
}