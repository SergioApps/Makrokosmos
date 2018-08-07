package com.keltapps.makrokosmos.makrokosmos.dagger.module.presentation

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentPagerAdapter
import com.keltapps.makrokosmos.domain.iteractor.GetCDUseCase
import com.keltapps.makrokosmos.makrokosmos.dagger.scope.ActivityScope
import com.keltapps.makrokosmos.presentation.base.view.createFactory
import com.keltapps.makrokosmos.presentation.songList.adapter.BaseBlockSongListAdapter
import com.keltapps.makrokosmos.presentation.songList.adapter.BlockSongListAdapter
import com.keltapps.makrokosmos.presentation.songList.view.PageAdapter
import com.keltapps.makrokosmos.presentation.songList.view.SongListActivity
import com.keltapps.makrokosmos.presentation.songList.viewModel.MakrokosmosSongListViewModel
import com.keltapps.makrokosmos.presentation.songList.viewModel.SongListViewModel
import dagger.Module
import dagger.Provides

@Module
class SongListModule {

    @Provides
    @ActivityScope
    fun provideSongListViewModel(activity: SongListActivity, getCDUseCase: GetCDUseCase): SongListViewModel {
        val viewModel = MakrokosmosSongListViewModel(getCDUseCase)
        val viewModelFactory = viewModel.createFactory()
        return ViewModelProviders.of(activity, viewModelFactory).get(viewModel.javaClass)
    }

    @Provides
    @ActivityScope
    fun provideAdapter(activity: SongListActivity): BaseBlockSongListAdapter<*> {
        return BlockSongListAdapter(activity)
    }

    @Provides
    @ActivityScope
    fun providePageAdapter(activity: SongListActivity): FragmentPagerAdapter {
        return PageAdapter(activity)
    }
}
