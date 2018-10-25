package com.keltapps.makrokosmos.dagger.module

import android.support.v4.media.session.MediaSessionCompat
import com.keltapps.makrokosmos.audio.service.*
import com.keltapps.makrokosmos.audio.service.players.MediaPlayerAdapter
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
    fun provideMediaPlayerAdapter(
            service: MusicService,
            mediaPlayerListener: MediaPlayerListener
    ): PlayerAdapter {
        return MediaPlayerAdapter(service, mediaPlayerListener)
    }

    @Provides
    @ServiceScope
    fun provideMediaSessionToken(
            session: MediaSessionCompat
    ): MediaSessionCompat.Token {
        return session.sessionToken
    }
}