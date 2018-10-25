package com.keltapps.makrokosmos.audio.service

import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.*
import javax.inject.Inject

class MediaPlayerListener @Inject constructor(
        private val session: MediaSessionCompat,
        private val serviceManager: ServiceManager
) : PlaybackInfoListener() {


    override fun onPlaybackStateChange(state: PlaybackStateCompat, currentMedia: MediaMetadataCompat) {
        // Report the state to the MediaSession.
        session.setPlaybackState(state)

        // Manage the started state of this service.
        when (state.state) {
            PlaybackStateCompat.STATE_PLAYING -> serviceManager.moveServiceToStartedState(state, currentMedia)
            PlaybackStateCompat.STATE_PAUSED -> serviceManager.updateNotificationForPause(state, currentMedia)
            PlaybackStateCompat.STATE_STOPPED -> serviceManager.moveServiceOutOfStartedState(state)
        }
    }
}
