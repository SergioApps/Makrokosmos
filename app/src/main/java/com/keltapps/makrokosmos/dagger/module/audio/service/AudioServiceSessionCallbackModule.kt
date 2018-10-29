package com.keltapps.makrokosmos.dagger.module.audio.service

import android.support.v4.media.session.MediaSessionCompat
import com.keltapps.makrokosmos.audio.service.MusicService
import com.keltapps.makrokosmos.audio.service.data.sessioncallback.*
import com.keltapps.makrokosmos.dagger.scope.ServiceScope
import dagger.*

@Module
class AudioServiceSessionCallbackModule {

    @Provides
    @ServiceScope
    fun provideMediaSessionCallback(
            makrokosmosMediaSessionCallback: MakrokosmosMediaSessionCallback
    ): MediaSessionCallback {
        return makrokosmosMediaSessionCallback
    }

    @Provides
    @ServiceScope
    fun provideMediaSessionCompat(service: MusicService): MediaSessionCompat {
        return MediaSessionCompat(service, "MusicService")
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
    fun provideMediaSessionToken(session: MediaSessionCompat): MediaSessionCompat.Token {
        return session.sessionToken
    }
}