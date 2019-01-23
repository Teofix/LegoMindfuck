package com.adev.android.legomindfuQ.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.adev.android.legomindfuQ.R;

public class InstructionActivity extends AppCompatActivity {

    /*
    Button software;
    Button hardware;
    */

    WebView instruction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_instruction);
        instruction = (WebView) findViewById(R.id.instructionView);
        instruction.setVisibility(View.INVISIBLE);
        WebSettings webSettings = instruction.getSettings();
        webSettings.setJavaScriptEnabled(true);
        instruction.loadUrl("file:///android_asset/HowTo.html");
        instruction.setVisibility(View.VISIBLE);

        /*
        setContentView(R.layout.activity_instruction);
        view = (WebView) findViewById(R.id.webview);
        view.setVisibility(View.INVISIBLE);
        software = (Button) findViewById(R.id.bSoftware);
        hardware = (Button) findViewById(R.id.bHardware);

        software.setOnClickListener(view1 -> {
            software.setVisibility(View.INVISIBLE);
            hardware.setVisibility(View.INVISIBLE);
            WebSettings webSettings = view.getSettings();
            webSettings.setJavaScriptEnabled(true);
            view.loadUrl("file:///android_asset/HowTo.html");
            view.setVisibility(View.VISIBLE);
        });

        hardware.setOnClickListener(view2 -> {
            software.setVisibility(View.INVISIBLE);
            hardware.setVisibility(View.INVISIBLE);
            WebSettings webSettings = view.getSettings();
            webSettings.setJavaScriptEnabled(true);
            view.loadUrl("file:///android_asset/Build.html");
            view.setVisibility(View.VISIBLE);
        });
        */

    }

}
