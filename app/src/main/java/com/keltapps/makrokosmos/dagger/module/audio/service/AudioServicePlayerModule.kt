package com.keltapps.makrokosmos.dagger.module.audio.service

import android.content.Context
import android.media.AudioManager
import com.keltapps.makrokosmos.audio.service.data.player.*
import com.keltapps.makrokosmos.audio.service.data.player.audiofocus.*
import com.keltapps.makrokosmos.dagger.scope.ServiceScope
import dagger.*
import javax.inject.Named

@Module
class AudioServicePlayerModule {

    @Provides
    @ServiceScope
    fun provideAudioFocusHelper(helper: MakrokosmosAudioFocusHelper): AudioFocusHelper {
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
    fun provideAudioNoisyReceiver(audioNoisyReceiver: MakrokosmosAudioNoisyReceiver): AudioNoisyReceiver {
        return audioNoisyReceiver
    }


    @Provides
    @ServiceScope
    fun provideMediaPlayerAdapter(mediaPlayerAdapter: MakrokosmosMediaPlayerAdapter): MediaPlayerAdapter {
        return mediaPlayerAdapter
    }


    @Provides
    @ServiceScope
    fun providePlaybackInfoListener(mediaPlayerListener: MediaPlayerListener): PlaybackInfoListener {
        return mediaPlayerListener
    }

    @Provides
    @ServiceScope
    fun provideMediaPlayerStateHelper(helper: MakrokosmosMediaPlayerStateHelper): MediaPlayerStateHelper {
        return helper
    }
}