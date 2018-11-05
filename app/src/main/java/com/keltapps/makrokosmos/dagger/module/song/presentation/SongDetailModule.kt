package com.keltapps.makrokosmos.dagger.module.song.presentation

import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.base.presentation.view.createFactory
import com.keltapps.makrokosmos.dagger.scope.FragmentScope
import com.keltapps.makrokosmos.song.presentation.ZodiacSignViewModel
import com.keltapps.makrokosmos.song.presentation.detail.view.SongDetailFragment
import com.keltapps.makrokosmos.song.presentation.detail.viewmodel.*
import com.keltapps.makrokosmos.song.presentation.list.MakrokosmosZodiacSignViewModel
import dagger.*
import javax.inject.Named

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

    @Provides
    @FragmentScope
    @Named("airColor")
    fun provideAirColor(fragment: SongDetailFragment): Int {
        return ContextCompat.getColor(fragment.requireContext(), R.color.air_light)
    }

    @Provides
    @FragmentScope
    @Named("fireColor")
    fun provideFireColor(fragment: SongDetailFragment): Int {
        return ContextCompat.getColor(fragment.requireContext(), R.color.fire_light)
    }

    @Provides
    @FragmentScope
    @Named("earthColor")
    fun provideAEarthColor(fragment: SongDetailFragment): Int {
        return ContextCompat.getColor(fragment.requireContext(), R.color.earth_light)
    }

    @Provides
    @FragmentScope
    @Named("waterColor")
    fun provideWaterColor(fragment: SongDetailFragment): Int {
        return ContextCompat.getColor(fragment.requireContext(), R.color.water_light)
    }
}