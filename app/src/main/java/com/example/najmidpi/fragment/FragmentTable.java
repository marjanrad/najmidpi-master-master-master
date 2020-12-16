package com.example.najmidpi.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.najmidpi.R;
import com.example.najmidpi.adapter.AdapterRecyclerHistory;
import com.example.najmidpi.database.DatabaseController;
import com.example.najmidpi.database.DatabaseHolder;
import com.example.najmidpi.database.tables.SensorTable;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentTable extends Fragment {

    private android.view.View View;
    RecyclerView recyclerView;
    AdapterRecyclerHistory adapter;
    List<SensorTable> historyList;
    private DatabaseController dbHelper;
    String startDate, endDate;

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View = inflater.inflate(R.layout.fragment_table, container, false);
        dbHelper = DatabaseHolder.Companion.getInstance(container.getContext()).databaseController();

        init();
        prepareData();
        showData();

        return View;
    }

    private void showData() {
        adapter = new AdapterRecyclerHistory();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.syncData(historyList);

        setFilterByDate();
    }

    private void setFilterByDate() {
//        Extension.INSTANCE.filterListByDate(
//                historyList,
//                startDate,
//                endDate
//        );
    }

    private void prepareData() {

        if (historyList == null) {
            historyList = new ArrayList<>();
        } else {
            historyList.clear();
        }

        for (int i = 0; i < dbHelper.getAllSensor().size(); i++) {
            float feshar = dbHelper.getAllSensor().get(i).getFeshar_sanj();
            float oxygen = dbHelper.getAllSensor().get(i).blood_oxygen();
            float vazn = dbHelper.getAllSensor().get(i).getVazn();
            float ghangKhon = dbHelper.getAllSensor().get(i).getGhandKhon();
            float zarabanGhalb = dbHelper.getAllSensor().get(i).getZaraban_ghalb();
            String date = dbHelper.getAllSensor().get(i).getDate();
            String time = dbHelper.getAllSensor().get(i).getTimes();


            SensorTable sensorTable = new SensorTable();

            sensorTable.setFeshar_sanj(feshar);
            sensorTable.setBlood_oxygen(oxygen);
            sensorTable.setVazn(vazn);
            sensorTable.setGhandKhon(ghangKhon);
            sensorTable.setZaraban_ghalb(zarabanGhalb);
            sensorTable.setDate(date);
            sensorTable.setTimes(time);

            historyList.add(sensorTable);
        }
    }


    private void init() {

        if (getArguments() != null) {

            String startYear = getArguments().getString("startYear");
            String startMonth = getArguments().getString("startMonth");
            String startDay = getArguments().getString("startDay");
            String endYear = getArguments().getString("endYear");

            String endMonth = getArguments().getString("endMonth");
            String endDay = getArguments().getString("endDay");

            startDate = startYear + "/" + startMonth + "/" + startDay;
            endDate = endYear + "/" + endMonth + "/" + endDay;


//            Extension.INSTANCE.filterListByDate(
//                    historyList,
//                    startDate,
//                    endDate
//            );

        }

        recyclerView = View.findViewById(R.id.history_recycler);
    }

}
