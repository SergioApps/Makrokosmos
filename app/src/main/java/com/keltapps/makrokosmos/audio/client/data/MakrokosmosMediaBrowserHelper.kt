package com.keltapps.makrokosmos.audio.client.data

import android.content.*
import android.support.v4.media.*
import android.support.v4.media.session.*
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MakrokosmosMediaBrowserHelper @Inject constructor(
        private val context: Context,
        private val mediaBrowserServiceClass: Class<out MediaBrowserServiceCompat>
) : MediaBrowserHelper {

    private val mediaBrowserConnectionCallback = MediaBrowserConnectionCallback()
    private val mediaControllerCallback = MediaControllerCallback()
    private val mediaBrowserSubscriptionCallback = MediaBrowserSubscriptionCallback()
    private val publishSubjectMetadata: PublishSubject<MediaMetadataCompat> = PublishSubject.create()
    override val onMetadataChanged: Observable<MediaMetadataCompat> = publishSubjectMetadata.hide()
    private val publishSubjectState: PublishSubject<Int> = PublishSubject.create()
    override val onStateChanged: Observable<Int> = publishSubjectState.hide()

    private var mediaBrowser: MediaBrowserCompat? = null
    private var mediaController: MediaControllerCompat? = null

    override fun onStart() {
        if (mediaBrowser == null) {
            mediaBrowser = MediaBrowserCompat(
                    context,
                    ComponentName(context, mediaBrowserServiceClass),
                    mediaBrowserConnectionCallback,
                    null
            )
            mediaBrowser?.connect()
        }
    }

    override fun onPlay(mediaId: String) {
        mediaController?.transportControls?.prepareFromMediaId(mediaId, null)
        mediaController?.transportControls?.play()
    }

    override fun onStop() {
        releaseMediaController()
        releaseMediaBrowser()
    }

    private fun releaseMediaBrowser() {
        if (mediaBrowser?.isConnected == true) {
            mediaBrowser?.disconnect()
            mediaBrowser = null
        }
    }

    private fun releaseMediaController() {
        mediaController?.unregisterCallback(mediaControllerCallback)
        mediaController = null
    }

    override fun getTransportControls(): MediaControllerCompat.TransportControls {
        mediaController?.let {
            it.playbackState.state
            return it.transportControls
        }
        throw IllegalStateException("MediaController is null!")
    }

    inner class MediaBrowserConnectionCallback : MediaBrowserCompat.ConnectionCallback() {

        override fun onConnected() {
            mediaBrowser?.let {
                mediaController = MediaControllerCompat(context, it.sessionToken).apply {
                    registerCallback(mediaControllerCallback)
                    mediaControllerCallback.onMetadataChanged(metadata)
                    mediaControllerCallback.onPlaybackStateChanged(playbackState)
                    it.subscribe(it.root, mediaBrowserSubscriptionCallback)
                }
            }
        }
    }

    inner class MediaBrowserSubscriptionCallback : MediaBrowserCompat.SubscriptionCallback() {

        override fun onChildrenLoaded(
                parentId: String,
                children: List<MediaBrowserCompat.MediaItem>
        ) {
            children.map { mediaController?.addQueueItem(it.description) }
        }
    }

    inner class MediaControllerCallback : MediaControllerCompat.Callback() {

        override fun onMetadataChanged(metadata: MediaMetadataCompat?) {
            metadata?.let { publishSubjectMetadata.onNext(metadata) }
        }

        override fun onPlaybackStateChanged(state: PlaybackStateCompat?) {
            super.onPlaybackStateChanged(state)
            state?.state?.let { publishSubjectState.onNext(it) }
        }
    }
}
