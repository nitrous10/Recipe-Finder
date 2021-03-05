package com.example.cs65_final_project.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs65_final_project.R;

public class RecipeViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);
        setTitle("Recipe");

        TextView titleTextView = (TextView) findViewById(R.id.recipe_view_title);
        titleTextView.setText(getIntent().getStringExtra("title"));

        TextView timeTextView = (TextView) findViewById(R.id.recipe_view_time);
        timeTextView.setText(
                String.format("Time until bliss: %d minutes", getIntent().getIntExtra("time", -1)));

        TextView ingredientsTextView = (TextView) findViewById(R.id.recipe_view_ingredients);
        ingredientsTextView.setText(getArrayAsString(getIntent().getStringArrayExtra("ingredients")));

        TextView stepsTextView = (TextView) findViewById(R.id.recipe_view_steps);
        stepsTextView.setText(getArrayAsString(getIntent().getStringArrayExtra("steps")));
    }

    private String getArrayAsString(String[] arr) {
        StringBuilder stepsStringBuilder = new StringBuilder();
        for (String element : arr) {
            stepsStringBuilder.append(element);
            stepsStringBuilder.append("\n\n");
        }

        return stepsStringBuilder.toString();
    }
}
