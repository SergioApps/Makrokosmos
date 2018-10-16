package com.keltapps.makrokosmos.base.view

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

fun <T : ViewModel> T.createFactory(): ViewModelProvider.Factory {
    val viewModel = this
    return object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModel as T
    }
}