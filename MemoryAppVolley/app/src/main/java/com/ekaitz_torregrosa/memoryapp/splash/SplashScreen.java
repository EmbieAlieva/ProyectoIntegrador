package com.ekaitz_torregrosa.memoryapp.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.ekaitz_torregrosa.memoryapp.R;
import com.ekaitz_torregrosa.memoryapp.activities.MainActivity;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splashscreen);

        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            };
        },SPLASH_TIME);
    }
}
