package com.keltapps.musicalzodiacpiano.song.presentation.list.viewmodel

import androidx.lifecycle.LiveData
import com.keltapps.musicalzodiacpiano.song.domain.entity.Song
import com.keltapps.musicalzodiacpiano.song.presentation.ZodiacSignViewModel

interface SongItemViewModel {
    val title: LiveData<String>
    val subTitle: LiveData<String>
    val backgroundElement: LiveData<Int>
    val zodiacSignViewModel: ZodiacSignViewModel

    fun setSong(song: Song)
    fun openSongDetail()
}