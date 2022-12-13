package com.example.sleepez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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