package com.zeting.demo3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView ball = findViewById(R.id.ball);

        Button askButton = findViewById(R.id.AskButton);

        final int[] images = {
                R.drawable.ball1,
                R.drawable.ball2,
                R.drawable.ball3,
                R.drawable.ball4,
                R.drawable.ball5
        };

        askButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ball.setImageResource(images[getRandomBallNumber()]);
            }
        });
    }


    private int getRandomBallNumber()
    {
        Random randomBall = new Random();

        return randomBall.nextInt(5);
    }
}
