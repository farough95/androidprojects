package com.example.miniproject3.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniproject3.R;
import com.example.miniproject3.databinding.CartItemLayoutBinding;
import com.example.miniproject3.model.CartItemModel;

import java.util.ArrayList;
import java.util.List;

public class CartFragmentListAdapter extends RecyclerView.Adapter<CartFragmentListAdapter.Holder> {

    private List<CartItemModel> models = new ArrayList<>();
    private ProductData listener;

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.cart_item_layout, parent, false);

        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        CartItemModel model = models.get(position);
        holder.binding.setModel(model);

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        CartItemLayoutBinding binding;

        public Holder(@NonNull CartItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.cartItemMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.minusOneProduct(getAdapterPosition());
                    }


                }
            });


            binding.cartItemPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.plusOneProduct(getAdapterPosition());
                    }


                }
            });

            binding.cartItemRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.removeProduct(getAdapterPosition());
                    }


                }
            });


        }
    }

    public void setModels(List<CartItemModel> models) {
        this.models = models;
        notifyDataSetChanged();
    }

    public interface ProductData {
        void plusOneProduct(int position);

        void minusOneProduct(int position);

        void removeProduct(int position);


    }

    public void setOnProductDataListener(ProductData listener) {
        this.listener = listener;
    }
}
