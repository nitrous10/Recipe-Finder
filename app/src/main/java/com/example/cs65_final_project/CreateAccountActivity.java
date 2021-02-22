package com.example.cs65_final_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_layout);
        setTitle("Create Account");
    }

    public void createCancelPressed(View v) {
        finish();
    }

    public void createAccountConfirmPressed(View v) {
        Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show();
        finish();
    }
}
