package com.keltapps.makrokosmos.audio.service

import android.os.Bundle
import android.support.v4.media.*
import android.support.v4.media.session.MediaSessionCompat
import com.keltapps.makrokosmos.audio.domain.iteractor.GetMediaItemsUseCase
import com.keltapps.makrokosmos.audio.domain.repository.MusicLibraryRepository
import com.keltapps.makrokosmos.audio.service.player.MakrokosmosMediaPlayerAdapter
import com.keltapps.makrokosmos.audio.service.player.MediaPlayerAdapter
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MusicService : MediaBrowserServiceCompat() {

    @Inject
    internal lateinit var session: MediaSessionCompat
    @Inject
    internal lateinit var playback: MediaPlayerAdapter
    @Inject
    internal lateinit var callbackMakrokosmos: MakrokosmosMediaSessionCallback
    @Inject
    internal lateinit var token: MediaSessionCompat.Token
    @Inject
    internal lateinit var getMediaItemsUseCase: GetMediaItemsUseCase
    @Inject
    internal lateinit var musicLibraryRepository: MusicLibraryRepository

    private val compositeDisposable = CompositeDisposable()

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
        return MediaBrowserServiceCompat.BrowserRoot(musicLibraryRepository.getRoot(), null)
    }

    override fun onLoadChildren(
            parentMediaId: String,
            result: MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>>
    ) {
        compositeDisposable.add(
                getMediaItemsUseCase.execute().subscribe(result::sendResult)
        )
    }

    override fun onDestroy() {
        playback.stop()
        session.release()
        compositeDisposable.dispose()
        super.onDestroy()
    }
}