package com.example.sleepez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class SleepListActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private ArrayList<SleepData> SLEEP_LIST;
    private RecyclerView sleepRecyclerView;
    private SleepListAdapter sleepAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREF_NAME, MODE_PRIVATE);
        SLEEP_LIST = convertDataStringToList(sharedPreferences.getString(MainActivity.SLEEP_DATA_LIST, null));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_list);

        sleepRecyclerView = findViewById(R.id.sleep_recycler);
        sleepAdapter = new SleepListAdapter(this,SLEEP_LIST);
        sleepRecyclerView.setAdapter(sleepAdapter);
        sleepRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public ArrayList<SleepData> convertDataStringToList(String listData) {
        String[] sleepDataObjects = listData.split("-");
        SleepDataList tempDataList = new SleepDataList();

        for(int i = sleepDataObjects.length - 1; i >= 0; i--) {
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