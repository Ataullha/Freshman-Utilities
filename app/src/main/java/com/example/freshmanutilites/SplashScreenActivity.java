package com.example.freshmanutilites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;
// splash screen is just a screen with a good UI for attracting the user . It has nothing to do with the work of this project
public class SplashScreenActivity extends AppCompatActivity {

    // progressbar for the splash screen progress process UI
    private ProgressBar progressBar;
    // int progress for tracking the progress variable
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // for full screen feature of the screen in your device
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        progressBar = findViewById(R.id.progressBarId);

        // thread for doWork() - what to do in the splash screen
        // startApp() - what to do after the Thread.sleep(time ms) is over
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
            }
        });

        thread.start();

    }
    private void startApp() {
        Intent intent = new Intent(SplashScreenActivity.this,RegisterActivity.class);
        //Intent intent = new Intent(SplashScreenActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void doWork() {

        for(progress = 20 ; progress<100 ; progress+=60) {

            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}