package com.example.cs65_final_project.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.cs65_final_project.R;
import com.example.cs65_final_project.activities.ProfileActivity;

public class PhotoGalleryDialog extends DialogFragment implements DialogInterface.OnClickListener {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.photo_or_gallery, android.R.layout.simple_list_item_1);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Dialog dialog = builder.setAdapter(adapter, this).create();
        return dialog;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        ProfileActivity parent = (ProfileActivity) getActivity();
        // 0 -> Take photo; 1 -> Find photo from gallery
        parent.onPictureMethodChosen(i);
    }
}
