package com.example.home1konstantinov.list;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.home1konstantinov.R;

import java.util.List;

class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {
    private final List<Integer> colorList;

    public ListAdapter(List<Integer> colorList) {
        this.colorList = colorList;
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_contact_view, viewGroup, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder listHolder, int i) {
        listHolder.bindColor(colorList.get(i));
        listHolder.bindTitle(String.format("#%06X", 0xFFFFFF & colorList.get(i)));
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }



    public class ListHolder extends RecyclerView.ViewHolder {

        View.OnClickListener deleteItemListener = (v) -> removeAt(getAdapterPosition());
        public ListHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnLongClickListener((v) -> {
                Snackbar
                        .make(v, "Are you sure?", Snackbar.LENGTH_INDEFINITE)
                        .setDuration(5000)
                        .setAction("Yes", deleteItemListener)
                        .show();
                Log.i("ACTION", "snackbar");
                return true;
            });
        }

        public void bindColor(int color) {
            ((ListView)itemView).setAvatarColor(color);
        }
        public void bindTitle(String title) {
            ((ListView)itemView).setTitle(title);
        }
    }

    public void removeAt(int position) {
        colorList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, colorList.size());
        Log.i("ACTION", "remove list item");
    }
}
