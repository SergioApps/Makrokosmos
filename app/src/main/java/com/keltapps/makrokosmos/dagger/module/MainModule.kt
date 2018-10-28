package com.keltapps.makrokosmos.dagger.module

import android.content.Context
import android.support.v4.media.MediaBrowserServiceCompat
import com.keltapps.makrokosmos.MainActivity
import com.keltapps.makrokosmos.audio.service.MusicService
import com.keltapps.makrokosmos.dagger.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    @ActivityScope
    fun provideMediaBrowserServiceClass(): Class<out MediaBrowserServiceCompat> {
        return MusicService::class.java
    }

    @Provides
    @ActivityScope
    fun provideContext(activity: MainActivity): Context = activity
}