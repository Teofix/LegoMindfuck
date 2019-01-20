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
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.adev.android.legomindfuQ.Motor;
import com.adev.android.legomindfuQ.R;

import java.util.Timer;
import java.util.TimerTask;

import static com.adev.android.legomindfuQ.Activity.MainMenuActivity.colors;
import static com.adev.android.legomindfuQ.Activity.MainMenuActivity.ev3;

public class MultiMotorActivityP2 extends AppCompatActivity {

    private Motor mMotorBraccio;
    private Motor mMotorMano;

    private ImageButton mButtonManoLeft;
    private ImageButton mButtonManoRight;

    private Switch mSpeedSwitch;

    private Button mPickUpButton;
    private Button mPutDownButton;
    private Button mCheckColorButton;
    private Button mStopButton;

    private boolean victory = false;

    private int mSpeed = 5;

    private int checked = 0;

    private Double mins = 0.0;
    private Double secs = 0.0;

    private Timer timer = new Timer();
    private Double time = 0.0;
    private TextView timeText;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_multi_p2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mStopButton = findViewById(R.id.stopButtonP2);

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

        mStopButton.setOnClickListener(view -> {
            ev3.sendMessage("#apstop#");
            victory = colors.colorMatch();
            colors.wipeColors();
            new Handler().post(() -> {
                Intent i = new Intent(getApplicationContext(), EndGameActivity.class);
                i.putExtra("min", mins);
                i.putExtra("sec", secs);
                i.putExtra("result", victory);
                startActivity(i);
                finish();
            });
        });

        if (mMotorBraccio == null) {
            mMotorBraccio = new Motor(4, 180);
        }

        if (mMotorMano == null) {
            mMotorMano = new Motor(7, 180);
        }

        mButtonManoLeft = (ImageButton) findViewById(R.id.motor3_left);
        mButtonManoLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ev3.sendMessage(mMotorMano.motorOn(mSpeed, "+"));
                        // PRESSED
                        return false; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        ev3.sendMessage(mMotorMano.motorOff());
                        // RELEASED
                        return false; // if you want to handle the touch event
                }
                return false;
            }
        });

        mButtonManoRight = (ImageButton) findViewById(R.id.motor3_right);
        mButtonManoRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ev3.sendMessage(mMotorMano.motorOn(mSpeed, "-"));
                        // PRESSED
                        return false; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        ev3.sendMessage(mMotorMano.motorOff());
                        // RELEASED
                        return false; // if you want to handle the touch event
                }
                return false;
            }
        });

        mSpeedSwitch = (Switch) findViewById(R.id.speed_switch);
        mSpeedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mSpeed == 5) {
                    mSpeed = 15;
                } else {
                    mSpeed = 5;
                }
            }
        });

        mPickUpButton = (Button) findViewById(R.id.pick_up_button);
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

        mPutDownButton = (Button) findViewById(R.id.put_down_button);
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

            AlertDialog.Builder builder = new AlertDialog.Builder(MultiMotorActivityP2.this);
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