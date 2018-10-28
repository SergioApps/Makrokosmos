package com.keltapps.makrokosmos.audio.client

import com.keltapps.makrokosmos.song.domain.entity.Song
import io.reactivex.Observable
import javax.inject.Inject

class MakrokosmosAudioRepository @Inject constructor(
        private val mediaBrowserHelper: MediaBrowserHelper
) : AudioRepository {

    override fun start(song: Song) {
        mediaBrowserHelper.onStart(song.id)
    }

    override fun playCurrent() {
        mediaBrowserHelper.getTransportControls().play()
    }

    override fun pause() {
        mediaBrowserHelper.getTransportControls().pause()
    }

    override fun stop() {
        mediaBrowserHelper.onStop()
    }

    override fun skipToNext() {
        mediaBrowserHelper.getTransportControls().skipToNext()
    }

    override fun skipToPrevious() {
        mediaBrowserHelper.getTransportControls().skipToPrevious()
    }

    override fun seekTo(position: Long) {
        mediaBrowserHelper.getTransportControls().seekTo(position)
    }

    override fun getSongPlaying(): Observable<Song> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}