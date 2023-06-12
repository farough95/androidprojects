package com.example.miniproject3.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.miniproject3.R;
import com.example.miniproject3.databinding.ActivityLoginOrSignUpBinding;
import com.example.miniproject3.viewmodel.LoginOrSignUpViewModel;

public class LoginOrSignUpActivity extends AppCompatActivity {
    private static final String TAG = "LoginOrSignUpActivity";
    private ActivityLoginOrSignUpBinding binding;
    public static final String DATA_NAME="number";
    public static final String DATA_NUMBER_KEY="number_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences=getSharedPreferences(DATA_NAME,MODE_PRIVATE);
        if (sharedPreferences.getString(DATA_NUMBER_KEY,null)==null){
            binding = DataBindingUtil.setContentView(this, R.layout.activity_login_or_sign_up);

        }else {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }






    }
}