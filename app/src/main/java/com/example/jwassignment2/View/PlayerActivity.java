package com.example.jwassignment2.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
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

        // Bind EditTexts to player names in ViewModel
        binding.playerOne.setText(viewModel.getPlayerOneName());
        binding.playerTwo.setText(viewModel.getPlayerTwoName());

        binding.playBtn.setOnClickListener(v -> {
            if (binding.playerOne.getText().toString().isEmpty() || binding.playerTwo.getText().toString().isEmpty()) {
                binding.errorTextView.setVisibility(View.VISIBLE);
            }else{
                // Set player names in ViewModel
                binding.errorTextView.setVisibility(View.INVISIBLE);
                viewModel.setPlayerOneName(binding.playerOne.getText().toString());
                viewModel.setPlayerTwoName(binding.playerTwo.getText().toString());

                // Navigate to MainActivity with player names
                Intent intent = new Intent(PlayerActivity.this, MainActivity.class);
                intent.putExtra("playerOne", viewModel.getPlayerOneName());
                intent.putExtra("playerTwo", viewModel.getPlayerTwoName());
                startActivity(intent);
            }
        });
    }
}