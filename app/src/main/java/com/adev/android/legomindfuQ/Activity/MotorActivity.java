package com.adev.android.legomindfuQ.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.adev.android.legomindfuQ.Motor;
import com.adev.android.legomindfuQ.R;

import java.util.Timer;
import java.util.TimerTask;

import static com.adev.android.legomindfuQ.Activity.MainMenuActivity.colors;
import static com.adev.android.legomindfuQ.Activity.MainMenuActivity.ev3;

public class MotorActivity extends AppCompatActivity {

    private Motor mMotorBase;
    private Motor mMotorBraccio;
    private Motor mMotorMano;

    private ImageButton mButtonBaseLeft;
    private ImageButton mButtonBaseRight;

    private ImageButton mButtonBraccioLeft;
    private ImageButton mButtonBraccioRight;

    private ImageButton mButtonManoLeft;
    private ImageButton mButtonManoRight;

    private Switch mSpeedSwitch;

    private Button mPickUpButton;
    private Button mPutDownButton;
    private Button mCheckColorButton;
    private Button mStopButton;

    private int mSpeed = 5;

    Timer timer = new Timer();
    private Double time = 0.0;
    private TextView timeText;

    //Minuti e secondi del timer sono stati spostati come variabili di classe in quanto vengono passate a endgame.class
    private Double mins = 0.0;
    private Double secs = 0.0;

    //variabili per la colorazione dei blocchi
    private Integer numberofblock = 0;
    private ImageButton[] blockplaced = new ImageButton[4];

    private int numclickManoLeft = 0;
    private double istanttimeManoLeft = 0;
    private int totaltimeManoLeft = 0;

    private int numclickManoRight = 0;
    private double istanttimeManoRight = 0;
    private int totaltimeManoRight = 0;

    private int numclickBraccioLeft = 0;
    private double istanttimeBraccioLeft = 0;
    private int totaltimeBraccioLeft = 0;

    private int numclickBraccioRight = 0;
    private double istanttimeBraccioRight = 0;
    private int totaltimeBraccioRight = 0;

    private int numclickBaseLeft = 0;
    private double istanttimeBaseLeft = 0;
    private int totaltimeBaseLeft = 0;

    private int numclickBaseRight = 0;
    private double istanttimeBaseRight = 0;
    private int totaltimeBaseRight = 0;



    private boolean victory = false;

    private int checked = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        mStopButton = findViewById(R.id.stopButton);
        mStopButton.setOnClickListener(view -> {
            ev3.sendMessage("#apstop#");
            victory = colors.colorMatch();
            colors.wipeColors();
            new Handler().post(() -> {
                Intent i = new Intent(getApplicationContext(), EndGameActivity.class);
                i.putExtra("min", mins);
                i.putExtra("sec", secs);
                i.putExtra("numclickManoLeft", numclickManoLeft);
                i.putExtra("totaltimeManoLeft",totaltimeManoLeft);
                i.putExtra("numclickManoRight", numclickManoRight);
                i.putExtra("totaltimeManoRight", totaltimeManoRight);
                i.putExtra("numclickBraccioLeft", numclickBraccioLeft);
                i.putExtra("totaltimeBraccioLeft", totaltimeBraccioLeft);
                i.putExtra("numclickBraccioRight", numclickBraccioRight);
                i.putExtra("totaltimeBraccioRight",totaltimeBraccioRight);
                i.putExtra("numclickBaseLeft", numclickBaseLeft);
                i.putExtra("totaltimeBaseLeft", totaltimeBaseLeft);
                i.putExtra("numclickBaseRight", numclickBaseRight);
                i.putExtra("totaltimeBaseRight", totaltimeBaseRight);
                i.putExtra("result", victory);
                startActivity(i);
                finish();
            });
        });

        timeText = findViewById(R.id.seconds_textBox);
        timer.scheduleAtFixedRate(new TimerTask() {
            private String min = "min: ";
            private String sec = "  sec: ";

            @Override
            public void run() {

                runOnUiThread(() -> {
                    mins = time / 60;
                    String[] formatM = mins.toString().split("\\.");
                    secs = time % 60;
                    String[] formatS = secs.toString().split("\\.");
                    timeText.setText(min + formatM[0] + sec + formatS[0] + "," + formatS[1].charAt(0));
                    time += 0.1;
                });
            }
        }, 100, 100);

        if (mMotorBase == null) {
            mMotorBase = new Motor(1, 180);
        }

        if (mMotorBraccio == null) {
            mMotorBraccio = new Motor(4, 180);
        }

        if (mMotorMano == null) {
            mMotorMano = new Motor(7, 180);
        }

        mButtonBaseLeft = findViewById(R.id.motor1_left);
        mButtonBaseLeft.setOnTouchListener((v, event) -> {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    ev3.sendMessage(mMotorBase.motorOn(mSpeed, "-"));
                    // PRESSED
                    numclickBaseLeft += 1;
                    istanttimeBaseLeft = time*100;
                    return false; // if you want to handle the touch event
                case MotionEvent.ACTION_UP:
                    ev3.sendMessage(mMotorBase.motorOff());

                    totaltimeBaseLeft += (int)(time*100)-istanttimeBaseLeft;
                    // RELEASED
                    return false; // if you want to handle the touch event
            }
            return false;
        });

        mButtonBaseRight = findViewById(R.id.motor1_right);
        mButtonBaseRight.setOnTouchListener((v, event) -> {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    ev3.sendMessage(mMotorBase.motorOn(mSpeed, "+"));
                    numclickBaseRight += 1;
                    // PRESSED
                    istanttimeBaseRight = time*100;
                    return false; // if you want to handle the touch event
                case MotionEvent.ACTION_UP:
                    ev3.sendMessage(mMotorBase.motorOff());
                    // RELEASED
                    totaltimeBaseRight += (int)(time*100) -istanttimeBaseRight;
                    return false; // if you want to handle the touch event
            }
            return false;
        });

        mButtonBraccioLeft = findViewById(R.id.motor2_left);
        mButtonBraccioLeft.setOnTouchListener((v, event) -> {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    ev3.sendMessage(mMotorBraccio.motorOn(mSpeed, "+"));
                    numclickBraccioLeft += 1;
                    // PRESSED
                    istanttimeBraccioLeft = time*100;
                    return false; // if you want to handle the touch event
                case MotionEvent.ACTION_UP:
                    ev3.sendMessage(mMotorBraccio.motorOff());
                    // RELEASED
                    totaltimeBraccioLeft += (int)(time*100)- istanttimeBraccioLeft;
                    return false; // if you want to handle the touch event
            }
            return false;
        });

        mButtonBraccioRight = findViewById(R.id.motor2_right);
        mButtonBraccioRight.setOnTouchListener((v, event) -> {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    ev3.sendMessage(mMotorBraccio.motorOn(mSpeed, "-"));
                    numclickBraccioRight += 1;
                    // PRESSED
                    istanttimeBraccioRight = time*100;
                    return false; // if you want to handle the touch event
                case MotionEvent.ACTION_UP:
                    ev3.sendMessage(mMotorBraccio.motorOff());
                    // RELEASED
                    totaltimeBraccioRight += (int)(time*100) - istanttimeBraccioRight;
                    return false; // if you want to handle the touch event
            }
            return false;
        });

        mButtonManoLeft = findViewById(R.id.motor3_left);
        mButtonManoLeft.setOnTouchListener((v, event) -> {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    ev3.sendMessage(mMotorMano.motorOn(mSpeed, "+"));
                    numclickManoLeft += 1;
                    // PRESSED
                    istanttimeManoLeft = time*100;
                    return false; // if you want to handle the touch event
                case MotionEvent.ACTION_UP:
                    ev3.sendMessage(mMotorMano.motorOff());
                    // RELEASED
                    totaltimeManoLeft += (int)(time*100) - istanttimeManoLeft;
                    return false; // if you want to handle the touch event
            }
            return false;
        });

        mButtonManoRight = findViewById(R.id.motor3_right);
        mButtonManoRight.setOnTouchListener((v, event) -> {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    ev3.sendMessage(mMotorMano.motorOn(mSpeed, "-"));
                    numclickManoRight += 1;
                    // PRESSED
                    istanttimeManoRight = time*100;
                    return false; // if you want to handle the touch event
                case MotionEvent.ACTION_UP:
                    ev3.sendMessage(mMotorMano.motorOff());
                    // RELEASED
                    totaltimeManoRight += (int)(time*100) - istanttimeManoRight;
                    return false; // if you want to handle the touch event
            }
            return false;
        });

        mSpeedSwitch = findViewById(R.id.speed_switch);
        mSpeedSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (mSpeed == 5) {
                mSpeed = 15;
            } else {
                mSpeed = 5;
            }
        });

        mPickUpButton = findViewById(R.id.pick_up_button);
        mPickUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public synchronized void onClick(View v) {
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        try {
                            ev3.sendMessage(mMotorBraccio.move(3, 10, "+"));
                            sleep(100);
                            ev3.sendMessage(mMotorMano.move(20, 20, "+"));
                            sleep(500);
                            ev3.sendMessage(mMotorBraccio.move(4, 10, "-"));
                            sleep(400);
                            ev3.sendMessage(mMotorMano.move(10, 20, "-"));
                            sleep(400);
                            ev3.sendMessage(mMotorBraccio.move(5, 10, "-"));
                            sleep(500);
                            ev3.sendMessage(mMotorMano.move(25, 20, "-"));
                            sleep(600);
                            ev3.sendMessage(mMotorBraccio.move(20, 15, "+"));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
            }
        });

        mPutDownButton = findViewById(R.id.put_down_button);
        mPutDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public synchronized void onClick(View v) {
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        try {
                            ev3.sendMessage(mMotorBraccio.move(4, 5, "-"));
                            sleep(250);
                            ev3.sendMessage(mMotorMano.move(8, 20, "+"));
                            sleep(250);
                            ev3.sendMessage(mMotorBraccio.move(2, 5, "+"));
                            sleep(250);
                            ev3.sendMessage(mMotorMano.move(8, 20, "+"));
                            sleep(250);
                            ev3.sendMessage(mMotorBraccio.move(2, 5, "+"));
                            sleep(250);
                            ev3.sendMessage(mMotorMano.move(10, 20, "+"));
                            sleep(250);
                            ev3.sendMessage(mMotorBraccio.move(15, 10, "+"));
                            sleep(300);
                            ev3.sendMessage(mMotorMano.move(15, 20, "-"));
                            sleep(300);
                            ev3.sendMessage(mMotorBraccio.move(9, 10, "-"));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
            }
        });

        mCheckColorButton = findViewById(R.id.check_color_button);
        mCheckColorButton.setOnClickListener(v -> {

            ev3.sendMessage("#arc#");

            AlertDialog.Builder builder = new AlertDialog.Builder(MotorActivity.this);
            builder.setMessage("Colore corretto?").setTitle("Conferma colore");
            builder.setPositiveButton("SI!", (dialogInterface, i) -> checked++);
            builder.setNegativeButton("NO!", (dialogInterface, i) -> {
                colors.wipeLastColor();
                checked--;
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });

    }


}