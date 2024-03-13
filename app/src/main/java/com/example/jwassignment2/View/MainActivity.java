package com.example.jwassignment2.View;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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
    public int activePlayer = 0; //0 for yellow, 1 for red

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
        counts.setTranslationY(-1500f); //move the token up
        if (activePlayer == 0) {
            counts.setImageResource(R.drawable.yellow);
            activePlayer = 1;
        } else {
            counts.setImageResource(R.drawable.red);
            activePlayer = 0;
        }
        counts.animate().translationYBy(1500f).rotation(3600).setDuration(300); //drop the token
    }
}