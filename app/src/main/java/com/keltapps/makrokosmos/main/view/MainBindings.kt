package com.keltapps.makrokosmos.main.view

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.BindingAdapter

@BindingAdapter("isStopped", "isSongDetail")
fun setVisibility(view: View, isStopped: Boolean, isSongDetail: Boolean) {
    if (!isStopped && !isSongDetail) {
        view.visibility = VISIBLE
    } else {
        view.visibility = GONE
    }
}