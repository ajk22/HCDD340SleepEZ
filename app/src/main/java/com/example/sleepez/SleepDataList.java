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

    public SleepData findInList(SleepData sleepData) {

        SleepData returnItem = null;

        for (int i = 0; i < sleepDataArrayList.size(); i++) {
            if (sleepData.equals(sleepDataArrayList.get(i))) {
                returnItem = sleepDataArrayList.get(i);
            }
        }

        if (returnItem == null) {
            Log.d(TAG, "Error: No object found in list");
        }

        return returnItem;
    }
}
