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

public class FeedListViewAdapter extends ArrayAdapter<Post> {

    public FeedListViewAdapter(Context context, ArrayList<Post> posts) {
        super(context, 0, posts);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Post post = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.feed_item_textview, parent, false);
        }

        TextView postTitle = convertView.findViewById(R.id.postTitle);
        TextView postCreator = convertView.findViewById(R.id.postCreator);
        TextView postRecipe = convertView.findViewById(R.id.postRecipe);
        TextView postComments = convertView.findViewById(R.id.postComments);

        postTitle.setText(post.getPostTitle());
        postCreator.setText(post.getPostCreator());
        postRecipe.setText(post.getPostRecipe());
        postComments.setText(post.getPostComments());

        return convertView;
    }
}
