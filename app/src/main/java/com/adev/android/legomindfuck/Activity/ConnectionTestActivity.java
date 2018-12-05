package com.adev.android.legomindfuck.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.IOException;

import com.adev.android.legomindfuck.R;
import com.adev.android.legomindfuck.ShowDialogMessage;
import com.adev.android.legomindfuck.Thread.SocketManager;

public class ConnectionTestActivity extends AppCompatActivity {

    public static boolean isConnected = false;
    private EditText ip;
    private EditText ip2;
    private TextView guide;
    private Button test;

    private SocketManager ev3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_test);

        ip = (EditText) findViewById(R.id.ip_num);
        ip2 = (EditText) findViewById(R.id.ip_num2);
        guide = (TextView) findViewById(R.id.conn_guide);
        test = (Button) findViewById(R.id.button_test);

        guide.setText("-Indicazioni per procedura guidata per effettuare connessione tra dispositivo e robot, con successivo test della connessione tramite pulsante.");

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstIp = ip.getText().toString();
                String secondIp = ip2.getText().toString();

                if(ip.length()<11 || ip2.length()<11) {

                    ShowDialogMessage err = new ShowDialogMessage();
                    err.setContent("Errore di inserimento!", "Invalid IP");
                    err.show(getSupportFragmentManager(), "");
                }

                else if (!firstIp.equals(secondIp)) {

                    ShowDialogMessage err = new ShowDialogMessage();
                    err.setContent("Errore di inserimento!", "IP non corrispondenti");
                    err.show(getSupportFragmentManager(), "");
                } else {
                    if (ev3 == null) ev3 = new SocketManager();
                    ev3.setIp(ip.getText().toString());
                    ev3.openSocket();
                    String connectionOkMessage = "#ataaaaaaaaaaaaaaaa#";
                    ev3.sendMessage(connectionOkMessage);
                    isConnected = true;
                }
            }
        });
    }
}
