package com.keltapps.makrokosmos.audio.service

import android.os.Bundle
import android.support.v4.media.*
import android.support.v4.media.session.MediaSessionCompat
import com.keltapps.makrokosmos.audio.service.content.MusicLibrary
import com.keltapps.makrokosmos.audio.service.notification.MediaNotificationManager
import dagger.android.AndroidInjection
import javax.inject.Inject

class MusicService : MediaBrowserServiceCompat() {

    @Inject
    internal lateinit var session: MediaSessionCompat
    @Inject
    internal lateinit var playback: PlayerAdapter
    @Inject
    internal lateinit var mediaNotificationManager: MediaNotificationManager
    @Inject
    internal lateinit var callback: MediaSessionCallback

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()

        session.setCallback(callback)
        session.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or
                        MediaSessionCompat.FLAG_HANDLES_QUEUE_COMMANDS or
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS)
    }

    override fun onDestroy() {
        mediaNotificationManager.onDestroy()
        playback.stop()
        session.release()
    }

    override fun onGetRoot(clientPackageName: String,
                           clientUid: Int,
                           rootHints: Bundle?): MediaBrowserServiceCompat.BrowserRoot? {
        return MediaBrowserServiceCompat.BrowserRoot(MusicLibrary.root, null)
    }

    override fun onLoadChildren(
            parentMediaId: String,
            result: MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>>) {
        result.sendResult(MusicLibrary.mediaItems)
    }
}