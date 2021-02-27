package com.example.cs65_final_project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.cs65_final_project.FirebaseAuthHelper;
import com.example.cs65_final_project.R;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private EditText emailEditText, passwordEditText;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_layout);
        setTitle("Create Account");

        email = findViewById(R.id.createEmailEditText);
        password = findViewById(R.id.createPasswordEditText);
        emailEditText = findViewById(R.id.createEmailEditText);
        passwordEditText = findViewById(R.id.createPasswordEditText);
    }

    public void createCancelPressed(View v) {
        finish();
    }

    public void createNextPressed(View v) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra(ProfileActivity.NEW_PROFILE, true);
        intent.putExtra(ProfileActivity.EMAIL, email.getText().toString());
        intent.putExtra(ProfileActivity.PASSWORD, password.getText().toString());
        startActivity(intent);

        // Slide animation
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


}
