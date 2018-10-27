package com.keltapps.makrokosmos.audio.service.notification

import android.app.Notification
import android.app.NotificationManager
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat

interface MediaNotificationManager {
    val notificationManager: NotificationManager
    fun getNotification(
            metadata: MediaMetadataCompat,
            state: PlaybackStateCompat,
            token: MediaSessionCompat.Token
    ): Notification
}