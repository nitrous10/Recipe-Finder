package com.example.cs65_final_project;

import java.util.List;

/**
 * Data type for a recipe. We can create subclasses and additional features in the future.
 */
public class Recipe {

    private final String name;
    private final List<Ingredient> ingredients;
    private final List<String> steps;
    private final int time;

    public Recipe(String name, List<Ingredient> ingredients, List<String> steps, int time) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public int getTime() {
        return time;
    }
}
