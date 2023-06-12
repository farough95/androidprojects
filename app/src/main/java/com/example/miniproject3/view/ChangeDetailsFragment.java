package com.example.miniproject3.view;

import android.content.Context;
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
import com.example.miniproject3.databinding.FragmentChangeDetailsBinding;
import com.example.miniproject3.model.AccountModel;
import com.example.miniproject3.viewmodel.MainActivityViewModel;

public class ChangeDetailsFragment extends Fragment {
    private FragmentChangeDetailsBinding binding;
    private AccountModel accountModel;
    private ChangeDetailsFragmentArgs args;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = ChangeDetailsFragmentArgs.fromBundle(getArguments());
        accountModel = new AccountModel(args.getParameters()[0], args.getParameters()[1], args.getParameters()[2], args.getParameters()[3],args.getParameters()[4]);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_change_details, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setModel(accountModel);

        MainActivityViewModel viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
        ChangeDetailsFragmentListener listener = new ChangeDetailsFragmentListener(getContext());
        binding.setViewmodel(viewModel);
        binding.setListener(listener);


    }


    public static class ChangeDetailsFragmentListener {

        private Context context;

        public ChangeDetailsFragmentListener(Context context) {
            this.context = context;
        }

        public void changeSubmitBtnListener(View view, AccountModel accountModel, MainActivityViewModel viewModel) {

            viewModel.updateAccount(accountModel).observe((LifecycleOwner) context, new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {

                    getCode(view, integer);

                }
            });

        }

        private void getCode(View view, Integer integer) {
            switch (integer) {
                case 1000:
                    Toast.makeText(context, "ارتباط برقرار نشد", Toast.LENGTH_SHORT).show();
                    break;
                case 211:
                    Toast.makeText(context, "رمز عبور فعلی نادرست است", Toast.LENGTH_SHORT).show();
                    break;
                case 1001:
                    Toast.makeText(context, "عملیات موفق نبود", Toast.LENGTH_SHORT).show();
                    break;
                case 216:
                    Toast.makeText(context, "تغییرات با موفقیت اعمال شد", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).popBackStack();
                    break;


            }


        }

    }


}
