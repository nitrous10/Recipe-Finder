package com.example.cs65_final_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs65_final_project.activities.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Executor;

public class FirebaseAuthHelper {

    /**
     * Attempts to create a user using the specified email and password
     * @param context
     * @param email
     * @param password
     */
    public static void createUser(Context context, String email, String password, String name, String bio) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult> () {
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) { // Account was successfully created
                    Log.d("RecipeFinderAuth", "Account Created!");
                    Toast.makeText(context, "Account Created!", Toast.LENGTH_SHORT).show();
                    addNewToDataBase(name, bio); // Add the user to the database of users
                    ((AppCompatActivity)(context)).finish();
                    Intent intent = new Intent(context, HomeActivity.class);
                    context.startActivity(intent);
                } else { // Account was not successfully created
                    Log.d("RecipeFinderAuth", "Sign up Failed!");
                    Toast.makeText(context, "Sign Up Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Adds user to database after having been created
     */
    public static void addNewToDataBase(String name, String bio) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        // Initialize the new user with a fridge, a list of friends, a list of allergies, and preferred units
        ref.child("users").child(uid).child("fridge").setValue("null");
        ref.child("users").child(uid).child("friends").setValue("null");
        ref.child("users").child(uid).child("allergies").setValue("null");
        ref.child("users").child(uid).child("units").setValue("imperial");
        ref.child("users").child(uid).child("feed").setValue("null");
        ref.child("users").child(uid).child("name").setValue(name);
        ref.child("users").child(uid).child("bio").setValue(bio);
    }

    /**
     * Attempts to send a reset password email to a user-submitted email
     * @param context
     * @param email
     */
    public static void sendResetPassword(Context context, String email) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) { // Reset email password successfully sent
                    Log.d("RecipeFinderAuth", "Email Sent!");
                    Toast.makeText(context, "Email Sent!", Toast.LENGTH_SHORT).show();
                    ((AppCompatActivity)(context)).finish();
                } else { // Failed to send reset email password
                    Toast.makeText(context, "Failed to Send Email!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Attempts to login using the user-submitted email and password
     * @param context
     * @param email
     * @param password
     */
    public static void login(Context context, String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) { // Successfully logged in
                    Log.d("RecipeFinderAuth", "Logged In");

                    // Display the home activity for the newly signed in user
                    Intent intent = new Intent(context, HomeActivity.class);
                    context.startActivity(intent);
                } else {  // Failed to log in with given credentials
                    Log.d("RecipeFinderAuth", "Login Failed");
                    Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void logout() {
        FirebaseAuth.getInstance().signOut();
    }


}
