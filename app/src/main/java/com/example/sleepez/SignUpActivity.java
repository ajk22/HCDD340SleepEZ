package com.example.sleepez;

import static com.example.sleepez.MainActivity.PASSWORD_KEY;
import static com.example.sleepez.MainActivity.USERNAME_KEY;
import static com.example.sleepez.MainActivity.SHARED_PREF_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViewById(R.id.loginLink).setOnClickListener(this);
        findViewById(R.id.signUpBtn).setOnClickListener(this);

        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREF_NAME, MODE_PRIVATE);
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

    void saveUserInformation(String username, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME_KEY, username);
        editor.putString(PASSWORD_KEY,password);
        editor.apply();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.loginLink){
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        }
        else if(id == R.id.signUpBtn){
            EditText txtUsername = findViewById(R.id.signupUsername);
            String username = txtUsername.getText().toString();

            EditText txtPassword = findViewById(R.id.signupPassword);
            String password = txtPassword.getText().toString();
            //System.out.println("username: " + username + " password: " + password);
            saveUserInformation(username,password);
            //make(findViewById(R.id.signUpBtn), R.string.accountSuccess, Snackbar.LENGTH_SHORT).show();
            AlertDialog.Builder d = new AlertDialog.Builder(this);
            d.setTitle("Account Created");
            d.setMessage("Welcome " + username + "!");
            d.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int i) {
                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                }
            });
            d.show();

        }
    }
}