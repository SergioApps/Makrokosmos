package com.keltapps.makrokosmos.audio.service

import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat

interface MediaSessionCallback {
    fun prepareMedia(media: MediaMetadataCompat)
    fun play(media: MediaMetadataCompat)
    fun setQueue(queue: List<MediaSessionCompat.QueueItem>)
}