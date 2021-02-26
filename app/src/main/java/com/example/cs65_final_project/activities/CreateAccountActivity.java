package com.example.cs65_final_project.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs65_final_project.FirebaseAuthHelper;
import com.example.cs65_final_project.R;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_layout);
        setTitle("Create Account");

        emailEditText = findViewById(R.id.createEmailEditText);
        passwordEditText = findViewById(R.id.createPasswordEditText);
    }

    public void createCancelPressed(View v) {
        finish();
    }

    public void createAccountConfirmPressed(View v) {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        try {
            FirebaseAuthHelper.createUser(this, email, password);
        } catch (Exception e) {
            Toast.makeText(this, "Sign Up Failed!", Toast.LENGTH_SHORT).show();
        }
    }
}
