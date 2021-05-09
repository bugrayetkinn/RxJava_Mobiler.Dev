package com.yetkin.rxjava_mobilerdev.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.yetkin.rxjava_mobilerdev.R

@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String?) =
    Glide.with(this).load(url).placeholder(R.drawable.default_image).into(this)