package com.example.najmidpi.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.najmidpi.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentBarChart extends Fragment {
    private View View;
    private BarChart barChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View = inflater.inflate(R.layout.fragment_barchart, container, false);
        findItem();
        setData();
        return View;
    }

    private void setData() {
        BarDataSet barDataSet1=new BarDataSet(barEntries1(),"");
        barDataSet1.setColor(Color.BLUE);

        BarDataSet barDataSet2=new BarDataSet(barEntries2(),"");
        barDataSet2.setColor(Color.CYAN);

        BarDataSet barDataSet3=new BarDataSet(barEntries3(),"");
        barDataSet3.setColor(Color.YELLOW);

        BarData data=new BarData(barDataSet1 , barDataSet2 , barDataSet3);
        barChart.setData(data);

        String[] days=new String[]{"شنبه","یکشنبه","دوشنبه","سه شنبه","چهار شنبه","پنج شنبه","جمعه"};

        XAxis xAxis=barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);

        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(3);

        float barSpace=0.08f;
        float groupSpace=0.19f;
        data.setBarWidth(0.19f);

        barChart.getXAxis().setAxisMinimum(0);
        barChart.getXAxis().setAxisMaximum(0+barChart.getBarData().getGroupWidth(groupSpace,barSpace*7));
        barChart.getAxisLeft().setAxisMinimum(0);

        barChart.groupBars(0,groupSpace,barSpace);

        barChart.invalidate();



    }

    private void findItem() {
        barChart = View.findViewById(R.id.fragment_barChart);
    }

    private ArrayList<BarEntry> barEntries1() {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, 200));
        barEntries.add(new BarEntry(2, 230));
        barEntries.add(new BarEntry(3, 150));
        barEntries.add(new BarEntry(4, 150));
        barEntries.add(new BarEntry(5, 260));
        barEntries.add(new BarEntry(6, 220));
        barEntries.add(new BarEntry(7, 200));
        return barEntries;
    }

    private ArrayList<BarEntry> barEntries2() {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, 180));
        barEntries.add(new BarEntry(2, 160));
        barEntries.add(new BarEntry(3, 200));
        barEntries.add(new BarEntry(4, 210));
        barEntries.add(new BarEntry(5, 190));
        barEntries.add(new BarEntry(6, 150));
        barEntries.add(new BarEntry(7, 200));
        return barEntries;
    }

    private ArrayList<BarEntry> barEntries3() {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, 110));
        barEntries.add(new BarEntry(2, 220));
        barEntries.add(new BarEntry(3, 300));
        barEntries.add(new BarEntry(4, 250));
        barEntries.add(new BarEntry(5, 160));
        barEntries.add(new BarEntry(6, 80));
        barEntries.add(new BarEntry(7, 240));
        return barEntries;
    }
}
