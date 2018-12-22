package com.adev.android.legomindfuck.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.adev.android.legomindfuck.R;

public class EndGameActivity extends AppCompatActivity {

    private Double mins;
    private Double secs;

    private Integer numclickManoLeft = 0;
    private Integer totaltimeManoLeft = 0;

    private Integer numclickManoRight = 0;
    private Integer totaltimeManoRight = 0;

    private Integer numclickBraccioLeft = 0;
    private Integer totaltimeBraccioLeft = 0;

    private Integer numclickBraccioRight = 0;
    private Integer totaltimeBraccioRight = 0;

    private Integer numclickBaseLeft = 0;
    private Integer totaltimeBaseLeft = 0;

    private Integer numclickBaseRight = 0;
    private Integer totaletimeBaseRight = 0;

    private Boolean victory = false;

    ViewPager viewPager;
    PagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Recupero i dati da stampare nelle statistiche
        Bundle datipassati = getIntent().getExtras();
        if (datipassati != null) {
            mins = datipassati.getDouble("min");
            secs = datipassati.getDouble("sec");
            numclickBaseLeft = datipassati.getInt("numclickBaseLeft");
            totaltimeBaseLeft = datipassati.getInt("totaltimeBaseLeft");
            numclickBaseRight = datipassati.getInt("numclickBaseRight");
            totaletimeBaseRight = datipassati.getInt("totaltimeBaseRight");
            numclickBraccioLeft = datipassati.getInt("numclickBraccioLeft");
            totaltimeBraccioLeft = datipassati.getInt("totaltimeBraccioLeft");
            numclickBraccioRight = datipassati.getInt("numclickBraccioRight");
            totaltimeBraccioRight = datipassati.getInt("totaltimeBraccioRight");
            numclickManoLeft = datipassati.getInt("numclickManoLeft");
            totaltimeManoLeft = datipassati.getInt("totaltimeManoLeft");
            numclickManoRight = datipassati.getInt("numclickManoRight");
            totaltimeManoRight = datipassati.getInt("totaltimeManoRight");
            victory = datipassati.getBoolean("result");

        }

        //set adapter:
        String[] title = new String[]{
                "NUMERO DI CLICK:",
                "TEMPO DI CLICK",
        };

        Integer[] manoDX = new Integer[]{
                numclickManoRight,
                totaltimeManoRight,
        };

        Integer[] manoSX = new Integer[]{
                numclickManoLeft,
                totaltimeManoLeft,
        };

        Integer[] braccioDX = new Integer[]{
                numclickBraccioRight,
                totaltimeBraccioRight,
        };

        Integer[] braccioSX = new Integer[]{
                numclickBraccioLeft,
                totaltimeBraccioLeft,
        };

        Integer[] baseDX = new Integer[]{
                numclickBaseRight,
                totaletimeBaseRight,
        };

        Integer[] baseSX = new Integer[]{
                numclickBaseLeft,
                totaltimeBaseLeft,
        };

        viewPager = (ViewPager) findViewById(R.id.pager_end);
        adapter = new PageViewerEndGame(EndGameActivity.this, title, manoDX, manoSX, braccioDX, braccioSX, baseDX, baseSX);
        viewPager.setAdapter(adapter);

        TextView result = findViewById(R.id.result);

        if (victory) {
            result.setText("HAI VINTO!");
            result.setBackgroundResource(R.drawable.round_background_green);
        } else {
            result.setText("HAI PERSO!");
            result.setBackgroundResource(R.drawable.round_background_red);
        }

        //stampo in una TextView i minuti e i secondi totali impiegati nel formato: MM:SS,s con s = millesimo di secondo
        TextView timeText = findViewById(R.id.timeText);

        String[] formatM = mins.toString().split("\\.");
        String[] formatS = secs.toString().split("\\.");
        String tempototale = formatM[0] + ":" + formatS[0] + "," + formatS[1].charAt(0);
        timeText.setText(tempototale);

        Log.i("TAG",numclickBaseLeft.toString());
        Log.i("TAG",numclickBaseRight.toString());
        Log.i("TAG",numclickBraccioLeft.toString());
        Log.i("TAG",numclickBraccioRight.toString());
        Log.i("TAG",numclickManoLeft.toString());
        Log.i("TAG",numclickManoRight.toString());
        Log.i("TAG",result.toString());
    }
}
