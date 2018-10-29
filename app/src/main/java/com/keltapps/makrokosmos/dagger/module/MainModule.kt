package com.keltapps.makrokosmos.dagger.module

import android.content.Context
import android.support.v4.media.MediaBrowserServiceCompat
import com.keltapps.makrokosmos.MainActivity
import com.keltapps.makrokosmos.audio.client.data.*
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import com.keltapps.makrokosmos.audio.service.MusicService
import com.keltapps.makrokosmos.dagger.scope.ActivityScope
import dagger.*

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
}