package com.keltapps.musicalzodiacpiano.song.presentation.list.factory

import com.keltapps.musicalzodiacpiano.base.presentation.SingleLiveEvent
import com.keltapps.musicalzodiacpiano.song.presentation.list.viewmodel.MakrokosmosSongItemViewModel
import com.keltapps.musicalzodiacpiano.song.presentation.list.viewmodel.SongItemViewModel
import javax.inject.Inject

class MakrokosmosSongItemViewModelFactory @Inject constructor(
        private val zodiacSignViewModelFactory: ZodiacSignViewModelFactory,
        private val openSongDetail: SingleLiveEvent<String>
) : SongItemViewModelFactory {
    override fun createViewModel(): SongItemViewModel {
        return MakrokosmosSongItemViewModel(
                zodiacSignViewModelFactory.createViewModel(),
                openSongDetail
        )
    }
}