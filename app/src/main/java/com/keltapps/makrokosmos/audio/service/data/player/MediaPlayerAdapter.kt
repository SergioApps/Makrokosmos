package com.keltapps.makrokosmos.audio.service.data.player

import android.support.v4.media.MediaMetadataCompat
import io.reactivex.Observable

interface MediaPlayerAdapter {
    fun play()
    fun pause()
    fun stop()
    fun isPlaying(): Boolean
    fun seekTo(position: Long)
    fun setVolume(volume: Float)
    fun playFromMedia(metadata: MediaMetadataCompat)
    fun onPlaybackCompleted(): Observable<Unit>
}