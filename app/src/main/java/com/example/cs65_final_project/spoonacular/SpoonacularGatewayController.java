package com.example.cs65_final_project.spoonacular;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.cs65_final_project.Ingredient;
import com.example.cs65_final_project.Recipe;
import com.example.cs65_final_project.exceptions.SpoonacularException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller acting as a gateway to the Spoonacular client.
 */
public class SpoonacularGatewayController {

    private final SpoonacularClient mClient;

    public SpoonacularGatewayController() {
        this.mClient = new SpoonacularClient();
    }

    public List<Recipe> getRecipes(@Nullable String searchQuery,
                                   List<String> ingredients,
                                   int numOfResults) throws SpoonacularException {
        List<SpoonacularSearchRecipe> spoonacularSearchRecipes =
                mClient.searchRecipes(ingredients, numOfResults);

        List<SpoonacularRecipe> spoonacularRecipes = new ArrayList<>();
        if (spoonacularSearchRecipes.size() > 0){
            spoonacularRecipes = mClient.getRecipes(
                    spoonacularSearchRecipes.stream()
                            .map(SpoonacularSearchRecipe::getId)
                            .collect(Collectors.toList()));
        }

        return toRecipes(spoonacularRecipes.stream()
                .filter(recipe -> matchesSearchQuery(recipe, searchQuery))
                .filter(recipe -> !recipe.getAnalyzedInstructions().isEmpty())
                .collect(Collectors.toList()));
    }

    public List<Ingredient> getIngredients(String query,
                                           int numOfResults,
                                           boolean metaInfo) throws SpoonacularException {
        return toIngredients(mClient.getIngredients(query, numOfResults, metaInfo));
    }

    private boolean matchesSearchQuery(SpoonacularRecipe recipe, @Nullable String searchQuery) {
        if (searchQuery == null) {
            return true;
        }

        return recipe.getTitle().toLowerCase().contains(searchQuery);
    }

    private List<Ingredient> toIngredients(List<SpoonacularIngredient> ingredients) {
        return ingredients.stream().map(this::toIngredient).collect(Collectors.toList());
    }

    private List<Recipe> toRecipes(List<SpoonacularRecipe> spoonacularRecipes) {
        return spoonacularRecipes.stream().map(this::toRecipe).collect(Collectors.toList());
    }

    private Recipe toRecipe(SpoonacularRecipe spoonacularRecipe) {
        return new Recipe(
                spoonacularRecipe.getTitle(),
                spoonacularRecipe.getImage(),
                spoonacularRecipe.getExtendedIngredients()
                        .stream()
                        .map(this::toIngredient)
                        .collect(Collectors.toList()),
                spoonacularRecipe.getAnalyzedInstructions()
                        .stream()
                        .flatMap(instructions -> instructions.getSteps().stream())
                        .map(SpoonacularRecipeInstructionsStep::getStep)
                        .collect(Collectors.toList()),
                spoonacularRecipe.getReadyInMinutes());
    }

    // TODO: Amount?
    private Ingredient toIngredient(SpoonacularRecipeIngredient ingredient) {
        return new Ingredient(ingredient.getOriginal(), 1.0f);
    }

    // TODO: Amount?
    private Ingredient toIngredient(SpoonacularIngredient ingredient) {
        return new Ingredient(ingredient.getName(), 1.0f, ingredient.getAisle());
    }
}
