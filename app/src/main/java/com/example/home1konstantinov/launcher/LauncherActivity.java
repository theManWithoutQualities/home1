package com.example.home1konstantinov.launcher;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.home1konstantinov.BasicActivity;
import com.example.home1konstantinov.ColorDataUtil;
import com.example.home1konstantinov.EnterActivity;
import com.example.home1konstantinov.R;
import com.example.home1konstantinov.settings.Density;
import com.example.home1konstantinov.settings.Settings;
import java.util.List;

public class LauncherActivity extends BasicActivity {
    private Settings settings = new Settings();
    private List<Integer> colorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        colorList = ColorDataUtil.getRandomColorList();

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
        IconsAdapter iconsAdapter = new IconsAdapter(colorList);
        iconsRecyclerView.setAdapter(iconsAdapter);
        final int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.offset);
        iconsRecyclerView.addItemDecoration(new CustomDecoration(dimensionPixelOffset));
    }

    @Override
    public void onBackPressed() {
        final Intent intent = new Intent();
        intent.setClass(this, EnterActivity.class);
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

    public void addIcon(View view) {
        int color = ColorDataUtil.getRandomColor();
        colorList.add(0, color);
        final RecyclerView iconsRecyclerView = findViewById(R.id.iconsRecyclerView);
        iconsRecyclerView.getAdapter().notifyItemInserted(0);
        iconsRecyclerView.scrollToPosition(0);
        Log.i("ACTION", "add icon");
    }
}
