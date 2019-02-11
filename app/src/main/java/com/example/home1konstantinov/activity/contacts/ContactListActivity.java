package com.example.home1konstantinov.activity.contacts;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.home1konstantinov.R;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

public class ContactListActivity extends AppCompatActivity {
    private Random random = new Random();
    private LinkedList<Integer> colorList = new LinkedList<>();
    public static final int INITIAL_CONTACTS_SIZE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        generateColorData();

        final RecyclerView listRecyclerView = findViewById(R.id.listRecyclerView);
        listRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listRecyclerView.setLayoutManager(linearLayoutManager);
        listRecyclerView.setAdapter(new ContactListAdapter(colorList));
    }

    private void generateColorData() {
        for (int i = 0; i < INITIAL_CONTACTS_SIZE; i++) {
            int color = getRandomColor();
            colorList.add(color);
        }
    }

    private int getRandomColor() {
        return Color.rgb(
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)
        );
    }

    public void addContact(View view) {
        int color = getRandomColor();
        colorList.add(0, color);
        final RecyclerView listRecyclerView = findViewById(R.id.listRecyclerView);
        listRecyclerView.getAdapter().notifyItemInserted(0);
        Log.i("ACTION", "add item");
    }
}
