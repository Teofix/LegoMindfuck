package com.adev.android.legomindfuQ.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.adev.android.legomindfuQ.R;

public class WelcomeActivity extends AppCompatActivity {

    public static String COMPLETED_ONBOARDING_PREF_NAME = "false";

    private Button mCloseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mCloseButton = findViewById(R.id.closeButton);
        mCloseButton.setOnClickListener(v -> super.finish());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences.Editor sharedPreferencesEditor =
                PreferenceManager.getDefaultSharedPreferences(this).edit();
        sharedPreferencesEditor.putBoolean(
                COMPLETED_ONBOARDING_PREF_NAME, true);
        sharedPreferencesEditor.apply();
    }
}
