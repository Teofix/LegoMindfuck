package com.adev.android.legomindfuck;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import com.marcinmoskala.arcseekbar.ArcSeekBar;
import com.marcinmoskala.arcseekbar.ProgressListener;

public class MotorActivity extends AppCompatActivity {

    private Motor mMotorBase;
    private Motor mMotorBraccio;
    private Motor mMotorMano;

    private ImageButton mButtonBaseLeft;
    private ImageButton mButtonBaseRight;
    private ArcSeekBar mSeekBarBase;

    private ImageButton mButtonBraccioLeft;
    private ImageButton mButtonBraccioRight;
    private ArcSeekBar mSeekBarBraccio;

    private ImageButton mButtonManoLeft;
    private ImageButton mButtonManoRight;
    private ArcSeekBar mSeekBarMano;

    private int mBaseDegree = 180;
    private int mBraccioDegree = 180;
    private int mManoDegree = 180;

    private Switch mSpeedSwitch;

    private int mSpeed = 10;

    private SocketManager ev3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        ev3 = new SocketManager();
        ev3.openSocket();

        if (mMotorBase == null) {
            mMotorBase = new Motor(1, 0);
        }

        if (mMotorBraccio == null) {
            mMotorBraccio = new Motor(2, 0);
        }

        if (mMotorMano == null) {
            mMotorMano = new Motor(3, 0);
        }

        mButtonBaseLeft = (ImageButton) findViewById(R.id.motor1_left);
        mButtonBaseLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ev3.sendMessage(mMotorBase.move(2, mSpeed, "-"));
                mBaseDegree = ((mBaseDegree * 100 / 360) - 2) * 360 / 100;
                mSeekBarBase.setProgress(mBaseDegree * 100 / 360);
            }
        });

        mButtonBaseRight = (ImageButton) findViewById(R.id.motor1_right);
        mButtonBaseRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ev3.sendMessage(mMotorBase.move(2, mSpeed, "+"));
                mBaseDegree = ((mBaseDegree * 100 / 360) + 2) * 360 / 100;
                mSeekBarBase.setProgress(mBaseDegree * 100 / 360);
            }
        });

        mSeekBarBase = (ArcSeekBar) findViewById(R.id.seekBar1);
        mSeekBarBase.setOnStopTrackingTouch(new ProgressListener() {
            @Override
            public void invoke(int i) {
                if (i * 360 / 100 > mBaseDegree) {
                    ev3.sendMessage(mMotorBase.move(i * 360 / 100 - mBaseDegree, mSpeed, "+"));
                } else if (i * 360 / 100 < mBaseDegree) {
                    ev3.sendMessage(mMotorBase.move(mBaseDegree - i * 360 / 100, mSpeed, "-"));
                }
                mBaseDegree = i * 360 / 100;
            }
        });
        mSeekBarBase.setProgress(50);

        mButtonBraccioLeft = (ImageButton) findViewById(R.id.motor2_left);
        mButtonBraccioLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ev3.sendMessage(mMotorBraccio.move(2, mSpeed, "-"));
                mBraccioDegree = ((mBraccioDegree * 100 / 360) - 2) * 360 / 100;
                mSeekBarBraccio.setProgress(mBraccioDegree * 100 / 360);
            }
        });

        mButtonBraccioRight = (ImageButton) findViewById(R.id.motor2_right);
        mButtonBraccioRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ev3.sendMessage(mMotorBraccio.move(2, mSpeed, "+"));
                mBraccioDegree = ((mBraccioDegree * 100 / 360) + 2) * 360 / 100;
                mSeekBarBraccio.setProgress(mBraccioDegree * 100 / 360);
            }
        });

        mSeekBarBraccio = (ArcSeekBar) findViewById(R.id.seekBar2);
        mSeekBarBraccio.setOnStopTrackingTouch(new ProgressListener() {
            @Override
            public void invoke(int i) {
                if (i * 360 / 100 > mBaseDegree) {
                    ev3.sendMessage(mMotorBraccio.move(i * 360 / 100 - mBraccioDegree, mSpeed, "+"));
                } else if (i * 360 / 100 < mBaseDegree) {
                    ev3.sendMessage(mMotorBraccio.move(mBraccioDegree - i * 360 / 100, mSpeed, "-"));
                }
                mBraccioDegree = i * 360 / 100;
            }
        });
        mSeekBarBraccio.setProgress(50);

        mButtonManoLeft = (ImageButton) findViewById(R.id.motor3_left);
        mButtonManoLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ev3.sendMessage(mMotorMano.move(2, mSpeed, "-"));
                mManoDegree = ((mManoDegree * 100 / 360) - 2) * 360 / 100;
                mSeekBarMano.setProgress(mManoDegree * 100 / 360);
            }
        });

        mButtonManoRight = (ImageButton) findViewById(R.id.motor3_right);
        mButtonManoRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ev3.sendMessage(mMotorMano.move(2, mSpeed, "+"));
                mManoDegree = ((mManoDegree * 100 / 360) + 2) * 360 / 100;
                mSeekBarMano.setProgress(mManoDegree * 100 / 360);
            }
        });

        mSeekBarMano = (ArcSeekBar) findViewById(R.id.seekBar3);
        mSeekBarMano.setOnStopTrackingTouch(new ProgressListener() {
            @Override
            public void invoke(int i) {
                if (i * 360 / 100 > mManoDegree) {
                    ev3.sendMessage(mMotorMano.move(i * 360 / 100 - mManoDegree, mSpeed, "+"));
                } else if (i * 360 / 100 < mManoDegree) {
                    ev3.sendMessage(mMotorMano.move(mManoDegree - i * 360 / 100, mSpeed, "-"));
                }
                mManoDegree = i * 360 / 100;
            }
        });
        mSeekBarMano.setProgress(50);

        mSpeedSwitch = (Switch) findViewById(R.id.speed_switch);
        mSpeedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mSpeed == 10) {
                    mSpeed = 25;
                } else {
                    mSpeed = 10;
                }
            }
        });
    }
}