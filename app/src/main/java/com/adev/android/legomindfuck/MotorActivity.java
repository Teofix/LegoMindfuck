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
            mMotorBase = new Motor(1, 180);
        }

        if (mMotorBraccio == null) {
            mMotorBraccio = new Motor(2, 180);
        }

        if (mMotorMano == null) {
            mMotorMano = new Motor(3, 180);
        }

        mButtonBaseLeft = (ImageButton) findViewById(R.id.motor1_left);
        mButtonBaseLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ev3.sendMessage(mMotorBase.move(2, mSpeed, "-"));
                mSeekBarBase.setProgress(degreeToPercentage(mMotorBase.getDegrees()));
            }
        });

        mButtonBaseRight = (ImageButton) findViewById(R.id.motor1_right);
        mButtonBaseRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ev3.sendMessage(mMotorBase.move(2, mSpeed, "+"));
                mSeekBarBase.setProgress(degreeToPercentage(mMotorBase.getDegrees()));
            }
        });

        mSeekBarBase = (ArcSeekBar) findViewById(R.id.seekBar1);
        mSeekBarBase.setOnStopTrackingTouch(new ProgressListener() {
            @Override
            public void invoke(int i) {
                if (percentageToDegree(i) > mMotorBase.getDegrees()) {
                    ev3.sendMessage(mMotorBase.move(percentageToDegree(i) - mMotorBase.getDegrees(), mSpeed, "+"));
                } else if (i * 360 / 100 < mMotorBase.getDegrees()) {
                    ev3.sendMessage(mMotorBase.move(mMotorBase.getDegrees() - percentageToDegree(i), mSpeed, "-"));
                }
            }
        });
        mSeekBarBase.setProgress(50);

        mButtonBraccioLeft = (ImageButton) findViewById(R.id.motor2_left);
        mButtonBraccioLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ev3.sendMessage(mMotorBraccio.move(2, mSpeed, "-"));
                mSeekBarBraccio.setProgress(degreeToPercentage(mMotorBraccio.getDegrees()));
            }
        });

        mButtonBraccioRight = (ImageButton) findViewById(R.id.motor2_right);
        mButtonBraccioRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ev3.sendMessage(mMotorBraccio.move(2, mSpeed, "+"));
                mSeekBarBraccio.setProgress(degreeToPercentage(mMotorBraccio.getDegrees()));
            }
        });

        mSeekBarBraccio = (ArcSeekBar) findViewById(R.id.seekBar2);
        mSeekBarBraccio.setOnStopTrackingTouch(new ProgressListener() {
            @Override
            public void invoke(int i) {
                if (i * 360 / 100 > mMotorBraccio.getDegrees()) {
                    ev3.sendMessage(mMotorBraccio.move(percentageToDegree(i) - mMotorBraccio.getDegrees(), mSpeed, "+"));
                } else if (i * 360 / 100 < mMotorBraccio.getDegrees()) {
                    ev3.sendMessage(mMotorBraccio.move(mMotorBraccio.getDegrees() - percentageToDegree(i), mSpeed, "-"));
                }
            }
        });
        mSeekBarBraccio.setProgress(50);

        mButtonManoLeft = (ImageButton) findViewById(R.id.motor3_left);
        mButtonManoLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ev3.sendMessage(mMotorMano.move(2, mSpeed, "-"));
                mSeekBarMano.setProgress(degreeToPercentage(mMotorMano.getDegrees()));
            }
        });

        mButtonManoRight = (ImageButton) findViewById(R.id.motor3_right);
        mButtonManoRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ev3.sendMessage(mMotorMano.move(2, mSpeed, "+"));
                mSeekBarMano.setProgress(degreeToPercentage(mMotorMano.getDegrees()));
            }
        });

        mSeekBarMano = (ArcSeekBar) findViewById(R.id.seekBar3);
        mSeekBarMano.setOnStopTrackingTouch(new ProgressListener() {
            @Override
            public void invoke(int i) {
                if (i * 360 / 100 > mMotorMano.getDegrees()) {
                    ev3.sendMessage(mMotorMano.move(percentageToDegree(i) - mMotorMano.getDegrees(), mSpeed, "+"));
                } else if (i * 360 / 100 < mMotorMano.getDegrees()) {
                    ev3.sendMessage(mMotorMano.move(mMotorMano.getDegrees() - percentageToDegree(i), mSpeed, "-"));
                }
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

    private int degreeToPercentage(int degree) {
        return degree * 100 / 360;
    }

    private int percentageToDegree(int percentage) {
        return percentage * 360 / 100;
    }
}