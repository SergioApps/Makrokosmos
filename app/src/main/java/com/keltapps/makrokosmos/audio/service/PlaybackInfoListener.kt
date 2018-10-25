package com.keltapps.makrokosmos.audio.service

import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat

abstract class PlaybackInfoListener {

    abstract fun onPlaybackStateChange(state: PlaybackStateCompat, currentMedia: MediaMetadataCompat)

    fun onPlaybackCompleted() {}
}