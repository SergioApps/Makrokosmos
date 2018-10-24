package com.keltapps.makrokosmos.song.presentation.detail.viewmodel

import com.keltapps.makrokosmos.base.presentation.viewmodel.MakrokosmosBaseViewModel
import com.keltapps.makrokosmos.song.presentation.ZodiacSignViewModel
import javax.inject.Inject

class MakrokosmosSongDetailViewModel @Inject constructor(
        override val zodiacSignViewModel: ZodiacSignViewModel
) : MakrokosmosBaseViewModel(), SongDetailViewModel {
    override fun initialize(songId: String) {

    }
}