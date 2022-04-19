package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

import androidx.appcompat.app.AppCompatActivity;



public class Loading extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        //로딩화면 시작.
        Loadingstart();
    }

    private void Loadingstart() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    class ProgressThread extends Thread {
        private ProgressDialog progressBar;

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                SystemClock.sleep(1000);
                progressBar.incrementProgressBy(10);
            }
        }


    }
}
