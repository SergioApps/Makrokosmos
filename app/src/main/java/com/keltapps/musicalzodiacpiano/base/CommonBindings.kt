package com.keltapps.musicalzodiacpiano.base

import com.bumptech.glide.Glide
import androidx.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView


@BindingAdapter("imageDrawable")
fun setImageDrawable(view: ImageView, drawable: Int) {
    Glide.with(view.context).load(drawable).into(view)
}

@BindingAdapter("backgroundDrawable")
fun setImageDrawable(view: View, drawable: Int) {
    view.background = view.context.getDrawable(drawable)
}