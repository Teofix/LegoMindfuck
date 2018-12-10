package com.adev.android.legomindfuck.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.adev.android.legomindfuck.R;

import static com.adev.android.legomindfuck.Activity.MainMenuActivity.ev3;

import static com.adev.android.legomindfuck.Statistics.player;

public class MultiplayerSelectionActivity extends AppCompatActivity {

    private Button p1;
    private Button p2;
    private TextView instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_selection);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        instructions = (TextView) findViewById(R.id.multi_instructions);
        instructions.setText("Varie \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n fine");

        p1 = (Button) findViewById(R.id.p1_button);
        p2 = (Button) findViewById(R.id.p2_button);

        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ev3.sendMessage("#ap21#");
                player[0] = 1;
                Intent i = new Intent(getApplicationContext(), MultiMotorActivityP1.class);
                startActivity(i);
            }
        });

        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ev3.sendMessage("#ap22#");
                player[1] = 2;
                Intent i = new Intent(getApplicationContext(), MultiMotorActivityP2.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        return super.onCreateOptionsMenu(menu);
    }
}
