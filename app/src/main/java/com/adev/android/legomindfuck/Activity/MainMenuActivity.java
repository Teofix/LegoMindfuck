package com.adev.android.legomindfuck.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.adev.android.legomindfuck.R;
import com.adev.android.legomindfuck.ShowConnectionErrorMessage;
import com.adev.android.legomindfuck.ShowDialogMessage;

public class MainMenuActivity extends AppCompatActivity {

    private Button play;
    private Button instr;
    private Button connection;

    private TextView tHelp;
    private ConstraintLayout mLayout;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        SharedPreferences sharedPref = getSharedPreferences("access", MODE_PRIVATE);
        int firstAccess = sharedPref.getInt("access", 0);

        play = findViewById(R.id.play_mode);
        instr = findViewById(R.id.mount_instructions);
        connection = findViewById(R.id.connection);
        tHelp = findViewById(R.id.t_main_txt);

        sharedPref.edit().putInt("access", 0).apply();

        if(firstAccess == 0) {

            sharedPref.edit().putInt("access", 1).apply();

            final ConstraintLayout mLayout = (ConstraintLayout) findViewById(R.id.mainMenuLayout);
            mLayout.setBackgroundColor(R.color.mGrey);
            instr.setAlpha((float) 0.4);
            connection.setAlpha((float) 0.4);
            final int[] taps = {0};


            mLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch(taps[0])    {
                        case 0  :
                            play.setAlpha((float) 0.4);
                            instr.setAlpha(1);
                            tHelp.setText("Accesso alle istruzioni di montaggio (tap per continuare)");
                            taps[0]++;
                            break;

                        case 1  :
                            instr.setAlpha((float) 0.4);
                            connection.setAlpha(1);
                            tHelp.setText("Accesso all'area per la connessione (tap per continuare)");
                            taps[0]++;
                            break;

                        case 2:
                            connection.setAlpha((float) 0.4);
                            tHelp.setText("Premi play per accedere all'area gioco!");
                            play.setAlpha(1);
                            play.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i = new Intent(getApplicationContext(), PlayMenuActivity.class);
                                    startActivity(i);
                                }
                            });
                    }
                    return false;
                }
            });



        }

        else if(firstAccess == 2) {

            play.setAlpha((float) 0.4);
            instr.setAlpha((float) 0.4);
            tHelp.setText("Premi connect per accedere all'area di connessione");
            final ConstraintLayout mLayout = (ConstraintLayout) findViewById(R.id.mainMenuLayout);
            mLayout.setBackgroundColor(R.color.mGrey);

            connection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), ConnectionTestActivity.class);
                    startActivity(i);
                }
            });

        }

        else {


            tHelp.setVisibility(View.INVISIBLE);
            wImg.setVisibility(View.INVISIBLE);
            play = findViewById(R.id.play_mode);
            instr = findViewById(R.id.mount_instructions);
            connection = findViewById(R.id.connection);

            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ConnectionTestActivity.isConnected) {
                        Intent i = new Intent(getApplicationContext(), PlayMenuActivity.class);
                        startActivity(i);
                    } else {
                        ShowConnectionErrorMessage err = new ShowConnectionErrorMessage();
                        err.setContent("Errore di connessione!", "Robot non connesso all'app");
                        err.show(getSupportFragmentManager(), "");
                    }
                }
            });

            connection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), ConnectionTestActivity.class);
                    startActivity(i);
                }
            });
/*
        instr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MountInstructions.class);
                startActivity(i);
            }
        });
        */
        }
    }
}
