package com.keltapps.makrokosmos.audio.service.content

import android.content.*
import android.graphics.*
import android.support.v4.media.*
import com.keltapps.makrokosmos.*
import java.util.*
import java.util.concurrent.TimeUnit

object MusicLibrary {

    private val music = TreeMap<String, MediaMetadataCompat>()
    private val albumRes = HashMap<String, Int>()
    private val musicFileName = HashMap<String, String>()

    val root: String
        get() = "root"

    val mediaItems: List<MediaBrowserCompat.MediaItem>
        get() {
            val result = ArrayList<MediaBrowserCompat.MediaItem>()
            for (metadata in music.values) {
                result.add(
                        MediaBrowserCompat.MediaItem(
                                metadata.description, MediaBrowserCompat.MediaItem.FLAG_PLAYABLE))
            }
            return result
        }

    init {
        createMediaMetadataCompat(
                "Jazz_In_Paris",
                "Jazz in Paris",
                "Media Right Productions",
                "Jazz & Blues",
                "Jazz",
                103,
                TimeUnit.SECONDS,
                "1. Primeval Sounds (Genesis I) Cancer.mp3",
                R.drawable.cd_cover,
                "album_jazz_blues")
        createMediaMetadataCompat(
                "The_Coldest_Shoulder",
                "The Coldest Shoulder",
                "The 126ers",
                "Youtube Audio Library Rock 2",
                "Rock",
                160,
                TimeUnit.SECONDS,
                "2. Proteus (Pisces).mp3",
                R.drawable.cd_cover,
                "album_youtube_audio_library_rock_2")
    }

    private fun getAlbumArtUri(albumArtResName: String): String {
        return ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                BuildConfig.APPLICATION_ID + "/drawable/" + albumArtResName
    }

    fun getMusicFilename(mediaId: String): String? {
        return if (musicFileName.containsKey(mediaId)) musicFileName[mediaId] else null
    }

    private fun getAlbumRes(mediaId: String): Int {
        return if (albumRes.containsKey(mediaId)) albumRes[mediaId] ?: 0 else 0
    }

    fun getAlbumBitmap(context: Context, mediaId: String): Bitmap {
        return BitmapFactory.decodeResource(context.resources,
                MusicLibrary.getAlbumRes(mediaId))
    }

    fun getMetadata(context: Context, mediaId: String): MediaMetadataCompat {
        val metadataWithoutBitmap = music[mediaId]
        val albumArt = getAlbumBitmap(context, mediaId)

        // Since MediaMetadataCompat is immutable, we need to create a copy to set the album art.
        // We don't set it initially on all items so that they don't take unnecessary memory.
        val builder = MediaMetadataCompat.Builder()
        for (key in arrayOf(
                MediaMetadataCompat.METADATA_KEY_MEDIA_ID,
                MediaMetadataCompat.METADATA_KEY_ALBUM,
                MediaMetadataCompat.METADATA_KEY_ARTIST,
                MediaMetadataCompat.METADATA_KEY_GENRE,
                MediaMetadataCompat.METADATA_KEY_TITLE
        )) {
            builder.putString(key, metadataWithoutBitmap?.getString(key))
        }
        builder.putLong(
                MediaMetadataCompat.METADATA_KEY_DURATION,
                metadataWithoutBitmap?.getLong(MediaMetadataCompat.METADATA_KEY_DURATION) ?: 0)
        builder.putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, albumArt)
        return builder.build()
    }

    private fun createMediaMetadataCompat(
            mediaId: String,
            title: String,
            artist: String,
            album: String,
            genre: String,
            duration: Long,
            durationUnit: TimeUnit,
            musicFilename: String,
            albumArtResId: Int,
            albumArtResName: String) {
        music[mediaId] = MediaMetadataCompat.Builder()
                .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, mediaId)
                .putString(MediaMetadataCompat.METADATA_KEY_ALBUM, album)
                .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, artist)
                .putLong(MediaMetadataCompat.METADATA_KEY_DURATION,
                        TimeUnit.MILLISECONDS.convert(duration, durationUnit))
                .putString(MediaMetadataCompat.METADATA_KEY_GENRE, genre)
                .putString(
                        MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI,
                        getAlbumArtUri(albumArtResName))
                .putString(
                        MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI,
                        getAlbumArtUri(albumArtResName))
                .putString(MediaMetadataCompat.METADATA_KEY_TITLE, title)
                .build()
        albumRes[mediaId] = albumArtResId
        musicFileName[mediaId] = musicFilename
    }
}
