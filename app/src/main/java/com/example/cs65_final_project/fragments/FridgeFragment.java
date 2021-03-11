package com.example.cs65_final_project.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cs65_final_project.R;
import com.example.cs65_final_project.activities.EditOrAddIngredientActivity;
import com.example.cs65_final_project.activities.SearchAddIngredientActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Note that "Category" and "Aisle" is used interchangeably
 */
public class FridgeFragment extends Fragment implements View.OnClickListener {

    private FloatingActionButton searchAdd;
    private FloatingActionButton manualAdd;


    public static final String CATEGORIES_TAG = "categories tag";

    public FridgeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fridge, container, false);

        searchAdd = view.findViewById(R.id.search_add_ingredient);
        manualAdd = view.findViewById(R.id.manual_add_ingredient);
        searchAdd.setOnClickListener(this);
        manualAdd.setOnClickListener(this);

        Fragment categories = new FridgeCategoryFragment();
        FragmentManager fm = getChildFragmentManager();
        fm.beginTransaction().add(R.id.current_fridge, categories, CATEGORIES_TAG).commit();

        return view;
    }

    @Override
    public void onResume() {
        Log.d("RecipeFinder", "onResume() called");
        super.onResume();
        if (searchAdd != null){
            searchAdd.setVisibility(View.GONE);
        }
    }

    /** Animation for spinning manual add button and showing search add button*/
    private void animateButtons(){
        searchAdd.setVisibility(View.VISIBLE);
        searchAdd.setAlpha(0f);
        searchAdd.animate().setDuration(200).alpha(1f).start();

        manualAdd.animate().setDuration(200).rotation(180f).start();
    }

    /** Handles onClick for the floating buttons*/
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.manual_add_ingredient){
            // Second time
            if(searchAdd.getVisibility() != View.GONE){
                Intent intent = new Intent(getActivity(), EditOrAddIngredientActivity.class);
                startActivity(intent);
            } else{ // First time it's clicked
                animateButtons();
            }
        }
        else if(view.getId() == R.id.search_add_ingredient){
            Intent intent = new Intent(getActivity(), SearchAddIngredientActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Runs when category/aisle is selected from the list
     * @param id of the button selected
     */
    public void onAisleSelected(int id){
        FragmentManager fm = getChildFragmentManager();
        Bundle bundle = new Bundle();
        String aisle = "";
        if (id == R.id.meat_button){
            aisle = FridgeCategorySelectedFragment.MEAT;
        } else if(id == R.id.carbs_button){
            aisle = FridgeCategorySelectedFragment.CARBS;
        } else if(id == R.id.dairy_button){
            aisle = FridgeCategorySelectedFragment.DAIRY;
        } else if(id == R.id.seasoning_button){
            aisle = FridgeCategorySelectedFragment.SEASONING;
        } else if(id == R.id.seafood_button){
            aisle = FridgeCategorySelectedFragment.SEAFOOD;
        } else if(id == R.id.vegetables_button){
            aisle = FridgeCategorySelectedFragment.PRODUCE;
        } else if(id == R.id.beverages_button){
            aisle = FridgeCategorySelectedFragment.BEVERAGES;
        } else if(id == R.id.nuts_button){
            aisle = FridgeCategorySelectedFragment.NUTS;
        } else if(id == R.id.others_button){
            aisle = FridgeCategorySelectedFragment.OTHERS;
        }
        bundle.putString(FridgeCategorySelectedFragment.CATEGORY_KEY, aisle);

        Fragment fridge = new FridgeCategorySelectedFragment();
        fridge.setArguments(bundle);
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        ft.replace(R.id.current_fridge, fridge).commit();
    }

    /**
     * @return true if current fragment is FridgeCategorySelected
     */
    public boolean categorySelected(){
        return getChildFragmentManager().findFragmentById(R.id.current_fridge)
                instanceof FridgeCategorySelectedFragment;
    }

    /** Slide animation */
    public void runBackAnimation(){
        FridgeCategorySelectedFragment fragment = (FridgeCategorySelectedFragment) getChildFragmentManager()
                .findFragmentById(R.id.current_fridge);
        fragment.backAnimation();
    }
}