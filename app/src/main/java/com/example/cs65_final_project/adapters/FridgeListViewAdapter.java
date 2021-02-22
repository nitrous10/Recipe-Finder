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

import java.util.ArrayList;

public class FridgeListViewAdapter extends ArrayAdapter<Ingredient> {
    public FridgeListViewAdapter(@NonNull Context context, ArrayList<Ingredient> ingredients) {
        super(context, 0, ingredients);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Ingredient ingredient = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fridge_item_textview, parent, false);
        }

        //Get ingredient names and amount
        TextView ingredientText = convertView.findViewById(R.id.ingredient);
        TextView amountText = convertView.findViewById(R.id.amount);

        ingredientText.setText(ingredient.getName());
        amountText.setText(String.valueOf(ingredient.getAmount()));

        return convertView;
    }
}
