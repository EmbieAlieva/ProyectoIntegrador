package com.ekaitz_torregrosa.memoryapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ekaitz_torregrosa.memoryapp.R;

public class MainActivity extends AppCompatActivity {

    Button buttonPlay, buttonPunctuation, buttonCredits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonPlay = (Button)findViewById(R.id.buttonPlay);
        buttonPunctuation = (Button)findViewById(R.id.buttonPunctuation);
        buttonCredits = (Button)findViewById(R.id.buttonCredits);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
        buttonPunctuation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PunctuationActivity.class);
                startActivity(intent);
            }
        });
        buttonCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreditsActivity.class);
                startActivity(intent);
            }
        });
    }

}