package com.example.cs65_final_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cs65_final_project.FirebaseDatabaseHelper;
import com.example.cs65_final_project.R;

public class ManualAddIngredientActivity extends AppCompatActivity implements View.OnClickListener{

    Button saveButton;
    Button cancelButton;
    EditText ingredientEditText, amountEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_add_ingredient);
        setTitle("Manually Add Ingredient");

        ingredientEditText = findViewById(R.id.input_ingredient);
        amountEditText = findViewById(R.id.input_amount);
        saveButton = findViewById(R.id.save_button);
        cancelButton = findViewById(R.id.cancel_button);
        saveButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        Spinner units = findViewById(R.id.input_units);
        units.setAdapter(ArrayAdapter.createFromResource(this, R.array.ingredient_units,
                android.R.layout.simple_spinner_dropdown_item));
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.save_button){
            try {
                // Add the ingredient to Firebase
                FirebaseDatabaseHelper.addIngredient(this, ingredientEditText.getText().toString(), Float.parseFloat(amountEditText.getText().toString()), "Others");
            } catch (Exception e) {
                Toast.makeText(this, "Invalid Entry", Toast.LENGTH_SHORT).show();
            }
        } else if(view.getId() == R.id.cancel_button){
            finish();
        }
    }
}