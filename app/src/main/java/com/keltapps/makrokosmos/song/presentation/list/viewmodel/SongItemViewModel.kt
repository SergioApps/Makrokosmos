package com.keltapps.makrokosmos.song.presentation.list.viewmodel

import androidx.lifecycle.LiveData
import com.keltapps.makrokosmos.song.domain.entity.Song
import com.keltapps.makrokosmos.song.presentation.ZodiacSignViewModel

interface SongItemViewModel {
    val title: LiveData<String>
    val subTitle: LiveData<String>
    val backgroundElement: LiveData<Int>
    val zodiacSignViewModel: ZodiacSignViewModel

    fun setSong(song: Song)
    fun openSongDetail()
}