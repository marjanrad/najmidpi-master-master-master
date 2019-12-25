package com.example.najmidpi.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.najmidpi.adapter.AdapterRecyclerHistory;
import com.example.najmidpi.database.DbHelper;
import com.example.najmidpi.model.HistoryTableList;
import com.example.najmidpi.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentTable extends Fragment {

    private android.view.View View;
    RecyclerView recyclerView;
    AdapterRecyclerHistory adapter;
    List<HistoryTableList> historyList;
    private DbHelper dbHelper;

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View = inflater.inflate(R.layout.fragment_table, container, false);
        dbHelper = new DbHelper(container.getContext());
        init();
        prepareData();
        showData();
        return View;
    }

    private void showData() {
        adapter = new AdapterRecyclerHistory(historyList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void prepareData() {

        if (historyList==null){
            historyList=new ArrayList<>();
        }else {
            historyList.clear();
        }

        for (int i = 0; i < dbHelper.getAllSensor().size(); i++) {
            String feshar = dbHelper.getAllSensor().get(i).getFesharSanj();
            String gam = dbHelper.getAllSensor().get(i).getGamShomar();
            String date = dbHelper.getAllSensor().get(i).getDate();
            String time = dbHelper.getAllSensor().get(i).getTime();


            historyList.add(new HistoryTableList(String.valueOf(i) , date,time,gam));
        }
    }


    private void init() {
        recyclerView=View.findViewById(R.id.history_recycler);
    }

}
