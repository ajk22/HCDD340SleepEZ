package com.example.sleepez;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Calendar;

public class TimePickerDialog extends Activity {

    private TimePicker timePicker1;
    private Calendar calendar;
    private String format = "";
    private SleepTime time;

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker_dialog);

        sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREF_NAME, MODE_PRIVATE);

        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        calendar = Calendar.getInstance();
        time = new SleepTime(Calendar.HOUR_OF_DAY, Calendar.MINUTE, "");

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
    }

    public void setTime(View view) {
        int hour = timePicker1.getHour();
        int min = timePicker1.getMinute();
        showTime(hour, min);
    }

    public void showTime(int hour, int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }

        time = new SleepTime(hour, min, format);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MainActivity.TEMP_TIME, time.toString());
        editor.apply();
        finish();
    }
}