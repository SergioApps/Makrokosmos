package com.keltapps.musicalzodiacpiano.song.presentation.detail.viewmodel

import androidx.lifecycle.LiveData
import com.keltapps.musicalzodiacpiano.audio.client.presentation.viewmodel.AudioViewModel
import com.keltapps.musicalzodiacpiano.song.presentation.ZodiacSignViewModel

interface SongDetailViewModel {
    val zodiacSignViewModel: ZodiacSignViewModel
    val mediaSeekBarViewModel: MediaSeekBarViewModel
    val zodiacSignColor: LiveData<Int>
    val title: LiveData<String>
    val subTitle: LiveData<String>
    val zodiacSignName: LiveData<String>
    val isPlaying: LiveData<Boolean>
    val audioViewModel: AudioViewModel
}