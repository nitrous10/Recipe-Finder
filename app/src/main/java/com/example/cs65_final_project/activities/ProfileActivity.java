package com.example.cs65_final_project.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaSession2Service;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cs65_final_project.FirebaseAuthHelper;
import com.example.cs65_final_project.FirebaseDatabaseHelper;
import com.example.cs65_final_project.FirebaseStorageHelper;
import com.example.cs65_final_project.R;
import com.example.cs65_final_project.fragments.PhotoGalleryDialog;
import com.google.firebase.database.FirebaseDatabase;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    public static final String NEW_PROFILE = "new profile";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String PHOTO_GALLERY_TAG = "photo gallery tag";
    public static final String TEMP_FILE_NAME = "temp_profile_pic.jpg";
    public static final int CAMERA_CODE = 0;
    public static final int GALLERY_CODE = 0;

    private String newPassword;
    private String newEmail;
    private EditText nameEditText, bioEditText, emailEditText;
    private boolean isNew;
    private Uri tempUri;
    private CircleImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profile");
        pic = findViewById(R.id.profile_pic);
        nameEditText = findViewById(R.id.input_name);
        bioEditText = findViewById(R.id.self_introduction);
        emailEditText = findViewById(R.id.input_email);
        emailEditText.setEnabled(false);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            Log.d("Recipe Finder", "New Account");
            isNew = bundle.getBoolean(NEW_PROFILE);
            newEmail = bundle.getString(EMAIL);
            newPassword = bundle.getString(PASSWORD);
            emailEditText.setText(newEmail);
        }

        if (!isNew) {
            nameEditText.setHint("Loading");
            bioEditText.setHint("Loading");
            nameEditText.setEnabled(false);
            loadProfile();
        }
    }

    /** Change profile pic*/
    public void onPicPressed(View view){
        checkPermissions();
        PhotoGalleryDialog dialog = new PhotoGalleryDialog();
        dialog.show(getSupportFragmentManager(), PHOTO_GALLERY_TAG);
    }

    public void onSavePressed(View view){
        // Save to firebase
        if(isNew){
            //Create new account in firebase
            try {
                FirebaseAuthHelper.createUser(this, newEmail, newPassword, nameEditText.getText().toString(), bioEditText.getText().toString());
                FirebaseStorageHelper.savePicture(pic);
            } catch (Exception e) {
            }
        } else {
            try {
                FirebaseDatabaseHelper.updateProfile(this, nameEditText.getText().toString(), bioEditText.getText().toString());
                FirebaseStorageHelper.savePicture(pic);
            } catch (Exception e) {
                Toast.makeText(this, "Failed to Update Info!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onCancelPressed(View view){
        finish();
    }

    /** i = 0: Take photo, i = 1: Gallery*/
    public void onPictureMethodChosen(int i){
        File tempFile = new File(getExternalFilesDir(null), TEMP_FILE_NAME);
        tempUri = FileProvider.getUriForFile(this,
                "com.example.cs65_final_project", tempFile);
        if (i == 0){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
            startActivityForResult(intent, CAMERA_CODE);
        }
        else if(i == 1){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Select File"), GALLERY_CODE);
        }
    }

    /** Crop the photo received*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK){
            return;
        }
        if (requestCode == CAMERA_CODE){
            CropImage.activity(tempUri).start(this);
        }
        else if(requestCode == GALLERY_CODE){
            CropImage.activity(data.getData()).start(this);
        }
        else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            pic.setImageURI(result.getUri());
        }
    }

    private void loadProfile(){
        FirebaseDatabaseHelper.loadProfile(nameEditText, bioEditText, emailEditText);
        FirebaseStorageHelper.loadPicture(pic);
    }

    /** Helper class to check for necessary permissions */
    private void checkPermissions(){
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
        || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }
    }

    /** Sliding animation */
    @Override
    public void finish() {
        super.finish();
        if(isNew){
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
    }
}