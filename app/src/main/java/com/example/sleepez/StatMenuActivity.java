package com.example.sleepez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class StatMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_menu);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.stat_select_array, R.layout.stat_spinner_text);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner;
        spinner = findViewById(R.id.stat_spinner);
        spinner.setAdapter(adapter);
    }
}