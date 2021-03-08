package com.example.cs65_final_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cs65_final_project.Post;
import com.example.cs65_final_project.R;

import java.util.ArrayList;
import java.util.Collections;

public class FeedListViewAdapter extends ArrayAdapter<Post> {

    public FeedListViewAdapter(Context context, ArrayList<Post> posts) {
        super(context, 0, posts);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Post post = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.feed_item_textview, parent, false);
        }

        TextView feedTitle = convertView.findViewById(R.id.feedTitle);
        TextView feedCreator = convertView.findViewById(R.id.feedCreator);
        TextView feedTime = convertView.findViewById(R.id.feedTime);
        TextView feedComments = convertView.findViewById(R.id.feedComments);
        TextView feedSteps = convertView.findViewById(R.id.feedSteps);
        TextView feedIngredients = convertView.findViewById(R.id.feedIngredients);

        feedTitle.setText(post.getPostTitle());
        feedCreator.setText(post.getPostCreator());
        feedTime.setText(post.getPostTime());
        feedSteps.setText(post.getPostSteps());
        feedIngredients.setText(post.getPostIngredients());
        feedComments.setText("Comments: " + post.getPostComments());

        return convertView;
    }
}
