package com.keltapps.makrokosmos.song.presentation.factory

import com.keltapps.makrokosmos.song.presentation.viewmodel.MakrokosmosSongItemViewModel
import javax.inject.Inject

class MakrokosmosSongItemViewModelFactory @Inject constructor() : SongItemViewModelFactory {
    override fun createViewModel() = MakrokosmosSongItemViewModel()
}