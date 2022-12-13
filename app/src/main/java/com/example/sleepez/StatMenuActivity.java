package com.example.sleepez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StatMenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button showStatBtn;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_menu);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.stat_select_array, R.layout.stat_spinner_text);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner;
        spinner = findViewById(R.id.stat_spinner);
        spinner.setAdapter(adapter);

        showStatBtn = findViewById(R.id.stat_show_btn);
        showStatBtn.setOnClickListener(this);

        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.menuNewSleep) {
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
            return true;
        } else if (menuId == R.id.menuNewSleep) {
            Intent aboutIntent = new Intent(this, NewSleepActivity.class);
            startActivity(aboutIntent);
            return true;
        } else if (menuId == R.id.menuStats) {
            Intent aboutIntent = new Intent(this, StatMenuActivity.class);
            startActivity(aboutIntent);
            return true;
        } else if (menuId == R.id.menuGoals) {
            Intent aboutIntent = new Intent(this, GoalsMenuActivity.class);
            startActivity(aboutIntent);
            return true;
        } else if (menuId == R.id.menuLogOut) {
            Intent aboutIntent = new Intent(this, LoginActivity.class);
            startActivity(aboutIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int eventSourceId = view.getId();
        if (eventSourceId == R.id.stat_show_btn) {
            Spinner spinner = findViewById(R.id.stat_spinner);
            String range = (String) spinner.getSelectedItem();
            Log.d("Stat_Test", "Selected range: " + range);

            TextView avgWakeTime = findViewById(R.id.avgWakeTimeTxt);
            TextView avgBedTime = findViewById(R.id.avgBedTimeTxt);
            TextView avgSleepDuration = findViewById(R.id.avgSleepDurationTxt);
            TextView avgSleepQuality = findViewById(R.id.avgSleepQualityTxt);

            sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREF_NAME, MODE_PRIVATE);
            String dataString = sharedPreferences.getString(MainActivity.SLEEP_DATA_LIST, null);
            SleepDataList sleepListForStats = convertDataStringToList(dataString);

            if(range.equals("All Sleep Data")){
                Intent allSleepIntent = new Intent(this, SleepListActivity.class);
                startActivity(allSleepIntent);
            } else if (range.equals("Past 3 Days")) {
                avgBedTime.setText(sleepListForStats.getAverageBedTime(3, sleepListForStats));
                avgWakeTime.setText(sleepListForStats.getAverageWakeTime(3, sleepListForStats));
                avgSleepQuality.setText(sleepListForStats.getAverageSleepQuality(3, sleepListForStats));
                avgSleepDuration.setText(sleepListForStats.getAverageDuration(3, sleepListForStats));

            } else if (range.equals("Past 7 Days")) {
                avgBedTime.setText(sleepListForStats.getAverageBedTime(7, sleepListForStats));
                avgWakeTime.setText(sleepListForStats.getAverageWakeTime(7, sleepListForStats));
                avgSleepQuality.setText(sleepListForStats.getAverageSleepQuality(7, sleepListForStats));
                avgSleepDuration.setText(sleepListForStats.getAverageDuration(7, sleepListForStats));

            } else if (range.equals("Past 14 Days")) {
                avgBedTime.setText(sleepListForStats.getAverageBedTime(14, sleepListForStats));
                avgWakeTime.setText(sleepListForStats.getAverageWakeTime(14, sleepListForStats));
                avgSleepQuality.setText(sleepListForStats.getAverageSleepQuality(14, sleepListForStats));
                avgSleepDuration.setText(sleepListForStats.getAverageDuration(14, sleepListForStats));

            } else if (range.equals("Past 30 Days")) {
                avgBedTime.setText(sleepListForStats.getAverageBedTime(30, sleepListForStats));
                avgWakeTime.setText(sleepListForStats.getAverageWakeTime(30, sleepListForStats));
                avgSleepQuality.setText(sleepListForStats.getAverageSleepQuality(30, sleepListForStats));
                avgSleepDuration.setText(sleepListForStats.getAverageDuration(30, sleepListForStats));

            }
        }
    }

    public SleepDataList convertDataStringToList(String listData) {
        String[] sleepDataObjects = listData.split("-");
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

        return tempDataList;
    }

}