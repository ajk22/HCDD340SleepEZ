package com.example.sleepez;

import static com.example.sleepez.MainActivity.PASSWORD_KEY;
import static com.example.sleepez.MainActivity.USERNAME_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {
    private SharedPreferences sharedPreferences;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.menu_info) {
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.loginBtn).setOnClickListener(this);
        findViewById(R.id.signinLink).setOnClickListener(this);
        findViewById(R.id.usernameInput).setOnClickListener(this);
        findViewById(R.id.passwordInput).setOnClickListener(this);
        sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREF_NAME, MODE_PRIVATE);
        findViewById(R.id.passwordInput).setOnFocusChangeListener(this);
        findViewById(R.id.usernameInput).setOnFocusChangeListener(this);
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
        if (id == R.id.loginBtn){

            EditText txtUsername = findViewById(R.id.usernameInput);
            EditText txtPassword = findViewById(R.id.passwordInput);

            if(txtUsername.getText() != null && txtPassword.getText() != null) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                String defVal = "xxxxx";

                String currentUsername = sharedPreferences.getString(USERNAME_KEY,defVal);
                String currentPassword = sharedPreferences.getString(PASSWORD_KEY,defVal);
                if(username.equals(currentUsername) && password.equals(currentPassword)){

                    Intent mainMenuIntent = new Intent(this, MainActivity.class);
                    startActivity(mainMenuIntent);
                }
                else{
                    TextInputLayout til = (TextInputLayout) findViewById(R.id.passwordInputLayout);
                    til.setError("Username or Password is incorrect.");
                }
            }
            else{
                TextInputLayout til = (TextInputLayout) findViewById(R.id.passwordInputLayout);
                til.setError("Missing Username or Password.");
            }

            //System.out.println("password: " + password + " username: " + username);


        }
        else if (id == R.id.signinLink){
            Intent signUpIntent = new Intent(this, SignUpActivity.class);
            startActivity(signUpIntent);
        }
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus) {
            TextInputLayout til = (TextInputLayout) findViewById(R.id.passwordInputLayout);
            til.setError(null);
        }
    }
}
