package com.example.sleepez;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;

public class NewSleepActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private TextView wakeTimeSelected;
    private TextView bedTimeSelected;
    private int year, month, day;
    private TimePicker timePicker1;
    private TextView time;
    private String format = "";
    private SharedPreferences sharedPreferences;
    private String bedTime;
    private String wakeTime;
    boolean flag = true;
    boolean flag2 = false;
    private RadioButton rb1;
    private RadioGroup rg1;
    private EditText dreamText;
    private String dateString;
    private int ratingLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sleep);

        sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREF_NAME, MODE_PRIVATE);

        //TESTING persistent data
//        SleepDataList testList = convertDataStringToList(sharedPreferences.getString(MainActivity.SLEEP_DATA_LIST, null));
//        System.out.println("TESTING PERSISTENT DATA 1...\n");
//        for (int i = 0; i < testList.getSleepDataArrayList().size(); i++) {
//            System.out.println(testList.getSleepDataArrayList().get(i).toString());
//        }

//        //UNCOMMENT and run to clear the data
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(MainActivity.SLEEP_DATA_LIST, "empty");
//        editor.apply();
        try {
            String tempPrefString = sharedPreferences.getString(MainActivity.SLEEP_DATA_LIST, null);
            if (tempPrefString.equals("empty")) {
                System.out.println("Testing for empty list");
            }
        } catch (NullPointerException ex) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(MainActivity.SLEEP_DATA_LIST, "empty");
            editor.apply();
        }

        dateView = (TextView) findViewById(R.id.date_selected_text);
        wakeTimeSelected = (TextView) findViewById(R.id.wake_time_selected);
        wakeTimeSelected.setText("");
        bedTimeSelected = (TextView) findViewById(R.id.bed_time_selected);
        bedTimeSelected.setText("");
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
    }


    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                        Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (flag2 == true) {
            if (flag == false) {
                //wake time
                System.out.println(sharedPreferences.getString(MainActivity.TEMP_TIME, null));

                wakeTimeSelected.setText(sharedPreferences.getString(MainActivity.TEMP_TIME, null));

                flag2 = false;
            } else if (flag) {
                //bed time
                System.out.println(sharedPreferences.getString(MainActivity.TEMP_TIME, null));

                bedTimeSelected.setText(sharedPreferences.getString(MainActivity.TEMP_TIME, null));

                flag2 = false;
            }
        }
    }

    public void setWakeTime(View view) {
        flag = false;
        Intent timePickIntent = new Intent(this, TimePickerDialog.class);
        startActivity(timePickIntent);
        flag2 = true;
    }

    public void setBedTime(View view) {
        flag = true;
        Intent timePickIntent = new Intent(this, TimePickerDialog.class);
        startActivity(timePickIntent);
        flag2 = true;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {

        dateString = month + "/" + day + "/" + year;
        dateView.setText(new StringBuilder().append(month).append("/")
                .append(day).append("/").append(year));
    }

    public void getRating(View view) {
        rg1 = (RadioGroup) findViewById(R.id.radio_group);
        int selected = rg1.getCheckedRadioButtonId();
        rb1 = (RadioButton)findViewById(selected);
        ratingLevel = Integer.valueOf(String.valueOf(rb1.getText()));

        System.out.println(rb1.getText());
    }

    public void saveNewSleep(View view) {
        //bed time
        try {
            String tempBedString = String.valueOf(bedTimeSelected.getText());
            int hour = Integer.valueOf(tempBedString.substring(0,2));
            int min = Integer.valueOf(tempBedString.substring(3,5));
            String format = tempBedString.substring(6);
            SleepTime bedTime = new SleepTime(hour, min, format);

            String tempWakeString = String.valueOf(wakeTimeSelected.getText());
            int hour1 = Integer.valueOf(tempWakeString.substring(0,2));
            int min1 = Integer.valueOf(tempWakeString.substring(3,5));
            String format1 = tempWakeString.substring(6);
            SleepTime wakeTime = new SleepTime(hour1, min1, format1);

            dreamText = (EditText) findViewById(R.id.edit_dream_text);

            rg1 = (RadioGroup) findViewById(R.id.radio_group);
            int selected = rg1.getCheckedRadioButtonId();
            rb1 = (RadioButton)findViewById(selected);
            ratingLevel = Integer.valueOf(String.valueOf(rb1.getText()));

            String tempDreamString = dreamText.getText().toString();

            if (tempDreamString.isEmpty()) {
                tempDreamString = "No Dream Entered";
            }

            if (dateString.equals(null) || tempWakeString.equals(null) || tempBedString.equals(null) || (String.valueOf(rb1.getText()).equals(null))) {
                throw new IOException();
            } else if (tempDreamString.contains("-") || tempDreamString.contains(",")){
                //need to make sure that dream string does not contain the character which is used to split
                throw new IOException();
            } else {
                System.out.println("DATA BEFORE SAVING...");
                System.out.println(sharedPreferences.getString(MainActivity.SLEEP_DATA_LIST, null));
                SleepData tempSleepData = new SleepData(dateString, bedTime, wakeTime, ratingLevel, tempDreamString);
                String tempPrefString = sharedPreferences.getString(MainActivity.SLEEP_DATA_LIST, null);

                    if (tempPrefString.equals("empty")) {
                        System.out.println("Testing IS EMPTY");
                        tempPrefString = tempSleepData.toString();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(MainActivity.SLEEP_DATA_LIST, tempPrefString);
                        editor.apply();
                        finish();
                    } else {
                        tempPrefString = tempPrefString + tempSleepData.toString();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(MainActivity.SLEEP_DATA_LIST, tempPrefString);
                        editor.apply();
                        finish();
                    }

                System.out.println("DATA AFTER SAVING...");
                System.out.println(sharedPreferences.getString(MainActivity.SLEEP_DATA_LIST, null));
            }
        }
        catch (IOException exception){
            //TO DO
            //need to pop up an error message... "a required input was left blank, please provide input and try again"
            System.out.println(exception.getMessage());
        }
        catch (IndexOutOfBoundsException exception) {
            //TO DO
            //need to pop up an error message... "a required input was left blank, please provide input and try again"
            System.out.println(exception.getMessage());
        }
    }

    public SleepDataList convertDataStringToList(String listData) {
        String[] sleepDataObjects = listData.split("-");
        System.out.println("TESTING PERSISTENT DATA 2...\n");
        System.out.println(sleepDataObjects[0]);
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