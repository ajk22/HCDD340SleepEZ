package com.example.sleepez;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class NewGoalActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_goal_activity);

        Button newGoal = findViewById(R.id.set_goal_btn_id);
        newGoal.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREF_NAME, MODE_PRIVATE);
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
                    EditText goalTitleInput = (EditText) findViewById(R.id.goalTitleInput);
                    EditText goalDescriptionInput = (EditText) findViewById(R.id.goalDescriptionInput);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(MainActivity.GOAL_DESCRIPTION, goalDescriptionInput.getText().toString());
                    editor.putString(MainActivity.GOAL_TITLE, goalTitleInput.getText().toString());
                    editor.apply();
                    finish();
                }
            });
            warning.setNegativeButton(R.string.warning_negative, null);
            warning.show();
        }
    }
}
