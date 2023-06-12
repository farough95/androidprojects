package com.example.miniproject3.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniproject3.R;
import com.example.miniproject3.databinding.PayItemBinding;
import com.example.miniproject3.model.PurchaseModel;

import java.util.ArrayList;
import java.util.List;

public class PaysDetailAdapter extends RecyclerView.Adapter<PaysDetailAdapter.Holder> {
    private List<PurchaseModel> models = new ArrayList<>();


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PayItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.pay_item, parent, false);


        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        PurchaseModel model = models.get(position);
        holder.binding.setModel(model);



    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        PayItemBinding binding;


        public Holder(@NonNull PayItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setModels(List<PurchaseModel> models) {
        this.models = models;
        notifyDataSetChanged();
    }
}
