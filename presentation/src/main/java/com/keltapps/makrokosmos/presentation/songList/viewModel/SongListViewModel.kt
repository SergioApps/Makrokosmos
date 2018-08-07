package com.keltapps.makrokosmos.presentation.songList.viewModel


import android.arch.lifecycle.LiveData
import com.keltapps.makrokosmos.domain.entity.Song
import com.keltapps.makrokosmos.presentation.base.viewModel.BaseViewModel

interface SongListViewModel: BaseViewModel {
    fun initialize(volumeIndex: Int)
    fun getTitleRes(volumeIndex: Int): Int
    val cdListItems: LiveData<List<Song>>
}