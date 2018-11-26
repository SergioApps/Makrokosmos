package com.keltapps.musicalzodiacpiano.dagger.module

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.keltapps.musicalzodiacpiano.audio.client.presentation.viewmodel.*
import com.keltapps.musicalzodiacpiano.base.presentation.view.createFactory
import com.keltapps.musicalzodiacpiano.dagger.scope.ActivityScope
import com.keltapps.musicalzodiacpiano.main.view.MainActivity
import com.keltapps.musicalzodiacpiano.main.viewmodel.*
import com.keltapps.musicalzodiacpiano.navigation.*
import dagger.*

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