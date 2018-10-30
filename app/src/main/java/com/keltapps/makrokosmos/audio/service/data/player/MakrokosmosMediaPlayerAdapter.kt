package com.keltapps.makrokosmos.audio.service.data.player

import android.content.Context
import android.media.MediaPlayer
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat.*
import com.keltapps.makrokosmos.audio.service.data.player.audiofocus.AudioFocusHelper
import com.keltapps.makrokosmos.audio.service.domain.repository.MusicLibraryRepository
import javax.inject.*

class MakrokosmosMediaPlayerAdapter @Inject constructor(
        @Named("applicationContext") private val applicationContext: Context,
        private val playbackInfoListener: PlaybackInfoListener,
        private val musicLibraryRepository: MusicLibraryRepository,
        private val audioFocusHelper: AudioFocusHelper,
        private val audioNoisyReceiver: AudioNoisyReceiver,
        private val mediaPlayerStateHelper: MediaPlayerStateHelper
) : MediaPlayerAdapter {

    init {
        audioFocusHelper.initialize(this)
        audioNoisyReceiver.initialize(this)
    }

    private var mediaPlayer: MediaPlayer? = null
    private var filename: String? = null
    private var currentMedia: MediaMetadataCompat? = null
    private var currentMediaPlayedToCompletion: Boolean = false

    override fun isPlaying(): Boolean = mediaPlayer?.isPlaying ?: false

    override fun play() {
        if (audioFocusHelper.requestAudioFocus()) {
            audioNoisyReceiver.register()
            if (mediaPlayer?.isPlaying == false) {
                mediaPlayer?.start()
                mediaPlayerStateHelper.setNewState(
                        STATE_PLAYING,
                        currentMedia,
                        mediaPlayer?.currentPosition?.toLong() ?: 0
                )
            }
        }
    }

    override fun pause() {
        if (!audioFocusHelper.playOnAudioFocus) {
            audioFocusHelper.abandonAudioFocus()
        }

        audioNoisyReceiver.unregister()
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
            mediaPlayerStateHelper.setNewState(
                    STATE_PAUSED,
                    currentMedia,
                    mediaPlayer?.currentPosition?.toLong() ?: 0
            )
        }
    }

    override fun stop() {
        audioFocusHelper.abandonAudioFocus()
        audioNoisyReceiver.unregister()
        mediaPlayerStateHelper.setNewState(
                STATE_STOPPED,
                currentMedia,
                mediaPlayer?.currentPosition?.toLong() ?: 0
        )
        currentMediaPlayedToCompletion = true
        release()
    }

    override fun playFromMedia(metadata: MediaMetadataCompat) {
        currentMedia = metadata
        playFile(musicLibraryRepository.getMusicFilename(metadata.description.mediaId ?: ""))
    }

    private fun playFile(filename: String) {
        if (hasMediaChanged(filename) || currentMediaPlayedToCompletion) {
            release()
        } else if (!hasMediaChanged(filename)) {
            if (!isPlaying()) play()
            return
        }
        currentMediaPlayedToCompletion = false
        this.filename = filename
        playNewSong(filename)
    }

    private fun hasMediaChanged(filename: String) = filename != this.filename

    private fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun playNewSong(filename: String) {
        initializeMediaPlayer()
        with(applicationContext.assets.openFd(filename)) {
            mediaPlayer?.setDataSource(fileDescriptor, startOffset, length)
        }
        mediaPlayer?.prepare()
        play()
    }

    private fun initializeMediaPlayer() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer()
            mediaPlayer?.setOnCompletionListener {
                playbackInfoListener.onPlaybackCompleted()
                mediaPlayerStateHelper.setNewState(
                        STATE_PAUSED,
                        currentMedia,
                        mediaPlayer?.currentPosition?.toLong() ?: 0
                )
            }
        }
    }

    override fun seekTo(position: Long) {
        mediaPlayer?.let {
            it.seekTo(position.toInt())
            mediaPlayerStateHelper.seekTo(
                    currentMedia,
                    position
            )
        }
    }

    override fun setVolume(volume: Float) {
        mediaPlayer?.setVolume(volume, volume)
    }
}