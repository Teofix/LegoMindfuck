package com.adev.android.legomindfuck.Activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.adev.android.legomindfuck.ColorSensor;
import com.adev.android.legomindfuck.R;

public class PlayMenuActivity extends AppCompatActivity {

    public static final ColorSensor sColorSensor = new ColorSensor();

    ViewPager viewPager;
    PagerAdapter adapter;
    int[] images;
    String[] titles;
    String[] descriptions;

    Button selector;

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_menu);

        SharedPreferences sharedPref = getSharedPreferences("access", MODE_PRIVATE);
        int firstAccess = sharedPref.getInt("access", 0);

        //ActionBar actionBar = get();

        if (firstAccess==1) {

            sharedPref.edit().putInt("access", 2).apply();

            ConstraintLayout mLayout = (ConstraintLayout) findViewById(R.id.play_pager_layout);
            mLayout.setBackgroundColor(R.color.mGrey);

            images = new int[]{R.drawable.tutorial,
                    R.drawable.single_player,
                    R.drawable.multiplayer
            };

            titles = new String[]{"Tutorial",
                    "Single play",
                    "Multiplayer",
            };

            descriptions = new String[]{"Tutorial di gioco",
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

                    switch (viewPager.getCurrentItem()) {
                        case 0:

                            break;

                        case 1:
                            //Intent i2 = new Intent(getApplicationContext(), MotorActivity.class);
                            //startActivity(i2);
                            break;

                        case 2:
                            //Intent i3 = new Intent(getApplicationContext(), MultiplayerSelectionActivity.class);
                            //startActivity(i3);
                            break;
                    }

                }
            });

        }

        else {

            images = new int[]{R.drawable.tutorial,
                    R.drawable.single_player,
                    R.drawable.multiplayer
            };

            titles = new String[]{"Tutorial",
                    "Single play",
                    "Multiplayer",
            };

            descriptions = new String[]{"Tutorial di gioco",
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

                    switch (viewPager.getCurrentItem()) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.menItem_play:
                break;

            case R.id.menItem_connection:
                Intent i = new Intent(getApplicationContext(), ConnectionTestActivity.class);
                startActivity(i);
        }

        return false;
    }

}
