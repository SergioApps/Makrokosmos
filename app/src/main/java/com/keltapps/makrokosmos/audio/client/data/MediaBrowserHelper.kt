package com.keltapps.makrokosmos.audio.client.data

import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import io.reactivex.Observable

interface MediaBrowserHelper {
    val onMetadataChanged: Observable<MediaMetadataCompat>
    val onStateChanged: Observable<Int>
    fun getCurrentState(): Int
    fun onStart()
    fun onPlay(mediaId: String)
    fun onStop()
    fun getTransportControls(): MediaControllerCompat.TransportControls
}