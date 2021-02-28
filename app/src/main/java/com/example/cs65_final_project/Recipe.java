package com.example.cs65_final_project;

import java.util.List;

/**
 * Data type for a recipe. We can create subclasses and additional features in the future
 *
 */
public class Recipe {
    private String name;
    private List<Ingredient> ingredients;
    private int time;

    public Recipe(String name, List<Ingredient> ingredients, int time){
        this.name = name;
        this.ingredients = ingredients;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    /** Format ingredient list as a string*/
    public String getIngredientsAsString(){
        //Format ingredients
        if(ingredients != null){
            String string = ingredients.get(0).getName();
            for(int i=1; i<ingredients.size(); i++){
                string += ", " + ingredients.get(i).getName();
            }
            return string;
        }
        return "";
    }

    public int getTime() {
        return time;
    }
}
