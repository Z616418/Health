package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frontpage);
        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask(){
            @Override
            public void run(){
                Intent intent1=new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent1);
                StartActivity.this.finish();
            }
        };
        timer.schedule(timerTask,1000*3);
    }
}