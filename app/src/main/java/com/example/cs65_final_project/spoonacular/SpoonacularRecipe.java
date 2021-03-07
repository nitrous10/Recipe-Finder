package com.example.cs65_final_project.spoonacular;

import java.util.List;

/**
 * DTO object used to capture the necessary fields from the Spoonacular Recipe Search API.
 */
class SpoonacularRecipe {

    private String title;
    private String image;
    private List<SpoonacularRecipeIngredient> extendedIngredients;
    private List<SpoonacularRecipeInstructions> analyzedInstructions;
    private int readyInMinutes;

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getImage() {
        return image;
    }

    void setImage(String image) {
        this.image = image;
    }

    List<SpoonacularRecipeIngredient> getExtendedIngredients() {
        return extendedIngredients;
    }

    void setExtendedIngredients(List<SpoonacularRecipeIngredient> extendedIngredients) {
        this.extendedIngredients = extendedIngredients;
    }

    List<SpoonacularRecipeInstructions> getAnalyzedInstructions() {
        return analyzedInstructions;
    }

    void setAnalyzedInstructions(List<SpoonacularRecipeInstructions> analyzedInstructions) {
        this.analyzedInstructions = analyzedInstructions;
    }

    int getReadyInMinutes() {
        return readyInMinutes;
    }

    void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }
}
