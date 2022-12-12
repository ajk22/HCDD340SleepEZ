package com.example.sleepez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;

public class SleepListActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private ArrayList<SleepData> SLEEP_LIST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREF_NAME, MODE_PRIVATE);
        SLEEP_LIST = convertDataStringToList(sharedPreferences.getString(MainActivity.SLEEP_DATA_LIST, null));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_list);
    }

    public ArrayList<SleepData> convertDataStringToList(String listData) {
        String[] sleepDataObjects = listData.split("-");
        System.out.println("TESTING PERSISTENT DATA 2...\n");
        System.out.println(sleepDataObjects[0]);
        SleepDataList tempDataList = new SleepDataList();

        for(int i = 0; i < sleepDataObjects.length; i++) {
            String tempObjectString = sleepDataObjects[i];
            String[] tempObjectStringValues = tempObjectString.split(",");

            String tempDate = tempObjectStringValues[0];

            String tempBedTime = tempObjectStringValues[1];
            int hour = Integer.valueOf(tempBedTime.substring(0,2));
            int min = Integer.valueOf(tempBedTime.substring(3,5));
            String format = tempBedTime.substring(6);
            SleepTime bedTime = new SleepTime(hour, min, format);

            String tempWakeTime = tempObjectStringValues[2];
            int hour1 = Integer.valueOf(tempWakeTime.substring(0,2));
            int min1 = Integer.valueOf(tempWakeTime.substring(3,5));
            String format1 = tempWakeTime.substring(6);
            SleepTime wakeTime = new SleepTime(hour1, min1, format1);

            String tempSleepQuality = tempObjectStringValues[3];
            System.out.println(tempSleepQuality);
            double sleepQualityDouble = Double.parseDouble(tempSleepQuality);
            int sleepQualityInt = (int) sleepQualityDouble;


            String tempDreamNotes = tempObjectStringValues[4];

            tempDataList.addToList(new SleepData(tempDate, bedTime, wakeTime, sleepQualityInt, tempDreamNotes));
        }

        return tempDataList.getSleepDataArrayList();
    }
}