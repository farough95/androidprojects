package com.example.miniproject3.utils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.miniproject3.model.AccountModel;
import com.example.miniproject3.model.ImagesModel;
import com.example.miniproject3.model.PostsModel;
import com.example.miniproject3.model.ProductModel;
import com.example.miniproject3.model.PurchaseModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Repository {
    private static final String TAG = "Repository";
    private static Repository instance = null;

    public static final Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public LiveData<List<PostsModel>> getPosts(CompositeDisposable disposable) {


        MutableLiveData<List<PostsModel>> liveData = new MutableLiveData<>();

        RetrofitInstance.getInstance().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<PostsModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<PostsModel> postsModels) {
                        liveData.setValue(postsModels);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

        return liveData;
    }


    public LiveData<Integer> login(String number, String password, CompositeDisposable disposable) {
        MutableLiveData<Integer> liveData = new MutableLiveData<>();
        RetrofitInstance.getInstance().login(number, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull Integer integer) {

                        liveData.setValue(integer);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });


        return liveData;

    }

    public LiveData<Integer> signUp(String number, String password, String name, CompositeDisposable disposable) {
        MutableLiveData<Integer> liveData = new MutableLiveData<>();
        RetrofitInstance.getInstance().signUp(number, password, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull Integer integer) {

                        liveData.setValue(integer);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });


        return liveData;

    }


    public LiveData<ProductModel> getDetails(String name, CompositeDisposable disposable) {
        MutableLiveData<ProductModel> liveData = new MutableLiveData<>();

        RetrofitInstance.getInstance().getDetails(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ProductModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull ProductModel productModel) {


                        liveData.setValue(productModel);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });


        return liveData;
    }

    public LiveData<List<ImagesModel>> getImages(String name, CompositeDisposable disposable) {
        MutableLiveData<List<ImagesModel>> liveData = new MutableLiveData<>();


        RetrofitInstance.getInstance().getImages(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ImagesModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<ImagesModel> imagesModels) {
                        liveData.setValue(imagesModels);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

        return liveData;

    }


    public LiveData<AccountModel> getAccountDetails(CompositeDisposable disposable, String number) {
        MutableLiveData<AccountModel> liveData = new MutableLiveData<>();
        RetrofitInstance.getInstance().getAccountDetails(number)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<AccountModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull AccountModel accountModel) {

                        liveData.setValue(accountModel);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });


        return liveData;


    }

    public LiveData<Integer> updateAccount(CompositeDisposable disposable, AccountModel accountModel) {
        MutableLiveData<Integer> liveData = new MutableLiveData<>();

        RetrofitInstance.getInstance().updateAccount(accountModel.getName(), accountModel.getNumber(), accountModel.getPassword()
                , accountModel.getAddress(), accountModel.getPostalcode(), accountModel.getReplacementnumber(), accountModel.getNewpassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull Integer integer) {

                        liveData.setValue(integer);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

        return liveData;

    }

    public LiveData<Integer> pay(CompositeDisposable disposable, String refID, String number, String price, String purchasedate) {
        MutableLiveData<Integer> liveData = new MutableLiveData<>();

        RetrofitInstance.getInstance().pay(refID, number, price, purchasedate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull Integer integer) {

                        liveData.setValue(integer);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

        return liveData;

    }


    public LiveData<List<PurchaseModel>> getPaysDetail(CompositeDisposable disposable) {
        MutableLiveData<List<PurchaseModel>> liveData = new MutableLiveData<>();

        RetrofitInstance.getInstance().getPaysDetail()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<PurchaseModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<PurchaseModel> purchaseModels) {
                        liveData.setValue(purchaseModels);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

        return liveData;
    }


}
