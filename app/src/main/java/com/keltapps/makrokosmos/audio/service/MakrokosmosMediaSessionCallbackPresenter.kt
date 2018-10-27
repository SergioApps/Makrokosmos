package com.keltapps.makrokosmos.audio.service

import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import com.keltapps.makrokosmos.audio.domain.repository.MusicLibraryRepository
import java.util.ArrayList
import javax.inject.Inject

class MakrokosmosMediaSessionCallbackPresenter @Inject constructor(
        private val mediaSessionCompatHelper: MediaSessionCompatHelper,
        private val musicLibraryRepository: MusicLibraryRepository
) : MediaSessionCallbackPresenter {

    private lateinit var mediaSessionCallback: MediaSessionCallback
    private val playlist = ArrayList<MediaSessionCompat.QueueItem>()
    var queueIndex = -1
    private var preparedMedia: MediaMetadataCompat? = null

    override fun attach(mediaSessionCallback: MediaSessionCallback) {
        this@MakrokosmosMediaSessionCallbackPresenter.mediaSessionCallback = mediaSessionCallback
    }

    override fun onAddQueueItem(description: MediaDescriptionCompat) {
        playlist.add(mediaSessionCompatHelper.getQueueItem(description))
        queueIndex = if (queueIndex == -1) 0 else queueIndex
        mediaSessionCallback.setQueue(playlist)
    }

    override fun onRemoveQueueItem(description: MediaDescriptionCompat) {
        playlist.remove(mediaSessionCompatHelper.getQueueItem(description))
        queueIndex = if (playlist.isEmpty()) -1 else queueIndex
        mediaSessionCallback.setQueue(playlist)
    }

    override fun onPrepare() {
        if (isSomethingToPlay()) {
            playlist[queueIndex].description.mediaId?.let {
                preparedMedia = musicLibraryRepository.getMetadata(it).apply {
                    mediaSessionCallback.prepareMedia(this)
                }
            }
        }
    }

    private fun isSomethingToPlay() = queueIndex >= 0 || !playlist.isEmpty()

    override fun onPlay() {
        if (!isReadyToPlay()) {
            return
        }
        if (preparedMedia == null) onPrepare()
        preparedMedia?.let { mediaSessionCallback.play(it) }
    }

    private fun isReadyToPlay(): Boolean = !playlist.isEmpty()

    override fun onSkipToNext() {
        queueIndex = incrementIndex()
        preparedMedia = null
        onPlay()
    }

    private fun incrementIndex() = ++queueIndex % playlist.size

    override fun onSkipToPrevious() {
        queueIndex = decrementIndex()
        preparedMedia = null
        onPlay()
    }

    private fun decrementIndex() = if (queueIndex > 0) queueIndex - 1 else playlist.size - 1
}