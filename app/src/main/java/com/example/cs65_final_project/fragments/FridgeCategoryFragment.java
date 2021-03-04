package com.example.cs65_final_project.fragments;

import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.cs65_final_project.R;

public class FridgeCategoryFragment extends Fragment {

    private ImageButton meatButton;
    private ImageButton carbsButton;
    private ImageButton dairyButton;
    private ImageButton seafoodButton;
    private ImageButton seasoningButton;
    private ImageButton vegetablesButton;
    private ImageButton beveragesButton;
    private ImageButton nutsButton;
    private ImageButton othersButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fridge_categories, null);
        meatButton = view.findViewById(R.id.meat_button);
        carbsButton = view.findViewById(R.id.carbs_button);
        dairyButton = view.findViewById(R.id.dairy_button);
        seafoodButton = view.findViewById(R.id.seafood_button);
        seasoningButton = view.findViewById(R.id.seasoning_button);
        vegetablesButton = view.findViewById(R.id.vegetables_button);
        beveragesButton = view.findViewById(R.id.beverages_button);
        nutsButton = view.findViewById(R.id.nuts_button);
        othersButton = view.findViewById(R.id.others_button);

        meatButton.setOnClickListener(this::onAisleSelected);
        carbsButton.setOnClickListener(this::onAisleSelected);
        dairyButton.setOnClickListener(this::onAisleSelected);
        seafoodButton.setOnClickListener(this::onAisleSelected);
        vegetablesButton.setOnClickListener(this::onAisleSelected);
        beveragesButton.setOnClickListener(this::onAisleSelected);
        nutsButton.setOnClickListener(this::onAisleSelected);
        othersButton.setOnClickListener(this::onAisleSelected);
        seasoningButton.setOnClickListener(this::onAisleSelected);

        return view;
    }

    public void onAisleSelected(View view){
        ((FridgeFragment)getParentFragment()).onAisleSelected(view.getId());
    }
}
