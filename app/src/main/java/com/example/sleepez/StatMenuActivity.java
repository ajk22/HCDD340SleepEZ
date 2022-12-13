package com.example.sleepez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class StatMenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button showStatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_menu);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.stat_select_array, R.layout.stat_spinner_text);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner;
        spinner = findViewById(R.id.stat_spinner);
        spinner.setAdapter(adapter);

        showStatBtn = findViewById(R.id.stat_show_btn);
        showStatBtn.setOnClickListener(this);

        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
}

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int eventSourceId = view.getId();
        if (eventSourceId == R.id.stat_show_btn) {
            Spinner spinner = findViewById(R.id.stat_spinner);
            String range = (String) spinner.getSelectedItem();
            Log.d("Stat_Test", "Selected range: " + range);

            if(range.equals("All Sleep Data")){
                Intent allSleepIntent = new Intent(this, SleepListActivity.class);
                startActivity(allSleepIntent);
            }
        }
    }
}