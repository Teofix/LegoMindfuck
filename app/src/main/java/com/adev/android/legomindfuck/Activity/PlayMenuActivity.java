package com.adev.android.legomindfuck.Activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.adev.android.legomindfuck.R;

public class PlayMenuActivity extends AppCompatActivity {

    ViewPager viewPager;
    PagerAdapter adapter;
    int[] images;
    String[] titles;
    String[] descriptions;

    Button selector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_menu);

        images = new int[]   { R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background
        };

        titles = new String[]   {"Tutorial",
                "Single play",
                "Multiplayer",
        };

        descriptions = new String[] {"Tutorial di gioco",
                "Modalità giocatore singolo",
                "Modalità multigiocatore",
        };

        viewPager = (ViewPager) findViewById(R.id.pager);

        adapter = new ImagePageAdapter(PlayMenuActivity.this, images, titles, descriptions);

        viewPager.setAdapter(adapter);

        selector = (Button) findViewById(R.id.mode_selector);

        selector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(viewPager.getCurrentItem()) {
                    case 0:

                        break;

                    case 1:
                        Intent i2 = new Intent(getApplicationContext(), MotorActivity.class);
                        startActivity(i2);
                        break;

                    case 2:
                        Intent i3 = new Intent(getApplicationContext(), MultiplayerSelectionActivity.class);
                        startActivity(i3);
                        break;
                }

            }
        });


    }
}
