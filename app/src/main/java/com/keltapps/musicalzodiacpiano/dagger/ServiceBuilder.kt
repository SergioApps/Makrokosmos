package com.keltapps.musicalzodiacpiano.dagger

import com.keltapps.musicalzodiacpiano.audio.service.MusicService
import com.keltapps.musicalzodiacpiano.dagger.module.audio.service.*
import com.keltapps.musicalzodiacpiano.dagger.scope.ServiceScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ServiceBuilder {

    @ServiceScope
    @ContributesAndroidInjector(modules = [
        AudioServiceModule::class,
        AudioServicePlayerModule::class,
        AudioServiceSessionCallbackModule::class
    ])
    fun bindMusicService(): MusicService
}