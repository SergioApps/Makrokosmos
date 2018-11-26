package com.keltapps.musicalzodiacpiano.dagger.module.audio.client

import androidx.media.MediaBrowserServiceCompat
import com.keltapps.musicalzodiacpiano.audio.client.data.*
import com.keltapps.musicalzodiacpiano.audio.client.domain.repository.AudioRepository
import com.keltapps.musicalzodiacpiano.audio.service.MusicService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AudioClientModule {

    @Provides
    @Singleton
    fun provideAudioRepository(repository: MakrokosmosAudioRepository): AudioRepository = repository

    @Provides
    @Singleton
    fun providePlayingStateMapper(mapper: MakrokosmosPlayingStateMapper): PlayingStateMapper = mapper

    @Provides
    @Singleton
    fun provideMediaBrowserHelper(helper: MakrokosmosMediaBrowserHelper): MediaBrowserHelper {
        return helper
    }

    @Provides
    @Singleton
    fun provideMediaBrowserServiceClass(): Class<out MediaBrowserServiceCompat> {
        return MusicService::class.java
    }
}