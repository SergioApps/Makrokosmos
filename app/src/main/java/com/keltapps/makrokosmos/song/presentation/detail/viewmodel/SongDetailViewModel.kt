package com.keltapps.makrokosmos.song.presentation.detail.viewmodel

import android.arch.lifecycle.LiveData
import com.keltapps.makrokosmos.song.presentation.ZodiacSignViewModel

interface SongDetailViewModel {
    fun initialize(songId: String)
    val zodiacSignViewModel: ZodiacSignViewModel
    val colorZodiacSign: LiveData<Int>
    val title: LiveData<String>
    val subTitle: LiveData<String>
    val zodiacSignName: LiveData<String>
    val duration: LiveData<String>
    fun playOrPause()
    fun skipToNext()
    fun skipToPrevious()
}