package com.example.miniproject3.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.miniproject3.BR;

public class LoginOrSignUpModel extends BaseObservable {
    private String name;
    private String number;
    private String password;



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
}
