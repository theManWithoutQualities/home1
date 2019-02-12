package com.example.home1konstantinov;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.home1konstantinov.settings.Settings;
import com.example.home1konstantinov.settings.Theme;

public class ChooseThemeActivity extends AppCompatActivity {
    private Settings settings = new Settings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_theme);

        final Intent currentIntent = getIntent();
        if (currentIntent.getExtras() != null) {
            final Settings currentSettings = (Settings) currentIntent.getExtras().get("settings");
            if (currentSettings != null) {
                settings = currentSettings;
            }
        }
        final RadioButton lightThemeRadioButton = findViewById(R.id.light_theme);
        final RadioButton darkThemeRadioButton = findViewById(R.id.dark_theme);
        if (settings.getTheme() != null) {
            if (settings.getTheme().equals(Theme.LIGHT)) {
                lightThemeRadioButton.setChecked(true);
                darkThemeRadioButton.setChecked(false);
            } else if (settings.getTheme().equals(Theme.DARK)) {
                lightThemeRadioButton.setChecked(false);
                darkThemeRadioButton.setChecked(true);
            }
        }

        final View buttonView = findViewById(R.id.button);
        buttonView.setOnClickListener((v) -> {
            if (this.settings.getTheme() != null) {
                final Intent intent = new Intent();
                intent.setClass(v.getContext(), ChooseDensityActivity.class);
                intent.putExtra("settings", this.settings);
                startActivity(intent);
            }
        });
    }

    public void clickRadioButtonTheme(View view) {
        switch (view.getId()) {
            case R.id.light_theme:
                settings.setTheme(Theme.LIGHT);
                break;
            case R.id.dark_theme:
                settings.setTheme(Theme.DARK);
                break;
            default:
                settings.setTheme( Theme.LIGHT);
                break;
        }
    }
}
