package com.example.cs65_final_project.spoonacular;

import java.util.List;

/**
 * DTO object used to capture the necessary fields from the Spoonacular Recipe Search API.
 */
class SpoonacularSearchRecipe {

    private long id;
    private String title;
    private List<SpoonacularRecipeIngredient> usedIngredients;
    private List<SpoonacularRecipeIngredient> missedIngredients;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    List<SpoonacularRecipeIngredient> getUsedIngredients() {
        return usedIngredients;
    }

    void setUsedIngredients(List<SpoonacularRecipeIngredient> usedIngredients) {
        this.usedIngredients = usedIngredients;
    }

    List<SpoonacularRecipeIngredient> getMissedIngredients() {
        return missedIngredients;
    }

    void setMissedIngredients(List<SpoonacularRecipeIngredient> missedIngredients) {
        this.missedIngredients = missedIngredients;
    }


}
