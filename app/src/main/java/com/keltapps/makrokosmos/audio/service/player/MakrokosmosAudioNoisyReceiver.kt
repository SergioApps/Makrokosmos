package com.keltapps.makrokosmos.audio.service.player

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import javax.inject.Inject
import javax.inject.Named

class MakrokosmosAudioNoisyReceiver @Inject constructor(
        @Named("applicationContext") private val applicationContext: Context
) : AudioNoisyReceiver {

    private companion object {
        val AUDIO_NOISY_INTENT_FILTER = IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY)
    }

    private lateinit var mediaPlayerAdapter: MediaPlayerAdapter

    override fun initialize(mediaPlayerAdapter: MediaPlayerAdapter) {
        this.mediaPlayerAdapter = mediaPlayerAdapter
    }

    private var audioNoisyReceiverRegistered = false
    private val audioNoisyReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (AudioManager.ACTION_AUDIO_BECOMING_NOISY == intent.action) {
                if (mediaPlayerAdapter.isPlaying()) {
                    mediaPlayerAdapter.pause()
                }
            }
        }
    }

    override fun register() {
        if (!audioNoisyReceiverRegistered) {
            applicationContext.registerReceiver(audioNoisyReceiver, AUDIO_NOISY_INTENT_FILTER)
            audioNoisyReceiverRegistered = true
        }
    }

    override fun unregister() {
        if (audioNoisyReceiverRegistered) {
            applicationContext.unregisterReceiver(audioNoisyReceiver)
            audioNoisyReceiverRegistered = false
        }
    }
}