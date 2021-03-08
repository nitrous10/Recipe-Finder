package com.example.cs65_final_project.controllers;

import android.util.Log;

import com.example.cs65_final_project.FirebaseDatabaseHelper;
import com.example.cs65_final_project.Ingredient;
import com.example.cs65_final_project.Recipe;
import com.example.cs65_final_project.exceptions.RecipeSearchException;
import com.example.cs65_final_project.exceptions.SpoonacularException;
import com.example.cs65_final_project.listeners.GetIngredientsListener;
import com.example.cs65_final_project.spoonacular.SpoonacularGatewayController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeSearchController {

    SpoonacularGatewayController mSpoonacularGatewayController = new SpoonacularGatewayController();

    public List<Recipe> getRecipes(String searchQuery,
                                   int numOfRecipes) throws RecipeSearchException {
        List<String> ingredients = getIngredientsInFridge().stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());

        Log.e("ammarmazen", ingredients.toString());
        if (ingredients.isEmpty()) {
            return Collections.emptyList();
        }

        try {
            return mSpoonacularGatewayController.getRecipes(searchQuery, ingredients, numOfRecipes);
        } catch (SpoonacularException e) {
            throw new RecipeSearchException(e);
        }
    }

    private List<Ingredient> getIngredientsInFridge() {
        List<Ingredient> ingredients = new ArrayList<>();
        GetIngredientsListener listener = new GetIngredientsListener();
        FirebaseDatabaseHelper.getIngredients(ingredients, listener);

        while (!listener.isReady()) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException ignored) {
            }
        }

        return ingredients;
    }
}
