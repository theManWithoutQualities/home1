package com.example.home1konstantinov;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.example.home1konstantinov.contacts.ContactListActivity;
import com.example.home1konstantinov.settings.Settings;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Answers(), new Crashlytics());
        setContentView(R.layout.activity_main);

        final Intent currentIntent = getIntent();
        if (currentIntent.getExtras() != null) {
            final Settings currentSettings = (Settings) currentIntent.getExtras().get("settings");
            if (currentSettings != null) {
                settings = currentSettings;
            }
        }

        final View buttonView = findViewById(R.id.button);
        buttonView.setOnClickListener((v) -> {
            final Intent intent = new Intent();
            intent.setClass(v.getContext(), Description.class);
            intent.putExtra("settings", this.settings);
            startActivity(intent);
        });
    }

    public void showContactList(View view) {
        final Intent intent = new Intent();
        intent.setClass(view.getContext(), ContactListActivity.class);
        startActivity(intent);
    }
}
