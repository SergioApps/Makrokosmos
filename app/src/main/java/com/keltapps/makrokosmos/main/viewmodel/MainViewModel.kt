package com.keltapps.makrokosmos.main.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.keltapps.makrokosmos.audio.client.presentation.viewmodel.AudioViewModel
import com.keltapps.makrokosmos.base.presentation.SingleLiveEvent
import com.keltapps.makrokosmos.song.presentation.detail.viewmodel.MediaSeekBarViewModel

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