package com.example.cs65_final_project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class FirebaseStorageHelper {

    public static void savePicture(CircleImageView circleImageView) {
        Log.d("Recipe Finder", "Saving picture");
        String uid = FirebaseAuth.getInstance().getUid();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference userRef = storageReference.child(uid); // Location in firebase storage where image will be saved

        // Retrieve the image from the profile image and convert it to an uploadable format
        circleImageView.setDrawingCacheEnabled(true);
        circleImageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) circleImageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = userRef.putBytes(data); // Upload the image as a byte array
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) { // Handle unsuccessful uploads
                Log.d("Recipe Finder", "failed image");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) { // Handle successful uploads
                Log.d("Recipe Finder", "success image");
            }
        });
    }

    public static void loadPicture(CircleImageView circleImageView) {
        String uid = FirebaseAuth.getInstance().getUid();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference userRef = storageReference.child(uid); // Location in firebase storage where image is stored

        final long FIVE_MEGABYTES = 1024*1024*5; // Maximum size of file that can be downloaded
        Log.d("Recipe Finder", "Loading Picture");
        userRef.getBytes(FIVE_MEGABYTES).addOnSuccessListener(new OnSuccessListener<byte[]>() { // Retrieve the image byte array from storage
            @Override
            public void onSuccess(byte[] bytes) { // Handle successful downloads
                // Format byte array to specify image for imageView
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                circleImageView.setImageBitmap(bmp);
                Log.d("Recipe Finder", "Success Pic");
            }
        });
    }
}
