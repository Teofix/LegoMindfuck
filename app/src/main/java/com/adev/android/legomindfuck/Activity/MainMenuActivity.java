package com.adev.android.legomindfuck.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.adev.android.legomindfuck.DataColors.Colors;
import com.adev.android.legomindfuck.R;
import com.adev.android.legomindfuck.ShowConnectionErrorMessage;
import com.adev.android.legomindfuck.Thread.SocketManager;

public class MainMenuActivity extends AppCompatActivity {

    private Button play;
    private Button instr;
    private Button connection;
    private Button segnalazioni;

    private TextView tHelp;
    private ConstraintLayout mLayout;

    public static SocketManager ev3;
    public static Colors colors = new Colors();

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        play = findViewById(R.id.play_mode);
        instr = findViewById(R.id.mount_instructions);
        connection = findViewById(R.id.connection);
        segnalazioni = findViewById(R.id.segnalazioni);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ev3 != null && ev3.isSocketReady()) {
                    ev3.sendMessage("#aplayer#");
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


        segnalazioni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SegnalazioniActivity.class);
                startActivity(i);
            }
        });


    }

}
