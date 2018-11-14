package com.keltapps.makrokosmos.audio.client.domain.repository

import com.keltapps.makrokosmos.audio.client.domain.entity.PlayingState
import com.keltapps.makrokosmos.song.domain.entity.Song
import io.reactivex.Observable

interface AudioRepository {
    fun start()
    fun stop()
    fun play(songId: String)
    fun continuePlaying()
    fun pause()
    fun skipToNext()
    fun skipToPrevious()
    fun seekTo(position: Long)
    fun getCurrentPositionInSeconds(): Long
    fun getSongIdPlaying(): Observable<String>
    fun getPlayingState(): Observable<PlayingState>
    fun getCurrentPlayingState(): PlayingState
}