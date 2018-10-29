package com.keltapps.makrokosmos.audio.service.data.sessioncallback

import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat

abstract class MediaSessionCallback : MediaSessionCompat.Callback() {
    abstract fun prepareMedia(media: MediaMetadataCompat)
    abstract fun play(media: MediaMetadataCompat)
    abstract fun setQueue(queue: List<MediaSessionCompat.QueueItem>)
}