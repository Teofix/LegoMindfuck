package com.adev.android.legomindfuck.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.adev.android.legomindfuck.R;

public class MultiplayerSelectionActivity extends AppCompatActivity {

    private Button p1;
    private Button p2;
    private TextView instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_selection);

        instructions = (TextView) findViewById(R.id.multi_instructions);
        instructions.setText("Varie \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n fine");

        p1 = (Button) findViewById(R.id.p1_button);
        p2 = (Button) findViewById(R.id.p2_button);

        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MultiMotorActivityP1.class);
                startActivity(i);
            }
        });

        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MultiMotorActivityP2.class);
                startActivity(i);
            }
        });
    }
}
