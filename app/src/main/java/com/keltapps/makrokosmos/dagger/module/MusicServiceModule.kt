package com.keltapps.makrokosmos.dagger.module

import android.content.Context
import android.media.AudioManager
import android.support.v4.media.session.MediaSessionCompat
import com.keltapps.makrokosmos.MainActivity
import com.keltapps.makrokosmos.audio.MakrokosmosMediaSessionCompatHelper
import com.keltapps.makrokosmos.audio.service.*
import com.keltapps.makrokosmos.audio.service.manager.MakrokosmosServiceManager
import com.keltapps.makrokosmos.audio.service.manager.ServiceManager
import com.keltapps.makrokosmos.audio.service.notification.MakrokosmosMediaNotificationManager
import com.keltapps.makrokosmos.audio.service.notification.MediaNotificationManager
import com.keltapps.makrokosmos.audio.service.player.*
import com.keltapps.makrokosmos.audio.service.player.audiofocus.AudioFocusHelper
import com.keltapps.makrokosmos.audio.service.player.audiofocus.MakrokosmosAudioFocusHelper
import com.keltapps.makrokosmos.dagger.scope.ServiceScope
import dagger.*
import javax.inject.Named

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
            mediaPlayerAdapter: MakrokosmosMediaPlayerAdapter
    ): MediaPlayerAdapter {
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

    @Provides
    @ServiceScope
    fun provideAudioFocusHelper(
            helper: MakrokosmosAudioFocusHelper
    ): AudioFocusHelper {
        return helper
    }

    @Provides
    @ServiceScope
    fun provideAudioManager(
            @Named("applicationContext") applicationContext: Context
    ): AudioManager {
        return applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    }

    @Provides
    @ServiceScope
    fun provideAudioNoisyReceiver(
            audioNoisyReceiver: MakrokosmosAudioNoisyReceiver
    ): AudioNoisyReceiver {
        return audioNoisyReceiver
    }

    @Provides
    @ServiceScope
    fun provideMediaPlayerStateHelper(
            helper: MakrokosmosMediaPlayerStateHelper
    ): MediaPlayerStateHelper {
        return helper
    }
}