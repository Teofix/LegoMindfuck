package com.adev.android.legomindfuck.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.adev.android.legomindfuck.Motor;
import com.adev.android.legomindfuck.R;

import java.util.Timer;
import java.util.TimerTask;

import static com.adev.android.legomindfuck.Activity.MainMenuActivity.ev3;

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

    private int numclickManoLeft=0;

    private int numclickManoRight=0;

    private int numclickBraccioLeft=0;

    private int numclickBraccioRight=0;

    private int numclickBaseLeft=0;

    private int numclickBaseRight=0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //Blocchi checked
        blockplaced[0]=findViewById(R.id.blockPlaced_1);
        blockplaced[1]=findViewById(R.id.blockPlaced_2);
        blockplaced[2]=findViewById(R.id.blockPlaced_3);
        blockplaced[3]=findViewById(R.id.blockPlaced_4);
        for(int i=0; i<4; i++){
            blockplaced[i].setImageResource(R.color.mColorTrasparent);
        }

        blockplaced[0].setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setTitle("Elimina blocco");
            builder.setMessage("Hai davvero tolto questo blocco?");
            builder.setPositiveButton("Sì", (dialog, id) -> {
                if(numberofblock%4 == 1){
                    blockplaced[0].setImageResource(R.color.mColorTrasparent);
                    numberofblock -= 1;
                }else{
                    dialog.cancel();
                }
            });
            builder.setNegativeButton("No", (dialog, id) -> dialog.cancel());
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        blockplaced[1].setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setTitle("Elimina blocco");
            builder.setMessage("Hai davvero tolto questo blocco?");
            builder.setPositiveButton("Sì", (dialog, id) -> {
                if(numberofblock%4 == 2){
                    blockplaced[1].setImageResource(R.color.mColorTrasparent);
                    numberofblock -= 1;
                }else{
                    dialog.cancel();
                }
            });
            builder.setNegativeButton("No", (dialog, id) -> dialog.cancel());
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        blockplaced[2].setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setTitle("Elimina blocco");
            builder.setMessage("Hai davvero tolto questo blocco?");
            builder.setPositiveButton("Sì", (dialog, id) -> {
                if(numberofblock%4 == 3){
                    blockplaced[2].setImageResource(R.color.mColorTrasparent);
                    numberofblock -= 1;
                }else{
                    dialog.cancel();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        blockplaced[3].setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setTitle("Elimina blocco");
            builder.setMessage("Hai davvero tolto questo blocco?");
            builder.setPositiveButton("Sì", (dialog, id) -> {
                if(numberofblock%4 == 0){
                    blockplaced[3].setImageResource(R.color.mColorTrasparent);
                    numberofblock -= 1;
                }else{
                    dialog.cancel();
                }
            });
            builder.setNegativeButton("No", (dialog, id) -> dialog.cancel());
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        mStopButton = findViewById(R.id.stopButton);
        mStopButton.setOnClickListener(view -> {
            ev3.sendMessage("#apstop#");
            new Handler().post(() -> {
                Intent i = new Intent(getApplicationContext(), EndGameActivity.class);
                i.putExtra("min", mins);
                i.putExtra("sec", secs);
                i.putExtra("numclickManoLeft", numclickManoLeft);
                i.putExtra("numclickManoRight", numclickManoRight);
                i.putExtra("numclickBraccioLeft", numclickBraccioLeft);
                i.putExtra("numclickBraccioRight", numclickBraccioRight);
                i.putExtra("numclickBaseLeft", numclickBaseLeft);
                i.putExtra("numclickBaseRight", numclickBaseRight);
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
                    return false; // if you want to handle the touch event
                case MotionEvent.ACTION_UP:
                    ev3.sendMessage(mMotorBase.motorOff());
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
                    return false; // if you want to handle the touch event
                case MotionEvent.ACTION_UP:
                    ev3.sendMessage(mMotorBase.motorOff());
                    // RELEASED
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
                    return false; // if you want to handle the touch event
                case MotionEvent.ACTION_UP:
                    ev3.sendMessage(mMotorBraccio.motorOff());
                    // RELEASED
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
                    return false; // if you want to handle the touch event
                case MotionEvent.ACTION_UP:
                    ev3.sendMessage(mMotorBraccio.motorOff());
                    // RELEASED
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
                    return false; // if you want to handle the touch event
                case MotionEvent.ACTION_UP:
                    ev3.sendMessage(mMotorMano.motorOff());
                    // RELEASED
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
                    return false; // if you want to handle the touch event
                case MotionEvent.ACTION_UP:
                    ev3.sendMessage(mMotorMano.motorOff());
                    // RELEASED
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
        mCheckColorButton.setOnClickListener(v -> ev3.sendMessage("#arc#"));

    }

}

/*
    STATISTICHE DI GIOCO :

        + TEMPO DI GIOCO SP
        + TEMPO DI GIOCO MP
        + DIFFICOLTA' IN MATTONCINI (diamo un punteggio in base all'altezza della torre da costruire)
        +
 */