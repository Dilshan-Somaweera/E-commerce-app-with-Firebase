package com.example.myapplication;

public class CartModel {

    private String rf_Flower_name;
    private String rf_flower_rating;

    private String offer;

    private String price;
    private int rf_flower_image;

    private int Quantity=1;



    // Constructor
    public CartModel(String rf_Flower_name, String rf_flower_rating, int rf_flower_image, String offer, String price) {
        this.rf_Flower_name = rf_Flower_name;
        this.rf_flower_rating = rf_flower_rating;
        this.rf_flower_image = rf_flower_image;
        this.offer=offer;
        this.price=price;
    }

    // Getter and Setter
    public String getRf_Flower_name() {
        return rf_Flower_name;
    }

    public void setRf_Flower_name(String rf_Flower_name) {
        this.rf_Flower_name = rf_Flower_name;
    }

    public String getRf_flower_rating() {
        return rf_flower_rating;
    }

    public String get_offer(){
        return offer;
    }

    public String get_price(){
        return price;
    }

    public void setRf_flower_rating(String rf_flower_rating) {
        this.rf_flower_rating = rf_flower_rating;
    }

    public int getRf_flower_image() {
        return rf_flower_image;
    }

    public int getQuantity(){return Quantity;}

    public void  setQuantity(int quantity){this.Quantity=quantity;}

    public void setRf_flower_image(int rf_flower_image) {
        this.rf_flower_image = rf_flower_image;
    }
}

