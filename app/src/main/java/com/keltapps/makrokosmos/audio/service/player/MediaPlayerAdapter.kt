package com.keltapps.makrokosmos.audio.service.player

import android.support.v4.media.MediaMetadataCompat

interface MediaPlayerAdapter {
    fun play()
    fun pause()
    fun stop()
    fun isPlaying(): Boolean
    fun seekTo(position: Long)
    fun setVolume(volume: Float)
    fun playFromMedia(metadata: MediaMetadataCompat)
}