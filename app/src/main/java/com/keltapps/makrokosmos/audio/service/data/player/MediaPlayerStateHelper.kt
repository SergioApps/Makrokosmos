package com.keltapps.makrokosmos.audio.service.data.player

import android.support.v4.media.MediaMetadataCompat

interface MediaPlayerStateHelper {
    fun setNewState(
            state: Int,
            currentMedia: MediaMetadataCompat,
            position: Long
    )

    fun seekTo(
            currentMedia: MediaMetadataCompat,
            position: Long
    )
}