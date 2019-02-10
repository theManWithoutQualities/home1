package com.example.home1konstantinov.activity.launcher;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.home1konstantinov.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class IconsAdapter extends RecyclerView.Adapter<IconsAdapter.IconsViewHolder>{
    private Random random = new Random();
    private Map<Integer, Integer> colorMap = new HashMap<>();

    @NonNull
    @Override
    public IconsAdapter.IconsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_icon_view, viewGroup, false);
        view.setOnLongClickListener((v) -> {
            final int color = ((ColorDrawable) v.getBackground()).getColor();
            Toast
                    .makeText(
                            v.getContext(), "" + String.format("#%06X", 0xFFFFFF & color),
                            Toast.LENGTH_LONG
                    )
                    .show();
            return true;
        });
        return new IconsViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    @Override
    public void onBindViewHolder(@NonNull IconsViewHolder iconsViewHolder, int position) {
        iconsViewHolder.bindColor(getColor(position));
    }

    private int getColor(int position) {
        Integer color = colorMap.get(position);
        if (color == null) {
            color = Color.rgb(
                    random.nextInt(256),
                    random.nextInt(256),
                    random.nextInt(256)
            );
            colorMap.put(position, color);
        }
        return color;
    }

    public static class IconsViewHolder extends RecyclerView.ViewHolder {
        public IconsViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindColor(int color) {
            itemView.setBackgroundColor(color);
        }
    }
}
