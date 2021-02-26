package com.example.cs65_final_project.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.cs65_final_project.Ingredient;
import com.example.cs65_final_project.R;
import com.example.cs65_final_project.Recipe;
import com.example.cs65_final_project.activities.RecipeViewActivity;
import com.example.cs65_final_project.adapters.SuggestedRecipeAdapter;

import java.util.ArrayList;


public class SearchFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ArrayList<Recipe> suggestedRecipes;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        //Demo recipes
        suggestedRecipes = new ArrayList<>();
        ArrayList<Ingredient> ingredientsOne = new ArrayList<>();
        ingredientsOne.add(new Ingredient("Tomatoes", 2));
        ingredientsOne.add(new Ingredient("Chicken", 100));
        ingredientsOne.add(new Ingredient("Salt", 15));
        suggestedRecipes.add(new Recipe("Suggested recipe #1", ingredientsOne,30));

        ArrayList<Ingredient> ingredientsTwo = new ArrayList<>();
        ingredientsTwo.add(new Ingredient("Tomatoes", 1));
        ingredientsTwo.add(new Ingredient("Chicken", 150));
        ingredientsTwo.add(new Ingredient("Onion", 1));
        suggestedRecipes.add(new Recipe("Suggested recipe #2", ingredientsTwo, 20));

        ArrayList<Ingredient> ingredientsThree = new ArrayList<>();
        ingredientsThree.add(new Ingredient("Cabbage", 1));
        ingredientsThree.add(new Ingredient("Chicken", 150));
        ingredientsThree.add(new Ingredient("Onion", 1));
        ingredientsThree.add(new Ingredient("Salt", 10));
        suggestedRecipes.add(new Recipe("Suggested recipe #3", ingredientsThree, 20));

        ListView listView = view.findViewById(R.id.list_recipe);
        SuggestedRecipeAdapter demoAdapter = new SuggestedRecipeAdapter(getActivity(), suggestedRecipes);
        listView.setAdapter(demoAdapter);
        listView.setOnItemClickListener(this);

        return view;
    }

    /** Start RecipeViewActivity when a recipe is clicked*/
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getActivity(), RecipeViewActivity.class);
        startActivity(intent);
    }
}