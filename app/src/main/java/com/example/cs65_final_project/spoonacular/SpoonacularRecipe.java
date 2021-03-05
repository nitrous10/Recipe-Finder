package com.example.cs65_final_project.spoonacular;

import java.util.List;

/**
 * DTO object used to capture the necessary fields from the Spoonacular Recipe Search API.
 */
class SpoonacularRecipe {

    private String title;
    private List<SpoonacularRecipeIngredient> extendedIngredients;
    private List<SpoonacularRecipeInstructions> analyzedInstructions;
    private int readyInMinutes;

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    public List<SpoonacularRecipeIngredient> getExtendedIngredients() {
        return extendedIngredients;
    }

    public void setExtendedIngredients(List<SpoonacularRecipeIngredient> extendedIngredients) {
        this.extendedIngredients = extendedIngredients;
    }

    public List<SpoonacularRecipeInstructions> getAnalyzedInstructions() {
        return analyzedInstructions;
    }

    public void setAnalyzedInstructions(List<SpoonacularRecipeInstructions> analyzedInstructions) {
        this.analyzedInstructions = analyzedInstructions;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }
}
