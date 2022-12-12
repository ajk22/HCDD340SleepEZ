package com.example.sleepez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;

public class GoalsMenuActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_menu);

        Button newGoal = findViewById(R.id.newGoalBtn);
        newGoal.setOnClickListener(this);
    }

    public void onClick (View view) {
        if (view.equals(findViewById(R.id.newGoalBtn))) {
            Intent newGoalIntent  = new Intent(this, NewGoalActivity.class);
            startActivity(newGoalIntent);
        }
    }
}