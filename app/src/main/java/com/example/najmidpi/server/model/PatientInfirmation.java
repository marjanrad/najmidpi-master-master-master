package com.example.najmidpi.server.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatientInfirmation {

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("patId")
    @Expose
    String patId;

    @SerializedName("age")
    @Expose
    String age;

    @SerializedName("gender")
    @Expose
    String gender;

    @SerializedName("height")
    @Expose
    String height;

    @SerializedName("weight")
    @Expose
    String weight;

    @SerializedName("call")
    @Expose
    String call;

    @SerializedName("address")
    @Expose
    String address;

    @SerializedName("casehist")
    @Expose
    String casehist;

    @SerializedName("drId")
    @Expose
    String drId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatId() {
        return patId;
    }

    public void setPatId(String patId) {
        this.patId = patId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCasehist() {
        return casehist;
    }

    public void setCasehist(String casehist) {
        this.casehist = casehist;
    }

    public String getDrId() {
        return drId;
    }

    public void setDrId(String drId) {
        this.drId = drId;
    }
}
