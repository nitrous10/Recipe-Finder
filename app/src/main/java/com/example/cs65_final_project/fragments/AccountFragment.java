package com.example.cs65_final_project.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.cs65_final_project.FirebaseDatabaseHelper;
import com.example.cs65_final_project.FirebaseStorageHelper;
import com.example.cs65_final_project.R;
import com.example.cs65_final_project.activities.HomeActivity;
import com.example.cs65_final_project.activities.SearchFriendsActivity;
import com.example.cs65_final_project.activities.ViewFriendsActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private TextView followers, following, name, bio;
    private ListView posts;
    private CircleImageView pic;
    private Button addFollowing, manageSettings;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_account, container, false);

        pic = view.findViewById(R.id.profile_pic);
        followers = view.findViewById(R.id.followers);
        following = view.findViewById(R.id.following);

        followers.setText(R.string.followers_initial);
        following.setText(R.string.following_initial);

        name = view.findViewById(R.id.name);
        bio = view.findViewById(R.id.bio);
        posts = view.findViewById(R.id.posts_lists);
        addFollowing = view.findViewById(R.id.add_following);
        manageSettings = view.findViewById(R.id.manage_settings);

        loadProfile();

        followers.setOnClickListener(this);
        following.setOnClickListener(this);
        addFollowing.setOnClickListener(this);
        manageSettings.setOnClickListener(this);

        return view;
    }

    public void loadProfile() {
        // FirebaseStorageHelper.loadPicture(pic);
        FirebaseDatabaseHelper.loadAccount(followers, following, name, bio, posts);
    }

    public void followersClicked() {
        Intent intent = new Intent(getActivity(), ViewFriendsActivity.class);
        startActivity(intent);
    }

    public void followingClicked() {
        Intent intent = new Intent(getActivity(), ViewFriendsActivity.class);
        startActivity(intent);
    }

    public void addFollowing() {
        Intent intent = new Intent(getActivity(), SearchFriendsActivity.class);
        startActivity(intent);
    }

    public void manageSettings() {
        FragmentManager fm = getParentFragmentManager();
        Fragment settings = new SettingsFragment();
        fm.beginTransaction().replace(R.id.frame_layout, settings, HomeActivity.SETTINGS_TAG).commit();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.followers) {
            followersClicked();
        } else if (v.getId() == R.id.following) {
            followingClicked();
        } else if (v.getId() == R.id.add_following) {
            addFollowing();
        } else if (v.getId() == R.id.manage_settings) {
            manageSettings();
        }
    }
}
