package com.example.miniproject3.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniproject3.R;
import com.example.miniproject3.databinding.PostItemLayoutBinding;
import com.example.miniproject3.model.PostsModel;
import com.example.miniproject3.view.HomeFragmentDirections;

import java.util.ArrayList;
import java.util.List;

public class PostsListAdapter extends RecyclerView.Adapter<PostsListAdapter.Holder> {

    private List<PostsModel> postsModels = new ArrayList<>();


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PostItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.post_item_layout, parent, false);


        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        PostsModel model = postsModels.get(position);
        holder.binding.setModel(model);


    }

    @Override
    public int getItemCount() {
        return postsModels.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        PostItemLayoutBinding binding;

        public Holder(@NonNull PostItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {

                        Navigation.findNavController(v).navigate(HomeFragmentDirections.actionHomeFragmentToPostsItemClickFragment(postsModels.get(getAdapterPosition()).getName()));

                    }
                }
            });
        }
    }

    public void setPostsModels(List<PostsModel> postsModels) {
        this.postsModels = postsModels;
        notifyDataSetChanged();
    }



}
