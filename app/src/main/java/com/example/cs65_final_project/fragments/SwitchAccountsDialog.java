package com.example.cs65_final_project.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.cs65_final_project.R;
//import com.example.cs65_final_project.adapters.AccountAdapter;

public class SwitchAccountsDialog extends DialogFragment implements DialogInterface.OnClickListener {

//    AccountAdapter adapter;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        ArrayList<RecipeAccount> accountList = firebase
//        adapter = new AccountAdapter();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        Dialog dialog = builder.setAdapter(adapter, this).create();
        return null;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        // Switch to account
    }
}
