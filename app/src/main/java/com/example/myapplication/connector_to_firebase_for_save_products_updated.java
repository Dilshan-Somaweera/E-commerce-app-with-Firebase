package com.example.myapplication;


import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class connector_to_firebase_for_save_products_updated {
    private final StorageReference storageRef;

    public static String imageDownloadUri;

    public connector_to_firebase_for_save_products_updated() {
        storageRef = FirebaseStorage.getInstance().getReference();
    }

    public interface ImageUploadCallback {
        void onImageUploadSuccess(String imageUrl);

        void onImageUploadFailure();
    }

    public void uploadImage(Activity activity, Uri imageUri, final ImageView imageView, final ImageUploadCallback callback) {
        if (imageUri != null) {
            StorageReference imageRef = storageRef.child("images/" + System.currentTimeMillis() + ".jpg");

            imageRef.putFile(imageUri)
                    .addOnSuccessListener(activity, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri downloadUri) {
                                    String imageUrl = downloadUri.toString();
                                    imageDownloadUri = imageUrl;

                                    callback.onImageUploadSuccess(imageUrl);

                                    Toast.makeText(activity.getApplicationContext(), "New Product Created Successfully", Toast.LENGTH_LONG).show();

                                    if (imageView != null) {
                                        imageView.setImageURI(downloadUri);
                                    }
                                }
                            });
                        }
                    });
        } else {
            callback.onImageUploadFailure();
        }
    }

    public String geturl() {
        return imageDownloadUri;
    }
}

