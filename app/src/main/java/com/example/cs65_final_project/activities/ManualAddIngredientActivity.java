package com.example.cs65_final_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cs65_final_project.R;

public class ManualAddIngredientActivity extends AppCompatActivity implements View.OnClickListener{

    Button saveButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_add_ingredient);
        setTitle("Manually Add Ingredient");

        saveButton = findViewById(R.id.save_button);
        cancelButton = findViewById(R.id.cancel_button);
        saveButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.save_button){
            //Save to firebase
            finish();
        } else if(view.getId() == R.id.cancel_button){
            finish();
        }
    }
}