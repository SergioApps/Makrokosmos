package com.keltapps.musicalzodiacpiano.song.presentation.list.factory

import com.keltapps.musicalzodiacpiano.song.presentation.list.viewmodel.SongItemViewModel

interface SongItemViewModelFactory {
    fun createViewModel(): SongItemViewModel
}