package com.example.miniproject3.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.miniproject3.model.AccountModel;
import com.example.miniproject3.model.CartItemModel;
import com.example.miniproject3.model.ImagesModel;
import com.example.miniproject3.model.PostsModel;
import com.example.miniproject3.model.ProductModel;
import com.example.miniproject3.model.PurchaseModel;
import com.example.miniproject3.utils.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import retrofit2.http.Field;

public class MainActivityViewModel extends ViewModel {
    private CompositeDisposable disposable = new CompositeDisposable();

    private List<CartItemModel> cartItemModels = new ArrayList<>();
    private MutableLiveData<List<CartItemModel>> cartItemsLiveData = new MutableLiveData<>();
    private MutableLiveData<String> totalPriceLiveData = new MutableLiveData<>();

    public LiveData<List<PostsModel>> getPosts() {

        return Repository.getInstance().getPosts(disposable);
    }


    public LiveData<ProductModel> getDetails(String name) {

        return Repository.getInstance().getDetails(name, disposable);

    }

    public LiveData<List<ImagesModel>> getImages(String name) {


        return Repository.getInstance().getImages(name, disposable);
    }

    public LiveData<AccountModel> getAccountDetails(String number) {

        return Repository.getInstance().getAccountDetails(disposable, number);

    }

    public LiveData<Integer> updateAccount(AccountModel model) {

        return Repository.getInstance().updateAccount(disposable, model);


    }


    public void addProductToCart(CartItemModel cartItemModel) {


        if (isProductExisted(cartItemModel)) {
            cartItemModels.add(cartItemModel);
            cartItemsLiveData.setValue(cartItemModels);
            setTotalPriceLiveData();
        }


    }

    private boolean isProductExisted(CartItemModel cartItemModel) {
        boolean isOk = true;
        for (CartItemModel model : cartItemModels) {

            if (cartItemModel.getName().equals(model.getName())) {
                isOk = false;
                break;

            }

        }
        return isOk;
    }

    public MutableLiveData<List<CartItemModel>> getCartItemsLiveData() {
        return cartItemsLiveData;
    }


    public void plusOneProduct(int position) {
        cartItemModels.get(position).setNumberOfProduct(String.valueOf(Integer.parseInt(cartItemModels.get(position).getNumberOfProduct()) + 1));

        cartItemsLiveData.setValue(cartItemModels);
        setTotalPriceLiveData();

    }

    public void minusOneProduct(int position) {
        if (Integer.parseInt(cartItemModels.get(position).getNumberOfProduct()) > 1) {
            cartItemModels.get(position).setNumberOfProduct(String.valueOf(Integer.parseInt(cartItemModels.get(position).getNumberOfProduct()) - 1));

            setTotalPriceLiveData();
            cartItemsLiveData.setValue(cartItemModels);
        }

    }

    public void removeProduct(int position) {
        cartItemModels.remove(position);
        cartItemsLiveData.setValue(cartItemModels);
        setTotalPriceLiveData();

    }


    public String getTotalPrice() {
        int totalPrice = 0;

        for (CartItemModel model : cartItemModels) {
            totalPrice += Integer.parseInt(model.getPrice()) * Integer.parseInt(model.getNumberOfProduct());

        }

        return String.valueOf(totalPrice);


    }

    public void setTotalPriceLiveData() {

        totalPriceLiveData.setValue(getTotalPrice());

    }

    public LiveData<String> getTotalPriceLiveData() {
        return totalPriceLiveData;
    }


    public LiveData<Integer> pay(String refID, String number, String price
            , String purchasedate) {
        return Repository.getInstance().pay(disposable, refID, number, price, purchasedate);

    }

    public LiveData<List<PurchaseModel>> getPaysDetail() {
        return Repository.getInstance().getPaysDetail(disposable);

    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
