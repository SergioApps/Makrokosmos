package com.keltapps.musicalzodiacpiano.audio.service.data.sessioncallback

import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.session.MediaSessionCompat
import javax.inject.Inject

class MakrokosmosMediaSessionCompatHelper @Inject constructor() : MediaSessionCompatHelper {

    override fun getQueueItem(
            mediaDescriptionCompat: MediaDescriptionCompat
    ): MediaSessionCompat.QueueItem {
        return MediaSessionCompat.QueueItem(
                mediaDescriptionCompat,
                mediaDescriptionCompat.hashCode().toLong()
        )
    }
}