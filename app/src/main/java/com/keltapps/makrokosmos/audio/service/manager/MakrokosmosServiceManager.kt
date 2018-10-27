package com.keltapps.makrokosmos.audio.service.manager

import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.*
import com.keltapps.makrokosmos.audio.service.MusicService
import com.keltapps.makrokosmos.audio.service.notification.MakrokosmosMediaNotificationManager
import com.keltapps.makrokosmos.audio.service.notification.MediaNotificationManager
import javax.inject.Inject

class MakrokosmosServiceManager @Inject constructor(
        private val makrokosmosMediaNotificationManager: MediaNotificationManager,
        private val sessionToken: MediaSessionCompat.Token,
        private val musicService: MusicService
) : ServiceManager {

    private var serviceInStartedState: Boolean = false

    override fun moveServiceToStartedState(state: PlaybackStateCompat, currentMedia: MediaMetadataCompat) {
        val notification = makrokosmosMediaNotificationManager.getNotification(
                currentMedia, state, sessionToken)

        if (!serviceInStartedState) {
            ContextCompat.startForegroundService(
                    musicService,
                    Intent(musicService, MusicService::class.java))
            serviceInStartedState = true
        }

        musicService.startForeground(MakrokosmosMediaNotificationManager.NOTIFICATION_ID, notification)
    }

    override fun updateNotificationForPause(state: PlaybackStateCompat, currentMedia: MediaMetadataCompat) {
        musicService.stopForeground(false)
        val notification = makrokosmosMediaNotificationManager.getNotification(
                currentMedia, state, sessionToken)
        makrokosmosMediaNotificationManager.notificationManager
                .notify(MakrokosmosMediaNotificationManager.NOTIFICATION_ID, notification)
    }

    override fun moveServiceOutOfStartedState() {
        musicService.stopForeground(true)
        musicService.stopSelf()
        serviceInStartedState = false
    }
}