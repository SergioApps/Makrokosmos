package com.keltapps.musicalzodiacpiano.audio.client.presentation.viewmodel

interface AudioViewModel {
    fun playOrPause()
    fun skipToNext()
    fun skipToPrevious()
    fun clean()
}