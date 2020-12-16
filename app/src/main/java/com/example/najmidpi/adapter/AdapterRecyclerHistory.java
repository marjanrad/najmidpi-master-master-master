package com.example.najmidpi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.najmidpi.R;
import com.example.najmidpi.database.tables.SensorTable;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterRecyclerHistory extends RecyclerView.Adapter<AdapterRecyclerHistory.viewHolder> {

    Context context;
    private List<SensorTable> ListHistory = new ArrayList<>();
    public static int sensoreSelected;
    float firstValue;

    public AdapterRecyclerHistory() {

    }

    public void syncData(List<SensorTable> ListHistory) {
        this.ListHistory.clear();
        notifyDataSetChanged();
        this.ListHistory.addAll(ListHistory);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_recycler_list, viewGroup, false);
        context = viewGroup.getContext();
        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
//        viewHolder.bind(ListHistory.get(i));


        if (sensoreSelected == 0) {
            firstValue = ListHistory.get(i).getFeshar_sanj();
            Log.d("sens", "feshar");
        } else if (sensoreSelected == 1) {
            firstValue = ListHistory.get(i).getZaraban_ghalb();
            Log.d("sens", "zaraban");
        } else if (sensoreSelected == 2) {
            firstValue = ListHistory.get(i).blood_oxygen();
            Log.d("sens", "gam");
        } else if (sensoreSelected == 3) {
            firstValue = ListHistory.get(i).getVazn();
            Log.d("sens", "vazn");
        }else if (sensoreSelected==4){
            firstValue=ListHistory.get(i).getGhandKhon();
            Log.d("sens", "ghand");
        }

        String date = ListHistory.get(i).getDate();
        float value = firstValue;
        String time = ListHistory.get(i).getTimes();

        viewHolder.counter.setText(String.valueOf(i + 1));
        viewHolder.date.setText(date);
        viewHolder.value.setText(value + "");
        viewHolder.time.setText(time);
    }

    @Override
    public int getItemCount() {
        return ListHistory.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {

        TextView counter, date, time, value;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            counter = itemView.findViewById(R.id.recyler_history_count);
            date = itemView.findViewById(R.id.recyler_history_date);
            time = itemView.findViewById(R.id.recyler_history_time);
            value = itemView.findViewById(R.id.recyler_history_value);

        }

    }

    public void sensoreType(int type) {
        Log.d("sens", String.valueOf(type));
        sensoreSelected = type;
    }

}
