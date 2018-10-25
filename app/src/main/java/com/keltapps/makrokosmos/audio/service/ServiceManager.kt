package com.keltapps.makrokosmos.audio.service

import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.*
import com.keltapps.makrokosmos.audio.service.notification.MediaNotificationManager
import javax.inject.Inject

class ServiceManager @Inject constructor(
        private val mediaNotificationManager: MediaNotificationManager,
        private val sessionToken: MediaSessionCompat.Token,
        private val musicService: MusicService
) {
    private var serviceInStartedState: Boolean = false

    fun moveServiceToStartedState(state: PlaybackStateCompat, currentMedia: MediaMetadataCompat) {
        val notification = mediaNotificationManager.getNotification(
                currentMedia, state, sessionToken)

        if (!serviceInStartedState) {
            ContextCompat.startForegroundService(
                    musicService,
                    Intent(musicService, MusicService::class.java))
            serviceInStartedState = true
        }

        musicService.startForeground(MediaNotificationManager.NOTIFICATION_ID, notification)
    }

    fun updateNotificationForPause(state: PlaybackStateCompat, currentMedia: MediaMetadataCompat) {
        musicService.stopForeground(false)
        val notification = mediaNotificationManager.getNotification(
                currentMedia, state, sessionToken)
        mediaNotificationManager.notificationManager
                .notify(MediaNotificationManager.NOTIFICATION_ID, notification)
    }

    fun moveServiceOutOfStartedState(state: PlaybackStateCompat) {
        musicService.stopForeground(true)
        musicService.stopSelf()
        serviceInStartedState = false
    }
}