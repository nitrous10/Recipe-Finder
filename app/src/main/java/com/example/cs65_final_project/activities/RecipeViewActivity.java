package com.example.cs65_final_project.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs65_final_project.R;
import com.squareup.picasso.Picasso;

public class RecipeViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);
        setTitle("Recipe");

        ImageView imageView = findViewById(R.id.recipe_view_image);
        Picasso.get().load(getIntent().getStringExtra("imageUrl"))
                .placeholder(R.drawable.placeholder)
                .fit()
                .centerCrop()
                .into(imageView);

        TextView titleTextView = (TextView) findViewById(R.id.recipe_view_title);
        titleTextView.setText(getIntent().getStringExtra("title"));

        TextView timeTextView = (TextView) findViewById(R.id.recipe_view_time);
        timeTextView.setText(
                String.format("Time until bliss: %d minutes", getIntent().getIntExtra("time", -1)));

        TextView ingredientsTextView = (TextView) findViewById(R.id.recipe_view_ingredients);
        ingredientsTextView.setText(
                getArrayAsString(getIntent().getStringArrayExtra("ingredients"), 1));

        TextView stepsTextView = (TextView) findViewById(R.id.recipe_view_steps);
        stepsTextView.setText(getArrayAsString(getIntent().getStringArrayExtra("steps"), 2));
    }

    private String getArrayAsString(String[] arr, int numOfNewlines) {
        StringBuilder stepsStringBuilder = new StringBuilder();
        for (String element : arr) {
            stepsStringBuilder.append(element);
            for (int i = 0; i < numOfNewlines; i++) {
                stepsStringBuilder.append("\n");
            }
        }

        return stepsStringBuilder.toString();
    }
}
