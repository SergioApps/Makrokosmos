package com.keltapps.makrokosmos.presentation.songList.viewModel


import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.keltapps.makrokosmos.domain.iteractor.GetCDUseCase
import com.keltapps.makrokosmos.presentation.base.viewModel.MakrokosmosBaseViewModel
import com.keltapps.makrokosmos.presentation.songList.model.CDListItem
import com.keltapps.makrokosmos.presentation.songList.model.SongListItem
import com.keltapps.makrokosmos.presentation.songList.model.TitleListItem
import javax.inject.Inject

class MakrokosmosSongListViewModel @Inject constructor(private val cdUseCase: GetCDUseCase)
    : MakrokosmosBaseViewModel(), SongListViewModel {

    override val cdListItems = MutableLiveData<List<CDListItem>>()
    override val title = MutableLiveData<String>()

    override fun initialize() {
        super.initialize()
        val disposable = cdUseCase.execute().subscribe {
            val songListItemList = ArrayList<CDListItem>()
            for (blockSong in it.data.blockSongList) {
                songListItemList += TitleListItem(blockSong.title)
                for (song in blockSong.songList) {
                    songListItemList += SongListItem(song)
                }
            }
            cdListItems.value = songListItemList
            title.value = it.data.title
        }
        addDisposable(disposable)
    }
}