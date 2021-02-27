package com.example.cs65_final_project.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs65_final_project.FirebaseAuthHelper;
import com.example.cs65_final_project.R;
import com.google.firebase.auth.FirebaseAuth;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText sendEmailEditText;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password_layout);
        setTitle("Change Password");

        sendEmailEditText = findViewById(R.id.changePasswordEditText);
    }

    public void sendEmailPressed(View v) {
        try {
            FirebaseAuthHelper.sendResetPassword(this, sendEmailEditText.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, "Failed to Send Email!", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelPressed(View v) {
        finish();
    }
}
