package com.keltapps.makrokosmos.presentation.songList.viewModel


import android.databinding.ObservableField
import com.keltapps.makrokosmos.domain.entity.BlockSong
import com.keltapps.makrokosmos.presentation.base.viewModel.BaseViewModel

interface SongListViewModel: BaseViewModel {
    val blockSongList: ObservableField<List<BlockSong>>
    val title: ObservableField<String>
}