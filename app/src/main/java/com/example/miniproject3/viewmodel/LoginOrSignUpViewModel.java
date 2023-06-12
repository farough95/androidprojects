package com.example.miniproject3.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.miniproject3.utils.Repository;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class LoginOrSignUpViewModel extends ViewModel {
    private CompositeDisposable disposable=new CompositeDisposable();


    public LiveData<Integer> login(String number,String password){

       return Repository.getInstance().login(number,password,disposable);

    }

    public LiveData<Integer> signUp(String number,String password,String name){

        return Repository.getInstance().signUp(number,password,name,disposable);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
