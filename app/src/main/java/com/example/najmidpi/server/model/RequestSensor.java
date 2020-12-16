package com.example.najmidpi.server.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestSensor {
    @SerializedName("date")
    @Expose
    String date;

    @SerializedName("vazn")
    @Expose
    String vazn;

    @SerializedName("hbeat")
    @Expose
    String hbeat;

    @SerializedName("oxygen")
    @Expose
    String oxygen;

    @SerializedName("feshar")
    @Expose
    String feshar;

    @SerializedName("sugar")
    @Expose
    String sugar;

    @SerializedName("lat")
    @Expose
    String lat;

    @SerializedName("lon")
    @Expose
    String lon;

    @SerializedName("patId")
    @Expose
    String patId;

    @SerializedName("drId")
    @Expose
    String drId;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVazn() {
        return vazn;
    }

    public void setVazn(String vazn) {
        this.vazn = vazn;
    }

    public String getHbeat() {
        return hbeat;
    }

    public void setHbeat(String hbeat) {
        this.hbeat = hbeat;
    }

    public String getOxygen() {
        return oxygen;
    }

    public void setOxygen(String oxygen) {
        this.oxygen = oxygen;
    }

    public String getFeshar() {
        return feshar;
    }

    public void setFeshar(String feshar) {
        this.feshar = feshar;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getPatId() {
        return patId;
    }

    public void setPatId(String patId) {
        this.patId = patId;
    }

    public String getDrId() {
        return drId;
    }

    public void setDrId(String drId) {
        this.drId = drId;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
