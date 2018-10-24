package com.keltapps.makrokosmos.song.presentation.list.viewmodel

import android.arch.lifecycle.LiveData
import com.keltapps.makrokosmos.base.presentation.SingleLiveEvent
import com.keltapps.makrokosmos.song.domain.entity.Song

interface SongListViewModel {
    fun initialize(volumeIndex: Int)
    val cdListItems: LiveData<List<Song>>
}