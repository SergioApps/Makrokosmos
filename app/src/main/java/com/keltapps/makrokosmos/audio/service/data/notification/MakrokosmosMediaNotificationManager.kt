package com.keltapps.makrokosmos.audio.service.data.notification

import android.app.*
import android.content.*
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import android.support.v4.media.*
import android.support.v4.media.app.NotificationCompat.MediaStyle
import android.support.v4.media.session.*
import com.keltapps.makrokosmos.*
import com.keltapps.makrokosmos.audio.service.MusicService
import javax.inject.Inject


class MakrokosmosMediaNotificationManager @Inject constructor(
        private val service: MusicService,
        private val activityToOpen: Class<*>
) : MediaNotificationManager {

    companion object {
        private const val CHANNEL_ID = "com.keltapps.makrokosmos.channel"
        private const val REQUEST_CODE = 501
    }

    private val playAction: NotificationCompat.Action = NotificationCompat.Action(
            R.drawable.ic_play,
            service.getString(R.string.label_play),
            MediaButtonReceiver.buildMediaButtonPendingIntent(
                    service,
                    PlaybackStateCompat.ACTION_PLAY))
    private val pauseAction: NotificationCompat.Action = NotificationCompat.Action(
            R.drawable.ic_pause,
            service.getString(R.string.label_pause),
            MediaButtonReceiver.buildMediaButtonPendingIntent(
                    service,
                    PlaybackStateCompat.ACTION_PAUSE))
    private val nextAction: NotificationCompat.Action = NotificationCompat.Action(
            R.drawable.ic_skip_next,
            service.getString(R.string.label_next),
            MediaButtonReceiver.buildMediaButtonPendingIntent(
                    service,
                    PlaybackStateCompat.ACTION_SKIP_TO_NEXT))
    private val previousAction: NotificationCompat.Action = NotificationCompat.Action(
            R.drawable.ic_skip_previous,
            service.getString(R.string.label_previous),
            MediaButtonReceiver.buildMediaButtonPendingIntent(
                    service,
                    PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS))

    override val notificationManager: NotificationManager = service.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        restartNotification()
    }

    private fun restartNotification() {
        notificationManager.cancelAll()
    }

    override fun getNotification(
            metadata: MediaMetadataCompat,
            state: PlaybackStateCompat,
            token: MediaSessionCompat.Token
    ): Notification {
        val isPlaying = state.state == PlaybackStateCompat.STATE_PLAYING
        val description = metadata.description
        val builder = buildNotification(state, token, isPlaying, description)
        return builder.build()
    }

    private fun buildNotification(
            state: PlaybackStateCompat,
            token: MediaSessionCompat.Token,
            isPlaying: Boolean,
            description: MediaDescriptionCompat
    ): NotificationCompat.Builder {
        createChannelForAndroidO()
        val builder = getNotificationBuilder(token, description)
        skipNext(state, builder)
        builder.addAction(if (isPlaying) pauseAction else playAction)
        skipPrev(state, builder)
        return builder
    }

    private fun createChannelForAndroidO() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
            val nameChannel = "MediaSession"
            val userVisibleDescriptionChannel = "MediaSession and MediaPlayer"
            val importance = NotificationManager.IMPORTANCE_LOW
            NotificationChannel(CHANNEL_ID, nameChannel, importance).apply {
                description = userVisibleDescriptionChannel
                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
                vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
                notificationManager.createNotificationChannel(this)
            }
        }
    }

    private fun getNotificationBuilder(
            token: MediaSessionCompat.Token,
            description: MediaDescriptionCompat
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(service, CHANNEL_ID)
                .setStyle(
                        MediaStyle()
                                .setMediaSession(token)
                                .setShowActionsInCompactView(0, 1, 2)
                                .setShowCancelButton(true)
                                .setCancelButtonIntent(
                                        MediaButtonReceiver.buildMediaButtonPendingIntent(
                                                service,
                                                PlaybackStateCompat.ACTION_STOP
                                        )
                                )
                )
                .setColor(ContextCompat.getColor(service, R.color.blueGray800))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(createContentIntent())
                .setContentTitle(description.title)
                .setContentText(description.subtitle)
                .setLargeIcon(getLargeIcon())
                .setDeleteIntent(MediaButtonReceiver.buildMediaButtonPendingIntent(
                        service, PlaybackStateCompat.ACTION_STOP))
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
    }

    private fun createContentIntent(): PendingIntent {
        val openUI = Intent(service, activityToOpen)
        openUI.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        return PendingIntent.getActivity(
                service, REQUEST_CODE, openUI, PendingIntent.FLAG_CANCEL_CURRENT)
    }

    private fun getLargeIcon() = BitmapFactory.decodeResource(service.resources, R.drawable.cd_cover)

    private fun skipNext(state: PlaybackStateCompat, builder: NotificationCompat.Builder) {
        if (state.actions and PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS != 0L) {
            builder.addAction(previousAction)
        }
    }

    private fun skipPrev(state: PlaybackStateCompat, builder: NotificationCompat.Builder) {
        if (state.actions and PlaybackStateCompat.ACTION_SKIP_TO_NEXT != 0L) {
            builder.addAction(nextAction)
        }
    }
}
