package com.keltapps.makrokosmos.song.presentation.list.factory

import com.keltapps.makrokosmos.song.presentation.list.viewmodel.SongItemViewModel

interface SongItemViewModelFactory {
    fun createViewModel(): SongItemViewModel
}