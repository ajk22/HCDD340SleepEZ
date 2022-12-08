package com.example.sleepez;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;


import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            System.out.println("Stats button");

        } else if (view.equals(findViewById(R.id.newSleepButton))) {
            System.out.println("New sleep button");

        } else if (view.equals(findViewById(R.id.MyGoalsButton))) {
            System.out.println("Goals button");

        }
    }


}