package com.example.geekshivam.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView timer;
    EditText press;
    Button come;
    LinearLayout click, moon;
    TextView tv;

    private CountDownTimer countDownTimer;
    private long timeLeft = 300000;
    private boolean timing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        press = findViewById(R.id.type);
        timer = findViewById(R.id.timer);
        come = findViewById(R.id.come);
        moon = findViewById(R.id.moon);
        tv = findViewById(R.id.text12);
        final MediaPlayer sound = MediaPlayer.create(this, R.raw.tada);
        click = findViewById(R.id.clickany);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "lick below your Luck!", Toast.LENGTH_LONG).show();
            }
        });

        moon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(in);
                sound.start();
                Toast.makeText(MainActivity.this, "Congrats", Toast.LENGTH_LONG);
            }
        });

        come.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(press.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Bug-1 Found!! Oh! Yeah!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Bug-2 Found!! only one Bug left!!", Toast.LENGTH_LONG).show();
                }
                startStop();
            }
        });
    }

    public void startStop() {
        if (timing) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = 1;
                updateTimer();
            }
            @Override
            public void onFinish() {

            }
        }.start();
        timing = true;
    }

    public void stopTimer() {
        countDownTimer.cancel();
        timing = true;
    }

    public void updateTimer() {
        int min = (int) timeLeft / 30000;
        int sec = (int) timeLeft / 30000 / 1000;

        String timeleftText;

        timeleftText = "" + min;
        timeleftText += ":";
        if (sec < 5) timeleftText += "0";
        timeleftText += sec;
        timer.setText(timeleftText);

    }

}