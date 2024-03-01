package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ProductDisplay extends AppCompatActivity {

    private FirebaseFirestore firestore;
    public static  ArrayList<rf_main_model> rfmainmodelArrayList = new ArrayList<rf_main_model>();

    public static ArrayList<String> yourorderonly123456 = new ArrayList<>();
    int[] drawableResources = {R.drawable.top, R.drawable.top, R.drawable.add,R.drawable.top, R.drawable.top, R.drawable.add};

    rf_main_adapter rfmainadapter = new rf_main_adapter(this, rfmainmodelArrayList);
    //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 2);
    Set<String> printedItems = new HashSet<>();
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> http = new ArrayList<>();
    ArrayList<Categorymodel> categorymodelArrayList2 = new ArrayList<Categorymodel>();

    ArrayList<String> categorystring = new ArrayList<>();
    ArrayList<String> categorystringafterfilter = new ArrayList<>();

    CategoryAdapter categoryAdapter2 = new CategoryAdapter(this, categorymodelArrayList2);
    LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rf_flower_display);
        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton odes = findViewById(R.id.gocheckouts);
        FloatingActionButton signout = findViewById(R.id.signout);




        firestore = FirebaseFirestore.getInstance();
        get_data_from_firebase dataRetriever = new get_data_from_firebase(this);

        Categorydatagetter dataRetrievercat = new Categorydatagetter(this);
        categorymodelArrayList2.add(new Categorymodel("All","", 1));
        Log.d("TAG", "oncreate");



        dataRetrievercat.retrieveAndDisplayData("products", new Categorydatagetter.DataCallback() {


            @Override
            public void onDataRetrieved(String[] P_name, String[] P_price, String[] P_category, String[] P_offers, StringBuilder[] P_url, int itemCount) {
                for (int i = 0; i < itemCount; i++) {
                    String leng = P_name[i];
                    name.add(leng);
                    String leng2 = P_offers[i];

                    String leng3 = P_url[i].toString(); // Convert StringBuilder to String
                    http.add(leng3);

                    String leng4 = P_category[i];
                    categorystring.add(leng4);
                    String leng5 = P_price[i];
                    Log.d("TAG", "ondataretrive"+leng5);

                    rfmainmodelArrayList.add(new rf_main_model(leng,leng3,1,"Offers: "+leng2,"Rs:"+leng5));


                    //categorymodelArrayList.add(new Categorymodel(name.get(i),http.get(i), 1));

                }
                Log.d("TAG", "First for");
                RecyclerView rf_Flowerrv = findViewById(R.id.FlowerDisplay_inside);
                //GridLayoutManager layoutManager = new GridLayoutManager(this, 2); // Set span count to 2 for two elements per row
                //rf_Flowerrv.setLayoutManager(layoutManager);

                rf_Flowerrv.setLayoutManager(linearLayoutManager);
                rf_Flowerrv.setAdapter(rfmainadapter);

                //me yata for loops dekama add wenna oni Main layout eke product display weddi wenama recycler view ekak enna oni


                for (int i = 0; i < itemCount; i++) {
                    Log.d("TAG", "second for "+i);
                    String category = categorystring.get(i);
                    // Check if the item has already been printed
                    if (!printedItems.contains(category)) {
                        printedItems.add(category); // Add the item to the Set to mark it as printed

                    }
                }

                for (String item : printedItems) {
                    Log.d("TAG", "third for");
                    categorymodelArrayList2.add(new Categorymodel(item,"", 1));
                    categorystringafterfilter.add(item);
                    RecyclerView rf_flowerrv2 = findViewById(R.id.FlowerDIsplay);
                    Log.d("TAG", "third for 2");
                    rf_flowerrv2.setLayoutManager(linearLayoutManager2);
                    rf_flowerrv2.setAdapter(categoryAdapter2);
                    Log.d("TAG", "third for 3");

                    categoryAdapter2.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Log.d("TAG", "onItemclick");
                            int position2=position-1;
                            Log.d("TAG", "onItemClick: "+position);
                            //Log.d("TAG", "onItemClick: "+categorystringafterfilter.get(position2));
                            rfmainmodelArrayList.clear();



                            if(position==0){
                                dataRetrievercat.retrieveAndDisplayData("products", new Categorydatagetter.DataCallback() {
                                    @Override
                                    public void onDataRetrieved(String[] P_name, String[] P_price, String[] P_category, String[] P_offers, StringBuilder[] P_url, int itemCount) {
                                        for (int i = 0; i < itemCount; i++) {
                                            String leng = P_name[i];
                                            name.add(leng);
                                            String leng2 = P_offers[i];

                                            String leng3 = P_url[i].toString(); // Convert StringBuilder to String
                                            http.add(leng3);

                                            String leng4 = P_category[i];

                                            categorystring.add(leng4);
                                            String leng5 = P_price[i];

                                            rfmainmodelArrayList.add(new rf_main_model(leng,leng3,1,"Offers: "+leng2,"Rs:"+leng5));

                                        }
                                        rf_Flowerrv.setLayoutManager(linearLayoutManager);
                                        rf_Flowerrv.setAdapter(rfmainadapter);
                                    }
                                    @Override
                                    public void onError(Exception e) {
                                        // Handle errors here
                                    }
                                });

                            }
                            else{

                                dataRetrievercat.retrieveAndDisplayData("products", new Categorydatagetter.DataCallback() {
                                    @Override
                                    public void onDataRetrieved(String[] P_name, String[] P_price, String[] P_category, String[] P_offers, StringBuilder[] P_url, int itemCount) {
                                        for (int i = 0; i < itemCount; i++) {
                                            String leng = P_name[i];
                                            name.add(leng);
                                            String leng2 = P_offers[i];

                                            String leng3 = P_url[i].toString(); // Convert StringBuilder to String
                                            http.add(leng3);

                                            String leng4 = P_category[i];

                                            categorystring.add(leng4);
                                            String leng5 = P_price[i];

                                            Log.d("TAG", "onDataRetrieved outside if: "+categorystringafterfilter.get(position2));
                                            Log.d("TAG", "onDataRetrieved leng4: "+leng4);

                                            if(leng4.equals(categorystringafterfilter.get(position2))){
                                                Log.d("TAG", "onDataRetrieved inside if: "+categorystringafterfilter.get(position2));
                                                rfmainmodelArrayList.add(new rf_main_model(leng,leng3,1,"Offers: "+leng2,"Rs:"+leng5));
                                            }



                                        }
                                        rf_Flowerrv.setLayoutManager(linearLayoutManager);
                                        rf_Flowerrv.setAdapter(rfmainadapter);
                                    }
                                    @Override
                                    public void onError(Exception e) {
                                        // Handle errors here
                                    }
                                });


                            }





                            // Handle item click here
                            // 'position' is the position of the clicked item
                        }


                    });


                }



            }



            @Override
            public void onError(Exception e) {
                // Handle errors here
            }




        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openpage3();
            }});

        odes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opencheckoutafterorder();
            }});

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signout();
            }});







    }

    public void openpage3(){
        Intent intent=new Intent(this,cart_main.class);
        startActivity(intent);
    }

    public void opencheckoutafterorder(){
        Intent intent=new Intent(this,yourorder.class);
        startActivity(intent);
    }

    public void signout(){
        Intent intent=new Intent(this,MainActivity.class);
        finish();
        startActivity(intent);
    }





}