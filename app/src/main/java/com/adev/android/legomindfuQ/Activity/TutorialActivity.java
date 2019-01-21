package com.adev.android.legomindfuQ.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.adev.android.legomindfuQ.R;

public class TutorialActivity extends AppCompatActivity {

    WebView tutorial;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tutorial);
        tutorial = (WebView) findViewById(R.id.tutorialView);
        tutorial.setVisibility(View.INVISIBLE);
        WebSettings webSettings = tutorial.getSettings();
        webSettings.setJavaScriptEnabled(true);
        tutorial.loadUrl("file:///android_asset/Tutorial.html");
        tutorial.setVisibility(View.VISIBLE);
    }
}
