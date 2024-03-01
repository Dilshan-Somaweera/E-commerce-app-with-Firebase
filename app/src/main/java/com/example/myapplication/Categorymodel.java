package com.example.myapplication;

public class Categorymodel {

    private String Rf_Flower_name;
    private String offer;
    private int Rf_flower_image;

    // Constructor
    public Categorymodel(String Rf_Flower_name, String offer, int Rf_flower_image) {
        this.Rf_Flower_name = Rf_Flower_name;
        this.offer = offer;
        this.Rf_flower_image = Rf_flower_image;
    }

    // Getter and Setter
    public String getRf_Flower_name() {
        return Rf_Flower_name;
    }

    public void setRf_Flower_name(String rf_Flower_name) {
        this.Rf_Flower_name = rf_Flower_name;
    }

    public String getrf_flower_rating() {
        return offer;
    }

    public void set_rf_flower_rating(String rf_flower_rating) {
        this.offer = rf_flower_rating;
    }

    public int getRf_flower_image() {
        return Rf_flower_image;
    }

    public void setRf_flower_image(int rf_flower_image) {
        this.Rf_flower_image = rf_flower_image;
    }
}

