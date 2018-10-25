package com.keltapps.makrokosmos.audio.service.players


import android.content.Context
import android.media.MediaPlayer
import android.os.SystemClock
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat
import com.keltapps.makrokosmos.audio.service.*
import com.keltapps.makrokosmos.audio.service.content.MusicLibrary


class MediaPlayerAdapter(context: Context, private val mPlaybackInfoListener: PlaybackInfoListener) : PlayerAdapter(context) {

    private val mContext: Context = context.applicationContext
    private var mMediaPlayer: MediaPlayer? = null
    private var mFilename: String? = null
    override lateinit var currentMedia: MediaMetadataCompat

    private var mState: Int = 0
    private var mCurrentMediaPlayedToCompletion: Boolean = false

    // Work-around for a MediaPlayer bug related to the behavior of MediaPlayer.seekTo()
    // while not playing.
    private var mSeekWhileNotPlaying = -1

    override val isPlaying: Boolean
        get() = mMediaPlayer != null && mMediaPlayer!!.isPlaying

    /**
     * Set the current capabilities available on this session. Note: If a capability is not
     * listed in the bitmask of capabilities then the MediaSession will not handle it. For
     * example, if you don't want ACTION_STOP to be handled by the MediaSession, then don't
     * included it in the bitmask that's returned.
     */
    private val availableActions: Long
        @PlaybackStateCompat.Actions
        get() {
            var actions = (PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID
                    or PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH
                    or PlaybackStateCompat.ACTION_SKIP_TO_NEXT
                    or PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS)
            actions = when (mState) {
                PlaybackStateCompat.STATE_STOPPED -> actions or (PlaybackStateCompat.ACTION_PLAY or PlaybackStateCompat.ACTION_PAUSE)
                PlaybackStateCompat.STATE_PLAYING -> actions or (PlaybackStateCompat.ACTION_STOP
                        or PlaybackStateCompat.ACTION_PAUSE
                        or PlaybackStateCompat.ACTION_SEEK_TO)
                PlaybackStateCompat.STATE_PAUSED -> actions or (PlaybackStateCompat.ACTION_PLAY or PlaybackStateCompat.ACTION_STOP)
                else -> actions or (PlaybackStateCompat.ACTION_PLAY
                        or PlaybackStateCompat.ACTION_PLAY_PAUSE
                        or PlaybackStateCompat.ACTION_STOP
                        or PlaybackStateCompat.ACTION_PAUSE)
            }
            return actions
        }

    private fun initializeMediaPlayer() {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer()
            mMediaPlayer?.setOnCompletionListener {
                mPlaybackInfoListener.onPlaybackCompleted()

                // Set the state to "paused" because it most closely matches the state
                // in MediaPlayer with regards to available state transitions compared
                // to "stop".
                // Paused allows: seekTo(), start(), pause(), stop()
                // Stop allows: stop()
                setNewState(PlaybackStateCompat.STATE_PAUSED)
            }
        }
    }

    // Implements PlaybackControl.
    override fun playFromMedia(metadata: MediaMetadataCompat) {
        currentMedia = metadata
        val mediaId = metadata.description.mediaId
        playFile(MusicLibrary.getMusicFilename(mediaId ?: ""))
    }

    private fun playFile(filename: String?) {
        var mediaChanged = mFilename == null || filename != mFilename
        if (mCurrentMediaPlayedToCompletion) {
            // Last audio file was played to completion, the resourceId hasn't changed, but the
            // player was released, so force a reload of the media file for playback.
            mediaChanged = true
            mCurrentMediaPlayedToCompletion = false
        }
        if (!mediaChanged) {
            if (!isPlaying) {
                play()
            }
            return
        } else {
            release()
        }

        mFilename = filename

        initializeMediaPlayer()

        try {
            val assetFileDescriptor = mContext.assets.openFd(mFilename)
            mMediaPlayer?.setDataSource(
                    assetFileDescriptor.fileDescriptor,
                    assetFileDescriptor.startOffset,
                    assetFileDescriptor.length)
        } catch (e: Exception) {
            throw RuntimeException("Failed to open file: $mFilename", e)
        }

        try {
            mMediaPlayer?.prepare()
        } catch (e: Exception) {
            throw RuntimeException("Failed to open file: $mFilename", e)
        }

        play()
    }

    public override fun onStop() {
        // Regardless of whether or not the MediaPlayer has been created / started, the state must
        // be updated, so that MediaNotificationManager can take down the notification.
        setNewState(PlaybackStateCompat.STATE_STOPPED)
        release()
    }

    private fun release() {
        if (mMediaPlayer != null) {
            mMediaPlayer?.release()
            mMediaPlayer = null
        }
    }

    override fun onPlay() {
        if (mMediaPlayer != null && mMediaPlayer?.isPlaying == false) {
            mMediaPlayer?.start()
            setNewState(PlaybackStateCompat.STATE_PLAYING)
        }
    }

    override fun onPause() {
        if (mMediaPlayer != null && mMediaPlayer?.isPlaying == true) {
            mMediaPlayer?.pause()
            setNewState(PlaybackStateCompat.STATE_PAUSED)
        }
    }

    // This is the main reducer for the player state machine.
    private fun setNewState(@PlaybackStateCompat.State newPlayerState: Int) {
        mState = newPlayerState

        // Whether playback goes to completion, or whether it is stopped, the
        // mCurrentMediaPlayedToCompletion is set to true.
        if (mState == PlaybackStateCompat.STATE_STOPPED) {
            mCurrentMediaPlayedToCompletion = true
        }

        // Work around for MediaPlayer.getCurrentPosition() when it changes while not playing.
        val reportPosition: Long
        if (mSeekWhileNotPlaying >= 0) {
            reportPosition = mSeekWhileNotPlaying.toLong()

            if (mState == PlaybackStateCompat.STATE_PLAYING) {
                mSeekWhileNotPlaying = -1
            }
        } else {
            reportPosition = (if (mMediaPlayer == null) 0 else mMediaPlayer!!.currentPosition).toLong()
        }

        val stateBuilder = PlaybackStateCompat.Builder()
        stateBuilder.setActions(availableActions)
        stateBuilder.setState(mState,
                reportPosition,
                1.0f,
                SystemClock.elapsedRealtime())
        mPlaybackInfoListener.onPlaybackStateChange(stateBuilder.build(), currentMedia)
    }

    override fun seekTo(position: Long) {
        if (mMediaPlayer != null) {
            if (mMediaPlayer?.isPlaying == false) {
                mSeekWhileNotPlaying = position.toInt()
            }
            mMediaPlayer?.seekTo(position.toInt())

            // Set the state (to the current state) because the position changed and should
            // be reported to clients.
            setNewState(mState)
        }
    }

    override fun setVolume(volume: Float) {
        if (mMediaPlayer != null) {
            mMediaPlayer?.setVolume(volume, volume)
        }
    }
}