package com.example.cs65_final_project.exceptions;

/**
 * Checked exception used to handle failures related to the Spoonacular API.
 */
public class SpoonacularException extends Exception {

    public SpoonacularException(String message) {
        super(message);
    }
}
