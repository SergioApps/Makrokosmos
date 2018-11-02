package com.keltapps.makrokosmos.audio.service.data.player

import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat

interface PlaybackInfoListener {
    fun onPlaybackStateChange(state: PlaybackStateCompat, currentMedia: MediaMetadataCompat?)
}