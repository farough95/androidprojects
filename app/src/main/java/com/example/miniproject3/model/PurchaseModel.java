package com.example.miniproject3.model;

public class PurchaseModel {
    private int refID;
    private String price;
    private String purchasedate;

    public String getRefID() {
        return String.valueOf(refID);
    }

    public void setRefID(int refID) {
        this.refID = refID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPurchasedate() {
        return purchasedate;
    }

    public void setPurchasedate(String purchasedate) {
        this.purchasedate = purchasedate;
    }
}
