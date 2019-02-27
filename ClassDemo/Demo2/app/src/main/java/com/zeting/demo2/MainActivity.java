package com.zeting.demo2;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Range;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button rollButton;
        rollButton = (Button) findViewById(R.id.roll_button);



        final ImageView leftDice = (ImageView) findViewById(R.id.dice1);
        final ImageView rightDice = (ImageView) findViewById(R.id.dice2);

        final int[] diceArray = {
                R.drawable.dice1,
                R.drawable.dice2,
                R.drawable.dice3,
                R.drawable.dice4,
                R.drawable.dice5,
                R.drawable.dice6
        };

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftDice.setImageResource(diceArray[getRandomDice()]);
                rightDice.setImageResource(diceArray[getRandomDice()]);

                Log.d("Demo2", "Button hit!");
            }
        });

    }
    private int getRandomDice()
    {
        Random randomNumberGen = new Random();
        return randomNumberGen.nextInt(6);
    }
}
