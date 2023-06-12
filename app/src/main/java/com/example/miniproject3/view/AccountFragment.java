package com.example.miniproject3.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.miniproject3.R;
import com.example.miniproject3.databinding.FragmentAccountBinding;
import com.example.miniproject3.model.AccountModel;
import com.example.miniproject3.viewmodel.MainActivityViewModel;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;
    private MainActivityViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences preferences = getActivity().getSharedPreferences(LoginOrSignUpActivity.DATA_NAME, Context.MODE_PRIVATE);

        AccountFragmentListener listener = new AccountFragmentListener();
        binding.setListener(listener);


        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
        viewModel.getAccountDetails(preferences.getString(LoginOrSignUpActivity.DATA_NUMBER_KEY, null))
                .observe(getViewLifecycleOwner(), new Observer<AccountModel>() {
                    @Override
                    public void onChanged(AccountModel accountModel) {
                        binding.setModel(accountModel);
                    }
                });


    }

    public static class AccountFragmentListener {


        public void changeBtnListener(View view, AccountModel accountModel) {
            String[] strings = {accountModel.getName(), accountModel.getAddress(), accountModel.getPostalcode(), accountModel.getReplacementnumber(), accountModel.getNumber()};

            Navigation.findNavController(view).navigate(AccountFragmentDirections.actionAccountFragmentToChangeDetailsFragment(strings));


        }

        public void goToPaysDetailFragment(View view){
            Navigation.findNavController(view).navigate(AccountFragmentDirections.actionAccountFragmentToPaysDetailFragment());

        }


    }


}
