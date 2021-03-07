package com.example.cs65_final_project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs65_final_project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class RecipeViewActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton shareButton;
    private TextView titleTextView, timeTextView, ingredientsTextView, stepsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);
        setTitle("Recipe");

        shareButton = findViewById(R.id.share_recipe);
        shareButton.setOnClickListener(this);

        ImageView imageView = findViewById(R.id.recipe_view_image);
        Picasso.get().load(getIntent().getStringExtra("imageUrl"))
                .placeholder(R.drawable.placeholder)
                .fit()
                .centerCrop()
                .into(imageView);

        titleTextView = (TextView) findViewById(R.id.recipe_view_title);
        titleTextView.setText(getIntent().getStringExtra("title"));

        timeTextView = (TextView) findViewById(R.id.recipe_view_time);
        timeTextView.setText(
                String.format("Time until bliss: %d minutes", getIntent().getIntExtra("time", -1)));

        ingredientsTextView = (TextView) findViewById(R.id.recipe_view_ingredients);
        ingredientsTextView.setText(
                getArrayAsString(getIntent().getStringArrayExtra("ingredients"), 1));

        stepsTextView = (TextView) findViewById(R.id.recipe_view_steps);
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.share_recipe) {
            Intent intent = new Intent(this, CreatePost.class);
            intent.putExtra(CreatePost.TITLE_KEY, titleTextView.getText().toString());
            intent.putExtra(CreatePost.TIME_KEY, timeTextView.getText().toString());
            intent.putExtra(CreatePost.INGREDIENT_KEY, ingredientsTextView.getText().toString());
            intent.putExtra(CreatePost.STEPS_KEY, stepsTextView.getText().toString());
            startActivity(intent);
        }
    }
}
