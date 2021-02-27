package com.example.cs65_final_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.cs65_final_project.R;

public class SearchAddIngredientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_add_ingredient);
        setTitle("Search to add Ingredient");

        ListView listView = findViewById(R.id.list_ingredients);
    }
}