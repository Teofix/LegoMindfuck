package com.adev.android.legomindfuQ.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.adev.android.legomindfuQ.Motor;
import com.adev.android.legomindfuQ.R;

import static com.adev.android.legomindfuQ.Activity.MainMenuActivity.ev3;

public class MultiMotorActivityP1 extends AppCompatActivity {

    private Motor mMotorBase;
    private Motor mMotorBraccio;
    private Motor mMotorMano;

    private ImageButton mButtonBaseLeft;
    private ImageButton mButtonBaseRight;

    private ImageButton mButtonBraccioLeft;
    private ImageButton mButtonBraccioRight;

    private Button mButtonStop;

    private int mSpeed = 5;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_multi_p1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mButtonStop = findViewById(R.id.stopButtonP1);
        mButtonStop.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainMenuActivity.class)));

        if (mMotorBase == null) {
            mMotorBase = new Motor(1, 180);
        }

        if (mMotorBraccio == null) {
            mMotorBraccio = new Motor(4, 180);
        }

        if (mMotorMano == null) {
            mMotorMano = new Motor(7, 180);
        }

        mButtonBaseLeft = (ImageButton) findViewById(R.id.m_motor1_left);
        mButtonBaseLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ev3.sendMessage(mMotorBase.motorOn(mSpeed, "-"));
                        // PRESSED
                        return false; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        ev3.sendMessage(mMotorBase.motorOff());
                        // RELEASED
                        return false; // if you want to handle the touch event
                }
                return false;
            }
        });

        mButtonBaseRight = (ImageButton) findViewById(R.id.m_motor1_right);
        mButtonBaseRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ev3.sendMessage(mMotorBase.motorOn(mSpeed, "+"));
                        // PRESSED
                        return false; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        ev3.sendMessage(mMotorBase.motorOff());
                        // RELEASED
                        return false; // if you want to handle the touch event
                }
                return false;
            }
        });

        mButtonBraccioLeft = (ImageButton) findViewById(R.id.m_motor2_left);
        mButtonBraccioLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ev3.sendMessage(mMotorBraccio.motorOn(mSpeed, "+"));
                        // PRESSED
                        return false; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        ev3.sendMessage(mMotorBraccio.motorOff());
                        // RELEASED
                        return false; // if you want to handle the touch event
                }
                return false;
            }
        });

        mButtonBraccioRight = (ImageButton) findViewById(R.id.m_motor2_right);
        mButtonBraccioRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ev3.sendMessage(mMotorBraccio.motorOn(mSpeed, "-"));
                        // PRESSED
                        return false; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        ev3.sendMessage(mMotorBraccio.motorOff());
                        // RELEASED
                        return false; // if you want to handle the touch event
                }
                return false;
            }
        });
    }

    private int degreeToPercentage(int degree) {
        return degree * 100 / 360;
    }

    private int percentageToDegree(int percentage) {
        return percentage * 360 / 100;
    }
}