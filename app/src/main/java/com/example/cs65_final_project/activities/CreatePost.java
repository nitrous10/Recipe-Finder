package com.example.cs65_final_project.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs65_final_project.R;
import com.google.firebase.database.FirebaseDatabase;

//Title, time, ingredient, steps


public class CreatePost extends AppCompatActivity implements View.OnClickListener {

    public static final String TITLE_KEY = "title key";
    public static final String TIME_KEY = "time key";
    public static final String INGREDIENT_KEY = "ingredient key";
    public static final String STEPS_KEY = "steps key";

    private String title, time, ingredients, steps;
    private TextView titleTextView, timeTextView, ingredientsTextView, stepsTextView;
    private EditText commentEditText;
    private Button cancelButton, postButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.post_activity);

        Bundle bundle = getIntent().getExtras();
        title = bundle.getString(TITLE_KEY);
        time = bundle.getString(TIME_KEY);
        ingredients = bundle.getString(INGREDIENT_KEY);
        steps = bundle.getString(STEPS_KEY);

        titleTextView = findViewById(R.id.postTitle);
        timeTextView = findViewById(R.id.postTime);
        ingredientsTextView = findViewById(R.id.postIngredients);
        stepsTextView = findViewById(R.id.postSteps);
        commentEditText = findViewById(R.id.postComments);
        cancelButton = findViewById(R.id.postCancel);
        postButton = findViewById(R.id.postConfirm);

        cancelButton.setOnClickListener(this);
        postButton.setOnClickListener(this);

        titleTextView.setText(title);
        timeTextView.setText(time);
        ingredientsTextView.setText(ingredients);
        stepsTextView.setText(steps);

    }

    public void onClick(View v) {
        if (v.getId() == R.id.postCancel) {
            finish();
        } else if (v.getId() == R.id.postConfirm) {
            
        }
    }

}
