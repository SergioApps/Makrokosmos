package com.keltapps.makrokosmos.dagger.module.song.presentation

import android.arch.lifecycle.ViewModelProviders
import com.keltapps.makrokosmos.base.presentation.view.createFactory
import com.keltapps.makrokosmos.dagger.scope.FragmentScope
import com.keltapps.makrokosmos.song.presentation.ZodiacSignViewModel
import com.keltapps.makrokosmos.song.presentation.detail.view.SongDetailFragment
import com.keltapps.makrokosmos.song.presentation.detail.viewmodel.MakrokosmosSongDetailViewModel
import com.keltapps.makrokosmos.song.presentation.detail.viewmodel.SongDetailViewModel
import com.keltapps.makrokosmos.song.presentation.list.MakrokosmosZodiacSignViewModel
import dagger.Module
import dagger.Provides

@Module
class SongDetailModule {

    @Provides
    @FragmentScope
    fun provideSongDetailViewModel(
            fragment: SongDetailFragment,
            viewModel: MakrokosmosSongDetailViewModel
    ): SongDetailViewModel {
        val viewModelFactory = viewModel.createFactory()
        return ViewModelProviders.of(fragment, viewModelFactory).get(viewModel.javaClass)
    }

    @Provides
    @FragmentScope
    fun provideZodiacSignViewModel(viewModel: MakrokosmosZodiacSignViewModel): ZodiacSignViewModel {
        return viewModel
    }
}