package com.example.najmidpi.model;

import androidx.annotation.NonNull;

public class HistoryTableList {

    String count, date, time, value;
    int dataSend;


    public HistoryTableList(String count, String date, String time, String value, int dataSend) {

        this.count = count;
        this.date = date;
        this.time = time;
        this.value = value;
        this.dataSend = dataSend;

    }

    public int getDataSend() {
        return dataSend;
    }

    public void setDataSend(int dataSend) {
        this.dataSend = dataSend;
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
