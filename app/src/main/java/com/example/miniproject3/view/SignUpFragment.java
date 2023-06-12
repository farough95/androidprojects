package com.example.miniproject3.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.miniproject3.R;
import com.example.miniproject3.databinding.FragmentSignupBinding;
import com.example.miniproject3.model.LoginOrSignUpModel;
import com.example.miniproject3.viewmodel.LoginOrSignUpViewModel;

public class SignUpFragment extends Fragment {
    private FragmentSignupBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SignUpFragmentEventListener listener = new SignUpFragmentEventListener(getActivity());
        LoginOrSignUpModel model = new LoginOrSignUpModel();
        LoginOrSignUpViewModel viewModel = new ViewModelProvider(getActivity()).get(LoginOrSignUpViewModel.class);
        binding.setViewmodel(viewModel);
        binding.setModel(model);
        binding.setListener(listener);


    }


    public static class SignUpFragmentEventListener {


        private Context context;

        public SignUpFragmentEventListener(Context context) {
            this.context = context;
        }

        public void signUpBtnListener(View view, LoginOrSignUpViewModel viewModel, LoginOrSignUpModel model) {

            viewModel.signUp(model.getNumber(), model.getPassword(), model.getName()).observe((LifecycleOwner) context, new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    getCode(view, integer, model.getNumber());
                }
            });


        }

        public void goToLoginFragmentListener(View view) {
            Navigation.findNavController(view).navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment());
        }

        private void getCode(View view, int code, String number) {

            switch (code) {
                case 213:
                    Toast.makeText(context, "از قبل با این شماره حساب دارید", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_loginFragment);

                    break;
                case 1000:
                    Toast.makeText(context, "اتصال ناموفق بوده است", Toast.LENGTH_SHORT).show();


                    break;
                case 214:
                    Toast.makeText(context, "ثبت نام موفقیت آمیز نبود.دوباره تلاش کنید", Toast.LENGTH_SHORT).show();


                    break;
                case 215:
                    Toast.makeText(context, "ثبت نام موفقیت آمیز بود.خوش آمدید", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor=context.getSharedPreferences(LoginOrSignUpActivity.DATA_NAME,Context.MODE_PRIVATE).edit();
                    editor.putString(LoginOrSignUpActivity.DATA_NUMBER_KEY,number);
                    editor.apply();
                    context.startActivity(new Intent(context, MainActivity.class));
                    ((LoginOrSignUpActivity) context).finish();


                    break;


            }


        }


    }
}
