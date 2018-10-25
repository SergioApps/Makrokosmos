package com.keltapps.makrokosmos.audio.service

import android.support.v4.media.*
import android.support.v4.media.session.MediaSessionCompat
import com.keltapps.makrokosmos.audio.service.content.MusicLibrary
import java.util.*
import javax.inject.Inject

class MediaSessionCallback @Inject constructor(
        private val session: MediaSessionCompat,
        private val musicService: MusicService,
        private val playback: PlayerAdapter
) : MediaSessionCompat.Callback() {
    private val mPlaylist = ArrayList<MediaSessionCompat.QueueItem>()
    private var mQueueIndex = -1
    private var mPreparedMedia: MediaMetadataCompat? = null

    private val isReadyToPlay: Boolean
        get() = !mPlaylist.isEmpty()

    override fun onAddQueueItem(description: MediaDescriptionCompat?) {
        mPlaylist.add(MediaSessionCompat.QueueItem(description!!, description.hashCode().toLong()))
        mQueueIndex = if (mQueueIndex == -1) 0 else mQueueIndex
        session.setQueue(mPlaylist)
    }

    override fun onRemoveQueueItem(description: MediaDescriptionCompat?) {
        mPlaylist.remove(MediaSessionCompat.QueueItem(description!!, description.hashCode().toLong()))
        mQueueIndex = if (mPlaylist.isEmpty()) -1 else mQueueIndex
        session.setQueue(mPlaylist)
    }

    override fun onPrepare() {
        if (mQueueIndex < 0 && mPlaylist.isEmpty()) {
            // Nothing to play.
            return
        }

        val mediaId = mPlaylist[mQueueIndex].description.mediaId ?: ""
        mPreparedMedia = MusicLibrary.getMetadata(musicService, mediaId)
        session.setMetadata(mPreparedMedia)

        if (!session.isActive) {
            session.isActive = true
        }
    }

    override fun onPlay() {
        if (!isReadyToPlay) {
            // Nothing to play.
            return
        }

        if (mPreparedMedia == null) {
            onPrepare()
        }

        playback.playFromMedia(mPreparedMedia!!)
    }

    override fun onPause() {
        playback.pause()
    }

    override fun onStop() {
        playback.stop()
        session.isActive = false
    }

    override fun onSkipToNext() {
        mQueueIndex = ++mQueueIndex % mPlaylist.size
        mPreparedMedia = null
        onPlay()
    }

    override fun onSkipToPrevious() {
        mQueueIndex = if (mQueueIndex > 0) mQueueIndex - 1 else mPlaylist.size - 1
        mPreparedMedia = null
        onPlay()
    }

    override fun onSeekTo(pos: Long) {
        playback.seekTo(pos)
    }
}