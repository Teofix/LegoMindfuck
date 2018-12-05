package com.adev.android.legomindfuck.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.adev.android.legomindfuck.R;
import com.adev.android.legomindfuck.ShowConnectionErrorMessage;
import com.adev.android.legomindfuck.ShowDialogMessage;

public class MainMenuActivity extends AppCompatActivity {

    private Button play;
    private Button instr;
    private Button connection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        play = findViewById(R.id.play_mode);
        instr = findViewById(R.id.mount_instructions);
        connection = findViewById(R.id.connection);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ConnectionTestActivity.isConnected)    {
                    Intent i = new Intent(getApplicationContext(), PlayMenuActivity.class);
                    startActivity(i);
                }
                else    {
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
