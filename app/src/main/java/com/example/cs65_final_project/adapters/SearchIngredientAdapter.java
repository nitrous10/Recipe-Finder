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

import java.util.List;

public class SearchIngredientAdapter extends ArrayAdapter<Ingredient> {
    public SearchIngredientAdapter(@NonNull Context context, @NonNull List<Ingredient> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Ingredient ingredient = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ingredient_search_text_view, parent, false);
        }

        //Get ingredient names
        TextView ingredientText = convertView.findViewById(R.id.ingredient_name);
        ingredientText.setText(ingredient.getName());
        TextView aisleText = convertView.findViewById(R.id.aisle);
        aisleText.setText(ingredient.getAisle());

        return convertView;
    }

    public void updateData(List<Ingredient> data){
        clear();
        addAll(data);
    }
}
