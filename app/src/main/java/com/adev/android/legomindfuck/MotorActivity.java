package com.adev.android.legomindfuck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;

public class MotorActivity extends AppCompatActivity {

    private Motor mMotorBase;
    private Motor mMotorBraccio;
    private Motor mMotorMano;

    private ImageButton mButtonBaseLeft;
    private ImageButton mButtonBaseRight;
    private SeekBar mSeekBarBase;

    private ImageButton mButtonBraccioLeft;
    private ImageButton mButtonBraccioRight;
    private SeekBar mSeekBarBraccio;

    private ImageButton mButtonManoLeft;
    private ImageButton mButtonManoRight;
    private SeekBar mSeekBarMano;

    private Switch mSpeedSwitch;

    private int mSpeed = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor);

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
                mMotorBase.move(2, 1, "+");
            }
        });

        mButtonBaseRight = (ImageButton) findViewById(R.id.motor1_right);
        mButtonBaseRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMotorBase.move(2, 1, "-");
            }
        });

        mSeekBarBase = (SeekBar) findViewById(R.id.seekBar1);
        mSeekBarBase.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int oldDegrees = 0;
            int newDegrees = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                newDegrees = 360 * progress / 100;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Log.i("Gradi", String.valueOf(newDegrees));

                if (oldDegrees - newDegrees < 0) {
                    mMotorBase.move(newDegrees - oldDegrees, mSpeed, "+");
                } else if (oldDegrees - newDegrees > 0) {
                    mMotorBase.move(oldDegrees - newDegrees, mSpeed, "-");
                }

                oldDegrees = newDegrees;
            }
        });

        mButtonBraccioLeft = (ImageButton) findViewById(R.id.motor2_left);
        mButtonBraccioLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMotorBraccio.move(2, 1, "+");
            }
        });

        mButtonBraccioRight = (ImageButton) findViewById(R.id.motor2_right);
        mButtonBraccioRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMotorBraccio.move(2, 1, "-");
            }
        });

        mSeekBarBraccio = (SeekBar) findViewById(R.id.seekBar2);
        mSeekBarBraccio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int oldDegrees = 0;
            int newDegrees = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                newDegrees = 360 * progress / 100;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Log.i("Gradi", String.valueOf(newDegrees));

                if (oldDegrees - newDegrees < 0) {
                    mMotorBraccio.move(newDegrees - oldDegrees, mSpeed, "+");
                } else if (oldDegrees - newDegrees > 0) {
                    mMotorBraccio.move(oldDegrees - newDegrees, mSpeed, "-");
                }

                oldDegrees = newDegrees;
            }
        });

        mButtonManoLeft = (ImageButton) findViewById(R.id.motor3_left);
        mButtonManoLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMotorMano.move(2, 1, "+");
            }
        });

        mButtonManoRight = (ImageButton) findViewById(R.id.motor3_right);
        mButtonManoRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMotorMano.move(2, 1, "-");
            }
        });

        mSeekBarMano = (SeekBar) findViewById(R.id.seekBar3);
        mSeekBarMano.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int oldDegrees = 0;
            int newDegrees = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                newDegrees = 360 * progress / 100;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Log.i("Gradi", String.valueOf(newDegrees));

                if (oldDegrees - newDegrees < 0) {
                    mMotorMano.move(newDegrees - oldDegrees, mSpeed, "+");
                } else if (oldDegrees - newDegrees > 0) {
                    mMotorMano.move(oldDegrees - newDegrees, mSpeed, "-");
                }

                oldDegrees = newDegrees;
            }
        });

        mSpeedSwitch = (Switch) findViewById(R.id.speed_switch);
        mSpeedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mSpeed == 1) {
                    mSpeed = 10;
                } else {
                    mSpeed = 1;
                }
            }
        });
    }
}
