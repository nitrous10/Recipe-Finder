package com.example.cs65_final_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cs65_final_project.Ingredient;
import com.example.cs65_final_project.R;
import com.example.cs65_final_project.Recipe;

import java.util.ArrayList;

public class SuggestedRecipeAdapter extends ArrayAdapter<Recipe> {
    public SuggestedRecipeAdapter(@NonNull Context context, ArrayList<Recipe> recipes) {
        super(context, 0, recipes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Recipe recipe = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.suggested_recipe_textview, parent, false);
        }

        //Get textViews for the listView
        TextView recipeText = convertView.findViewById(R.id.recipe);
        TextView ingredientsText = convertView.findViewById(R.id.ingredients);
        TextView timeText = convertView.findViewById(R.id.time);

        //Set correponding text
        recipeText.setText(recipe.getName());
        ingredientsText.setText(recipe.getIngredientsAsString());
        String timeString = String.valueOf(recipe.getTime()) + " minutes";
        timeText.setText(timeString);

        return convertView;
    }
}