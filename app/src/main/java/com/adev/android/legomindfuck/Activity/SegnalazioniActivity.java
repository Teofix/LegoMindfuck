package com.adev.android.legomindfuck.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.adev.android.legomindfuck.R;

public class SegnalazioniActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segnalazioni);

        //TODO: togliere la textview della email e lasciare solop il testo

        ImageButton sender = (ImageButton) findViewById(R.id.sendbutton);
        EditText body = (EditText) findViewById(R.id.body);
        EditText email = (EditText) findViewById(R.id.email);

        email.setFocusable(true);
        body.setFocusable(true);

        email.setOnClickListener(v -> {if(email.getText().toString().equals("email@email.com"))  email.setText("");});

        body.setOnFocusChangeListener((v, hasFocus) -> { if (hasFocus && body.getText().toString().equals("Scrivi qui le tue segnalazioni")) {body.setText("");}});

        sender.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_SEND).setType("message/rfc822").putExtra(Intent.EXTRA_EMAIL, new String[]{"info.adeveloper@gmail.com"}).putExtra(Intent.EXTRA_SUBJECT, "Feedback").putExtra(Intent.EXTRA_TEXT,body.getText().toString());
            try {
                startActivity(Intent.createChooser(i, "Invio email..."));
            } catch (android.content.ActivityNotFoundException e) {
                Toast.makeText(getApplicationContext(), "Nessun client email trovato.", Toast.LENGTH_SHORT).show();
            }
        });

    }

}