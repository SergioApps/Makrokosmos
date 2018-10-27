package com.keltapps.makrokosmos.audio.service

import android.os.Bundle
import android.support.v4.media.*
import android.support.v4.media.session.MediaSessionCompat
import com.keltapps.makrokosmos.audio.service.content.MusicLibrary
import com.keltapps.makrokosmos.audio.service.player.PlayerAdapter
import com.keltapps.makrokosmos.song.data.repository.MakrokosmosCDRepository
import com.keltapps.makrokosmos.song.domain.repository.CDRepository
import dagger.android.AndroidInjection
import javax.inject.Inject

class MusicService : MediaBrowserServiceCompat() {

    @Inject
    internal lateinit var session: MediaSessionCompat
    @Inject
    internal lateinit var playback: PlayerAdapter
    @Inject
    internal lateinit var callbackMakrokosmos: MakrokosmosMediaSessionCallback
    @Inject
    internal lateinit var token: MediaSessionCompat.Token
    @Inject
    internal lateinit var musicLibrary: MusicLibrary
    @Inject
    internal lateinit var cdRepository: MakrokosmosCDRepository

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
        setSession()
    }

    private fun setSession() {
        session.setCallback(callbackMakrokosmos)
        session.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or
                        MediaSessionCompat.FLAG_HANDLES_QUEUE_COMMANDS or
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS
        )
        sessionToken = token
    }

    override fun onGetRoot(
            clientPackageName: String,
            clientUid: Int,
            rootHints: Bundle?
    ): MediaBrowserServiceCompat.BrowserRoot? {
        return MediaBrowserServiceCompat.BrowserRoot(musicLibrary.getRoot(), null)
    }

    override fun onLoadChildren(
            parentMediaId: String,
            result: MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>>
    ) {
        //TODO fix it
        result.sendResult(musicLibrary.getMediaItems(cdRepository.createCD()))

    }

    override fun onDestroy() {
        playback.stop()
        session.release()
        super.onDestroy()
    }
}