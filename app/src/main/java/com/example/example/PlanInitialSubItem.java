package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PlanInitialSubItem extends AppCompatActivity {


    String placeTime, placeName, placeMemo, transBudgetText, transportText;
    int transport, placeType;

    public PlanInitialSubItem(String placeTime, String placeName, String placeMemo, int transport, int placeType) {
        this.placeTime = placeTime;
        this.placeName = placeName;
        this.placeMemo = placeMemo;
        this.transport = transport;
        this.placeType = placeType;
    }


    public String getPlaceTime() {
        return placeTime;
    }

    public void setPlaceTime(String placeTime) {
        this.placeTime = placeTime;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceMemo() {
        return placeMemo;
    }

    public void setPlaceMemo(String placeMemo) {
        this.placeMemo = placeMemo;
    }

    public void setTransport(int transport){ this.transport = transport; }

    public int getTransport() {
        return transport;
    }

    public int getPlaceType() {
        return placeType;
    }

    public void setPlaceType(int placeType) {
        this.placeType = placeType;
    }

    @Override
    public String toString() {
        return "PlanInitialSubItem{" +
                "placeTime='" + placeTime + '\'' +
                ", placeName='" + placeName + '\'' +
                ", placeMemo='" + placeMemo + '\'' +
                ", transBudgetText='" + transBudgetText + '\'' +
                ", transport=" + transport +
                ", placeType=" + placeType +
                '}';
    }
}

