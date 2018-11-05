package com.keltapps.makrokosmos.dagger.module

import android.animation.ValueAnimator
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import androidx.media.MediaBrowserServiceCompat
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.audio.client.data.*
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import com.keltapps.makrokosmos.audio.client.presentation.viewmodel.*
import com.keltapps.makrokosmos.audio.service.MusicService
import com.keltapps.makrokosmos.base.presentation.view.createFactory
import com.keltapps.makrokosmos.dagger.scope.ActivityScope
import com.keltapps.makrokosmos.main.view.MainActivity
import com.keltapps.makrokosmos.main.viewmodel.*
import com.keltapps.makrokosmos.navigation.*
import com.keltapps.makrokosmos.song.presentation.detail.viewmodel.*
import dagger.*
import javax.inject.Named

@Module
class MainModule {

    @Provides
    @ActivityScope
    fun provideContext(activity: MainActivity): Context = activity


    @Provides
    @ActivityScope
    fun provideMediaBrowserHelper(helper: MakrokosmosMediaBrowserHelper): MediaBrowserHelper {
        return helper
    }

    @Provides
    @ActivityScope
    fun provideMediaBrowserServiceClass(): Class<out MediaBrowserServiceCompat> {
        return MusicService::class.java
    }

    @Provides
    @ActivityScope
    fun provideAudioRepository(repository: MakrokosmosAudioRepository): AudioRepository = repository

    @Provides
    @ActivityScope
    fun providePlayingStateMapper(mapper: MakrokosmosPlayingStateMapper): PlayingStateMapper = mapper

    @Provides
    @ActivityScope
    fun provideMainViewModel(
            activity: MainActivity,
            viewModel: MakrokosmosMainViewModel
    ): MainViewModel {
        val viewModelFactory = viewModel.createFactory()
        return ViewModelProviders.of(activity, viewModelFactory).get(viewModel.javaClass)
    }

    @Provides
    @ActivityScope
    fun provideAudioViewModel(viewModel: MakrokosmosAudioViewModel): AudioViewModel = viewModel

    @Provides
    fun provideMediaSeekBarViewModel(viewModel: MakrokosmosMediaSeekBarViewModel): MediaSeekBarViewModel {
        return viewModel
    }

    @Provides
    fun provideValueAnimator() = ValueAnimator()

    @Provides
    @ActivityScope
    @Named("timeFormat")
    fun provideTimeFormat(activity: MainActivity): String {
        return activity.getString(R.string.time_format)
    }

    @Provides
    @ActivityScope
    fun provideNavigator(navigator: MakrokosmosNavigator): Navigator = navigator
}