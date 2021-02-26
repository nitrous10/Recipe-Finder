package com.example.cs65_final_project.spoonacular;

import java.util.List;

/**
 * DTO object used to capture the necessary fields from the Spoonacular Recipe Search API.
 */
class SpoonacularRecipe {

    private String title;
    private List<SpoonacularRecipeIngredient> usedIngredients;
    private List<SpoonacularRecipeIngredient> missedIngredients;

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

    private static class SpoonacularRecipeIngredient {

        private String original;

        private String getOriginal() {
            return original;
        }

        private void setOriginal(String original) {
            this.original = original;
        }
    }
}
