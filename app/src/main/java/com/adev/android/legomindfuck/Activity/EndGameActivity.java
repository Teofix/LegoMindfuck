package com.adev.android.legomindfuck.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.adev.android.legomindfuck.R;

import static com.adev.android.legomindfuck.Activity.MainMenuActivity.colors;

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

        TextView tbaseDx = findViewById(R.id.tempoclickbasedx);
        TextView nbaseDx = findViewById(R.id.numeroclickbasedx);
        TextView tbaseSx = findViewById(R.id.tempoclickbasesx);
        TextView nbaseSx = findViewById(R.id.numeroclickbasesx);

        TextView tbraccioDx = findViewById(R.id.tempoclickbracciodx);
        TextView nbraccioDx = findViewById(R.id.numeroclickbracciodx);
        TextView tbraccioSx = findViewById(R.id.tempoclickbracciosx);
        TextView nbraccioSx = findViewById(R.id.numeroclickbracciosx);

        TextView tmanoDx = findViewById(R.id.tempoclickmanodx);
        TextView nmanoDx = findViewById(R.id.numeroclickmanodx);
        TextView tmanoSx = findViewById(R.id.tempoclickmanosx);
        TextView nmanoSx = findViewById(R.id.numeroclickmanosx);

        TextView base1 = findViewById(R.id.textViewBaseDX);
        TextView base2 = findViewById(R.id.textViewBaseSX);
        TextView braccio1 = findViewById(R.id.textViewBraccioDX);
        TextView braccio2 = findViewById(R.id.textViewBraccioSX);
        TextView mano1 = findViewById(R.id.textViewManoDX);
        TextView mano2 = findViewById(R.id.textViewManoSX);

        ImageView m11 = findViewById(R.id.motor1_left);
        ImageView m12 = findViewById(R.id.motor1_right);
        ImageView m21 = findViewById(R.id.textView_motor2_left);
        ImageView m22 = findViewById(R.id.textView_motor2_right);
        ImageView m31 = findViewById(R.id.textView_motor3_left);
        ImageView m32 = findViewById(R.id.textView_motor3_right);

        TextView top1 = findViewById(R.id.idnome);
        TextView top2 = findViewById(R.id.idtempoclick);
        TextView top3 = findViewById(R.id.idnumeroclick);

        TextView timeText = findViewById(R.id.timeText);

        //Recupero i dati da stampare nelle statistiche
        Bundle datipassati = getIntent().getExtras();
        if (datipassati != null && colors.getPlayers() == 1) {
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

            //stampo in una TextView i minuti e i secondi totali impiegati nel formato: MM:SS,s con s = millesimo di secondo
            timeText.setText(formatTime(mins.toString().split("\\."), secs.toString().split("\\.")));

            //Riempio i campi degli score
            tbaseDx.setText(totaltimeBaseRight.toString());
            nbaseDx.setText(numclickBaseRight.toString());
            tbaseSx.setText(totaltimeBaseLeft.toString());
            nbaseSx.setText(numclickBaseLeft.toString());

            //setText per braccio
            tbraccioDx.setText(totaltimeBraccioRight.toString());
            nbraccioDx.setText(numclickBraccioRight.toString());
            tbraccioSx.setText(totaltimeBraccioLeft.toString());
            nbraccioSx.setText(numclickBraccioLeft.toString());

            //setText per mano
            tmanoDx.setText(totaltimeManoRight.toString());
            nmanoDx.setText(numclickManoRight.toString());
            tmanoSx.setText(totaltimeManoLeft.toString());
            nmanoSx.setText(numclickManoLeft.toString());

        } else if (datipassati != null && colors.getPlayers() == 2) {
            mins = datipassati.getDouble("min");
            secs = datipassati.getDouble("sec");
            victory = datipassati.getBoolean("result");
            timeText.setText(formatTime(mins.toString().split("\\."), secs.toString().split("\\.")));
            tbaseDx.setVisibility(View.INVISIBLE);
            nbaseDx.setVisibility(View.INVISIBLE);
            tbaseSx.setVisibility(View.INVISIBLE);
            nbaseSx.setVisibility(View.INVISIBLE);
            tbraccioDx.setVisibility(View.INVISIBLE);
            nbraccioDx.setVisibility(View.INVISIBLE);
            tbraccioSx.setVisibility(View.INVISIBLE);
            nbraccioSx.setVisibility(View.INVISIBLE);
            tmanoDx.setVisibility(View.INVISIBLE);
            nmanoDx.setVisibility(View.INVISIBLE);
            tmanoSx.setVisibility(View.INVISIBLE);
            nmanoSx.setVisibility(View.INVISIBLE);
            base1.setVisibility(View.INVISIBLE);
            base2.setVisibility(View.INVISIBLE);
            braccio1.setVisibility(View.INVISIBLE);
            braccio2.setVisibility(View.INVISIBLE);
            mano1.setVisibility(View.INVISIBLE);
            mano2.setVisibility(View.INVISIBLE);
            m11.setVisibility(View.INVISIBLE);
            m12.setVisibility(View.INVISIBLE);
            m21.setVisibility(View.INVISIBLE);
            m22.setVisibility(View.INVISIBLE);
            m31.setVisibility(View.INVISIBLE);
            m32.setVisibility(View.INVISIBLE);
            top1.setVisibility(View.INVISIBLE);
            top2.setVisibility(View.INVISIBLE);
            top3.setVisibility(View.INVISIBLE);
        }

            TextView result = findViewById(R.id.result);

            if (victory) {
                result.setText("HAI VINTO!");
                result.setBackgroundResource(R.drawable.round_background_green);
            } else {
                result.setText("HAI PERSO!");
                result.setBackgroundResource(R.drawable.round_background_red);
            }
    }

    private String formatTime(String[] formatM, String[] formatS) {
        return formatM[0] + ":" + formatS[0] + "," + formatS[1].charAt(0);
    }

}
