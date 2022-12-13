package com.example.sleepez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GoalsMenuActivity extends AppCompatActivity implements View.OnClickListener{

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_menu);

        Button newGoal = findViewById(R.id.new_goal_btn_id);
        newGoal.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREF_NAME, MODE_PRIVATE);
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
    protected void onResume() {
        super.onResume();
        SharedPreferences.Editor editor = sharedPreferences.edit();

        TextView goalDescription = (TextView) findViewById(R.id.goalDescriptionDisplayed);
        TextView goalTitle = (TextView) findViewById(R.id.goalTitleDisplayed);

        goalDescription.setText(sharedPreferences.getString(MainActivity.GOAL_DESCRIPTION, null));
        goalTitle.setText(sharedPreferences.getString(MainActivity.GOAL_TITLE, null));

    }

    public void onClick (View view) {
        if (view.equals(findViewById(R.id.new_goal_btn_id))) {
            Intent newGoalIntent  = new Intent(this, NewGoalActivity.class);
            startActivity(newGoalIntent);
        }
    }
}