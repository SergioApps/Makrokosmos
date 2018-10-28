package com.keltapps.makrokosmos.audio.client

import com.keltapps.makrokosmos.song.domain.entity.Song
import io.reactivex.Observable

interface AudioRepository {
    fun start()
    fun play(song: Song)
    fun play()
    fun pause()
    fun stop()
    fun skipToNext()
    fun skipToPrevious()
    fun seekTo(position: Long)
    fun getSongPlaying(): Observable<Song>
    fun isPlaying(): Observable<Boolean>

}