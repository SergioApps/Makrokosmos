package com.keltapps.musicalzodiacpiano.song.presentation

import androidx.lifecycle.LiveData
import com.keltapps.musicalzodiacpiano.song.domain.entity.ZodiacSign

interface ZodiacSignViewModel {
    fun initialize(zodiacSignValue: ZodiacSign)
    val zodiacSign : LiveData<Int>
}