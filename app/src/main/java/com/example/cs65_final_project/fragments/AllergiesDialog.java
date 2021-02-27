package com.example.cs65_final_project.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.cs65_final_project.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class AllergiesDialog extends DialogFragment implements DialogInterface.OnClickListener {

    public static final String ALLERGIES = "Allergies";
    public static final String FISH_ALLERGY = "Fish allergy";
    public static final String SHELLFISH_ALLERGY = "Shellfish allergy";
    public static final String WHEAT_ALLERGY = "Wheat allergy";
    public static final String MILK_ALLERGY = "Milk allergy";
    public static final String SOY_ALLERGY = "Soy allergy";
    public static final String EGG_ALLERGY = "Egg allergy";
    public static final String PEANUT_ALLERGY = "Peanut allergy";
    public static final String TREE_NUTS_ALLERGY = "Tree nuts allergy";

    private CheckBox fish;
    private CheckBox shellfish;
    private CheckBox wheat;
    private CheckBox soy;
    private CheckBox egg;
    private CheckBox peanut;
    private CheckBox treeNut;
    private CheckBox milk;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_allergies, null);

        fish = view.findViewById(R.id.fish);
        shellfish = view.findViewById(R.id.shellfish);
        wheat = view.findViewById(R.id.wheat);
        milk = view.findViewById(R.id.milk);
        soy = view.findViewById(R.id.soy);
        egg = view.findViewById(R.id.eggs);
        peanut = view.findViewById(R.id.peanuts);
        treeNut = view.findViewById(R.id.tree_nuts);

        loadAllergies();

        Dialog dialog = builder.setTitle("Select from Common Allergies:")
                .setPositiveButton("Save", this)
                .setNegativeButton("Cancel", this)
                .setView(view).create();

        return dialog;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i == DialogInterface.BUTTON_NEGATIVE){
            dismiss();
        } else if(i == DialogInterface.BUTTON_POSITIVE){
            saveAllergies();
            dismiss();
        }
    }

    private void saveAllergies(){
        // Save to SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(ALLERGIES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(FISH_ALLERGY, fish.isChecked());
        editor.putBoolean(SHELLFISH_ALLERGY, shellfish.isChecked());
        editor.putBoolean(WHEAT_ALLERGY, wheat.isChecked());
        editor.putBoolean(MILK_ALLERGY, milk.isChecked());
        editor.putBoolean(SOY_ALLERGY, soy.isChecked());
        editor.putBoolean(EGG_ALLERGY, egg.isChecked());
        editor.putBoolean(PEANUT_ALLERGY, peanut.isChecked());
        editor.putBoolean(TREE_NUTS_ALLERGY, treeNut.isChecked());
        editor.commit();
    }

    private void loadAllergies(){
        // Load from SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(ALLERGIES, Context.MODE_PRIVATE);
        fish.setChecked(sharedPreferences.getBoolean(FISH_ALLERGY, false));
        shellfish.setChecked(sharedPreferences.getBoolean(SHELLFISH_ALLERGY, false));
        wheat.setChecked(sharedPreferences.getBoolean(WHEAT_ALLERGY, false));
        milk.setChecked(sharedPreferences.getBoolean(MILK_ALLERGY, false));
        soy.setChecked(sharedPreferences.getBoolean(SOY_ALLERGY, false));
        egg.setChecked(sharedPreferences.getBoolean(EGG_ALLERGY, false));
        peanut.setChecked(sharedPreferences.getBoolean(PEANUT_ALLERGY, false));
        treeNut.setChecked(sharedPreferences.getBoolean(TREE_NUTS_ALLERGY, false));
    }
}
