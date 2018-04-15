package com.keltapps.makrokosmos.presentation.songList.viewModel

import com.keltapps.makrokosmos.domain.entity.Song

interface SongItemViewModel {
    fun setSong(song: Song)
}