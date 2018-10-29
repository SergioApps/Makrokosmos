package com.keltapps.makrokosmos.audio.service.data.manager

import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.*
import com.keltapps.makrokosmos.audio.service.MusicService
import com.keltapps.makrokosmos.audio.service.data.notification.MediaNotificationManager
import javax.inject.Inject

class MakrokosmosServiceManager @Inject constructor(
        private val mediaNotificationManager: MediaNotificationManager,
        private val sessionToken: MediaSessionCompat.Token,
        private val musicService: MusicService
) : ServiceManager {

    private var serviceInStartedState: Boolean = false

    override fun moveServiceToStartedState(state: PlaybackStateCompat, currentMedia: MediaMetadataCompat) {
        if (!serviceInStartedState) {
            ContextCompat.startForegroundService(
                    musicService,
                    Intent(musicService, MusicService::class.java))
            serviceInStartedState = true
        }
        musicService.startForeground(
                MediaNotificationManager.NOTIFICATION_ID,
                mediaNotificationManager.getNotification(
                        currentMedia,
                        state,
                        sessionToken
                )
        )
    }

    override fun updateNotificationForPause(state: PlaybackStateCompat, currentMedia: MediaMetadataCompat) {
        musicService.stopForeground(false)
        mediaNotificationManager.notificationManager
                .notify(
                        MediaNotificationManager.NOTIFICATION_ID,
                        mediaNotificationManager.getNotification(
                                currentMedia,
                                state,
                                sessionToken
                        )
                )
    }

    override fun moveServiceOutOfStartedState() {
        musicService.stopForeground(true)
        musicService.stopSelf()
        serviceInStartedState = false
    }
}