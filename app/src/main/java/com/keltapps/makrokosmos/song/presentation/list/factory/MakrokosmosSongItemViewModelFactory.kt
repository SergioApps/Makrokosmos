package com.keltapps.makrokosmos.song.presentation.list.factory

import com.keltapps.makrokosmos.base.presentation.SingleLiveEvent
import com.keltapps.makrokosmos.song.presentation.list.viewmodel.MakrokosmosSongItemViewModel
import com.keltapps.makrokosmos.song.presentation.list.viewmodel.SongItemViewModel
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