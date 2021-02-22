package com.example.cs65_final_project.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.cs65_final_project.Ingredient;
import com.example.cs65_final_project.R;
import com.example.cs65_final_project.activities.AddIngredientActivity;
import com.example.cs65_final_project.adapters.FridgeListViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FridgeFragment extends Fragment{

    private ArrayList<Ingredient> ingredients;

    public FridgeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fridge, container, false);
        FloatingActionButton addButton = view.findViewById(R.id.add_ingredient);

        //Implement onClickListener for floating add button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddIngredientActivity.class);
                startActivity(intent);
            }
        });

        //Demo ingredients
        ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Tomatoes", 5));
        ingredients.add(new Ingredient("Chicken", 200));
        ingredients.add(new Ingredient("Onion", 2));
        ingredients.add(new Ingredient("Salt", 300));
        ingredients.add(new Ingredient("Cabbage", 2));

        ListView listView = view.findViewById(R.id.list_fridge);
        FridgeListViewAdapter demoAdapter = new FridgeListViewAdapter(getActivity(), ingredients);
        listView.setAdapter(demoAdapter);

        return view;
    }
}