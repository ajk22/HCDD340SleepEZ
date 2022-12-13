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
    public static final String USERNAME_KEY = "USERNAME";
    public static final String PASSWORD_KEY = "PASSWORD";
    public static final String SLEEP_DATA_LIST = "SLEEP_DATA_LIST";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TESTING SLEEP DATA
//        SleepDataList testingList = convertDataStringToList(sharedPreferences.getString(MainActivity.SLEEP_DATA_LIST, null));
//        System.out.println("TESTING LIST OUTPUT: \n" + testingList.toString());

//UNCOMMENT and run to clear the data
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(MainActivity.SLEEP_DATA_LIST, "empty");
//        editor.apply();

        //Testing to see if lists has been created yet...
        //If not it, we must add some sample data
        sharedPreferences = getSharedPreferences(this.SHARED_PREF_NAME, MODE_PRIVATE);

        try {
            String tempPrefString = sharedPreferences.getString(MainActivity.SLEEP_DATA_LIST, null);
            if (tempPrefString.equals("empty")) {
                System.out.println("Testing for empty list");
            }
        } catch (NullPointerException ex) {
            System.out.println("List has not yet been created..." +
                    "\nCreating new list with sample data");
            SharedPreferences.Editor editor2 = sharedPreferences.edit();
            editor2.putString(MainActivity.SLEEP_DATA_LIST, "11/9/2022,11:50 PM,07:45 AM,5,Persistent data nightmares,07:55-" +
                    "11/10/2022,09:55 PM,05:54 AM,3,No Dream Entered,07:59-" +
                    "11/11/2022,10:54 PM,06:27 AM,2,Sean Clifford gets drafted,07:33-" +
                    "11/12/2022,11:40 PM,08:10 AM,4,James Franklin gets fired,08:30-" +
                    "11/13/2022,12:15 AM,06:45 AM,4,No Dream Entered,06:30-" +
                    "11/14/2022,10:55 PM,07:45 AM,4,Driving a Bugatti with Top G,08:50-" +
                    "11/15/2022,01:30 AM,09:55 AM,3,Steelers win superbowl,08:25-" +
                    "11/16/2022,02:30 AM,11:05 AM,2,No Dream Entered,08:35-" +
                    "11/17/2022,11:25 PM,06:20 AM,2,No Dream Entered,06:55-" +
                    "11/18/2022,10:45 PM,07:45 AM,5,Hit it big on Doge coin,09:00-" +
                    "11/19/2022,11:50 PM,06:35 AM,1,Bicycling to the moon,06:45-" +
                    "11/20/2022,12:35 AM,09:10 AM,5,No Dream Entered,08:35-" +
                    "11/21/2022,01:40 AM,10:20 AM,3,Golfing in the Himalayas,08:40-" +
                    "11/22/2022,11:45 PM,08:30 AM,3,No Dream Entered,08:45-" +
                    "11/23/2022,09:55 PM,05:45 AM,5,No Dream Entered,07:50-" +
                    "11/24/2022,10:31 PM,09:31 AM,4,Nightmare about android studio,11:00-" +
                    "11/25/2022,11:25 PM,07:25 AM,3,Snowboarding with Lebron,08:00-" +
                    "11/26/2022,11:50 PM,07:45 AM,5,No Dream Entered,07:55-" +
                    "11/27/2022,09:55 PM,05:54 AM,3,World War III,07:59-" +
                    "11/28/2022,10:54 PM,06:27 AM,2,World War IV,07:33-" +
                    "11/29/2022,11:40 PM,08:10 AM,4,Bought a maybach,08:30-" +
                    "11/30/2022,12:15 AM,06:45 AM,4,No Dream Entered,06:30-" +
                    "12/1/2022,10:55 PM,07:45 AM,4,No Dream Entered,08:50-" +
                    "12/2/2022,01:30 AM,09:55 AM,3,Business meeting with Drake,08:25-" +
                    "12/3/2022,02:30 AM,11:05 AM,2,Puppies everywhere,08:35-" +
                    "12/4/2022,11:25 PM,06:20 AM,2,Puppies everywhere again,06:55-" +
                    "12/5/2022,10:45 PM,07:45 AM,5,Dance class with Joe Biden,09:00-" +
                    "12/6/2022,11:50 PM,06:35 AM,3,Lost in the sauce,06:45-" +
                    "12/7/2022,12:35 AM,09:10 AM,5,No Dream Entered,08:35-" +
                    "12/8/2022,01:40 AM,10:20 AM,3,Ice skating with Pop Smoke,08:40-" +
                    "12/9/2022,11:45 PM,08:30 AM,4,Sunshine and rainbows,08:45-" +
                    "12/10/2022,09:55 PM,05:45 AM,5,No Dream Entered,07:50-" +
                    "12/11/2022,10:31 PM,09:31 AM,5,Nightmare about java code,11:00-" +
                    "12/12/2022,11:25 PM,07:25 AM,4,Aliens and unicorns,08:00-");
            editor2.apply();
        }

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