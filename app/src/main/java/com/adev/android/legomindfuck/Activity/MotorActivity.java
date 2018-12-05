package com.adev.android.legomindfuck;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import com.adev.android.legomindfuck.Thread.SocketManager;

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

    private int mSpeed = 5;

    public static final ColorSensor sColorSensor = new ColorSensor();

    private SocketManager ev3;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        if (ev3 == null) ev3 = new SocketManager();
        ev3.openSocket();

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

        mButtonBaseRight = findViewById(R.id.motor1_right);
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

        mButtonBraccioLeft = findViewById(R.id.motor2_left);
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

        mButtonBraccioRight = findViewById(R.id.motor2_right);
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

        mButtonManoLeft = findViewById(R.id.motor3_left);
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

        mButtonManoRight = findViewById(R.id.motor3_right);
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

        mSpeedSwitch = findViewById(R.id.speed_switch);
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
        mCheckColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ev3.sendMessage(mMotorMano.motorOn(10, "+"));

                Thread t = new Thread() {
                    private Boolean finished = false;

                    @Override
                    public void run() {
                        while(!finished) {
                            if(sColorSensor.getColor() > 0) {
                                ev3.sendMessage(mMotorMano.motorOff());
                                finished = true;
                            }
                        }
                        Log.i("thread", "found" + sColorSensor.getColor());
                    }
                };
                t.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ev3.closeSocket();
    }

    private int degreeToPercentage(int degree) {
        return degree * 100 / 360;
    }

    private int percentageToDegree(int percentage) {
        return percentage * 360 / 100;
    }
}