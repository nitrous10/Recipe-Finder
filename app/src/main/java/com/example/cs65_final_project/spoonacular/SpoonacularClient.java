package com.example.cs65_final_project.spoonacular;

import android.util.Log;

import com.example.cs65_final_project.exceptions.SpoonacularException;
import com.google.gson.Gson;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Client for interacting with the Spoonacular API. Everything related to the Spoonacular API should
 * live here.
 */
class SpoonacularClient {

    private final static String BASE_URL = "https://api.spoonacular.com";
    // TODO: Make this more secure?
    private final static String API_KEY = "bd80ef59d3274d0c8efc90e844ff81c7";

    private final OkHttpClient mClient;

    public SpoonacularClient() {
        this.mClient = new OkHttpClient()
                .newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }


    /**
     * Fetch a list of possible ingredients based on the provided query.
     *
     * @param query search query provided to find ingredients
     * @param numOfResults the number of ingredients to return
     * @param metaInfo true if want additional info like aisle
     * @return list of ingredients
     * @throws SpoonacularException if we fail to successfully retrieve and parse the list of
     *                              ingredients from the Spoonacular API
     */
    public List<SpoonacularIngredient> getIngredients(final String query, final int numOfResults,
                                                      final boolean metaInfo) throws SpoonacularException {
        Request request = new Request.Builder()
                .url(HttpUrl.parse(BASE_URL + "/food/ingredients/autocomplete")
                        .newBuilder()
                        .addQueryParameter("apiKey", API_KEY)
                        .addQueryParameter("query", query)
                        .addQueryParameter("number", String.valueOf(numOfResults))
                        .addQueryParameter("metaInformation", String.valueOf(metaInfo))
                        .build())
                .build();

        try (Response response = mClient.newCall(request).execute()) {
            String body = response.body().string();
            SpoonacularIngredient[] ingredients =
                    (new Gson()).fromJson(body, SpoonacularIngredient[].class);

            return Arrays.asList(ingredients);
        } catch (IOException e) {
            Log.e("RecipeFinder", e.getMessage());
            throw new SpoonacularException("Couldn't communicate with the kitchen. Please try again!");
        }
    }

    /**
     * Fetch a list of possible recipes based on the provided list of ingredients.
     *
     * @param ingredients list of ingredients for the recipe to include
     * @param numOfResults the number of recipes to return
     * @return list of recipes
     * @throws SpoonacularException if we fail to successfully retrieve and parse the list of
     *                              recipes from the Spoonacular API
     */
    public List<SpoonacularSearchRecipe> searchRecipes(List<String> ingredients,
                                                       int numOfResults) throws SpoonacularException {
        Request request = new Request.Builder()
                .url(HttpUrl.parse(BASE_URL + "/recipes/findByIngredients")
                        .newBuilder()
                        .addQueryParameter("apiKey", API_KEY)
                        .addQueryParameter("ingredients", StringUtils.join(ingredients, ","))
                        .addQueryParameter("number", String.valueOf(numOfResults))
                        .addQueryParameter("ranking", String.valueOf(2))
                        .build())
                .build();

        try (Response response = mClient.newCall(request).execute()) {
            SpoonacularSearchRecipe[] recipes =
                    (new Gson()).fromJson(response.body().string(), SpoonacularSearchRecipe[].class);

            return Arrays.asList(recipes);
        } catch (IOException e) {
            Log.e("RecipeFinder", e.getMessage());
            throw new SpoonacularException("Couldn't communicate with the kitchen. Please try again!");
        }
    }

    /**
     * Fetch the recipes associated with the provided ids.
     *
     * @param ids the IDs of the recipes to fetch
     * @return list of recipes
     * @throws SpoonacularException if we fail to successfully retrieve and parse the list of
     *                              recipes from the Spoonacular API
     */
    public List<SpoonacularRecipe> getRecipes(List<Long> ids) throws SpoonacularException {
        Request request = new Request.Builder()
                .url(HttpUrl.parse(BASE_URL + "/recipes/informationBulk")
                        .newBuilder()
                        .addQueryParameter("apiKey", API_KEY)
                        .addQueryParameter("ids", StringUtils.join(ids, ","))
                        .build())
                .build();

        try (Response response = mClient.newCall(request).execute()) {
            SpoonacularRecipe[] recipes =
                    (new Gson()).fromJson(response.body().string(), SpoonacularRecipe[].class);

            return Arrays.asList(recipes);
        } catch (IOException e) {
            Log.e("RecipeFinder", e.getMessage());
            throw new SpoonacularException("Couldn't communicate with the kitchen. Please try again!");
        }
    }
}
