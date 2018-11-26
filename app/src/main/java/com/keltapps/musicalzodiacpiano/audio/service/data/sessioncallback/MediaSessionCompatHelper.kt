package com.keltapps.musicalzodiacpiano.audio.service.data.sessioncallback

import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.session.MediaSessionCompat

interface MediaSessionCompatHelper {
    fun getQueueItem(mediaDescriptionCompat: MediaDescriptionCompat): MediaSessionCompat.QueueItem
}