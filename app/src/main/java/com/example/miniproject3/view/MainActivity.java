package com.example.miniproject3.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.miniproject3.R;
import com.example.miniproject3.databinding.ActivityMainBinding;
import com.example.miniproject3.viewmodel.MainActivityViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zarinpal.ewallets.purchase.OnCallbackVerificationPaymentListener;
import com.zarinpal.ewallets.purchase.PaymentRequest;
import com.zarinpal.ewallets.purchase.ZarinPal;

import saman.zamani.persiandate.PersianDate;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private ActivityMainBinding binding;
    private NavController navController;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        checkForPayment();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);

        navController = navHostFragment.getNavController();

        binding.myBottomNav.setOnNavigationItemSelectedListener(this);


    }

    private void checkForPayment() {
        if (getIntent() != null) {
            Uri data = getIntent().getData();

            ZarinPal.getPurchase(this).verificationPayment(data, new OnCallbackVerificationPaymentListener() {
                @Override
                public void onCallbackResultVerificationPayment(boolean isPaymentSuccess, String refID, PaymentRequest paymentRequest) {
                    if (isPaymentSuccess) {

                        viewModel = new ViewModelProvider(MainActivity.this).get(MainActivityViewModel.class);

                        viewModel.pay(refID, paymentRequest.getMobile(), paymentRequest.getAmount() + " تومان ", new PersianDate(System.currentTimeMillis()).toString());

                    } else {
                        Toast.makeText(MainActivity.this, "پرداخت ناموفق بود", Toast.LENGTH_SHORT).show();
                    }


                }
            });

        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home:
                navController.navigate(R.id.homeFragment);

                break;
            case R.id.item_account:
                navController.navigate(R.id.accountFragment);

                break;
            case R.id.item_cart:
                navController.navigate(R.id.cartFragment);

                break;


        }


        return false;
    }



}