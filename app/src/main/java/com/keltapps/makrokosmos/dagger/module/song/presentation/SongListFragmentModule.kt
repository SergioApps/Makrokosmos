package com.keltapps.makrokosmos.dagger.module.song.presentation

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.recyclerview.extensions.ListAdapter
import com.keltapps.makrokosmos.base.presentation.SingleLiveEvent
import com.keltapps.makrokosmos.base.presentation.view.createFactory
import com.keltapps.makrokosmos.dagger.scope.SubFragmentScope
import com.keltapps.makrokosmos.song.domain.entity.Song
import com.keltapps.makrokosmos.song.presentation.list.adapter.BlockSongListAdapter
import com.keltapps.makrokosmos.song.presentation.list.factory.MakrokosmosSongItemViewModelFactory
import com.keltapps.makrokosmos.song.presentation.list.factory.MakrokosmosZodiacSignViewModelFactory
import com.keltapps.makrokosmos.song.presentation.list.factory.SongItemViewModelFactory
import com.keltapps.makrokosmos.song.presentation.list.factory.ZodiacSignViewModelFactory
import com.keltapps.makrokosmos.song.presentation.list.view.SongListFragment
import com.keltapps.makrokosmos.song.presentation.list.viewmodel.MakrokosmosSongListViewModel
import com.keltapps.makrokosmos.song.presentation.list.viewmodel.SongListViewModel
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

    @Provides
    @SubFragmentScope
    fun provideZodiacSignViewModelFactory(
            factory: MakrokosmosZodiacSignViewModelFactory
    ): ZodiacSignViewModelFactory = factory

    @Provides
    @SubFragmentScope
    fun provideOpenSongDetailSingleLiveEvent() = SingleLiveEvent<String>()
}