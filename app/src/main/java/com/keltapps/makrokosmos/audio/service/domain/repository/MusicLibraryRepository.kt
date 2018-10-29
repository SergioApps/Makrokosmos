package com.keltapps.makrokosmos.audio.service.domain.repository

import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import com.keltapps.makrokosmos.song.domain.entity.CD

interface MusicLibraryRepository {
    fun getRoot(): String
    fun getMusicFilename(mediaId: String): String
    fun getMetadata(mediaId: String): MediaMetadataCompat
    fun getMediaItems(cd: CD): List<MediaBrowserCompat.MediaItem>
}