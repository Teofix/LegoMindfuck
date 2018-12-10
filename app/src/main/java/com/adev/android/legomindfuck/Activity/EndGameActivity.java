package com.adev.android.legomindfuck.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.adev.android.legomindfuck.R;

public class EndGameActivity extends AppCompatActivity {

    private Double mins;
    private Double secs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        //Recupero i dati da stampare nelle statistiche
        Bundle datipassati = getIntent().getExtras();
        if (datipassati != null) {
            mins = datipassati.getDouble("min");
            secs = datipassati.getDouble("sec");
        }

        //stampo in una TextView i minuti e i secondi totali impiegati nel formato: MM:SS,s con s = millesimo di secondo
        TextView timeText = findViewById(R.id.timeText);

        String[] formatM = mins.toString().split("\\.");
        String[] formatS = secs.toString().split("\\.");
        String tempototale = formatM[0] + ":" + formatS[0] + "," + formatS[1].charAt(0);
        timeText.setText(tempototale);
    }
}
