package com.adev.android.legomindfuck.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.regex.Pattern;

import com.adev.android.legomindfuck.R;
import com.adev.android.legomindfuck.ShowDialogMessage;
import com.adev.android.legomindfuck.Thread.SocketManager;

public class ConnectionTestActivity extends AppCompatActivity {

    public static boolean isConnected = true;
    private EditText ip;
    private EditText ip2;
    private TextView guide;
    private Button test;

    private SocketManager ev3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_test);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ip = (EditText) findViewById(R.id.ip_num);
        ip2 = (EditText) findViewById(R.id.ip_num2);
        guide = (TextView) findViewById(R.id.conn_guide);
        test = (Button) findViewById(R.id.button_test);

        guide.setText("Indicazioni per procedura guidata per effettuare connessione tra dispositivo e robot, con successivo test della connessione tramite pulsante.");

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstIp = ip.getText().toString();
                String secondIp = ip2.getText().toString();

                boolean validIP = IpRegex.isValid(firstIp) && IpRegex.isValid(secondIp) && firstIp.equals(secondIp);

                if(!validIP) {

                    ShowDialogMessage err = new ShowDialogMessage();
                    err.setContent("Errore di inserimento!", "Invalid IP or mismatching IP");
                    err.show(getSupportFragmentManager(), "");
                }

                 else {
                    if (ev3 == null) ev3 = new SocketManager();
                    ev3.setIp(firstIp);
                    ev3.openSocket();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Context context = getApplicationContext();
                    CharSequence text = "Connection with EV3: succefull!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
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
            case R.id.menItem_connection  :
                break;

            case R.id.menItem_play   :
                Intent i = new Intent(getApplicationContext(), PlayMenuActivity.class);
                startActivity(i);
        }

        return false;
    }


    public static class IpRegex {

        private static final String zeroTo255 = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])";

        private static final String IP_REGEXP = zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255;

        private static final Pattern IP_PATTERN = Pattern.compile(IP_REGEXP);

        public static boolean isValid(String address) {

            return IP_PATTERN.matcher(address).matches();

        }

    }

}
