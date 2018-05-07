package com.keltapps.makrokosmos.makrokosmos.dagger.module.presentation

import android.arch.lifecycle.ViewModelProviders
import com.keltapps.makrokosmos.domain.iteractor.GetCDUseCase
import com.keltapps.makrokosmos.makrokosmos.dagger.scope.ActivityScope
import com.keltapps.makrokosmos.presentation.base.view.createFactory
import com.keltapps.makrokosmos.presentation.songList.adapter.BaseBlockSongListAdapter
import com.keltapps.makrokosmos.presentation.songList.adapter.BlockSongListAdapter
import com.keltapps.makrokosmos.presentation.songList.view.SongListActivity
import com.keltapps.makrokosmos.presentation.songList.viewModel.MakrokosmosSongListViewModel
import com.keltapps.makrokosmos.presentation.songList.viewModel.SongListViewModel
import dagger.Module
import dagger.Provides

@Module
class SongListModule {

    @ActivityScope
    @Provides
    fun provideSongListViewModel(activity: SongListActivity, getCDUseCase: GetCDUseCase): SongListViewModel {
        val viewModel = MakrokosmosSongListViewModel(getCDUseCase)
        val viewModelFactory = viewModel.createFactory()
        return ViewModelProviders.of(activity, viewModelFactory).get(viewModel.javaClass)
    }

    @ActivityScope
    @Provides
    fun provideAdapter(activity: SongListActivity): BaseBlockSongListAdapter<*> {
        return BlockSongListAdapter(activity)
    }
}
