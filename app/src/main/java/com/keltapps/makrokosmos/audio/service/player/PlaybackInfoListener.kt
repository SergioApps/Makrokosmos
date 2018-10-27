package com.keltapps.makrokosmos.audio.service.player

import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat

interface PlaybackInfoListener {
    fun onPlaybackStateChange(state: PlaybackStateCompat, currentMedia: MediaMetadataCompat)
    fun onPlaybackCompleted()
}