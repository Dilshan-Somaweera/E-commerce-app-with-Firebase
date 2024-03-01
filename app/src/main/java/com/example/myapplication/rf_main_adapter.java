package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import android.widget.Toast;


import java.util.ArrayList;

public class rf_main_adapter extends RecyclerView.Adapter<rf_main_adapter.ViewHolder> {

    private final Context context;
    private final ArrayList<rf_main_model> rfmainmodelArrayList;

    StringBuilder selectedCartDetails = new StringBuilder();

    static ArrayList<String> upname = new ArrayList<>();
    static ArrayList<String> upurl = new ArrayList<>();
    static ArrayList<String> upprice = new ArrayList<>();
    //String imageUrl = "https://firebasestorage.googleapis.com/v0/b/testing-99ef8.appspot.com/o/images%2F1694782293029.jpg?alt=media&token=a706cc9a-762b-4b7b-ab10-603595751975";

    // Constructor
    public rf_main_adapter(Context context, ArrayList<rf_main_model> rfmainmodelArrayList) {
        this.context = context;
        this.rfmainmodelArrayList = rfmainmodelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rf_flower_display_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("TAG", "check");
        // to set data to textview and imageview of each card layout

        rf_main_model model = rfmainmodelArrayList.get(position);
        Log.d("TAG", "check"+model.getRf_Flower_name_name());
        Log.d("TAG", "check"+model.get_offer());
        Log.d("TAG", "check"+model.get_price());
        Log.d("TAG", "check"+model.getRf_flower_rating());
        holder.rf_flower_name.setText(model.getRf_Flower_name_name());
        holder.offer.setText(model.get_offer());
        holder.price.setText(model.get_price());
        Log.d("TAG", "check22");
        Picasso.get().load(model.getRf_flower_rating()).into(holder.rf_flower_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log details of the selected product
                Log.d(" inside click ProductDetails", "inside click ProductDetails Flower Name: " + model.getRf_Flower_name_name());
                Log.d("ProductDetails", "Offer: " + model.get_offer());
                Log.d("ProductDetails", "Price: " + model.get_price());
                Log.d("ProductDetails", "Flower Rating: " + model.getRf_flower_rating());

                // You can also start a new activity and pass these details if needed
                 Intent intent = new Intent(context, view_product.class);

                 intent.putExtra("flower_name", model.getRf_Flower_name_name());
                 intent.putExtra("offer", model.get_offer());
                 intent.putExtra("price", model.get_price());
                 intent.putExtra("flower_rating", model.getRf_flower_rating());

                context.startActivity(intent);

            }
        });

        holder.cart.setOnClickListener(v -> {
            selectedCartDetails.setLength(0);
            selectedCartDetails.append(model.getRf_Flower_name_name()).append("\n");
            upname.add(selectedCartDetails.toString());
            selectedCartDetails.setLength(0);
            //selectedCartDetails.append("Offer: ").append(model.get_offer()).append("\n");
            selectedCartDetails.append(model.get_price()).append("\n");
            upprice.add(selectedCartDetails.toString());
            selectedCartDetails.setLength(0);
            selectedCartDetails.append(model.getRf_flower_rating()).append("\n");
            upurl.add(selectedCartDetails.toString());
            selectedCartDetails.setLength(0);
            Toast.makeText(context, "product is added to cart ", Toast.LENGTH_SHORT).show();







            // Display the details in a Toast

            //Toast.makeText(context, ""+position+"", Toast.LENGTH_SHORT).show();
            //Toast.makeText(context, upurl.get(0), Toast.LENGTH_SHORT).show();
            //Toast.makeText(context, upprice.get(position), Toast.LENGTH_SHORT).show();



        });

    }

    public static String getnamecart(int index){
        return upname.get(index);
    }

    public static String geturlcart(int index1){
        return upurl.get(index1);
    }

    public static String getprice(int index2){
        return upprice.get(index2);
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return rfmainmodelArrayList.size();
    }



    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView rf_flower_image;
        private final TextView rf_flower_name;

        private  final TextView offer;

        private final TextView price;

        private Button cart;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rf_flower_image = itemView.findViewById(R.id.Flower_image);
            rf_flower_name = itemView.findViewById(R.id.Flower_name);
            offer=itemView.findViewById(R.id.Flower_offer);
            price=itemView.findViewById(R.id.price);
            cart = itemView.findViewById(R.id.cartbutton);

        }


    }

    // Define this method outside of any specific class
    public static void addToCart() {

    }



}



