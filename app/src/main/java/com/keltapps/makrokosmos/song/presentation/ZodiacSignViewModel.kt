package com.keltapps.makrokosmos.song.presentation

import androidx.lifecycle.LiveData
import com.keltapps.makrokosmos.song.domain.entity.ZodiacSign

interface ZodiacSignViewModel {
    fun initialize(zodiacSignValue: ZodiacSign)
    val zodiacSign : LiveData<Int>
}