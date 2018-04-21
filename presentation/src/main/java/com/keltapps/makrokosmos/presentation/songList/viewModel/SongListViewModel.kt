package com.keltapps.makrokosmos.presentation.songList.viewModel


import android.arch.lifecycle.LiveData
import android.databinding.ObservableField
import com.keltapps.makrokosmos.presentation.base.viewModel.BaseViewModel
import com.keltapps.makrokosmos.presentation.songList.model.CDListItem

interface SongListViewModel: BaseViewModel {
    val cdListItems: LiveData<List<CDListItem>>
    val title: LiveData<String>
}