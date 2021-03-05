package com.example.cs65_final_project.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cs65_final_project.Ingredient;
import com.example.cs65_final_project.R;
import com.example.cs65_final_project.Recipe;
import com.example.cs65_final_project.activities.RecipeViewActivity;
import com.example.cs65_final_project.adapters.SuggestedRecipeAdapter;
import com.example.cs65_final_project.exceptions.SpoonacularException;
import com.example.cs65_final_project.spoonacular.SpoonacularGatewayController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchFragment extends Fragment implements AdapterView.OnItemClickListener,
        SearchView.OnQueryTextListener {

    private Handler mSearchQueryHandler;

    private final List<Recipe> mRecipes = new ArrayList<>();
    private SuggestedRecipeAdapter mSuggestedRecipeAdapter;

    // TODO: Should be in RecipeSearchController
    private final SpoonacularGatewayController mSpoonacularGatewayController =
            new SpoonacularGatewayController();

    private ProgressBar mSearchProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        HandlerThread handlerThread = new HandlerThread("HandlerThread");
        handlerThread.start();
        mSearchQueryHandler = new Handler(handlerThread.getLooper());

        mSuggestedRecipeAdapter = new SuggestedRecipeAdapter(getActivity(), mRecipes);

        ListView listView = view.findViewById(R.id.recipe_list_view);
        listView.setAdapter(mSuggestedRecipeAdapter);
        listView.setOnItemClickListener(this);

        SearchView searchView = (SearchView) view.findViewById(R.id.recipe_search_view);
        searchView.setOnQueryTextListener(this);

        mSearchProgressBar = (ProgressBar) view.findViewById(R.id.search_progress_bar);

        searchRecipes(null, 10);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
        Intent intent = new Intent(getActivity(), RecipeViewActivity.class);

        Recipe recipe = mRecipes.get(pos);
        intent.putExtra("title", recipe.getName());
        intent.putExtra("time", recipe.getTime());
        intent.putExtra("ingredients",
                recipe.getIngredients()
                        .stream()
                        .map(Ingredient::getName)
                        .toArray(String[]::new));
        intent.putExtra("steps", recipe.getSteps().toArray(new String[0]));

        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        searchRecipes(query, 50);   // TODO: numOfResults
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    private void searchRecipes(@Nullable String query, int numOfResults) {
        mSearchQueryHandler.removeCallbacksAndMessages(null);
        mSearchQueryHandler.post(() -> {
            try {
                getActivity().runOnUiThread(() -> {
                    mSearchProgressBar.setVisibility(ProgressBar.VISIBLE);
                });

                List<Recipe> recipes = mSpoonacularGatewayController.getRecipes(
                        query,
                        // TODO: Use ingredients in fridge
                        Arrays.asList("blueberry","banana","sugar","eggs","milk"),
                        numOfResults);

                mRecipes.clear();
                mRecipes.addAll(recipes);
                getActivity().runOnUiThread(() -> {
                    mSearchProgressBar.setVisibility(ProgressBar.INVISIBLE);
                    mSuggestedRecipeAdapter.notifyDataSetChanged();
                });
            } catch (SpoonacularException e) {
                getActivity().runOnUiThread(() -> {
                    mSearchProgressBar.setVisibility(ProgressBar.INVISIBLE);
                    Toast.makeText(getActivity(), "Failed to get recipes!", Toast.LENGTH_LONG)
                            .show();
                });
            }
        });
    }
}
