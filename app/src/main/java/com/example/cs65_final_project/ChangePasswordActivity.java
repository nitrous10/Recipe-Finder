package com.example.cs65_final_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePasswordActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password_layout);
        setTitle("Change Password");
    }

    public void sendEmailPressed(View v) {
        Toast.makeText(this, "Email Sent!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void cancelPressed(View v) {
        finish();
    }
}
