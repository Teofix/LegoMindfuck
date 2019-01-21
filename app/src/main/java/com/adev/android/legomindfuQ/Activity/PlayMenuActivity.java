package com.adev.android.legomindfuQ.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;

import com.adev.android.legomindfuQ.R;

import static com.adev.android.legomindfuQ.Activity.MainMenuActivity.colors;
import static com.adev.android.legomindfuQ.Activity.MainMenuActivity.ev3;

public class PlayMenuActivity extends AppCompatActivity {


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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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
        selector.setOnClickListener(v -> {
            if (ev3.isSocketReady()) {
                switch (viewPager.getCurrentItem()) {
                    case 0:
                        startActivity(new Intent(getApplicationContext(), TutorialActivity.class));
                        break;
                    case 1:
                        colors.setPlayers(1);
                        startActivity(new Intent(getApplicationContext(), TowerActivity.class));
                        break;
                    case 2:
                        colors.setPlayers(2);
                        startActivity(new Intent(getApplicationContext(), MultiplayerSelectionActivity.class));
                        break;
                }
            }
        });

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
