package com.example.cs65_final_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cs65_final_project.R;
import com.example.cs65_final_project.Recipe;

import java.util.List;

public class SuggestedRecipeAdapter extends ArrayAdapter<Recipe> {

    public SuggestedRecipeAdapter(@NonNull Context context, List<Recipe> recipes) {
        super(context, 0, recipes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Recipe recipe = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.suggested_recipe_textview, parent, false);
        }

        TextView recipeText = convertView.findViewById(R.id.recipe_title);
        recipeText.setText(recipe.getName());

        return convertView;
    }
}
