package com.example.miniproject3.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;

import com.example.miniproject3.R;
import com.example.miniproject3.databinding.SliderItemBinding;
import com.example.miniproject3.model.ImagesModel;

import java.util.ArrayList;
import java.util.List;

public class MyViewPagerAdapter extends PagerAdapter {
    private List<ImagesModel> imagesModels;

    public MyViewPagerAdapter(List<ImagesModel> imagesModels) {
        this.imagesModels = imagesModels;
    }

    @Override
    public int getCount() {
        return imagesModels.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (RelativeLayout)view == object;


    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        SliderItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.slider_item, container, false);


        ImagesModel model = imagesModels.get(position);
        binding.setModel(model);
        container.addView(binding.getRoot());

        return binding.getRoot();


    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }
}
