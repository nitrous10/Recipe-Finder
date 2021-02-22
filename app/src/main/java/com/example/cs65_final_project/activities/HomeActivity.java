package com.example.cs65_final_project.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.cs65_final_project.R;
import com.example.cs65_final_project.adapters.RecipeFragmentPagerAdapter;
import com.example.cs65_final_project.fragments.FridgeFragment;
import com.example.cs65_final_project.fragments.SearchFragment;
import com.example.cs65_final_project.fragments.SettingsFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ArrayList<Fragment> fragmentArrayList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_layout);
        setTitle("Home");

        fragmentArrayList = new ArrayList<>();

        //Get fragments for tab
        Fragment fridgeFragment = new FridgeFragment();
        Fragment settingsFragment = new SettingsFragment();
        Fragment searchFragment = new SearchFragment();
        fragmentArrayList.add(fridgeFragment);
        fragmentArrayList.add(searchFragment);
        fragmentArrayList.add(settingsFragment);

        FragmentManager fragmentManager = getSupportFragmentManager();

        // Set up adapter
        FragmentPagerAdapter adapter = new RecipeFragmentPagerAdapter(fragmentManager, fragmentArrayList);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }
}
