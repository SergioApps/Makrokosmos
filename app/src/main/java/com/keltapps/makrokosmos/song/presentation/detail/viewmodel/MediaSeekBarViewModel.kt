package com.keltapps.makrokosmos.song.presentation.detail.viewmodel

import androidx.lifecycle.*

interface MediaSeekBarViewModel {
    fun initialize()
    val progress: MutableLiveData<Int>
    val maxValue: LiveData<Int>
    val progressFormatted: LiveData<String>
    val duration: LiveData<String>
    fun startTracking()
    fun stopTracking()
}