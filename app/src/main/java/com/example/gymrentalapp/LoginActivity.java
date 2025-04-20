package com.example.gymrentalapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText usernameInput, passwordInput;
    Button loginButton;
    CheckBox rememberMe;

    SharedPreferences sharedPreferences;
    public static final String PREFS_NAME = "loginPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        rememberMe = findViewById(R.id.rememberCheckBox);
        loginButton = findViewById(R.id.loginButton);

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if (sharedPreferences.contains("username") && sharedPreferences.contains("password")) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
            return;
        }

        String savedUser = sharedPreferences.getString("username", "");
        String savedPass = sharedPreferences.getString("password", "");
        if (!savedUser.isEmpty() && !savedPass.isEmpty()) {
            usernameInput.setText(savedUser);
            passwordInput.setText(savedPass);
            rememberMe.setChecked(true);
        }

        loginButton.setOnClickListener(v -> {
            String username = usernameInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (username.equals("mohammed") && password.equals("1221175")) {
                if (rememberMe.isChecked()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.apply();
                } else {
                    sharedPreferences.edit().clear().apply();
                }

                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
