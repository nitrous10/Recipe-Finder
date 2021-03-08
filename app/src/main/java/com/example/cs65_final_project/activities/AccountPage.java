package com.example.cs65_final_project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs65_final_project.FirebaseDatabaseHelper;
import com.example.cs65_final_project.FirebaseStorageHelper;
import com.example.cs65_final_project.R;
import com.example.cs65_final_project.adapters.SearchFriendAdapter;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountPage extends AppCompatActivity implements ListView.OnItemClickListener, View.OnClickListener {
    private TextView followers, following, name, bio;
    private ListView posts;
    private CircleImageView pic;

    private ArrayList<String> results;
    private SearchFriendAdapter adapter;
    private Handler resultsHandler;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        pic = findViewById(R.id.profile_pic);
        followers = findViewById(R.id.followers);
        following = findViewById(R.id.following);
        name = findViewById(R.id.name);
        bio = findViewById(R.id.bio);
        posts = findViewById(R.id.posts_lists);

        loadProfile();

        results = new ArrayList<>();

        adapter = new SearchFriendAdapter(this, results);
        posts.setAdapter(adapter);

        posts.setOnItemClickListener(this);

        HandlerThread handlerThread = new HandlerThread("FriendResultThread");
        handlerThread.start();

        resultsHandler = new Handler(handlerThread.getLooper());
    }

    public void loadProfile() {
        FirebaseStorageHelper.loadPicture(pic);
        FirebaseDatabaseHelper.loadAccount(followers, following, name, bio, posts);
    }

    public void followersClicked(View view) {
        Intent intent = new Intent(this, ViewFriendsActivity.class);
        startActivity(intent);
    }

    public void followingClicked(View view) {
        Intent intent = new Intent(this, ViewFriendsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private void updateListView(){
        resultsHandler.post(new Runnable() {
            @Override
            public void run() {
                results = FirebaseDatabaseHelper.getAllFriends(adapter, results);
            }
        });
        if(results != null){
            adapter.updateData(results);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {

    }
}
