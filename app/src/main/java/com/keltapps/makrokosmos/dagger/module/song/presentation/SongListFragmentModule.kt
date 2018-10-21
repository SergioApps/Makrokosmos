package com.keltapps.makrokosmos.dagger.module.song.presentation

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.recyclerview.extensions.ListAdapter
import com.keltapps.makrokosmos.base.view.createFactory
import com.keltapps.makrokosmos.dagger.scope.SubFragmentScope
import com.keltapps.makrokosmos.song.domain.entity.Song
import com.keltapps.makrokosmos.song.domain.iteractor.GetCDUseCase
import com.keltapps.makrokosmos.song.presentation.adapter.BlockSongListAdapter
import com.keltapps.makrokosmos.song.presentation.factory.MakrokosmosSongItemViewModelFactory
import com.keltapps.makrokosmos.song.presentation.factory.SongItemViewModelFactory
import com.keltapps.makrokosmos.song.presentation.view.SongListFragment
import com.keltapps.makrokosmos.song.presentation.viewmodel.MakrokosmosSongListViewModel
import com.keltapps.makrokosmos.song.presentation.viewmodel.SongListViewModel
import dagger.Module
import dagger.Provides

@Module
class SongListFragmentModule {

    @Provides
    @SubFragmentScope
    fun provideSongListViewModel(
            fragment: SongListFragment,
            viewModel: MakrokosmosSongListViewModel
    ): SongListViewModel {
        val viewModelFactory = viewModel.createFactory()
        return ViewModelProviders.of(fragment, viewModelFactory).get(viewModel.javaClass)
    }

    @Provides
    @SubFragmentScope
    fun provideAdapter(
            adapter: BlockSongListAdapter
    ): ListAdapter<Song, BlockSongListAdapter.SongViewHolder> = adapter


    @Provides
    @SubFragmentScope
    fun provideSongItemViewModelFactory(
            factory: MakrokosmosSongItemViewModelFactory
    ): SongItemViewModelFactory = factory
}