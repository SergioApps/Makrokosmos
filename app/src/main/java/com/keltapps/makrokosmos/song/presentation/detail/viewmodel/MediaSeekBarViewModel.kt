package com.keltapps.makrokosmos.song.presentation.detail.viewmodel

import android.arch.lifecycle.*

interface MediaSeekBarViewModel {
    fun initialize()
    val progress: MutableLiveData<Int>
    val maxValue: LiveData<Int>
    fun startTracking()
    fun stopTracking()
}