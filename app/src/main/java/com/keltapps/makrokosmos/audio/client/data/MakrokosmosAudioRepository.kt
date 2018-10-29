package com.keltapps.makrokosmos.audio.client.data

import android.support.v4.media.session.PlaybackStateCompat.*
import com.keltapps.makrokosmos.audio.client.domain.entity.PlayingState
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import com.keltapps.makrokosmos.song.domain.entity.Song
import io.reactivex.Observable
import javax.inject.Inject

class MakrokosmosAudioRepository @Inject constructor(
        private val mediaBrowserHelper: MediaBrowserHelper
) : AudioRepository {

    override fun start() {
        mediaBrowserHelper.onStart()
    }

    override fun play(song: Song) {
        mediaBrowserHelper.onPlay(song.id)
    }

    override fun continuePlaying() {
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

    override fun getSongIdPlaying(): Observable<String> {
        return mediaBrowserHelper.onMetadataChanged.map { it.description.mediaId }
    }

    override fun getPlayingState(): Observable<PlayingState> {
        return mediaBrowserHelper.onStateChanged.map {
            when (it) {
                STATE_SKIPPING_TO_PREVIOUS -> PlayingState.Playing
                STATE_SKIPPING_TO_NEXT -> PlayingState.Playing
                STATE_PLAYING -> PlayingState.Playing
                STATE_PAUSED -> PlayingState.Paused
                else -> PlayingState.Stopped
            }
        }
    }
}