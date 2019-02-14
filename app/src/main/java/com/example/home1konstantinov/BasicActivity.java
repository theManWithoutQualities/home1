package com.example.home1konstantinov;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;

public class BasicActivity extends AppCompatActivity {
    @Override
    public Resources.Theme getTheme() {
        final SharedPreferences defaultSharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        final String themeName = defaultSharedPreferences.getString("theme", "light");
        final Resources.Theme theme = super.getTheme();
        theme.applyStyle("light".equals(themeName) ? R.style.light : R.style.dark, true);
        return theme;
    }
}
