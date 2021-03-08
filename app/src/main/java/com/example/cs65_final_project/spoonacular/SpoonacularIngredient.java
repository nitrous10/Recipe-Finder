package com.example.cs65_final_project.spoonacular;

import androidx.annotation.Nullable;

/**
 * DTO object used to capture the necessary fields from the Spoonacular Ingredients Search API.
 */
class SpoonacularIngredient {

    private String name;

    @Nullable
    private String aisle;

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    @Nullable
    public String getAisle() { return aisle; }

    public void setAisle(@Nullable String aisle) {
        this.aisle = aisle;
    }
}
