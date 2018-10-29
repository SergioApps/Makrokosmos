package com.keltapps.makrokosmos.audio.service.data.manager

import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat

interface ServiceManager {
    fun moveServiceToStartedState(state: PlaybackStateCompat, currentMedia: MediaMetadataCompat)
    fun updateNotificationForPause(state: PlaybackStateCompat, currentMedia: MediaMetadataCompat)
    fun moveServiceOutOfStartedState()
}