package com.keltapps.musicalzodiacpiano.audio.service.data.player.audiofocus

import android.media.AudioManager
import android.media.AudioManager.*
import com.keltapps.musicalzodiacpiano.audio.service.data.player.MediaPlayerAdapter
import javax.inject.Inject

class MakrokosmosAudioFocusHelper @Inject constructor(
        private val audioManager: AudioManager
) : AudioManager.OnAudioFocusChangeListener, AudioFocusHelper {

    private companion object {
        const val MEDIA_VOLUME_DEFAULT = 1.0f
        const val MEDIA_VOLUME_DUCK = 0.2f
    }

    override var playOnAudioFocus = false
    private lateinit var playerAdapter: MediaPlayerAdapter

    override fun initialize(playerAdapter: MediaPlayerAdapter) {
        this.playerAdapter = playerAdapter
    }

    override fun requestAudioFocus(): Boolean {
        val result = audioManager.requestAudioFocus(
                this,
                STREAM_MUSIC,
                AUDIOFOCUS_GAIN
        )
        return result == AUDIOFOCUS_REQUEST_GRANTED
    }

    override fun abandonAudioFocus() {
        audioManager.abandonAudioFocus(this)
    }

    override fun onAudioFocusChange(focusChange: Int) {
        when (focusChange) {
            AUDIOFOCUS_GAIN -> handleFocusGain()
            AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> handleFocusLossTransientCanDuck()
            AUDIOFOCUS_LOSS_TRANSIENT -> handleFocusLossTransient()
            AUDIOFOCUS_LOSS -> handleFocusLoss()
        }
    }

    private fun handleFocusGain() {
        if (playOnAudioFocus && !playerAdapter.isPlaying()) {
            playerAdapter.play()
        } else if (playerAdapter.isPlaying()) {
            playerAdapter.setVolume(MEDIA_VOLUME_DEFAULT)
        }
        playOnAudioFocus = false
    }

    private fun handleFocusLossTransientCanDuck() {
        playerAdapter.setVolume(MEDIA_VOLUME_DUCK)
    }

    private fun handleFocusLossTransient() {
        if (playerAdapter.isPlaying()) {
            playOnAudioFocus = true
            playerAdapter.pause()
        }
    }

    private fun handleFocusLoss() {
        audioManager.abandonAudioFocus(this)
        playOnAudioFocus = false
        playerAdapter.stop()
    }
}