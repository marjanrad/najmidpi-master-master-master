package com.example.najmidpi.database.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sensor")
public class SensorTable {

    @PrimaryKey(autoGenerate = true)
    public long _id = 0;

    @ColumnInfo(name = "blood_oxygen")
    public float gam_shomar = 0;

    @ColumnInfo(name = "feshar_sanj")
    public float feshar_sanj = 0;

    @ColumnInfo(name = "zaraban_ghalb")
    public float zaraban_ghalb = 0;

    @ColumnInfo(name = "vazn")
    public float vazn = 0;

    @ColumnInfo(name = "ghnad_khon")
    public float ghandKhon = 0;

    @ColumnInfo(name = "date")
    public String date = "";

    @ColumnInfo(name = "times")
    public String times = "";



    public SensorTable(long _id, float blood_oxygen, float feshar_sanj, float zaraban_ghalb, float vazn, float ghand, String date, String times) {
        this._id = _id;
        this.gam_shomar = blood_oxygen;
        this.feshar_sanj = feshar_sanj;
        this.zaraban_ghalb = zaraban_ghalb;
        this.vazn = vazn;
        this.ghandKhon=ghand;
        this.date = date;
        this.times = times;

    }

    public SensorTable(float blood_oxygen, float feshar_sanj, float zaraban_ghalb, float vazn, float ghand, String date, String times) {
        this.gam_shomar = blood_oxygen;
        this.feshar_sanj = feshar_sanj;
        this.zaraban_ghalb = zaraban_ghalb;
        this.vazn = vazn;
        this.ghandKhon=ghand;
        this.date = date;
        this.times = times;
    }

    public SensorTable() {

    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public float blood_oxygen() {
        return gam_shomar;
    }

    public void setBlood_oxygen(float gam_shomar) {
        this.gam_shomar = gam_shomar;
    }

    public float getFeshar_sanj() {
        return feshar_sanj;
    }

    public void setFeshar_sanj(float feshar_sanj) {
        this.feshar_sanj = feshar_sanj;
    }

    public float getZaraban_ghalb() {
        return zaraban_ghalb;
    }

    public void setZaraban_ghalb(float zaraban_ghalb) {
        this.zaraban_ghalb = zaraban_ghalb;
    }

    public float getVazn() {
        return vazn;
    }

    public void setVazn(float vazn) {
        this.vazn = vazn;
    }

    public float getGhandKhon() {
        return ghandKhon;
    }

    public void setGhandKhon(float ghandKhon) {
        this.ghandKhon = ghandKhon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }
}
