package com.example.home1konstantinov;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.home1konstantinov.launcher.LauncherActivity;
import com.example.home1konstantinov.settings.Density;
import com.example.home1konstantinov.settings.Settings;

public class ChooseDensityActivity extends AppCompatActivity {
    private Settings settings = new Settings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_density);

        final Intent currentIntent = getIntent();
        if (currentIntent.getExtras() != null) {
            final Settings currentSettings = (Settings) currentIntent.getExtras().get("settings");
            if (currentSettings != null) {
                settings = currentSettings;
            }
        }

        final RadioButton standardDensityRadioButton = findViewById(R.id.standard_density);
        final RadioButton highDensityRadioButton = findViewById(R.id.high_density);
        if (settings.getDensity() != null) {
            if (settings.getDensity().equals(Density.STANDARD)) {
                standardDensityRadioButton.setChecked(true);
                highDensityRadioButton.setChecked(false);
            } else if (settings.getDensity().equals(Density.HIGH)) {
                standardDensityRadioButton.setChecked(false);
                highDensityRadioButton.setChecked(true);
            }
        }

        final View buttonView = findViewById(R.id.button);
        buttonView.setOnClickListener((v) -> {
            if (this.settings.getDensity() != null) {
                final Intent intent = new Intent();
                intent.setClass(v.getContext(), LauncherActivity.class);
                intent.putExtra("settings", this.settings);
                startActivity(intent);
            }
        });
    }

    public void clickRadioButtonDensity(View view) {
        switch (view.getId()) {
            case R.id.standard_density:
                settings.setDensity(Density.STANDARD);
                break;
            case R.id.high_density:
                settings.setDensity(Density.HIGH);
                break;
            default:
                settings.setDensity(Density.STANDARD);
                break;
        }
    }
}
