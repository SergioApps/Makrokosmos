package com.keltapps.makrokosmos.audio.service

import android.os.Bundle
import android.support.v4.media.*
import android.support.v4.media.session.MediaSessionCompat
import androidx.media.MediaBrowserServiceCompat
import com.keltapps.makrokosmos.audio.service.data.player.MediaPlayerAdapter
import com.keltapps.makrokosmos.audio.service.data.sessioncallback.MediaSessionCallback
import com.keltapps.makrokosmos.audio.service.domain.iteractor.GetMediaItemsUseCase
import com.keltapps.makrokosmos.audio.service.domain.repository.MusicLibraryRepository
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MusicService : MediaBrowserServiceCompat() {

    @Inject
    internal lateinit var session: MediaSessionCompat
    @Inject
    internal lateinit var mediaSessionCallback: MediaSessionCallback
    @Inject
    internal lateinit var token: MediaSessionCompat.Token
    @Inject
    internal lateinit var mediaPlayerAdapter: MediaPlayerAdapter
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
        session.setCallback(mediaSessionCallback)
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
        mediaPlayerAdapter.stop()
        session.release()
        compositeDisposable.dispose()
        super.onDestroy()
    }
}