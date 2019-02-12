package com.example.home1konstantinov.launcher;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.home1konstantinov.EnterActivity;
import com.example.home1konstantinov.R;
import com.example.home1konstantinov.settings.Density;
import com.example.home1konstantinov.settings.Settings;

public class Launcher extends AppCompatActivity {
    private Settings settings = new Settings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        final Intent currentIntent = getIntent();
        if (currentIntent.getExtras() != null) {
            final Settings currentSettings = (Settings) currentIntent.getExtras().get("settings");
            if (currentSettings != null) {
                settings = currentSettings;
            }
        }

        RecyclerView iconsRecyclerView = findViewById(R.id.iconsRecyclerView);
        iconsRecyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                this,
                calculateSpans(
                        settings.getDensity(),
                        getResources().getConfiguration().orientation
                )
        );
        iconsRecyclerView.setLayoutManager(gridLayoutManager);
        IconsAdapter iconsAdapter = new IconsAdapter();
        iconsRecyclerView.setAdapter(iconsAdapter);
        final int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.offset);
        iconsRecyclerView.addItemDecoration(new CustomDecoration(dimensionPixelOffset));
    }

    @Override
    public void onBackPressed() {
        final Intent intent = new Intent();
        intent.setClass(this, EnterActivity.class);
        intent.putExtra("settings", this.settings);
        startActivity(intent);
    }

    private int calculateSpans(Density density, int orientation) {
        if (density.equals(Density.STANDARD)) {
            switch (orientation) {
                case Configuration.ORIENTATION_PORTRAIT:
                    return 4;
                case Configuration.ORIENTATION_LANDSCAPE:
                    return 6;
                default:
                    return 4;
            }
        } else if (density.equals(Density.HIGH)){
            switch (orientation) {
                case Configuration.ORIENTATION_PORTRAIT:
                    return 5;
                case Configuration.ORIENTATION_LANDSCAPE:
                    return 7;
                default:
                    return 5;
            }
        }
        return 5;
    }
}
