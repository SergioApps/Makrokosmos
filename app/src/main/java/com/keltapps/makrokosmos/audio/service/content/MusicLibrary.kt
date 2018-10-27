package com.keltapps.makrokosmos.audio.service.content

import android.support.v4.media.*
import com.keltapps.makrokosmos.song.domain.entity.CD
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicLibrary @Inject constructor() {
    private companion object {
        const val root: String = "root"
    }

    private val music = LinkedHashMap<String, MediaMetadataCompat>()
    private val musicFileName = HashMap<String, String>()

    fun getRoot() = root

    fun getMusicFilename(mediaId: String): String = musicFileName[mediaId] ?: ""

    fun getMetadata(mediaId: String): MediaMetadataCompat {
        return music[mediaId] ?: MediaMetadataCompat.Builder().build()
    }

    fun getMediaItems(cd: CD): List<MediaBrowserCompat.MediaItem> {
        cd.volumeList
                .flatMap { it.songList }
                .map {
                    createMediaMetadataCompat(
                            it.id,
                            it.title,
                            it.zodiacSign.name,
                            it.durationInSeconds.toLong(),
                            TimeUnit.SECONDS,
                            it.id
                    )
                }
        return music.values.map {
            MediaBrowserCompat.MediaItem(
                    it.description,
                    MediaBrowserCompat.MediaItem.FLAG_PLAYABLE
            )
        }
    }

    private fun createMediaMetadataCompat(
            mediaId: String,
            title: String,
            zodiacSign: String,
            duration: Long,
            durationUnit: TimeUnit,
            musicFilename: String
    ) {
        music[mediaId] = MediaMetadataCompat.Builder()
                .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, mediaId)
                .putString(MediaMetadataCompat.METADATA_KEY_TITLE, title)
                .putString(MediaMetadataCompat.METADATA_KEY_ALBUM, zodiacSign)
                .putLong(
                        MediaMetadataCompat.METADATA_KEY_DURATION,
                        TimeUnit.MILLISECONDS.convert(duration, durationUnit)
                )
                .build()
        musicFileName[mediaId] = musicFilename
    }
}
