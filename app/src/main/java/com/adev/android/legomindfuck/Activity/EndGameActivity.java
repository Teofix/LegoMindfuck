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
    private Integer totaltimeBaseRight = 0;

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
            totaltimeBaseRight = datipassati.getInt("totaltimeBaseRight");
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

        //Riempio i campi degli score

        TextView tbaseDx = findViewById(R.id.tempoclickbasedx);
        TextView nbaseDx = findViewById(R.id.numeroclickbasedx);
        TextView tbaseSx = findViewById(R.id.tempoclickbasesx);
        TextView nbaseSx = findViewById(R.id.numeroclickbasesx);
        tbaseDx.setText(totaltimeBaseRight.toString());
        nbaseDx.setText(numclickBaseRight.toString());
        tbaseSx.setText(totaltimeBaseLeft.toString());
        nbaseSx.setText(numclickBaseLeft.toString());

        //setText per braccio
        TextView tbraccioDx = findViewById(R.id.tempoclickbracciodx);
        TextView nbraccioDx = findViewById(R.id.numeroclickbracciodx);
        TextView tbraccioSx = findViewById(R.id.tempoclickbracciosx);
        TextView nbraccioSx = findViewById(R.id.numeroclickbracciosx);
        tbraccioDx.setText(totaltimeBraccioRight.toString());
        nbraccioDx.setText(numclickBraccioRight.toString());
        tbraccioSx.setText(totaltimeBraccioLeft.toString());
        nbraccioSx.setText(numclickBraccioLeft.toString());

        //setText per mano
        TextView tmanoDx = findViewById(R.id.tempoclickmanodx);
        TextView nmanoDx = findViewById(R.id.numeroclickmanodx);
        TextView tmanoSx = findViewById(R.id.tempoclickmanosx);
        TextView nmanoSx = findViewById(R.id.numeroclickmanosx);
        tmanoDx.setText(totaltimeManoRight.toString());
        nmanoDx.setText(numclickManoRight.toString());
        tmanoSx.setText(totaltimeManoLeft.toString());
        nmanoSx.setText(numclickManoLeft.toString());

    }
}
