package com.example.miniproject3.utils;

import com.example.miniproject3.model.AccountModel;
import com.example.miniproject3.model.ImagesModel;
import com.example.miniproject3.model.PostsModel;
import com.example.miniproject3.model.ProductModel;
import com.example.miniproject3.model.PurchaseModel;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("Login.php")
    public Single<Integer> login(@Field("number") String number, @Field("password") String password);

    @FormUrlEncoded
    @POST("SignUp.php")
    public Single<Integer> signUp(@Field("number") String number, @Field("password") String password, @Field("name") String name);

    @GET("PostProduct.php")
    Single<List<PostsModel>> getPosts();

    @GET("Products.php")
    Single<ProductModel> getDetails(@Query("name") String name);

    @GET("Images.php")
    Single<List<ImagesModel>> getImages(@Query("name") String name);

    @GET("AccountDetails.php")
    Single<AccountModel> getAccountDetails(@Query("number") String number);

    @FormUrlEncoded
    @POST("UpdateAccount.php")
    Single<Integer> updateAccount(@Field("name") String name, @Field("number") String number, @Field("password") String password
            , @Field("address") String address, @Field("postalcode") String postalcode, @Field("replacementnumber") String replacementnumber
            , @Field("newpassword") String newpassword);


    @FormUrlEncoded
    @POST("Pay.php")
    public Single<Integer> pay(@Field("refID") String refID, @Field("number") String number, @Field("price") String price
            , @Field("purchasedate") String purchasedate);


    @GET("GetPaysDetail.php")
    Single<List<PurchaseModel>> getPaysDetail();


}
