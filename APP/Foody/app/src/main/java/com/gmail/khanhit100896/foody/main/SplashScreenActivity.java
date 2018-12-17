package com.gmail.khanhit100896.foody.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gmail.khanhit100896.foody.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        startAnimation();
    }

    private void startAnimation() {

        Thread mThread = new Thread() {

            @Override
            public void run() {
                super.run();
                int waited = 0;
                while (waited < 4500) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    waited += 100;
                }
                SplashScreenActivity.this.finish();
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        };
        mThread.start();
    }
}
