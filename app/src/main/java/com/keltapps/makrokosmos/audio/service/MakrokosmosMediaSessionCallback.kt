package com.keltapps.makrokosmos.audio.service

import android.support.v4.media.*
import android.support.v4.media.session.MediaSessionCompat
import com.keltapps.makrokosmos.audio.service.player.PlayerAdapter
import java.util.*
import javax.inject.Inject

class MakrokosmosMediaSessionCallback @Inject constructor(
        private val session: MediaSessionCompat,
        private val playback: PlayerAdapter,
        private val mediaSessionCallbackPresenter: MediaSessionCallbackPresenter
) : MediaSessionCompat.Callback(), MediaSessionCallback {

    init {
        mediaSessionCallbackPresenter.attach(this)
    }

    override fun onAddQueueItem(description: MediaDescriptionCompat) {
        mediaSessionCallbackPresenter.onAddQueueItem(description)
    }

    override fun onRemoveQueueItem(description: MediaDescriptionCompat) {
        mediaSessionCallbackPresenter.onRemoveQueueItem(description)
    }

    override fun onPrepare() {
        mediaSessionCallbackPresenter.onPrepare()
    }

    override fun onPlay() {
        mediaSessionCallbackPresenter.onPlay()
    }

    override fun onStop() {
        playback.stop()
        session.isActive = false
    }

    override fun onPause() {
        playback.pause()
    }

    override fun onSkipToNext() {
        mediaSessionCallbackPresenter.onSkipToNext()
    }

    override fun onSkipToPrevious() {
        mediaSessionCallbackPresenter.onSkipToPrevious()
    }

    override fun onSeekTo(position: Long) {
        playback.seekTo(position)
    }

    override fun setQueue(queue: List<MediaSessionCompat.QueueItem>) {
        session.setQueue(queue)
    }

    override fun play(media: MediaMetadataCompat) {
        playback.playFromMedia(media)
    }

    override fun prepareMedia(media: MediaMetadataCompat) {
        session.setMetadata(media)
        if (!session.isActive) session.isActive = true
    }
}