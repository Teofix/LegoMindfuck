package com.adev.android.legomindfuck.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Button;

import com.adev.android.legomindfuck.R;

public class MultiplayerSelectionActivity extends AppCompatActivity {

    private Button p1;
    private Button p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_selection);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        p1 = (Button) findViewById(R.id.p1_button);
        p2 = (Button) findViewById(R.id.p2_button);

        p1.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), TowerActivity.class));
        });

        p2.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), MultiMotorActivityP2.class));
        });
    }

}
