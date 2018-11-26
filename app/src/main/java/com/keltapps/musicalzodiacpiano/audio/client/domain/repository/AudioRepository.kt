package com.keltapps.musicalzodiacpiano.audio.client.domain.repository

import com.keltapps.musicalzodiacpiano.audio.client.domain.entity.PlayingState
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