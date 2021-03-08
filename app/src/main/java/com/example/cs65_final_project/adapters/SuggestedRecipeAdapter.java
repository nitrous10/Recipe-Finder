package com.example.cs65_final_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cs65_final_project.R;
import com.example.cs65_final_project.Recipe;
import com.squareup.picasso.Picasso;

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
                    .inflate(R.layout.recipe_search_cardview, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.recipe_image);
        Picasso.get().load(recipe.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .fit()
                .centerCrop()
                .into(imageView);

        TextView recipeTitle = convertView.findViewById(R.id.recipe_title);
        recipeTitle.setText(recipe.getName());

        TextView recipeTime = convertView.findViewById(R.id.recipe_time);
        recipeTime.setText(String.format("%d minutes", recipe.getTime()));

        return convertView;
    }
}
