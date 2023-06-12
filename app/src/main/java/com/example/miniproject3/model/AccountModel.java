package com.example.miniproject3.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class AccountModel extends BaseObservable {
    private String name;
    private String number;
    private String password;
    private String address;
    private String postalcode;
    private String replacementnumber;
    private String newpassword;


    public AccountModel(String name, String address, String postalcode, String replacementnumber, String number) {
        this.name = name;
        this.address = address;
        this.postalcode = postalcode;
        this.replacementnumber = replacementnumber;
        this.number = number;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
        notifyPropertyChanged(BR.number);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
    }

    @Bindable
    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
        notifyPropertyChanged(BR.postalcode);
    }

    @Bindable
    public String getReplacementnumber() {
        return replacementnumber;
    }

    public void setReplacementnumber(String replacementnumber) {
        this.replacementnumber = replacementnumber;
        notifyPropertyChanged(BR.replacementnumber);
    }

    @Bindable
    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
        notifyPropertyChanged(BR.newpassword);
    }
}
