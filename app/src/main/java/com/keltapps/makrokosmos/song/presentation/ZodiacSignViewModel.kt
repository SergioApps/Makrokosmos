package com.keltapps.makrokosmos.song.presentation

import android.arch.lifecycle.LiveData
import com.keltapps.makrokosmos.song.domain.entity.ZodiacSign

interface ZodiacSignViewModel {
    fun initialize(zodiacSignValue: ZodiacSign)
    val zodiacSign : LiveData<Int>
}