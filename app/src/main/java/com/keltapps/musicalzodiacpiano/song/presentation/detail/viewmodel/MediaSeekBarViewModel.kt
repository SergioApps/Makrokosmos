package com.keltapps.musicalzodiacpiano.song.presentation.detail.viewmodel

import androidx.lifecycle.*

interface MediaSeekBarViewModel {
    val progress: MutableLiveData<Int>
    val maxValue: LiveData<Int>
    val progressFormatted: LiveData<String>
    val duration: LiveData<String>
    fun startTracking()
    fun stopTracking()
}