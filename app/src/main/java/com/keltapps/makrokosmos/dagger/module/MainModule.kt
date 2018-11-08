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
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    @ActivityScope
    fun provideContext(activity: MainActivity): Context = activity

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
    @ActivityScope
    fun provideNavigator(navigator: MakrokosmosNavigator): Navigator = navigator
}