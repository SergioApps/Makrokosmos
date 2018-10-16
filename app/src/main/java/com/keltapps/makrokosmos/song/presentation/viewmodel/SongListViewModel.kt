package com.keltapps.makrokosmos.song.presentation.viewmodel

import android.arch.lifecycle.LiveData
import com.keltapps.makrokosmos.song.domain.entity.Song
import com.keltapps.makrokosmos.base.viewmodel.BaseViewModel

interface SongListViewModel: BaseViewModel {
    fun initialize(volumeIndex: Int)
    fun getTitleRes(volumeIndex: Int): Int
    val cdListItems: LiveData<List<Song>>
}