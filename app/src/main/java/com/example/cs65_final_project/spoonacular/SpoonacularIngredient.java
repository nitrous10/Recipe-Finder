package com.example.cs65_final_project.spoonacular;

/**
 * DTO object used to capture the necessary fields from the Spoonacular Ingredients Search API.
 */
class SpoonacularIngredient {

    private String name;
    private String aisle;

    String getName() {
        return name;
    }

    public String getAisle() { return aisle; }

    void setName(String name) {
        this.name = name;
    }
}
