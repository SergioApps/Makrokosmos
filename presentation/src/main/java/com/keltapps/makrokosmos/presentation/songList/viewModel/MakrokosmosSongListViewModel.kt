package com.keltapps.makrokosmos.presentation.songList.viewModel


import android.databinding.ObservableField
import com.keltapps.makrokosmos.domain.entity.BlockSong
import com.keltapps.makrokosmos.domain.iteractor.GetCDUseCase
import com.keltapps.makrokosmos.presentation.base.viewModel.MakrokosmosBaseViewModel
import javax.inject.Inject

class MakrokosmosSongListViewModel @Inject constructor(cdUseCase: GetCDUseCase)
    : MakrokosmosBaseViewModel(), SongListViewModel {

    override val blockSongList = ObservableField<List<BlockSong>>()
    override val title = ObservableField<String>()

    init {
        val disposable = cdUseCase.execute().subscribe {
            blockSongList.set(it.data.blockSongList)
            title.set(it.data.title)
        }
        addDisposable(disposable)
    }
}