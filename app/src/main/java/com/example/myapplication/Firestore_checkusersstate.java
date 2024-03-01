package com.example.myapplication;
import android.util.Log;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Firestore_checkusersstate {

    private static final String TAG = "Firestore_checkusersstate";
    private FirebaseFirestore firestore;
    private CollectionReference userDetailsCollection;

    public static String User_address_after_logged_in;

    public Firestore_checkusersstate() {
        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance();
        userDetailsCollection = firestore.collection("UserDetails");
    }


    public void checkUserExists(String email, String password, final UserExistenceCallback callback) {
        String address;
        userDetailsCollection
                .whereEqualTo("Email", email)
                .whereEqualTo("Password", password)
                .limit(1)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                DocumentSnapshot document = querySnapshot.getDocuments().get(0);
                                String address=document.getString("User_address");
                                User_address_after_logged_in=address;
                                String role = document.getString("Role");

                                if ("User".equals(role) || "Admin".equals(role)) {
                                    callback.onUserExists(true, role);
                                    User_address_after_logged_in=address;
                                    Log.d(TAG, "happen ");
                                } else {
                                    callback.onUserExists(true, role);
                                    User_address_after_logged_in=address;
                                    Log.d(TAG, "happening 2");
                                }
                            } else {
                                callback.onUserExists(false, null);
                                Log.d(TAG, "nothing");
                            }
                        } else {
                            Log.e(TAG, "Error checking user existence: ", task.getException());
                            callback.onUserExists(false, null);
                            Log.d(TAG, "No one");
                        }
                    }
                });
    }


    public interface UserExistenceCallback {
        void onUserExists(boolean exists, String role); // Include the user's role in the callback
    }

    public String getaddress(    ){
        return User_address_after_logged_in;
    };

}
