package com.example.miniproject3.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.miniproject3.R;
import com.example.miniproject3.databinding.FragmentCartBinding;
import com.example.miniproject3.model.CartItemModel;
import com.example.miniproject3.utils.adapter.CartFragmentListAdapter;
import com.example.miniproject3.viewmodel.MainActivityViewModel;
import com.zarinpal.ewallets.purchase.OnCallbackRequestPaymentListener;
import com.zarinpal.ewallets.purchase.PaymentRequest;
import com.zarinpal.ewallets.purchase.ZarinPal;

import java.util.List;

public class CartFragment extends Fragment implements CartFragmentListAdapter.ProductData {

    private FragmentCartBinding binding;
    private MainActivityViewModel viewModel;
    private CartFragmentListAdapter adapter = new CartFragmentListAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
        binding.cartList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.cartList.setAdapter(adapter);
        CartFragmentEventListener listener = new CartFragmentEventListener(getActivity());
        binding.setListener(listener);


        viewModel.getCartItemsLiveData().observe(getViewLifecycleOwner(), new Observer<List<CartItemModel>>() {
            @Override
            public void onChanged(List<CartItemModel> cartItemModels) {

                adapter.setModels(cartItemModels);

            }
        });

        viewModel.getTotalPriceLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.cartTotalPrice.setText(s);
            }
        });

        adapter.setOnProductDataListener(this);

    }

    @Override
    public void plusOneProduct(int position) {

        viewModel.plusOneProduct(position);


    }

    @Override
    public void minusOneProduct(int position) {

        viewModel.minusOneProduct(position);

    }

    @Override
    public void removeProduct(int position) {

        viewModel.removeProduct(position);

    }



    public static class CartFragmentEventListener {

        private Context context;

        public CartFragmentEventListener(Context context) {
            this.context = context;
        }

        public void payBtnListener(View view) {

            ZarinPal purchase = ZarinPal.getPurchase(context);
            PaymentRequest payment1 = ZarinPal.getPaymentRequest();
            //If you will test on our sandbox, you can use it:
//            PaymentRequest payment2 = ZarinPal.getSandboxPaymentRequest();


            payment1.setMerchantID("69e82dc0-5e73-436f-b61f-d51d81a4b40a");
            payment1.setAmount(1000);
            payment1.isZarinGateEnable(true);  // If you actived `ZarinGate`, can handle payment by `ZarinGate`
            payment1.setDescription("خرید از مینی پروژه");
            payment1.setCallbackURL("return://com.example.miniproject3");     /* Your App Scheme */
            payment1.setMobile(getNumber());            /* Optional Parameters */



            purchase.startPayment(payment1, new OnCallbackRequestPaymentListener() {
                @Override
                public void onCallbackResultPaymentRequest(int status, String authority, Uri paymentGatewayUri, Intent intent) {


                    if (status == 100) {
                   /*
                   When Status is 100 Open Zarinpal PG on Browser
                   */
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, "Your Payment Failure :(", Toast.LENGTH_LONG).show();
                    }

                }
            });


        }


        private String getNumber() {
            SharedPreferences sharedPreferences = context.getSharedPreferences(LoginOrSignUpActivity.DATA_NAME, Context.MODE_PRIVATE);

            return sharedPreferences.getString(LoginOrSignUpActivity.DATA_NUMBER_KEY, null);
        }

    }

}
