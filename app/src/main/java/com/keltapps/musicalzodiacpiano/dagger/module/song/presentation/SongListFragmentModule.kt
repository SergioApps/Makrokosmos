package com.keltapps.musicalzodiacpiano.dagger.module.song.presentation

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ListAdapter
import com.keltapps.musicalzodiacpiano.base.presentation.SingleLiveEvent
import com.keltapps.musicalzodiacpiano.base.presentation.view.createFactory
import com.keltapps.musicalzodiacpiano.dagger.scope.SubFragmentScope
import com.keltapps.musicalzodiacpiano.song.domain.entity.Song
import com.keltapps.musicalzodiacpiano.song.presentation.list.adapter.BlockSongListAdapter
import com.keltapps.musicalzodiacpiano.song.presentation.list.annotation.ProvideVolumeIndex
import com.keltapps.musicalzodiacpiano.song.presentation.list.factory.MakrokosmosSongItemViewModelFactory
import com.keltapps.musicalzodiacpiano.song.presentation.list.factory.MakrokosmosZodiacSignViewModelFactory
import com.keltapps.musicalzodiacpiano.song.presentation.list.factory.SongItemViewModelFactory
import com.keltapps.musicalzodiacpiano.song.presentation.list.factory.ZodiacSignViewModelFactory
import com.keltapps.musicalzodiacpiano.song.presentation.list.view.SongListFragment
import com.keltapps.musicalzodiacpiano.song.presentation.list.viewmodel.MakrokosmosSongListViewModel
import com.keltapps.musicalzodiacpiano.song.presentation.list.viewmodel.SongListViewModel
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

    @Provides
    @ProvideVolumeIndex
    fun provideVolumeIndex(fragment: SongListFragment): Int = fragment.getVolumeIndex()
}