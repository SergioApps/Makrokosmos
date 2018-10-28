package com.keltapps.makrokosmos.audio.client

import android.content.*
import android.support.v4.media.*
import android.support.v4.media.session.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MakrokosmosMediaBrowserHelper @Inject constructor(
        private val context: Context,
        private val mediaBrowserServiceClass: Class<out MediaBrowserServiceCompat>
) : MediaBrowserHelper {

    private val mediaBrowserConnectionCallback = MediaBrowserConnectionCallback()
    private val mediaControllerCallback = MediaControllerCallback()
    private val mediaBrowserSubscriptionCallback = MediaBrowserSubscriptionCallback()
    private val publishSubject: PublishSubject<MediaMetadataCompat> = PublishSubject.create()
    override val onMetadataChanged: Observable<MediaMetadataCompat> = publishSubject.hide()

    private var mediaBrowser: MediaBrowserCompat? = null
    private var mediaController: MediaControllerCompat? = null
    private lateinit var mediaId: String

    override fun onStart(mediaId: String) {
        this.mediaId = mediaId
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
            mediaController?.transportControls?.prepareFromMediaId(mediaId, null)
            mediaController?.transportControls?.play()
        }
    }

    inner class MediaControllerCallback : MediaControllerCompat.Callback() {

        override fun onMetadataChanged(metadata: MediaMetadataCompat?) {
            metadata?.let { publishSubject.onNext(metadata) }
        }

        override fun onPlaybackStateChanged(state: PlaybackStateCompat?) {
        }

        override fun onSessionDestroyed() {
        }
    }
}
