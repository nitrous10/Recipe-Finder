package com.example.cs65_final_project.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs65_final_project.FirebaseDatabaseHelper;
import com.example.cs65_final_project.R;
import com.example.cs65_final_project.adapters.SearchFriendAdapter;
import com.example.cs65_final_project.fragments.AccountFragment;

import java.util.ArrayList;

public class ViewFriendsActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;
    private ArrayList<String> results;
    private SearchFriendAdapter adapter;
    private Handler resultsHandler;
    private String type;

    private FirebaseDatabaseHelper firebaseDatabaseHelper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_friends_activity);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(AccountFragment.BUNDLE_TAG);
        type = bundle.getString(AccountFragment.FOLLOW_OR_FOLLOWING_TAG);

        listView = findViewById(R.id.friends_listview);
        results = new ArrayList<>();

        adapter = new SearchFriendAdapter(this, results);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        HandlerThread handlerThread = new HandlerThread("FriendResultThread");
        handlerThread.start();

        resultsHandler = new Handler(handlerThread.getLooper());

        firebaseDatabaseHelper = new FirebaseDatabaseHelper();

        updateListView();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String userChosen = (String) parent.getItemAtPosition(position);

        if (!userChosen.equals("No Results")) {
            View dialogView = LayoutInflater.from(this).inflate(R.layout.remove_friend, null);

            TextView friendLabel = (TextView) dialogView.findViewById(R.id.remove_friend_label);
            friendLabel.setText(userChosen);

            AlertDialog friendDialog = new AlertDialog.Builder(this).setTitle("Remove friend?")
                    .setView(dialogView).create();

            friendDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Remove", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    firebaseDatabaseHelper.removeFriend(ViewFriendsActivity.this, userChosen);
                }
            });

            friendDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {

                }
            });

            friendDialog.show();
        }
    }

    private void updateListView(){
        resultsHandler.post(new Runnable() {
            @Override
            public void run() {
                if (type.equals("following")) {
                    results = FirebaseDatabaseHelper.getAllFriends(adapter, results);
                } else if (type.equals("follow")) {
                    results = FirebaseDatabaseHelper.getAllFollowers(adapter, results);
                }
            }
        });
        if(results != null){
            adapter.updateData(results);
            Log.d("jrv", Integer.toString(results.size()));
            adapter.notifyDataSetChanged();
        }
    }
}
