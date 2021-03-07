package com.example.cs65_final_project;

import android.content.Context;

public class Post {

    private String postTitle, postCreator, postRecipe, postComments;

    public Post(String title, String creator, String recipe, String comments) {
        postTitle = title;
        postCreator = creator;
        postRecipe = recipe;
        postComments = comments;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostCreator() {
        return postCreator;
    }

    public String getPostRecipe() {
        return postRecipe;
    }

    public String getPostComments() {
        return postComments;
    }

    public static Post parsePost(Context context, String stringPost) {
        String[] postComponents = stringPost.split(context.getString(R.string.postComponentSeparator));
        return new Post(postComponents[0], postComponents[1], postComponents[2], postComponents[3]);
    }
}
