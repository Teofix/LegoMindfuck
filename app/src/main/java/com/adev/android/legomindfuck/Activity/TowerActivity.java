package com.adev.android.legomindfuck.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

import com.adev.android.legomindfuck.R;

import java.util.Timer;
import java.util.TimerTask;

public class TowerActivity extends AppCompatActivity {

    Timer timer = new Timer();
    private Double time = 10.0;
    private TextView timeText;

    public static String[] colorTower = new String[4];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tower);
        timeText = findViewById(R.id.countDown);

        timer.scheduleAtFixedRate(new TimerTask() {

            private String sec = "sec: ";
            private Double secs = 0.0;

            @Override
            public void run() {

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        secs = time % 60;
                        String[] formatS = secs.toString().split("\\.");
                        if (time >= 0) {
                            timeText.setText(sec + formatS[0] + "," + formatS[1].charAt(0));
                        }
                        else {
                            timeText.setText(String.format("Tempo scaduto!"));
                        }
                        time -= 0.1;
                    }
                });
            }
        }, 100, 100);

        ImageView block_1 = findViewById(R.id.block_1);
        ImageView block_2 = findViewById(R.id.block_2);
        ImageView block_3 = findViewById(R.id.block_3);
        ImageView block_4 = findViewById(R.id.block_4);

        if (colorTower.length == 4) {
            Log.i("Tower:", colorTower[0] + '-' + colorTower[1] + '-' + colorTower[2] + '-' + colorTower[3]);
            switch (colorTower[0]) {
                case "Black":
                    block_1.setImageResource(R.color.mColorBlack);
                case "Blue":
                    block_1.setImageResource(R.color.mColorBlue);
                case "Red":
                    block_1.setImageResource(R.color.mColorRed);
                case "Yellow":
                    block_1.setImageResource(R.color.mColorYellow);
            }

            switch (colorTower[1]) {
                case "Black":
                    block_2.setImageResource(R.color.mColorBlack);
                case "Blue":
                    block_2.setImageResource(R.color.mColorBlue);
                case "Red":
                    block_2.setImageResource(R.color.mColorRed);
                case "Yellow":
                    block_2.setImageResource(R.color.mColorYellow);
            }

            switch (colorTower[2]) {
                case "Black":
                    block_3.setImageResource(R.color.mColorBlack);
                case "Blue":
                    block_3.setImageResource(R.color.mColorBlue);
                case "Red":
                    block_3.setImageResource(R.color.mColorRed);
                case "Yellow":
                    block_3.setImageResource(R.color.mColorYellow);
            }

            switch (colorTower[3]) {
                case "Black":
                    block_4.setImageResource(R.color.mColorBlack);
                case "Blue":
                    block_4.setImageResource(R.color.mColorBlue);
                case "Red":
                    block_4.setImageResource(R.color.mColorRed);
                case "Yellow":
                    block_4.setImageResource(R.color.mColorYellow);
            }
        }

    }
}
