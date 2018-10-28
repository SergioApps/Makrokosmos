package com.keltapps.makrokosmos.audio.client

import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import io.reactivex.Observable

interface MediaBrowserHelper {
    val onMetadataChanged: Observable<MediaMetadataCompat>
    fun onStart(mediaId: String)
    fun onStop()
    fun getTransportControls(): MediaControllerCompat.TransportControls
}