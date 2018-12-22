package com.adev.android.legomindfuck.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.adev.android.legomindfuck.R;

import java.util.Timer;
import java.util.TimerTask;

import static com.adev.android.legomindfuck.Activity.MainMenuActivity.colors;
import static com.adev.android.legomindfuck.Statistics.players;

public class TowerActivity extends AppCompatActivity {

    Timer timer = new Timer();
    private Double time = 4.0;
    private TextView timeText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tower);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        timeText = findViewById(R.id.countDown);

        timer.scheduleAtFixedRate(new TimerTask() {

            private String sec = "sec: ";
            private Double secs = 0.0;

            @Override
            public void run() {

                runOnUiThread(() -> {
                    secs = time % 60;
                    String[] formatS = secs.toString().split("\\.");
                    if (time >= 0) {
                        timeText.setText(sec + formatS[0] + "," + formatS[1].charAt(0));
                    }
                    else {
                        timeText.setText(String.format("Tempo scaduto!"));
                    }
                    time -= 0.1;
                    if (time <= 0) {
                        timeText.setText(String.format("Tempo scaduto!"));
                        finish();
                    }
                });
            }
        }, 100, 100);

        ImageView[] block = new ImageView[4];
        int[] blockNumber = new int[4];
        blockNumber[0] = R.id.block_1;
        blockNumber[1] = R.id.block_2;
        blockNumber[2] = R.id.block_3;
        blockNumber[3] = R.id.block_4;

        for (int i = 0; i < 4; i++) {
            block[i] = findViewById(blockNumber[i]);
        }

        for (int i = 0; i < 4; i++) {
            int color = R.color.mColorTrasparent;
            switch (colors.getTowerColor(i)) {
                case "Black":
                    color = R.color.mColorBlack;
                    break;
                case "Blue":
                    color = R.color.mPressedButtonBlue;
                    break;
                case "Red":
                    color = R.color.mColorRed;
                    break;
                case "Yellow":
                    color = R.color.mPressedButtonYellow;
                    break;
                default:
                    break;
            }
            block[i].setImageResource(color);
        }

        new Handler().postDelayed(() -> {
            Intent i;
            if (players == 2) {
                i = new Intent(getApplicationContext(), MultiplayerSelectionActivity.class);
            }
            else {
                i = new Intent(getApplicationContext(), MotorActivity.class);
            }
            startActivity(i);
            finish();
        }, 5000);

    }
}
