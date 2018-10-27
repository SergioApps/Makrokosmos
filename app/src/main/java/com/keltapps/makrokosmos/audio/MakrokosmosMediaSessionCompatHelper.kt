package com.keltapps.makrokosmos.audio

import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.session.MediaSessionCompat
import com.keltapps.makrokosmos.audio.service.MediaSessionCompatHelper
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