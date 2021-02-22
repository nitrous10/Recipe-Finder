package com.example.cs65_final_project;

/**
 * Data type for ingredients. We can maybe create subclasses for vegetables, protein, etc. or
 * even by units (makes more sense to say 3 tomatoes than 100 grams of tomatoes)
 * */
public class Ingredient {
    private String name;
    private float amount;

    public Ingredient(String name, float amount){
        this.name = name;
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
}
