package com.example.miniproject3.view;

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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.miniproject3.R;
import com.example.miniproject3.databinding.FragmentPaysDetailBinding;
import com.example.miniproject3.model.PurchaseModel;
import com.example.miniproject3.utils.adapter.PaysDetailAdapter;
import com.example.miniproject3.viewmodel.MainActivityViewModel;

import java.util.List;

public class PaysDetailFragment extends Fragment {
    private FragmentPaysDetailBinding binding;
    private MainActivityViewModel viewModel;
    private PaysDetailAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pays_detail, container, false);
        adapter = new PaysDetailAdapter();
        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.paysDetailRv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.paysDetailRv.setAdapter(adapter);


        viewModel.getPaysDetail().observe(getViewLifecycleOwner(), new Observer<List<PurchaseModel>>() {
            @Override
            public void onChanged(List<PurchaseModel> purchaseModels) {
                adapter.setModels(purchaseModels);
            }
        });

    }
}
