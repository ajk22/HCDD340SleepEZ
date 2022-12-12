package com.example.sleepez;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;


import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MAIN_ACTIVITY";

    public static final String SHARED_PREF_NAME = "APP_PREFS";
    public static final String TEMP_TIME = "TIME_SELECTED";
    public static final String SLEEP_DATA_LIST = "SLEEP_DATA_LIST";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TESTING SLEEP DATA
//        SleepDataList testingList = convertDataStringToList(sharedPreferences.getString(MainActivity.SLEEP_DATA_LIST, null));
//        System.out.println("TESTING LIST OUTPUT: \n" + testingList.toString());


        Button statsButton = findViewById(R.id.StatsButton);
        statsButton.setOnClickListener(this);
        Button newSleepButton = findViewById(R.id.newSleepButton);
        newSleepButton.setOnClickListener(this);
        Button goalsButton = findViewById(R.id.MyGoalsButton);
        goalsButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.menu_info) {
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        if (view.equals(findViewById(R.id.StatsButton))) {

            Intent statsMenuIntent = new Intent(this, StatMenuActivity.class);
            startActivity(statsMenuIntent);

        } else if (view.equals(findViewById(R.id.newSleepButton))) {

            Intent newSleepIntent = new Intent(this, NewSleepActivity.class);
            startActivity(newSleepIntent);

        } else if (view.equals(findViewById(R.id.MyGoalsButton))) {

            Intent goalsMenuIntent = new Intent(this, GoalsMenuActivity.class);
            startActivity(goalsMenuIntent);

        }
    }


}