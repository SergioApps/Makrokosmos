package com.keltapps.makrokosmos.song.presentation.detail.viewmodel

import androidx.lifecycle.LiveData
import com.keltapps.makrokosmos.audio.client.presentation.viewmodel.AudioViewModel
import com.keltapps.makrokosmos.song.presentation.ZodiacSignViewModel

interface SongDetailViewModel {
    fun initialize(songId: String)
    val zodiacSignViewModel: ZodiacSignViewModel
    val mediaSeekBarViewModel: MediaSeekBarViewModel
    val zodiacSignColor: LiveData<Int>
    val title: LiveData<String>
    val subTitle: LiveData<String>
    val zodiacSignName: LiveData<String>
    val isPlaying: LiveData<Boolean>
    val audioViewModel: AudioViewModel
}