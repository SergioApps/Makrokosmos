package com.keltapps.makrokosmos.audio.service.data.player

import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.*
import android.support.v4.media.session.PlaybackStateCompat.STATE_PLAYING
import android.support.v4.media.session.PlaybackStateCompat.STATE_PAUSED
import android.support.v4.media.session.PlaybackStateCompat.STATE_STOPPED
import com.keltapps.makrokosmos.audio.service.data.manager.ServiceManager
import javax.inject.Inject

class MediaPlayerListener @Inject constructor(
        private val session: MediaSessionCompat,
        private val serviceManager: ServiceManager
) : PlaybackInfoListener {

    override fun onPlaybackStateChange(state: PlaybackStateCompat, currentMedia: MediaMetadataCompat) {
        session.setPlaybackState(state)
        with(serviceManager) {
            when (state.state) {
                STATE_PLAYING -> moveServiceToStartedState(state, currentMedia)
                STATE_PAUSED -> updateNotificationForPause(state, currentMedia)
                STATE_STOPPED -> moveServiceOutOfStartedState()
            }
        }
    }

    override fun onPlaybackCompleted() {}
}
