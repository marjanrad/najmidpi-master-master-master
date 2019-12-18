package com.example.najmidpi.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.najmidpi.R;
import com.example.najmidpi.model.HistoryTableList;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerHistory extends RecyclerView.Adapter<AdapterRecyclerHistory.viewHolder> {

    List<HistoryTableList> ListHistory;

   public AdapterRecyclerHistory(List<HistoryTableList> ListHistory) {
        if (ListHistory == null) {
            this.ListHistory = new ArrayList<>();
        } else {
            this.ListHistory = ListHistory;
        }

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_recycler_list, viewGroup, false);
        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        viewHolder.bind(ListHistory.get(i));

    }

    @Override
    public int getItemCount() {
        return ListHistory.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {

        TextView counter, date, time,value;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            counter=itemView.findViewById(R.id.recyler_history_count);
            date=itemView.findViewById(R.id.recyler_history_date);
            time=itemView.findViewById(R.id.recyler_history_time);
            value=itemView.findViewById(R.id.recyler_history_value);

        }
        public void bind(HistoryTableList historyList){
            counter.setText(historyList.getCount());
            date.setText(historyList.getDate());
            time.setText(historyList.getTime());
            value.setText(historyList.getValue());

        }
    }

}
