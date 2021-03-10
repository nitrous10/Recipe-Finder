package com.example.cs65_final_project.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.cs65_final_project.FirebaseAuthHelper;
import com.example.cs65_final_project.R;
import com.example.cs65_final_project.activities.MainActivity;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        findPreference("Logout").setOnPreferenceClickListener(preference -> {
            FirebaseAuthHelper.logout();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            return false;
        });
    }
}
