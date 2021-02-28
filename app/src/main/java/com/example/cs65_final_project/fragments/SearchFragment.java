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
import android.widget.SearchView;

import androidx.fragment.app.Fragment;

import com.example.cs65_final_project.R;
import com.example.cs65_final_project.Recipe;
import com.example.cs65_final_project.activities.RecipeViewActivity;
import com.example.cs65_final_project.adapters.SuggestedRecipeAdapter;
import com.example.cs65_final_project.spoonacular.SpoonacularGatewayController;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements AdapterView.OnItemClickListener,
        SearchView.OnQueryTextListener {

    private Handler mSearchQueryHandler;

    private List<Recipe> mSuggestedRecipes;
    private SuggestedRecipeAdapter mSuggestedRecipeAdapter;

    // TODO: Should be in RecipeSearchController
    private SpoonacularGatewayController mSpoonacularGatewayController;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        mSpoonacularGatewayController = new SpoonacularGatewayController();

        HandlerThread handlerThread = new HandlerThread("HandlerThread");
        handlerThread.start();
        mSearchQueryHandler = new Handler(handlerThread.getLooper());

        mSuggestedRecipes = new ArrayList<>();
        mSuggestedRecipeAdapter = new SuggestedRecipeAdapter(getActivity(), mSuggestedRecipes);

        ListView listView = view.findViewById(R.id.recipe_list_view);
        listView.setAdapter(mSuggestedRecipeAdapter);

        SearchView searchView = (SearchView) view.findViewById(R.id.recipe_search_view);
        searchView.setOnQueryTextListener(this);

        return view;
    }

    /** Start RecipeViewActivity when a recipe is clicked*/
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getActivity(), RecipeViewActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mSearchQueryHandler.removeCallbacksAndMessages(null);
        mSearchQueryHandler.postDelayed((Runnable) () -> {
//            try {
//                List<Recipe> recipes =
//                        mSpoonacularGatewayController.getRecipes(Arrays.asList("apple","sugar"), 10);
//
//                getActivity().runOnUiThread(() -> {
//                    mSuggestedRecipeAdapter.updateRecipes(recipes);
//                });
//            } catch (SpoonacularException e) {
//                Toast.makeText(getActivity(), "Faild to get recipes!", Toast.LENGTH_LONG).show();
//            }
        }, 500);

        return false;
    }
}
