package com.dailydealbd.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.dailydealbd.R;


public class SplashActivity extends AppCompatActivity {


@Override
protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    Thread splash = new Thread()
    {
        public void run()
        {

            try {
                sleep(2*700);
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    };


    splash.start();
}




}