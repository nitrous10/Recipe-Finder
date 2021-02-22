package com.example.cs65_final_project.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cs65_final_project.ChangePasswordActivity;
import com.example.cs65_final_project.CreateAccountActivity;
import com.example.cs65_final_project.R;
import com.example.cs65_final_project.adapters.RecipeFragmentPagerAdapter;
import com.example.cs65_final_project.fragments.FridgeFragment;
import com.example.cs65_final_project.fragments.SearchFragment;
import com.example.cs65_final_project.fragments.SettingsFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login");
    }

    public void loginButtonPressed(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void changePasswordPressed(View v) {
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        startActivity(intent);
    }

    public void createAccountPressed(View v) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }
}