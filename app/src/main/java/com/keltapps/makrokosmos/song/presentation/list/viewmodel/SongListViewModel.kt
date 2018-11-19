package com.keltapps.makrokosmos.song.presentation.list.viewmodel

import androidx.lifecycle.LiveData
import com.keltapps.makrokosmos.song.domain.entity.Song

interface SongListViewModel {
    val cdListItems: LiveData<List<Song>>
}