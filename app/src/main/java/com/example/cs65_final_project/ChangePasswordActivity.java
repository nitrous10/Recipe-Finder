package com.example.cs65_final_project;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePasswordActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password_layout);
        setTitle("Change Password");
    }

    public void sendEmailPressed(View v) {
        finish();
    }

    public void cancelPressed(View v) {
        finish();
    }
}
