package com.keltapps.makrokosmos.song.presentation.factory

import com.keltapps.makrokosmos.song.presentation.viewmodel.SongItemViewModel

interface SongItemViewModelFactory {
    fun createViewModel(): SongItemViewModel
}