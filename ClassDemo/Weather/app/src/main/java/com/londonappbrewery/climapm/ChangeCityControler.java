package com.londonappbrewery.climapm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ChangeCityControler extends AppCompatActivity {

    ImageButton mBackButton;
    EditText mEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_city_layout);
        this.mBackButton = findViewById(R.id.backButton);
        this.mEditText = findViewById(R.id.queryET);

        this.mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String newCity = mEditText.getText().toString();
                Intent newCityInten = new Intent(ChangeCityControler.this,WeatherController.class);
                newCityInten.putExtra("City",newCity);
                startActivity(newCityInten);
                return false;
            }
        });


    }
}
