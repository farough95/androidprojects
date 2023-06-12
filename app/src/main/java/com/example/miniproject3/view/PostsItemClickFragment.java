package com.example.miniproject3.view;

import android.os.Bundle;
import android.util.Log;
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

import com.example.miniproject3.R;
import com.example.miniproject3.databinding.FragmentPostsItemClickBinding;
import com.example.miniproject3.model.CartItemModel;
import com.example.miniproject3.model.ImagesModel;
import com.example.miniproject3.model.ProductModel;
import com.example.miniproject3.utils.adapter.MyViewPagerAdapter;
import com.example.miniproject3.viewmodel.MainActivityViewModel;

import java.util.List;

public class PostsItemClickFragment extends Fragment {

    private FragmentPostsItemClickBinding binding;
    private PostsItemClickFragmentArgs args;
    private MainActivityViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts_item_click, container, false);

        args = PostsItemClickFragmentArgs.fromBundle(getArguments());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
        PostsItemClickFragmentEventListener listener = new PostsItemClickFragmentEventListener();
        binding.setListener(listener);
        binding.setViewmodel(viewModel);

        viewModel.getDetails(args.getName().trim()).observe(getViewLifecycleOwner(), new Observer<ProductModel>() {
            @Override
            public void onChanged(ProductModel productModel) {

                binding.setModel(productModel);

            }
        });
        viewModel.getImages(args.getName().trim()).observe(getViewLifecycleOwner(), new Observer<List<ImagesModel>>() {
            @Override
            public void onChanged(List<ImagesModel> imagesModels) {
                MyViewPagerAdapter adapter = new MyViewPagerAdapter(imagesModels);
                binding.productImageSlider.setAdapter(adapter);
                binding.setImageurl(imagesModels.get(0).getImageurl());


            }
        });


    }

    public class PostsItemClickFragmentEventListener {


        public void addToCartBtnListener(View view, ProductModel productModel, String imageurl, MainActivityViewModel viewModel) {


            viewModel.addProductToCart(new CartItemModel(productModel.getName(), productModel.getPrice(), imageurl));


        }

        ;


    }


}
