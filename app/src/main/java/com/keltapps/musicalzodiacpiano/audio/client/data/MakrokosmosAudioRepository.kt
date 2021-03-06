package com.keltapps.musicalzodiacpiano.audio.client.data

import com.keltapps.musicalzodiacpiano.audio.client.domain.entity.PlayingState
import com.keltapps.musicalzodiacpiano.audio.client.domain.repository.AudioRepository
import io.reactivex.Observable
import javax.inject.Inject

class MakrokosmosAudioRepository @Inject constructor(
        private val mediaBrowserHelper: MediaBrowserHelper,
        private val playingStateMapper: PlayingStateMapper
) : AudioRepository {

    override fun start() {
        mediaBrowserHelper.onStart()
    }

    override fun play(songId: String) {
        mediaBrowserHelper.onPlay(songId)
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

    override fun getCurrentPositionInSeconds(): Long = mediaBrowserHelper.getCurrentPosition() / 1000


    override fun getSongIdPlaying(): Observable<String> {
        return mediaBrowserHelper.onMetadataChanged.map { it.description.mediaId }
    }

    override fun getPlayingState(): Observable<PlayingState> {
        return mediaBrowserHelper.onStateChanged.map(playingStateMapper::map)
    }

    override fun getCurrentPlayingState(): PlayingState {
        return playingStateMapper.map(mediaBrowserHelper.getCurrentState())
    }
}