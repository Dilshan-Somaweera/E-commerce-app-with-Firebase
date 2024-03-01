package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class manage_rf_products extends Activity {

    private Button managecupake;

    private Button managecategory;

    private Button vieworder;

    private Button addproducts;

    private Button removeproducts;

    public static ArrayAdapter<String> dropdownadapterremovecup;



    // Define the collection reference


    public static ArrayList<String> category_fordropdown = new ArrayList<>();

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    CollectionReference orderDetailsCollectionremovecup = firestore.collection("products");

    // Define the collection reference
    CollectionReference orderDetailsCollection = firestore.collection("Category");
    private static android.content.Context mContext;
    public static ArrayAdapter<String> dropdownadapter; //= new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_dropdown_item);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rf_manageflowers);
        retrieveOrderDetailsData();
        retrieveOrderDetailsDataforremovecup();
        dropdownadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        dropdownadapterremovecup = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);

        addproducts =(Button) findViewById(R.id.addproducts);
        removeproducts =(Button) findViewById(R.id.updateproducts);

        addproducts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addproductsgo();
                Log.d("Ordercategory", category_fordropdown.toString());



            }
        });

        removeproducts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                removeproductsgo();

            }
        });
    }

    public void addproductsgo(){
        Intent intent=new Intent(this,getproduct.class);
        startActivity(intent);
    }
    public void removeproductsgo(){
        Intent intent=new Intent(this, rf_remove_products.class);
        startActivity(intent);
    };

    public void retrieveOrderDetailsData() {
        // Retrieve all documents in the collection
        orderDetailsCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        // Access data from the document
                        //String orderId = document.getId(); // Get the document ID
                        //String  = document.getString("Address");
                        String details = document.getString("Category");
                        dropdownadapter.add(details);

                        // Use the retrieved data as needed
                        //Log.d("OrderDetails", "Document ID: " + orderId);
                        //Log.d("OrderDetails", "Email: " + email);
                        Log.d("Ordercategory", "Details: " + details);

                        // You can perform further processing or display the data here
                    }
                } else {
                    // Handle the error
                    Log.e("OrderDetails", "Error getting documents: " + task.getException());
                }
            }
        });

        FloatingActionButton managecupake= findViewById(R.id.floatingActionButton);
        FloatingActionButton managecategory= findViewById(R.id.floatingActionButton2);
        FloatingActionButton vieworder=findViewById(R.id.floatingActionButton4);

        managecupake.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //newpagego();

            }
        });

        managecategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                categorygo();

            }
        });

        vieworder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                govieworder();

            }
        });

    }

    public void retrieveOrderDetailsDataforremovecup() {
        // Retrieve all documents in the collection
        orderDetailsCollectionremovecup.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        // Access data from the document
                        //String orderId = document.getId(); // Get the document ID
                        //String  = document.getString("Address");
                        String details = document.getString("name");
                        dropdownadapterremovecup.add(details);

                        // Use the retrieved data as needed
                        //Log.d("OrderDetails", "Document ID: " + orderId);
                        //Log.d("OrderDetails", "Email: " + email);
                        Log.d("Ordercategory", "Details: " + details);

                        // You can perform further processing or display the data here
                    }
                } else {
                    // Handle the error
                    Log.e("OrderDetails", "Error getting documents: " + task.getException());
                }
            }
        });
    }

    public void newpagego(){
        Intent intent=new Intent(this, manage_rf_products.class);
        finish();
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
    public void categorygo(){
        Intent intent=new Intent(this,manage_category.class);
        finish();
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
    public void govieworder(){
        Intent intent=new Intent(this,GetOrderActivity.class);
        finish();
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

}
