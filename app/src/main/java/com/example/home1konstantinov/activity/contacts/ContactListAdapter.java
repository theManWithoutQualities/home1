package com.example.home1konstantinov.activity.contacts;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.home1konstantinov.R;

import java.util.LinkedList;

class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactListHolder> {
    private LinkedList<Integer> colorList;
    private int deleteCandidateId;
    private View.OnClickListener deleteItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            colorList.remove(deleteCandidateId);
            notifyItemRemoved(deleteCandidateId);
            notifyItemRangeChanged(deleteCandidateId, colorList.size());
            Log.i("ACTION", "remove item");
        }
    };

    public ContactListAdapter(LinkedList<Integer> colorList) {
        this.colorList = colorList;
    }

    @NonNull
    @Override
    public ContactListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_contact_view, viewGroup, false);
        view.setOnLongClickListener((v) -> {
            deleteCandidateId = (int) v.getTag();
            Snackbar
                    .make(v, "Are you sure?", Snackbar.LENGTH_INDEFINITE)
                    .setDuration(5000)
                    .setAction("Yes", deleteItemListener)
                    .show();
            Log.i("ACTION", "snackbar");
            return true;
        });
        return new ContactListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListHolder contactListHolder, int i) {
        contactListHolder.bindColor(colorList.get(i));
        contactListHolder.bindTitle(String.format("#%06X", 0xFFFFFF & colorList.get(i)));
        contactListHolder.bindTag(i);
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }



    public static class ContactListHolder extends RecyclerView.ViewHolder {
        public ContactListHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindColor(int color) {
            ((ContactView)itemView).setAvatarColor(color);
        }
        public void bindTitle(String title) {
            ((ContactView)itemView).setTitle(title);
        }
        public void bindTag(int tag) {
            itemView.setTag(tag);
        }
    }
}
