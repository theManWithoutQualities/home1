package com.example.home1konstantinov.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.home1konstantinov.R;
import com.example.home1konstantinov.settings.Settings;

public class Description extends AppCompatActivity {
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        final Intent currentIntent = getIntent();
        if (currentIntent.getExtras() != null) {
            final Settings currentSettings = (Settings) currentIntent.getExtras().get("settings");
            if (currentSettings != null) {
                settings = currentSettings;
            }
        }

        final View buttonView = findViewById(R.id.button);
        buttonView.setOnClickListener(v -> {
            final Intent intent = new Intent();
            intent.setClass(v.getContext(), ChooseTheme.class);
            intent.putExtra("settings", this.settings);
            startActivity(intent);
        });

    }
}
