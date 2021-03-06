package com.example.cs65_final_project.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.cs65_final_project.FirebaseDatabaseHelper;
import com.example.cs65_final_project.Ingredient;
import com.example.cs65_final_project.R;
import com.example.cs65_final_project.activities.EditOrAddIngredientActivity;
import com.example.cs65_final_project.adapters.FridgeListViewAdapter;

import java.util.ArrayList;

public class FridgeCategorySelectedFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String CATEGORY_KEY = "category key";
    public static final String MEAT = "Meat";
    public static final String CARBS = "Carbs";
    public static final String DAIRY = "Dairy";
    public static final String SEAFOOD = "Seafood";
    public static final String SEASONING = "Seasoning, Condiments, Dressings";
    public static final String PRODUCE = "Fruits and Vegetables";
    public static final String BEVERAGES = "Beverages";
    public static final String NUTS = "Nuts";
    public static final String OTHERS = "Others";

    private ArrayList<Ingredient> ingredients;
    private ListView listView;
    private String aisleSelected;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fridge_list_view, null);
        ImageButton backButton = view.findViewById(R.id.back_button);
        backButton.setOnClickListener(this::onBack);

        ingredients = new ArrayList<>();
        listView = view.findViewById(R.id.list_fridge);
        listView.setOnItemClickListener(this);

        Bundle bundle = getArguments();
        aisleSelected = bundle.getString(CATEGORY_KEY);

        TextView title = view.findViewById(R.id.aisle_title);
        title.setText(aisleSelected);
        if(aisleSelected.equals(SEASONING)){
            title.setText("Seasoning"); //Name too long
        } else if(aisleSelected.equals(PRODUCE)){
            title.setText("Produce");
        }

        getIngredientList();
        return view;
    }

    public void onBack(View view){
        backAnimation();
    }

    public void backAnimation(){
        Fragment categories = new FridgeCategoryFragment();
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        ft.replace(R.id.current_fridge, categories).commit();
    }

    public void getIngredientList() {
        //Set up the adapter
        ingredients = new ArrayList<>();
        FridgeListViewAdapter demoAdapter = new FridgeListViewAdapter(getActivity(), ingredients);
        listView.setAdapter(demoAdapter);

        FirebaseDatabaseHelper.getFridgeCategory(aisleSelected, ingredients, demoAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        getIngredientList();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Ingredient ingredient = (Ingredient)adapterView.getItemAtPosition(i);

        Intent intent = new Intent(getActivity(), EditOrAddIngredientActivity.class);

        intent.putExtra(EditOrAddIngredientActivity.AMOUNT_KEY, ingredient.getAmount());
        intent.putExtra(EditOrAddIngredientActivity.NAME_KEY, ingredient.getName());
        intent.putExtra(EditOrAddIngredientActivity.AISLE_KEY, ingredient.getAisle());

        startActivity(intent);
    }
}
