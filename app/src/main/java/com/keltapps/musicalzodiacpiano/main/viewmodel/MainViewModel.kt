package com.keltapps.musicalzodiacpiano.main.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.keltapps.musicalzodiacpiano.audio.client.presentation.viewmodel.AudioViewModel
import com.keltapps.musicalzodiacpiano.base.presentation.SingleLiveEvent
import com.keltapps.musicalzodiacpiano.song.presentation.detail.viewmodel.MediaSeekBarViewModel

interface MainViewModel : LifecycleObserver {
    val mediaSeekBarViewModel: MediaSeekBarViewModel
    val title: LiveData<String>
    val zodiacSignName: LiveData<String>
    val isPlaying: LiveData<Boolean>
    val isSongDetail: LiveData<Boolean>
    val isStopped: LiveData<Boolean>
    val openSongDetail: SingleLiveEvent<String>
    val audioViewModel: AudioViewModel
    val destination: MutableLiveData<Int>
    fun openSongDetail()
}