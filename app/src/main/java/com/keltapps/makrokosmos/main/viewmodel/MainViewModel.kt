package com.keltapps.makrokosmos.main.viewmodel

import androidx.lifecycle.LiveData
import com.keltapps.makrokosmos.audio.client.presentation.viewmodel.AudioViewModel
import com.keltapps.makrokosmos.base.presentation.SingleLiveEvent
import com.keltapps.makrokosmos.song.presentation.detail.viewmodel.MediaSeekBarViewModel

interface MainViewModel {
    val mediaSeekBarViewModel: MediaSeekBarViewModel
    val title: LiveData<String>
    val zodiacSignName: LiveData<String>
    val isPlaying: LiveData<Boolean>
    val isVisible: LiveData<Boolean>
    val openSongDetail: SingleLiveEvent<String>
    val audioViewModel: AudioViewModel
    fun initialize()
    fun openSongDetail()
}