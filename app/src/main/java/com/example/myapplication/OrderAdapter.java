package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<OrderModel> orderList;
    private Context context;

    public OrderAdapter(Context context, List<OrderModel> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_layout, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {
        OrderModel orderModel = orderList.get(position);
        //holder.text1TextView.setText(orderModel.getText1());
        //holder.text2TextView.setText(orderModel.getText2());
        holder.text3TextView.setText(orderModel.getText3());

        // Set OnClickListener for the itemView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click, start a new intent or perform any action
                Log.d("TAG", "onClick: "+orderModel.getText2());
                String[] result = parseInputString(orderModel.getText2());
                Log.d("TAG", "address: "+result[0]);
                Log.d("TAG", "content: "+result[1]);
                Intent intent = new Intent(context,view_product.class);
                intent.putExtra("flower_name","address: "+result[0]);
                intent.putExtra("offer", result[1]);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView text1TextView;
        public TextView text2TextView;
        public TextView text3TextView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            text1TextView = itemView.findViewById(R.id.text1TextView);
            text2TextView = itemView.findViewById(R.id.text2TextView);
            text3TextView = itemView.findViewById(R.id.text3TextView);
        }
    }

    public static String[] parseInputString(String inputString) {
        String[] result = new String[2];

        int startIndex = inputString.indexOf('{');
        int endIndex = inputString.indexOf('}');

        // Extract the address part before '{'
        result[0] = inputString.substring(0, startIndex).trim();

        // Extract the content part between '{' and '}'
        result[1] = inputString.substring(startIndex + 1, endIndex).trim();

        return result;
    }

}
