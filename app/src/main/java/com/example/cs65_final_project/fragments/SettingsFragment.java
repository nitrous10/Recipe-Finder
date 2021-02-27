package com.example.cs65_final_project.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.cs65_final_project.FirebaseAuthHelper;
import com.example.cs65_final_project.R;
import com.example.cs65_final_project.activities.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsFragment extends PreferenceFragmentCompat {

    public static final String ALLERGY_DIALOG_TAG = "allergy dialog tag";
    public static final String ACCOUNT_DIALOG_TAG = "account dialog tag";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        Preference allergies = findPreference("Allergies");

        allergies.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                AllergiesDialog dialog = new AllergiesDialog();
                dialog.show(getParentFragmentManager(), ALLERGY_DIALOG_TAG);
                return false;
            }
        });

        Preference accounts = findPreference("Accounts");
        accounts.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                SwitchAccountsDialog dialog = new SwitchAccountsDialog();
                dialog.show(getParentFragmentManager(), ACCOUNT_DIALOG_TAG);
                return false;
            }
        });
        Preference logout = findPreference("Logout");
        logout.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                FirebaseAuthHelper.logout();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                return false;
            }
        });
    }
}