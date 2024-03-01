package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class getproduct extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText productNameEditText;
    private EditText product_Category;
    private EditText productPriceEditText;
    private Button saveButton;
    private EditText Offers;

    private ImageView imageView;

    private Uri imageUri;

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button chooseimagebtn;

    private Button uploadImage;

    private FirebaseFirestore firestore;





    private Spinner spinner;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getnewflowers);

        spinner = (Spinner) findViewById(R.id.spinner);

        // Initialize Firebase Firestore
        firestore = FirebaseFirestore.getInstance();

        //show image before upload
        imageView = findViewById(R.id.imageViewProduct);

        // Initialize UI elements
        productNameEditText = findViewById(R.id.editTextProductName);
        //product_Category = findViewById(R.id.Category);
        productPriceEditText = findViewById(R.id.editTextProductPrice);
        saveButton = findViewById(R.id.buttonSave);
        chooseimagebtn=findViewById(R.id.chooseImageButton);
        Offers=findViewById(R.id.Offers);

        //Toast.makeText(getproduct.this, "catergoryfordropdown"+manage_rf_products.dropdownadapter.toString(), Toast.LENGTH_SHORT).show();
        Log.d("Ordercategory", "Details:in get product ");

        spinner.setAdapter(manage_rf_products.dropdownadapter);

        spinner.setOnItemSelectedListener(getproduct.this);


        final connector_to_firebase_for_save_products_updated storageHelper = new connector_to_firebase_for_save_products_updated();



        // Set a click listener for the Save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri != null) {
                    storageHelper.uploadImage(getproduct.this, imageUri, imageView, new connector_to_firebase_for_save_products_updated.ImageUploadCallback() {
                        @Override
                        public void onImageUploadSuccess(String imageUrl) {
                            saveProductToFirestore(imageUrl);
                        }

                        @Override
                        public void onImageUploadFailure() {
                            Toast.makeText(getproduct.this, "Image upload failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getproduct.this, "Please choose an image", Toast.LENGTH_SHORT).show();
                }
            }
        });


        chooseimagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();

            }
        });

    }

    /*public static void checking(){
        Toast.makeText(activity.getApplicationContext(), "just testing ", Toast.LENGTH_SHORT).show();
    }*/



    //product category eka gana balanna

    private void saveProductToFirestore(String yt) {
        String name = productNameEditText.getText().toString().trim();
        String Category =spinner.getSelectedItem().toString(); //product_Category.getText().toString().trim();
        String price = productPriceEditText.getText().toString().trim();
        String P_offers=Offers.getText().toString().trim();


        // Create a new product map
        Map<String, Object> product = new HashMap<>();
        product.put("name", name);
        product.put("Category", Category);
        product.put("price", price);
        product.put("Offers",P_offers);
        Log.d("TAG", yt);
        product.put("Url",yt);


        // Add the product to Firestore
        firestore.collection("products")
                .add(product)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getproduct.this, "Product Updated Successfully", Toast.LENGTH_SHORT).show();

                            finish(); // Close the activity
                        } else {
                            // Handle the error
                            // You can add error handling code here
                        }
                    }
                });
    }

    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("TAG",spinner.getSelectedItem().toString());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void change_view(){
        Intent intent=new Intent(this,adminpanel.class);
        startActivity(intent);
    }


}
