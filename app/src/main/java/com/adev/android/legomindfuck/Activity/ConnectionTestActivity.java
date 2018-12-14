package com.adev.android.legomindfuck.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
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

import java.util.regex.Pattern;

import com.adev.android.legomindfuck.R;
import com.adev.android.legomindfuck.ShowDialogMessage;
import com.adev.android.legomindfuck.Thread.SocketManager;

import static com.adev.android.legomindfuck.Activity.MainMenuActivity.ev3;

public class ConnectionTestActivity extends AppCompatActivity {

    private EditText ip;
    private TextView guide;
    private Button test;

    private static String lastIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_test);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ip = (EditText) findViewById(R.id.ip_num);
        guide = (TextView) findViewById(R.id.conn_guide);
        test = (Button) findViewById(R.id.button_test);

        ip.setText(lastIP);

        guide.setText("Assicurati che il robot ev3 sia connesso alla stessa rete wifi di questo dispositivo Android. Controlla l'indirizzo ip del robot ev3 e inseriscilo nel campo \"Robot IP\". Conferma poi l'inserimento riscrivendo l'indirizzo ip nel secondo campo \"Confirm robot IP\". Premi poi \"TEST\" per verificare la connessione.");

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstIp = ip.getText().toString();

                boolean validIP = IpRegex.isValid(firstIp);

                if(!validIP) {

                    ShowDialogMessage err = new ShowDialogMessage();
                    err.setContent("Errore di inserimento!", "Invalid IP or mismatching IP");
                    err.show(getSupportFragmentManager(), "");
                }

                 else {
                    ev3 = new SocketManager(firstIp);
                    ev3.connect();

                    new Handler().postDelayed(() -> {
                        if (ev3.isSocketReady()) {
                            ev3.sendMessage("#atconnect#");
                            lastIP = firstIp;
                            Toast.makeText(getApplicationContext(), "Connection with EV3: succefull!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), MainMenuActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "Connection FAILED!", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }, 1000);
                }
            }
        });
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
