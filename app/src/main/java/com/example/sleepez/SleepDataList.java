package com.example.sleepez;

import android.util.Log;

import java.util.ArrayList;

public class SleepDataList {

    private static final String TAG = "SleepDataList";
    private ArrayList<SleepData> sleepDataArrayList;

    public SleepDataList() {
        this.sleepDataArrayList = new ArrayList<>();
    }

    public ArrayList<SleepData> getSleepDataArrayList() {
        return sleepDataArrayList;
    }

    public void setSleepDataArrayList(ArrayList<SleepData> sleepDataArrayList) {
        this.sleepDataArrayList = sleepDataArrayList;
    }

    public void addToList(SleepData sleepData) {
        sleepDataArrayList.add(sleepData);
    }

}
