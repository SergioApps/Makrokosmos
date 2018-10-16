package com.keltapps.makrokosmos.song.presentation.viewmodel

import android.arch.lifecycle.LiveData
import com.keltapps.makrokosmos.song.domain.entity.Song

interface SongItemViewModel {
    val title: LiveData<String>
    val subTitle: LiveData<String>
    val backgroundElement: LiveData<Int>
    val zodiacSign: LiveData<Int>

    fun setSong(song: Song)
}