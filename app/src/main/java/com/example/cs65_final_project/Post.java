package com.example.cs65_final_project;

import android.content.Context;
import android.util.Log;

public class Post {

    private String postTitle, postCreator, postTime, postComments, postIngredients, postSteps;

    public Post(String title, String time, String ingredients, String steps, String comments, String creator) {
        postTitle = title;
        postCreator = creator;
        postTime = time;
        postSteps = steps;
        postIngredients = ingredients;
        postComments = comments;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostCreator() {
        return postCreator;
    }

    public String getPostTime() {
        return postTime;
    }

    public String getPostIngredients() {
        return postIngredients;
    }

    public String getPostSteps() {
        return postSteps;
    }

    public String getPostComments() {
        return postComments;
    }

    /**
     * Retrieves post information from a pre-formatted string and converts it into a post
     * @param context
     * @param stringPost
     * @param time
     * @return
     */
    public static Post parsePost(Context context, String stringPost, String time) {
        String[] postComponents = stringPost.split("\\$\\$%%\\$%\\$");
        Log.d("Recipe Finder", stringPost);
        return new Post(postComponents[0], postComponents[1], postComponents[2], postComponents[3], postComponents[4], postComponents[5]);
    }
}
