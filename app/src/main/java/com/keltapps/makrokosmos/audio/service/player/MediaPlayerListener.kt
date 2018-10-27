package com.keltapps.makrokosmos.audio.service.player

import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.*
import android.support.v4.media.session.PlaybackStateCompat.STATE_PLAYING
import android.support.v4.media.session.PlaybackStateCompat.STATE_PAUSED
import android.support.v4.media.session.PlaybackStateCompat.STATE_STOPPED
import com.keltapps.makrokosmos.audio.service.manager.MakrokosmosServiceManager
import javax.inject.Inject

class MediaPlayerListener @Inject constructor(
        private val session: MediaSessionCompat,
        private val makrokosmosServiceManager: MakrokosmosServiceManager
) : PlaybackInfoListener {

    override fun onPlaybackStateChange(state: PlaybackStateCompat, currentMedia: MediaMetadataCompat) {
        session.setPlaybackState(state)
        with(makrokosmosServiceManager) {
            when (state.state) {
                STATE_PLAYING -> moveServiceToStartedState(state, currentMedia)
                STATE_PAUSED -> updateNotificationForPause(state, currentMedia)
                STATE_STOPPED -> moveServiceOutOfStartedState()
            }
        }
    }

    override fun onPlaybackCompleted() {
    }
}
