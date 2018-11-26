package com.keltapps.musicalzodiacpiano.dagger.module

import android.animation.ValueAnimator
import android.content.Context
import com.keltapps.musicalzodiacpiano.R
import com.keltapps.musicalzodiacpiano.song.presentation.detail.viewmodel.MakrokosmosMediaSeekBarViewModel
import com.keltapps.musicalzodiacpiano.song.presentation.detail.viewmodel.MediaSeekBarViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


@Module
class MainSingletonModule {

    @Provides
    @Singleton
    fun provideMediaSeekBarViewModel(viewModel: MakrokosmosMediaSeekBarViewModel): MediaSeekBarViewModel {
        return viewModel
    }

    @Provides
    @Singleton
    fun provideValueAnimator() = ValueAnimator()

    @Provides
    @Singleton
    @Named("timeFormat")
    fun provideTimeFormat(@Named("applicationContext") context: Context): String {
        return context.getString(R.string.time_format)
    }
}
