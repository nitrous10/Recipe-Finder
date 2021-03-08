package com.example.cs65_final_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cs65_final_project.FirebaseDatabaseHelper;
import com.example.cs65_final_project.Ingredient;
import com.example.cs65_final_project.R;

public class EditOrAddIngredientActivity extends AppCompatActivity implements View.OnClickListener{

    Button saveButton;
    Button cancelButton;
    EditText ingredientEditText;
    EditText amountEditText;
    Spinner aisleSpinner;

    private String aisle;
    private String name;

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
        aisleSpinner = findViewById(R.id.input_aisle);
        aisleSpinner.setAdapter(ArrayAdapter.createFromResource(this, R.array.aisles,
                android.R.layout.simple_spinner_dropdown_item));

        Bundle bundle = getIntent().getExtras();

        // If edit instead of add
        if(bundle != null){
            setTitle("Edit Ingredient");

            name = bundle.getString(NAME_KEY);
            ingredientEditText.setText(name);

            String[] array = getResources().getStringArray(R.array.aisles);
            aisle = bundle.getString(AISLE_KEY);
            //Find position
            for (int i=0; i<array.length; i++){
                if(aisle.equals(array[i])){
                    aisleSpinner.setSelection(i);
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
                            Float.valueOf(amountEditText.getText().toString()), aisleSpinner.getSelectedItem().toString());
                    FirebaseDatabaseHelper.addIngredient(this, ingredient.getName(), ingredient.getAisle(), ingredient.getAmount());
                }
                else{
                    Ingredient ingredient = new Ingredient(ingredientEditText.getText().toString(),
                            aisleSpinner.getSelectedItem().toString());
                    FirebaseDatabaseHelper.addIngredient(this, ingredient.getName(), ingredient.getAisle(), ingredient.getAmount());
                }

            } catch (Exception e) {
                Toast.makeText(this, "Invalid Entry", Toast.LENGTH_SHORT).show();
            }
        } else if(view.getId() == R.id.cancel_button){
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.entry_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Ingredient ingredient = new Ingredient(name, aisle);
        FirebaseDatabaseHelper.deleteIngredient(this, ingredient);
        finish();
        return super.onOptionsItemSelected(item);
    }
}