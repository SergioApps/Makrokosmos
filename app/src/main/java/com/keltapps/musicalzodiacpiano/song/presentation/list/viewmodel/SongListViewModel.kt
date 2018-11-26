package com.keltapps.musicalzodiacpiano.song.presentation.list.viewmodel

import androidx.lifecycle.LiveData
import com.keltapps.musicalzodiacpiano.song.domain.entity.Song

interface SongListViewModel {
    val cdListItems: LiveData<List<Song>>
}