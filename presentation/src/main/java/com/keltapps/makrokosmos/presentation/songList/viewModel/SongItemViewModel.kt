package com.keltapps.makrokosmos.presentation.songList.viewModel

import android.arch.lifecycle.LiveData
import com.keltapps.makrokosmos.domain.entity.Song

interface SongItemViewModel {
    val title: LiveData<String>
    val subTitle: LiveData<String>

    fun setSong(song: Song)
}