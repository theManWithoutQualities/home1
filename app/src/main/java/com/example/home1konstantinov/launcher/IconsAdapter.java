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
    private List<Integer> colorList;
    private int deleteCandidateId;

    public List<Integer> getColorList() {
        return colorList;
    }

    public int getDeleteCandidateId() {
        return deleteCandidateId;
    }

    public IconsAdapter setDeleteCandidateId(int deleteCandidateId) {
        this.deleteCandidateId = deleteCandidateId;
        return this;
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
        view.setOnLongClickListener((v) -> {
            setDeleteCandidateId((int) v.getTag());
            Snackbar
                    .make(v, "Are you sure?", Snackbar.LENGTH_INDEFINITE)
                    .setDuration(5000)
                    .setAction("Yes", deleteIconListener)
                    .show();
            Log.i("ACTION", "snackbar");
            return true;
        });
        return new IconsViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull IconsViewHolder iconsViewHolder, int position) {
        iconsViewHolder.bindColor(colorList.get(position));
        iconsViewHolder.bindTag(position);
    }

    public static class IconsViewHolder extends RecyclerView.ViewHolder {
        public IconsViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindColor(int color) {
            itemView.setBackgroundColor(color);
        }

        public void bindTag(int tag) {
            itemView.setTag(tag);
        }
    }

    private final View.OnClickListener deleteIconListener = v -> {
        getColorList().remove(getDeleteCandidateId());
        notifyItemRemoved(getDeleteCandidateId());
        notifyItemRangeChanged(getDeleteCandidateId(), getColorList().size());
        Log.i("ACTION", "remove icon");
    };
}
