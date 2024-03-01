package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class adminpanel extends Activity {

    private Button managecupake;

    private Button managecategory;

    private Button vieworder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_panel);

        FloatingActionButton managecupake= findViewById(R.id.floatingActionButton);
        FloatingActionButton managecategory= findViewById(R.id.floatingActionButton2);
        FloatingActionButton vieworder=findViewById(R.id.floatingActionButton4);

        managecupake.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                gomanageproducts();

            }
        });

        managecategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                c_manage_class();

            }
        });

        vieworder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                g_getorder_open();

            }
        });




    }
    public void gomanageproducts(){
        Intent intent=new Intent(this, manage_rf_products.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
    public void c_manage_class(){
        Intent intent=new Intent(this,manage_category.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
    public void g_getorder_open(){
        Intent intent=new Intent(this,GetOrderActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
