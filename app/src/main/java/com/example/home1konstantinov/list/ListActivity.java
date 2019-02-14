package com.example.home1konstantinov.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.home1konstantinov.BasicActivity;
import com.example.home1konstantinov.ColorDataUtil;
import com.example.home1konstantinov.EnterActivity;
import com.example.home1konstantinov.R;
import java.util.List;

public class ListActivity extends BasicActivity {

    private List<Integer> colorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        colorList = ColorDataUtil.getRandomColorList();

        final RecyclerView listRecyclerView = findViewById(R.id.listRecyclerView);
        listRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listRecyclerView.setLayoutManager(linearLayoutManager);
        listRecyclerView.setAdapter(new ListAdapter(colorList));
    }

    public void addListItem(View view) {
        int color = ColorDataUtil.getRandomColor();
        colorList.add(0, color);
        final RecyclerView listRecyclerView = findViewById(R.id.listRecyclerView);
        listRecyclerView.getAdapter().notifyItemInserted(0);
        listRecyclerView.scrollToPosition(0);
        Log.i("ACTION", "add item");
    }

    @Override
    public void onBackPressed() {
        final Intent intent = new Intent();
        intent.setClass(this, EnterActivity.class);
        startActivity(intent);
    }
}
