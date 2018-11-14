package com.keltapps.makrokosmos.audio.client.presentation.viewmodel

import androidx.lifecycle.ViewModel

interface AudioViewModel {
    fun playOrPause()
    fun skipToNext()
    fun skipToPrevious()
    fun clean()
}