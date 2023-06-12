package com.example.miniproject3.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class ImageSetter {

    @BindingAdapter("setImage")
    public static void setImage(ImageView imageView, String imageurl) {

        Glide.with(imageView.getContext())
                .load(imageurl)
                .into(imageView);

    }
}
