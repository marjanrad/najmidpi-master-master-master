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
import com.example.najmidpi.model.HistoryTableList;
import com.example.najmidpi.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentTable extends Fragment {

    private android.view.View View;
    RecyclerView recyclerView;
    AdapterRecyclerHistory adapter;
    List<HistoryTableList> historyList;

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View = inflater.inflate(R.layout.fragment_table, container, false);
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
        historyList.add(new HistoryTableList("1" , "98.01.01","12:00","12"));
        historyList.add(new HistoryTableList("2" , "98.02.01","13:00","20"));
        historyList.add(new HistoryTableList("3" , "98.03.01","12:00","30"));
        historyList.add(new HistoryTableList("4" , "98.04.01","15:00","11"));
        historyList.add(new HistoryTableList("5" , "98.05.01","12:00","8"));
        historyList.add(new HistoryTableList("6" , "98.06.01","8:00","13"));
        historyList.add(new HistoryTableList("7" , "98.07.01","1:00","13"));
        historyList.add(new HistoryTableList("8" , "98.08.01","18:00","12"));
    }


    private void init() {
        recyclerView=View.findViewById(R.id.history_recycler);
    }

}
