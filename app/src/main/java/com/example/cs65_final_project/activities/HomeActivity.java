package com.example.cs65_final_project.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.cs65_final_project.R;
import com.example.cs65_final_project.adapters.RecipeFragmentPagerAdapter;
import com.example.cs65_final_project.fragments.FeedFragment;
import com.example.cs65_final_project.fragments.FridgeFragment;
import com.example.cs65_final_project.fragments.SearchFragment;
import com.example.cs65_final_project.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    public static final String FRIDGE_TAG = "fridge tag";
    public static final String SEARCH_TAG = "search tag";
    public static final String FEED_TAG = "feed tag";
    public static final String SETTINGS_TAG = "settings tag";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_layout);
        setTitle("Home");

        BottomNavigationView bnv = findViewById(R.id.bottom_navigation);
        bnv.setOnNavigationItemSelectedListener(this);

        // Initial fragment is the fridge
        if(savedInstanceState == null){
            Fragment fridge = new FridgeFragment();
            getSupportFragmentManager().beginTransaction().add
                    (R.id.frame_layout, fridge, FRIDGE_TAG).commit();
        }
    }

    /** Opens corresponding fragments when the navigation items are selected*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager fm = getSupportFragmentManager();

        if(item.getItemId() == R.id.fridge_page){
            Fragment fridge = new FridgeFragment();
            fm.beginTransaction().replace(R.id.frame_layout, fridge, FRIDGE_TAG).commit();
            return true;
        }
        else if(item.getItemId() == R.id.search_page){
            Fragment search = new SearchFragment();
            fm.beginTransaction().replace(R.id.frame_layout, search, SEARCH_TAG).commit();
            return true;
        }
        else if(item.getItemId() == R.id.feed_page){
            Fragment feed = new FeedFragment();
            fm.beginTransaction().replace(R.id.frame_layout, feed, FEED_TAG).commit();
            return true;
        }
        else if(item.getItemId() == R.id.settings_page){
            Fragment settings = new SettingsFragment();
            fm.beginTransaction().replace(R.id.frame_layout, settings, SETTINGS_TAG).commit();
            return true;
        }
        return false;
    }


    /** Mainly overrides for animation in fridge */
    @Override
    public void onBackPressed() {
        FridgeFragment fridge = (FridgeFragment) getSupportFragmentManager().findFragmentByTag(FRIDGE_TAG);
        if(fridge != null && fridge.categorySelected()){
            fridge.runBackAnimation(); // Run slide animation
        }
        else if(fridge != null){
            // Do nothing if fridge fragment, but category is not selected
        }
        else{
            super.onBackPressed();
        }
    }
}
