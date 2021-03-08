package com.example.cs65_final_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cs65_final_project.FirebaseDatabaseHelper;
import com.example.cs65_final_project.Ingredient;
import com.example.cs65_final_project.R;

public class ManualAddIngredientActivity extends AppCompatActivity implements View.OnClickListener{

    Button saveButton;
    Button cancelButton;
    EditText ingredientEditText;
    EditText amountEditText;
    Spinner aisle;

    public static final String NAME_KEY = "name";
    public static final String AMOUNT_KEY = "amount";
    public static final String AISLE_KEY = "aisle";

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
        aisle = findViewById(R.id.input_aisle);
        aisle.setAdapter(ArrayAdapter.createFromResource(this, R.array.aisles,
                android.R.layout.simple_spinner_dropdown_item));

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            setTitle("Edit Ingredient");

            ingredientEditText.setText(bundle.getString(NAME_KEY));

            String[] array = getResources().getStringArray(R.array.aisles);
            String correctAisle = bundle.getString(AISLE_KEY);
            //Find position
            for (int i=0; i<array.length; i++){
                if(correctAisle.equals(array[i])){
                    aisle.setSelection(i);
                }
            }
            Log.d("debug", bundle.getFloat(AMOUNT_KEY) + "");
            if(bundle.getFloat(AMOUNT_KEY) == -1){
                amountEditText.setText("");
            } else{
                amountEditText.setText((int) bundle.getFloat(AMOUNT_KEY) + "");
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.save_button){
            try {
                // Add the ingredient to Firebase
                if(!amountEditText.getText().toString().equals("")){
                    Ingredient ingredient = new Ingredient(ingredientEditText.getText().toString(),
                            Float.valueOf(amountEditText.getText().toString()), aisle.getSelectedItem().toString(), true);
                    FirebaseDatabaseHelper.addIngredient(this, ingredient.getName(), ingredient.getAisle(), ingredient.getAmount());
                }
                else{
                    Ingredient ingredient = new Ingredient(ingredientEditText.getText().toString(),
                            aisle.getSelectedItem().toString());
                    FirebaseDatabaseHelper.addIngredient(this, ingredient.getName(), ingredient.getAisle(), ingredient.getAmount());
                }

            } catch (Exception e) {
                Toast.makeText(this, "Invalid Entry", Toast.LENGTH_SHORT).show();
            }
        } else if(view.getId() == R.id.cancel_button){
            finish();
        }
    }
}