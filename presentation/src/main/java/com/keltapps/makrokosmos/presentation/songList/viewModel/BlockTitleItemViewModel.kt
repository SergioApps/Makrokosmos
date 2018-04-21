package com.keltapps.makrokosmos.presentation.songList.viewModel

import android.arch.lifecycle.LiveData

interface BlockTitleItemViewModel {
    val title: LiveData<String>

    fun setTitle(string: String)
}