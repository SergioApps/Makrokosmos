package com.keltapps.makrokosmos.presentation.songList.viewModel

import android.arch.lifecycle.MutableLiveData
import javax.inject.Inject

class MakrokosmosBlockTitleItemViewModel @Inject constructor() : BlockTitleItemViewModel {

    override val title = MutableLiveData<String>()

    override fun setTitle(string: String) {
        title.value = string
    }
}