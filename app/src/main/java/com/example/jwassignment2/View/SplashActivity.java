package com.example.jwassignment2.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jwassignment2.R;

public class SplashActivity extends AppCompatActivity {

    ImageView splashImgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.splashactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.splashImg), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        splashImgView = findViewById(R.id.splashImgView);
        splashImgView.setAlpha(0f);
        splashImgView.animate().alpha(1f).setDuration(2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splashImgView.animate().alpha(0f).setDuration(1000);
            }
        }, 2000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}