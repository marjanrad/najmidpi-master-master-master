package com.example.najmidpi.model;

import android.support.annotation.NonNull;

public class HistoryTableList {

    String count , date , time , value;

    public HistoryTableList(String count , String date , String time , String value){

        this.count=count;
        this.date=date;
        this.time=time;
        this.value=value;

    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @NonNull
    @Override
    public String toString() {
        return value;
    }
}
