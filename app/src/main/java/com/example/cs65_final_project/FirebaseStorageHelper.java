package com.example.cs65_final_project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

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
        String uid = FirebaseAuth.getInstance().getUid();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference userRef = storageReference.child(uid);

        circleImageView.setDrawingCacheEnabled(true);
        circleImageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) circleImageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = userRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
            }
        });
    }

    public static void loadPicture(CircleImageView circleImageView) {
        String uid = FirebaseAuth.getInstance().getUid();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference userRef = storageReference.child(uid);

        final long ONE_MEGABYTE = 1024*1024*5;
        userRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                circleImageView.setImageBitmap(bmp);
            }
        });
    }
}
