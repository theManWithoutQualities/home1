package com.example.home1konstantinov.launcher;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.home1konstantinov.R;
import java.util.List;

class IconsAdapter extends RecyclerView.Adapter<IconsAdapter.IconsViewHolder>{
    private final List<Integer> colorList;

    public List<Integer> getColorList() {
        return colorList;
    }

    public IconsAdapter(List<Integer> colorList) {
        this.colorList = colorList;
    }

    @NonNull
    @Override
    public IconsAdapter.IconsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_icon_view, viewGroup, false);
        return new IconsViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull IconsViewHolder iconsViewHolder, int position) {
        iconsViewHolder.bindColor(colorList.get(position));
    }

    public class IconsViewHolder extends RecyclerView.ViewHolder {
        private final View.OnClickListener deleteIconListener =
                (v) -> removeAt(getAdapterPosition());
        public IconsViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnLongClickListener((v) -> {
                Snackbar
                        .make(v, "Are you sure?", Snackbar.LENGTH_INDEFINITE)
                        .setDuration(5000)
                        .setAction("Yes", deleteIconListener)
                        .show();
                Log.i("ACTION", "snackbar");
                return true;
            });
        }

        public void bindColor(int color) {
            itemView.setBackgroundColor(color);
        }
    }

    public void removeAt(int position) {
        colorList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, colorList.size());
        Log.i("ACTION", "remove icon");
    }
}
