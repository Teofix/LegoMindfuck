package com.adev.android.legomindfuck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConnectionTest extends AppCompatActivity {

    private EditText id;
    private TextView guide;
    private Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_test);

        id = (EditText) findViewById(R.id.ip_num);
        guide = (TextView) findViewById(R.id.conn_guide);
        test = (Button) findViewById(R.id.button_test);

        guide.setText("-Indicazioni per procedura guidata per effettuare connessione tra dispositivo e robot, con successivo test della connessione tramite pulsante.");
    }
}
