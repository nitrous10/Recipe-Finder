package com.example.cs65_final_project.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.cs65_final_project.FirebaseDatabaseHelper;
import com.example.cs65_final_project.adapters.SearchFriendAdapter;
import com.example.cs65_final_project.adapters.SearchIngredientAdapter;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.cs65_final_project.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.IOException;
import java.util.ArrayList;

public class SearchFriendsActivity extends AppCompatActivity implements ListView.OnItemClickListener,
        SearchView.OnQueryTextListener {

    private ArrayList<String> results;
    private Handler resultsHandler;
    private SearchFriendAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_friends);
        setTitle("Search for friends");

        results = new ArrayList<>();
        HandlerThread handlerThread = new HandlerThread("FriendResultThread");
        handlerThread.start();

        resultsHandler = new Handler(handlerThread.getLooper());

        SearchView searchView = findViewById(R.id.friends_search_view);
        searchView.setOnQueryTextListener(this);

        ListView listView = findViewById(R.id.friends_results_list_view);
        adapter = new SearchFriendAdapter(this, results);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String userChosen = (String) parent.getItemAtPosition(position);

        if (!userChosen.equals("No Results")) {

            View dialogView = LayoutInflater.from(this).inflate(R.layout.add_friend, null);

            TextView friendLabel = (TextView) dialogView.findViewById(R.id.new_friend_label);
            friendLabel.setText(userChosen);

            AlertDialog friendDialog = new AlertDialog.Builder(this).setTitle("Add friend?")
                    .setView(dialogView).create();

            friendDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    FirebaseDatabaseHelper.addFriend(SearchFriendsActivity.this, userChosen);
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        updateListView(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


    private void updateListView(String s){
        resultsHandler.post(new Runnable() {
            @Override
            public void run() {
                results = FirebaseDatabaseHelper.getFriendSearchResults(s, adapter, results);
            }
        });
        if(results != null){
            adapter.updateData(results);
            adapter.notifyDataSetChanged();
        }
    }
}