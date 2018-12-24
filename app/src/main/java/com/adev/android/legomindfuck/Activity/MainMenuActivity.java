package com.adev.android.legomindfuck.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Button;

import com.adev.android.legomindfuck.DataColors.Colors;
import com.adev.android.legomindfuck.R;
import com.adev.android.legomindfuck.Thread.SocketManager;

public class MainMenuActivity extends AppCompatActivity {

    private Button play;
    private Button instruction;
    private Button connection;
    private Button segnalazioni;

    public static SocketManager ev3;
    public static Colors colors = new Colors();

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        colors.setPlayers(1);

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        // Check if we need to display our OnboardingFragment
        if (!sharedPreferences.getBoolean(
                WelcomeActivity.COMPLETED_ONBOARDING_PREF_NAME, false)) {
            // The user hasn't seen the OnboardingFragment yet, so show it
            startActivity(new Intent(this, WelcomeActivity.class));
        }

        play = findViewById(R.id.play_mode);
        instruction = findViewById(R.id.mount_instructions);
        connection = findViewById(R.id.connection);
        segnalazioni = findViewById(R.id.segnalazioni);

        play.setOnClickListener(v -> {
            if (ev3 != null && ev3.isSocketReady()) {
                ev3.sendMessage("#aplayer#");
                Intent i = new Intent(getApplicationContext(), PlayMenuActivity.class);
                startActivity(i);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainMenuActivity.this);
                builder.setMessage("Attenzione!\nNon è stata stabilita alcuna connessione con il robot.\nConnetti il robot e riprova.").setTitle("Errore di connessione");
                builder.setPositiveButton("Capito!", (dialogInterface, i) -> {});
                builder.setNegativeButton("Portami lì..", (dialogInterface, i) -> { startActivity(new Intent(getApplicationContext(), ConnectionTestActivity.class)); });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        connection.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), ConnectionTestActivity.class);
            startActivity(i);
        });

        instruction.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), InstructionActivity.class)));

        segnalazioni.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), SegnalazioniActivity.class);
            startActivity(i);
        });


    }

}
