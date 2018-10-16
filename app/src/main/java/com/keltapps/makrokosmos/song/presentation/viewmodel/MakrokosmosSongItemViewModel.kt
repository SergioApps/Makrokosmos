package com.keltapps.makrokosmos.song.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.song.domain.entity.Element
import com.keltapps.makrokosmos.song.domain.entity.Song
import com.keltapps.makrokosmos.song.domain.entity.ZodiacSign

class MakrokosmosSongItemViewModel : SongItemViewModel {

    override val subTitle = MutableLiveData<String>()
    override val title = MutableLiveData<String>()
    override val backgroundElement = MutableLiveData<Int>()
    override val zodiacSign = MutableLiveData<Int>()

    override fun setSong(song: Song) {
        title.value = song.title
        subTitle.value = song.zodiacSign.name
        setBackgroundElement(song.zodiacSign.element)
        setZodiacSign(song.zodiacSign)

    }

    private fun setBackgroundElement(element: Element) {
        backgroundElement.value = when (element) {
            is Element.Air -> R.drawable.gradient_air
            is Element.Earth -> R.drawable.gradient_earth
            is Element.Fire -> R.drawable.gradient_fire
            is Element.Water -> R.drawable.gradient_water
        }
    }

    private fun setZodiacSign(zodiacSignValue: ZodiacSign) {
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