package com.keltapps.makrokosmos.song.presentation.list

import android.arch.lifecycle.MutableLiveData
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.song.domain.entity.ZodiacSign
import com.keltapps.makrokosmos.song.presentation.ZodiacSignViewModel
import javax.inject.Inject

class MakrokosmosZodiacSignViewModel @Inject constructor() : ZodiacSignViewModel {

    override val zodiacSign = MutableLiveData<Int>()

    override fun initialize(zodiacSignValue: ZodiacSign) {
        zodiacSign.value = when (zodiacSignValue) {
            is ZodiacSign.Aries -> R.drawable.aries
            is ZodiacSign.Taurus -> R.drawable.taurus
            is ZodiacSign.Gemini -> R.drawable.gemini
            is ZodiacSign.Cancer -> R.drawable.cancer
            is ZodiacSign.Leo -> R.drawable.leo
            is ZodiacSign.Virgo -> R.drawable.virgo
            is ZodiacSign.Libra -> R.drawable.libra
            is ZodiacSign.Scorpio -> R.drawable.scorpio
            is ZodiacSign.Sagittarius -> R.drawable.sagittarius
            is ZodiacSign.Capricorn -> R.drawable.capricorn
            is ZodiacSign.Aquarius -> R.drawable.aquarius
            is ZodiacSign.Pisces -> R.drawable.pisces
        }
    }
}