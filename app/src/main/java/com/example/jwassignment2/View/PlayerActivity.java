package com.example.jwassignment2.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jwassignment2.Model.Player;
import com.example.jwassignment2.R;
import com.example.jwassignment2.ViewModel.PlayerViewModel;
import com.example.jwassignment2.databinding.ActivityMainBinding;
import com.example.jwassignment2.databinding.ActivityPlayerBinding;

public class PlayerActivity extends AppCompatActivity {

    private ActivityPlayerBinding binding;
    private PlayerViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new PlayerViewModel();

        // Set observer for LiveData in ViewModel
        viewModel.getPlayerOneName().observe(this, name -> {
            binding.playerOne.setText(name);
        });

        viewModel.getPlayerTwoName().observe(this, name -> {
            binding.playerTwo.setText(name);
        });


        binding.playBtn.setOnClickListener(v -> {
            if (binding.playerOne.getText().toString().isEmpty() || binding.playerTwo.getText().toString().isEmpty()) {
                binding.errorTextView.setVisibility(View.VISIBLE);
            }else{
                // Set player names in ViewModel
                binding.errorTextView.setVisibility(View.INVISIBLE);

                String playerOneName = binding.playerOne.getText().toString();
                String playerTwoName = binding.playerTwo.getText().toString();
                viewModel.setPlayerOneName(playerOneName);
                viewModel.setPlayerTwoName(playerTwoName);

                // Navigate to MainActivity with player names
                Intent intent = new Intent(PlayerActivity.this, MainActivity.class);
                intent.putExtra("playerOne", playerOneName);
                intent.putExtra("playerTwo", playerTwoName);
                startActivity(intent);
            }
        });
    }
}