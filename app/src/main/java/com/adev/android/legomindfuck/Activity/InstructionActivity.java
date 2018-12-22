package com.adev.android.legomindfuck.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.adev.android.legomindfuck.R;

public class InstructionActivity extends AppCompatActivity {

    WebView view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        view = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = view.getSettings();
        webSettings.setJavaScriptEnabled(true);
        view.loadUrl("file:///android_asset/HowTo.html");
        view.setVisibility(View.VISIBLE);

    }

}
