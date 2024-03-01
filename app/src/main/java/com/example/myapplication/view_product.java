package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class view_product extends Activity {

    private Button addtocartfromviewproduct;
    private TextView namefromviewproduct;
    private TextView offerfromviewproduct;

    private TextView pricefromviewproduct;

    private ImageView imagefromviewproduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_product);

        addtocartfromviewproduct=(Button)findViewById(R.id.view_product_add_to_cart);
        namefromviewproduct=findViewById(R.id.view_product_name);
        offerfromviewproduct=findViewById(R.id.view_product_offer);
        pricefromviewproduct=findViewById(R.id.view_product_price);
        imagefromviewproduct=findViewById(R.id.view_product_image);

        Intent intent = getIntent();
        String productName = intent.getStringExtra("flower_name");
        String productOffer = intent.getStringExtra("offer");
        String productPrice = intent.getStringExtra("price");
        String productImage = intent.getStringExtra("flower_rating");

        namefromviewproduct.setText(productName);
        offerfromviewproduct.setText(productOffer);
        pricefromviewproduct.setText(productPrice);
        offerfromviewproduct.setMovementMethod(new ScrollingMovementMethod());
        Picasso.get().load(productImage).into(imagefromviewproduct);





    }
}
