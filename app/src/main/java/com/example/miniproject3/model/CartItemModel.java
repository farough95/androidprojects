package com.example.miniproject3.model;

public class CartItemModel {
    private String name;
    private String price;
    private String imageurl;
    private String numberOfProduct;


    public CartItemModel(String name, String price, String imageurl) {
        this.name = name;
        this.price = price;
        this.imageurl = imageurl;
        numberOfProduct = "1";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getNumberOfProduct() {
        return numberOfProduct;
    }

    public void setNumberOfProduct(String numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }
}
