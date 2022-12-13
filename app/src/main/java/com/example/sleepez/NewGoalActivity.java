package com.example.sleepez;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class NewGoalActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_goal_activity);

        Button newGoal = findViewById(R.id.set_goal_btn_id);
        newGoal.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.equals(findViewById(R.id.set_goal_btn_id))) {
            AlertDialog.Builder warning = new AlertDialog.Builder(this);
            warning.setTitle(R.string.warning_title);
            warning.setMessage(R.string.warning_message);
            warning.setPositiveButton(R.string.warning_positive, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Persistent Data Implementation
                }
            });
            warning.setNegativeButton(R.string.warning_negative, null);
            warning.show();
        }
    }

    public void saveGoalInfo(String title, String description) {
        SharedPreferences.Editor editor = sharedPrefs.edit();

    }
}
