package com.keltapps.makrokosmos.song.presentation.list.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.base.presentation.SingleLiveEvent
import com.keltapps.makrokosmos.song.domain.entity.Element
import com.keltapps.makrokosmos.song.domain.entity.Song
import com.keltapps.makrokosmos.song.presentation.ZodiacSignViewModel

class MakrokosmosSongItemViewModel(
        override val zodiacSignViewModel: ZodiacSignViewModel,
        val openSongDetail: SingleLiveEvent<String>
) : SongItemViewModel {

    override val subTitle = MutableLiveData<String>()
    override val title = MutableLiveData<String>()
    override val backgroundElement = MutableLiveData<Int>()

    private lateinit var songId: String

    override fun setSong(song: Song) {
        zodiacSignViewModel.initialize(song.zodiacSign)
        songId = song.id
        title.value = song.title
        subTitle.value = song.zodiacSign.name
        setBackgroundElement(song.zodiacSign.element)
    }

    private fun setBackgroundElement(element: Element) {
        backgroundElement.value = when (element) {
            is Element.Air -> R.drawable.gradient_air
            is Element.Earth -> R.drawable.gradient_earth
            is Element.Fire -> R.drawable.gradient_fire
            is Element.Water -> R.drawable.gradient_water
        }
    }

    override fun openSongDetail() {
        openSongDetail.value = songId
    }
}