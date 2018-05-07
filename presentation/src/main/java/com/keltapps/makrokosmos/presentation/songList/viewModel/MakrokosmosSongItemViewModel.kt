package com.keltapps.makrokosmos.presentation.songList.viewModel

import android.arch.lifecycle.MutableLiveData
import com.keltapps.makrokosmos.domain.entity.Song

class MakrokosmosSongItemViewModel : SongItemViewModel {

    override val subTitle = MutableLiveData<String>()
    override val title = MutableLiveData<String>()

    override fun setSong(song: Song) {
        title.value = song.title
        subTitle.value = song.subTitle
    }
}