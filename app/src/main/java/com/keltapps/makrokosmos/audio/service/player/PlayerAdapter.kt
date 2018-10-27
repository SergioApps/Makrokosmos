package com.keltapps.makrokosmos.audio.service.player

import android.content.*
import android.media.AudioManager
import android.support.v4.media.MediaMetadataCompat

abstract class PlayerAdapter(context: Context) {

    private companion object {
        const val MEDIA_VOLUME_DEFAULT = 1.0f
        const val MEDIA_VOLUME_DUCK = 0.2f
        val AUDIO_NOISY_INTENT_FILTER = IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY)
    }

    private var mAudioNoisyReceiverRegistered = false
    private val mAudioNoisyReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (AudioManager.ACTION_AUDIO_BECOMING_NOISY == intent.action) {
                if (isPlaying) {
                    pause()
                }
            }
        }
    }

    private val mApplicationContext: Context = context.applicationContext
    private val mAudioManager: AudioManager
    private val mAudioFocusHelper: AudioFocusHelper

    private var mPlayOnAudioFocus = false

    abstract val currentMedia: MediaMetadataCompat

    abstract val isPlaying: Boolean

    init {
        mAudioManager = mApplicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        mAudioFocusHelper = AudioFocusHelper()
    }

    abstract fun playFromMedia(metadata: MediaMetadataCompat)

    fun play() {
        if (mAudioFocusHelper.requestAudioFocus()) {
            registerAudioNoisyReceiver()
            onPlay()
        }
    }

    /**
     * Called when media is ready to be played and indicates the app has audio focus.
     */
    protected abstract fun onPlay()

    fun pause() {
        if (!mPlayOnAudioFocus) {
            mAudioFocusHelper.abandonAudioFocus()
        }

        unregisterAudioNoisyReceiver()
        onPause()
    }

    /**
     * Called when media must be paused.
     */
    protected abstract fun onPause()

    fun stop() {
        mAudioFocusHelper.abandonAudioFocus()
        unregisterAudioNoisyReceiver()
        onStop()
    }

    /**
     * Called when the media must be stopped. The player should clean up resources at this
     * point.
     */
    protected abstract fun onStop()

    abstract fun seekTo(position: Long)

    abstract fun setVolume(volume: Float)

    private fun registerAudioNoisyReceiver() {
        if (!mAudioNoisyReceiverRegistered) {
            mApplicationContext.registerReceiver(mAudioNoisyReceiver, AUDIO_NOISY_INTENT_FILTER)
            mAudioNoisyReceiverRegistered = true
        }
    }

    private fun unregisterAudioNoisyReceiver() {
        if (mAudioNoisyReceiverRegistered) {
            mApplicationContext.unregisterReceiver(mAudioNoisyReceiver)
            mAudioNoisyReceiverRegistered = false
        }
    }

    /**
     * Helper class for managing audio focus related tasks.
     */
    private inner class AudioFocusHelper : AudioManager.OnAudioFocusChangeListener {

        fun requestAudioFocus(): Boolean {
            val result = mAudioManager.requestAudioFocus(this,
                    AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN)
            return result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED
        }

        fun abandonAudioFocus() {
            mAudioManager.abandonAudioFocus(this)
        }

        override fun onAudioFocusChange(focusChange: Int) {
            when (focusChange) {
                AudioManager.AUDIOFOCUS_GAIN -> {
                    if (mPlayOnAudioFocus && !isPlaying) {
                        play()
                    } else if (isPlaying) {
                        setVolume(MEDIA_VOLUME_DEFAULT)
                    }
                    mPlayOnAudioFocus = false
                }
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> setVolume(MEDIA_VOLUME_DUCK)
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> if (isPlaying) {
                    mPlayOnAudioFocus = true
                    pause()
                }
                AudioManager.AUDIOFOCUS_LOSS -> {
                    mAudioManager.abandonAudioFocus(this)
                    mPlayOnAudioFocus = false
                    stop()
                }
            }
        }
    }
}