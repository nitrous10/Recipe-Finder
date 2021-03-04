package com.example.cs65_final_project.fragments;

import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cs65_final_project.FirebaseDatabaseHelper;
import com.example.cs65_final_project.Ingredient;
import com.example.cs65_final_project.R;
import com.example.cs65_final_project.adapters.FridgeListViewAdapter;

import java.util.ArrayList;

public class FridgeCategorySelectedFragment extends Fragment {

    public static final String CATEGORY_KEY = "category key";
    public static final String MEAT = "Meat";
    public static final String CARBS = "Carbs";
    public static final String DAIRY = "Dairy";
    public static final String SEAFOOD = "Seafood";
    public static final String SEASONING = "Seasoning";
    public static final String VEGETABLES = "Vegetables";
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

        Bundle bundle = getArguments();
        aisleSelected = bundle.getString(CATEGORY_KEY);

        TextView title = view.findViewById(R.id.aisle_title);
        title.setText(aisleSelected);

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

    /**
     * TODO: update the ingredient list according to the aisle/category selected from firebase
     */
    public void getIngredientList() {
        //Set up the adapter
        ingredients = new ArrayList<>();
        FridgeListViewAdapter demoAdapter = new FridgeListViewAdapter(getActivity(), ingredients);
        listView.setAdapter(demoAdapter);

        if(aisleSelected.equals(MEAT)){

        } else if(aisleSelected.equals(CARBS)){

        } else if(aisleSelected.equals(DAIRY)){

        } else if(aisleSelected.equals(SEAFOOD)){

        } else if(aisleSelected.equals(SEASONING)){

        } else if(aisleSelected.equals(VEGETABLES)){

        } else if(aisleSelected.equals(BEVERAGES)){

        } else if(aisleSelected.equals(NUTS)){

        } else if(aisleSelected.equals(OTHERS)){

        }
    }
}
