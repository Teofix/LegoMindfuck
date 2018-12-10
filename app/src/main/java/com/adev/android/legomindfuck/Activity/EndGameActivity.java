package com.adev.android.legomindfuck.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.adev.android.legomindfuck.R;

public class EndGameActivity extends AppCompatActivity {

    private Double mins;
    private Double secs;

    private Integer numclickManoLeft=0;
    private Double gtimeLapManoLeft=0.0;
    private Double totaltimeManoLeft=0.0;

    private Integer numclickManoRight=0;
    private Double gtimeLapManoRight=0.0;
    private Double totaltimeManoRight=0.0;

    private Integer numclickBraccioLeft=0;
    private Double gtimeLapBraccioLeft=0.0;
    private Double totaltimeBraccioLeft=0.0;

    private Integer numclickBraccioRight=0;
    private Double gtimeLapBraccioRight=0.0;
    private Double totaltimeBraccioRight=0.0;

    private Integer numclickBaseLeft=0;
    private Double gtimeLapBaseLeft=0.0;
    private Double totaltimeBaseLeft=0.0;

    private Integer numclickBaseRight=0;
    private Double gtimeLapBaseRight=0.0;
    private Double totaltimeBaseRight=0.0;

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
            numclickBaseRight = datipassati.getInt("numclickBaseRight");
            numclickBraccioLeft = datipassati.getInt("numclickBraccioLeft");
            numclickBraccioRight = datipassati.getInt("numclickBraccioRight");
            numclickManoLeft = datipassati.getInt("numclickManoLeft");
            numclickManoRight = datipassati.getInt("numclickManoRight");

        }

        //stampo in una TextView i minuti e i secondi totali impiegati nel formato: MM:SS,s con s = millesimo di secondo
        TextView timeText = findViewById(R.id.timeText);
        TextView clickbasesx = findViewById(R.id.clickbasesx);
        TextView clickbasedx = findViewById(R.id.clickbasedx);
        TextView clickbracciosx = findViewById(R.id.clickbracciosx);
        TextView clickbracciodx = findViewById(R.id.clickbracciodx);
        TextView clickmanosx = findViewById(R.id.clickmanosx);
        TextView clickmanodx = findViewById(R.id.clickmanodx);

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

        clickbasesx.setText(numclickBaseLeft.toString());
        clickbasedx.setText(numclickBaseRight.toString());
        clickbracciosx.setText(numclickBraccioLeft.toString());
        clickbracciodx.setText(numclickBraccioRight.toString());
        clickmanosx.setText(numclickManoLeft.toString());
        clickmanodx.setText(numclickManoRight.toString());
    }
}
