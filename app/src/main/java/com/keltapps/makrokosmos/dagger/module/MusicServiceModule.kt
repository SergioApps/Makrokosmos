package com.keltapps.makrokosmos.dagger.module

import android.support.v4.media.session.MediaSessionCompat
import com.keltapps.makrokosmos.MainActivity
import com.keltapps.makrokosmos.audio.MakrokosmosMediaSessionCompatHelper
import com.keltapps.makrokosmos.audio.service.*
import com.keltapps.makrokosmos.audio.service.manager.MakrokosmosServiceManager
import com.keltapps.makrokosmos.audio.service.manager.ServiceManager
import com.keltapps.makrokosmos.audio.service.notification.MakrokosmosMediaNotificationManager
import com.keltapps.makrokosmos.audio.service.notification.MediaNotificationManager
import com.keltapps.makrokosmos.audio.service.player.MediaPlayerAdapter
import com.keltapps.makrokosmos.audio.service.player.MediaPlayerListener
import com.keltapps.makrokosmos.audio.service.player.PlaybackInfoListener
import com.keltapps.makrokosmos.audio.service.player.PlayerAdapter
import com.keltapps.makrokosmos.dagger.scope.ServiceScope
import dagger.*

@Module
class MusicServiceModule {

    @Provides
    @ServiceScope
    fun provideMediaSessionCompat(service: MusicService): MediaSessionCompat {
        return MediaSessionCompat(service, "MusicService")
    }

    @Provides
    @ServiceScope
    fun provideMediaNotificationManager(
            notificationManager: MakrokosmosMediaNotificationManager
    ): MediaNotificationManager {
        return notificationManager
    }

    @Provides
    @ServiceScope
    fun provideActivityToOpen(): Class<*> {
        return MainActivity::class.java
    }

    @Provides
    @ServiceScope
    fun provideMediaSessionCallback(
            makrokosmosMediaSessionCallback: MakrokosmosMediaSessionCallback
    ): MediaSessionCallback {
        return makrokosmosMediaSessionCallback
    }

    @Provides
    @ServiceScope
    fun provideMediaSessionCallbackPresenter(
            presenter: MakrokosmosMediaSessionCallbackPresenter
    ): MediaSessionCallbackPresenter {
        return presenter
    }

    @Provides
    @ServiceScope
    fun provideMediaSessionCompatHelper(
            helper: MakrokosmosMediaSessionCompatHelper
    ): MediaSessionCompatHelper {
        return helper
    }

    @Provides
    @ServiceScope
    fun provideMediaPlayerAdapter(
            mediaPlayerAdapter: MediaPlayerAdapter
    ): PlayerAdapter {
        return mediaPlayerAdapter
    }

    @Provides
    @ServiceScope
    fun provideMediaSessionToken(
            session: MediaSessionCompat
    ): MediaSessionCompat.Token {
        return session.sessionToken
    }

    @Provides
    @ServiceScope
    fun provideServiceManager(
            serviceManager: MakrokosmosServiceManager
    ): ServiceManager {
        return serviceManager
    }

    @Provides
    @ServiceScope
    fun providePlaybackInfoListener(
            mediaPlayerListener: MediaPlayerListener
    ): PlaybackInfoListener {
        return mediaPlayerListener
    }
}