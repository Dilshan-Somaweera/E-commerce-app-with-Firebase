package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class cart_main extends AppCompatActivity {

    public static int totalpricecheck=0;
    private Button clearcart;

    private Button checkout;

    private static String orderdetails="";

    private static String email=MainActivity.useremailafterlogin;

    public static TextView printtotoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rf_main_cart);

        clearcart=(Button)findViewById(R.id.clearcart);
        checkout=(Button)findViewById(R.id.Checkout);
        printtotoal=findViewById(R.id.totalPrice);

        // Here, we have created new array list and added data to it
         ArrayList<CartModel> cartModelArrayList = new ArrayList<CartModel>();

        int size= rf_main_adapter.upname.size();
        calculate(size,cartModelArrayList);
        //Toast.makeText(this,""+size+"", Toast.LENGTH_SHORT).show();
        //calculate(size,cartModelArrayList);

        clearcart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cartModelArrayList.clear();
                rf_main_adapter.upname.clear();
                rf_main_adapter.upurl.clear();
                rf_main_adapter.upprice.clear();
                orderdetails="";
                totalpricecheck=0;
                printtotoal.setText("Rs 0.00");
                int size= rf_main_adapter.upname.size();
                //Toast.makeText(cart_main.this,""+size+"", Toast.LENGTH_SHORT).show();
                //Toast.makeText(cart_main.this,"clicked", Toast.LENGTH_SHORT).show();
                calculate(size,cartModelArrayList);

            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                opencheckoutclass();

            }
        });

    }
    private void calculate(int size,ArrayList<CartModel> cartModelArrayList){
        for (int i = 0; i <= size; i++) {
            if(i==0){
                //Toast.makeText(this, "Nothing to see", Toast.LENGTH_SHORT).show();
            }
            else{
                if(i==1){
                    totalpricecheck=0;
                }
                String name= rf_main_adapter.getnamecart(i-1);
                String url= rf_main_adapter.geturlcart(i-1);
                String price= rf_main_adapter.getprice(i-1);
                String price2 = price.replaceAll("[^0-9]", "");
                if (!price2.isEmpty()) {
                    totalpricecheck = totalpricecheck + Integer.parseInt(price2);
                }

                //Toast.makeText(this, ""+name+"", Toast.LENGTH_SHORT).show();
                cartModelArrayList.add(new CartModel(name,url,1,"50",price));
                orderdetails=orderdetails+name+price;



            }

        }
        if(!cartModelArrayList.isEmpty()){
            //Toast.makeText(this, "NOt empty cart", Toast.LENGTH_SHORT).show();
            printtotoal.setText("Rs "+String.valueOf(totalpricecheck));
        }

        //Toast.makeText(this, ""+totalpricecheck+"", Toast.LENGTH_SHORT).show();
        RecyclerView rf_flowerview_2 = findViewById(R.id.FlowerDisplay_inside);
        CartAdapter cartAdapter = new CartAdapter(this, cartModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //Toast.makeText(this, ""+orderdetails+"", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "To user:"+email, Toast.LENGTH_SHORT).show();

        rf_flowerview_2.setLayoutManager(linearLayoutManager);
        rf_flowerview_2.setAdapter(cartAdapter);
    }

    public void gogetproducts(){
        Intent intent=new Intent(this,getproduct.class);
        startActivity(intent);
    }

    public static String get_orderdetails(){
        return orderdetails;
    }
    public static String getemail(){
        return email;
    }



    public void opencheckoutclass(){
        Intent intent=new Intent(this,checkout.class);
        startActivity(intent);
    }

    }
