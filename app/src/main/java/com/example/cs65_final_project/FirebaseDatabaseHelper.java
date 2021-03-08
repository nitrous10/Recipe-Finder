package com.example.cs65_final_project;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs65_final_project.adapters.FridgeListViewAdapter;
import com.example.cs65_final_project.adapters.SearchFriendAdapter;
import com.example.cs65_final_project.fragments.AccountFragment;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FirebaseDatabaseHelper {

    /**
     * Adds an ingredient to Firebase, incrementing the amount of the ingredient if it already exists
     * @param context
     * @param name
     * @param amount
     */
    public static void addIngredient(Context context, String name, float amount, String aisle) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        ref.child("users").child(auth.getUid()).child("fridge").child(aisle).child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) { // Ingredient already exists in the database
                    float existingAmount = snapshot.getValue(Float.class);
                    ref.child("users").child(auth.getUid()).child("fridge").child(aisle).child(name).setValue(existingAmount+amount);
                    Log.d("RecipeFinderDatabase", "Added to Existing Ingredient");
                    ((AppCompatActivity)(context)).finish();
                } else { // Ingredient does not currently exist in the database
                    ref.child("users").child(auth.getUid()).child("fridge").child(aisle).child(name).setValue(amount);
                    Log.d("RecipeFinderDatabase", "Added new Ingredient");
                    ((AppCompatActivity)(context)).finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /**
     * Gets all of the ingredients in a user's fridge
     * @param ingredients
     * @param fridgeListViewAdapter
     */
    public static void getIngredients(ArrayList<Ingredient> ingredients, FridgeListViewAdapter fridgeListViewAdapter) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        ref.child("users").child(auth.getUid()).child("fridge").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) { // Ensures that there is data to retrieve
                    for (DataSnapshot data : snapshot.getChildren()) { // Loops through each ingredient in the fridge
                        ingredients.add(new Ingredient(data.getKey(), data.getValue(Float.class)));
                    }
                    fridgeListViewAdapter.notifyDataSetChanged(); // Update the adapter to display all retrieved ingredients
                    Log.d("RecipeFinderDatabase", "Retrieved Fridge");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void getFridgeCategory(String category, ArrayList<Ingredient> ingredients, FridgeListViewAdapter fridgeListViewAdapter) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        ref.child("users").child(auth.getUid()).child("fridge").child(category).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) { // Ensures that there is data to retrieve
                    for (DataSnapshot data : snapshot.getChildren()) { // Loops through each ingredient in the fridge
                        ingredients.add(new Ingredient(data.getKey(), data.getValue(Float.class), category));
                    }
                    fridgeListViewAdapter.notifyDataSetChanged(); // Update the adapter to display all retrieved ingredients
                    Log.d("RecipeFinderDatabase", "Retrieved Fridge");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void loadProfile(EditText nameEditText, EditText bioEditText, EditText emailEditText) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("users").child(auth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nameEditText.setText(snapshot.child("name").getValue(String.class));
                bioEditText.setText(snapshot.child("bio").getValue(String.class));
                emailEditText.setText(auth.getCurrentUser().getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void loadAccount(TextView followers, TextView following, TextView name, TextView bio, ListView posts) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("users").child(auth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                followersCount(followers);
                followingCount(following);

                name.setText(snapshot.child("name").getValue(String.class));
                bio.setText(snapshot.child("bio").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public static void updateProfile(Context context, String name, String bio) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("users").child(auth.getUid()).child("name").setValue(name);
        ref.child("users").child(auth.getUid()).child("bio").setValue(bio).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context, "Profile Updated!", Toast.LENGTH_SHORT).show();
                ((AppCompatActivity)(context)).finish();
            }
        });
    }

    public static ArrayList<String> getFriendSearchResults(String query, SearchFriendAdapter searchFriendAdapter,
                                                           ArrayList<String> results) {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        ref.child("users").orderByChild("name").equalTo(query).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) { // Ensures that there is data to retrieve
                    for (DataSnapshot data : snapshot.getChildren()) {
                        results.add(data.child("name").getValue(String.class));
                    }
                } else {
                    results.add("No Results");
                }

                searchFriendAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return results;
    }

    public static void addFriend(Context context, String name, String myName) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        ref.child("users").child(auth.getUid()).child("following").child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ref.child("users").child(auth.getUid()).child("following").child(name).setValue(name);
                ((AppCompatActivity)(context)).finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ref.child("users").orderByChild("name").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String fullObj = snapshot.getValue().toString();
                String[] fullObjParts = fullObj.split("=");
                String followedID = fullObjParts[0].replace("{", "");
                ref.child("users").child(followedID).child("followers").child(myName)
                        .setValue(myName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void getName(TextView name) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("users").child(auth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name.setText(snapshot.child("name").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static ArrayList<String> getAllFriends(SearchFriendAdapter searchFriendAdapter,
                                     ArrayList<String> results) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("users").child(auth.getUid()).child("following").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) { // Ensures that there is data to retrieve
                    for (DataSnapshot data : snapshot.getChildren()) {
                        results.add(data.getValue(String.class));
                    }
                } else {
                    results.add("No Results");
                }

                searchFriendAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return results;
    }

    public static ArrayList<String> getAllPosts(SearchFriendAdapter searchFriendAdapter,
                                                  ArrayList<String> results) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("users").child(auth.getUid()).child("posts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) { // Ensures that there is data to retrieve
                    for (DataSnapshot data : snapshot.getChildren()) {
                        results.add(data.getValue(String.class));
                    }
                } else {
                    results.add("No Posts");
                }

                searchFriendAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return results;
    }

    public static void followersCount(TextView textView) {
        Log.d("jrv", "there");
        FirebaseAuth auth = FirebaseAuth.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("users").child(auth.getUid()).child("followers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) { // Ensures that there is data to retrieve
                    for (DataSnapshot data : snapshot.getChildren()) {
                        String previous = textView.getText().toString();
                        String[] previousParts = previous.split(" ");
                        int newNumber = Integer.parseInt(previousParts[1]) + 1;

                        String following_str = "Followers: ";
                        String following_label = following_str + Integer.toString(newNumber);

                        textView.setText(following_label);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void followingCount(TextView textView) {
        Log.d("jrv", "here");
        FirebaseAuth auth = FirebaseAuth.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("users").child(auth.getUid()).child("following").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) { // Ensures that there is data to retrieve
                    for (DataSnapshot data : snapshot.getChildren()) {

                        String previous = textView.getText().toString();
                        String[] previousParts = previous.split(" ");
                        int newNumber = Integer.parseInt(previousParts[1]) + 1;

                        String following_str = "Following: ";
                        String following_label = following_str + Integer.toString(newNumber);

                        textView.setText(following_label);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void removeFriend(Context context, String name) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        ref.child("users").child(auth.getUid()).child("following").child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ref.child("users").child(auth.getUid()).child("following").child(name).removeValue();
                ((AppCompatActivity)(context)).finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
