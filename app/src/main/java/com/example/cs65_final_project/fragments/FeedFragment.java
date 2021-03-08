package com.example.cs65_final_project.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.cs65_final_project.FirebaseDatabaseHelper;
import com.example.cs65_final_project.Post;
import com.example.cs65_final_project.R;
import com.example.cs65_final_project.adapters.FeedListViewAdapter;

import java.util.ArrayList;

public class FeedFragment extends Fragment {

    private ListView listView;
    private ArrayList<Post> posts;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        listView = view.findViewById(R.id.feed_List_View);

        posts = new ArrayList<Post>();
        FeedListViewAdapter feedAdapter = new FeedListViewAdapter(getActivity(), posts);
        listView.setAdapter(feedAdapter);

        FirebaseDatabaseHelper.getFeed(getContext(), posts, feedAdapter);
        return view;
    }
}
