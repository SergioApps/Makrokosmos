package com.keltapps.makrokosmos.audio.service.notification

import android.app.*
import android.content.*
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import android.support.v4.media.*
import android.support.v4.media.app.NotificationCompat.MediaStyle
import android.support.v4.media.session.*
import android.util.Log
import com.keltapps.makrokosmos.*
import com.keltapps.makrokosmos.audio.service.MusicService
import com.keltapps.makrokosmos.audio.service.content.MusicLibrary
import javax.inject.Inject


class MediaNotificationManager @Inject constructor(private val mService: MusicService) {

    companion object {
        const val NOTIFICATION_ID = 412

        private val TAG = MediaNotificationManager::class.java.simpleName
        private const val CHANNEL_ID = "com.example.android.musicplayer.channel"
        private const val REQUEST_CODE = 501
    }

    private val mPlayAction: NotificationCompat.Action = NotificationCompat.Action(
            //R.drawable.ic_play_arrow_white_24dp,
            R.drawable.aquarius,
            //mService.getString(R.string.label_play),
            "play",
            MediaButtonReceiver.buildMediaButtonPendingIntent(
                    mService,
                    PlaybackStateCompat.ACTION_PLAY))
    private val mPauseAction: NotificationCompat.Action = NotificationCompat.Action(
            //R.drawable.ic_pause_white_24dp,
            R.drawable.ic_pause,
            //mService.getString(R.string.label_pause),
            "pause",
            MediaButtonReceiver.buildMediaButtonPendingIntent(
                    mService,
                    PlaybackStateCompat.ACTION_PAUSE))
    private val mNextAction: NotificationCompat.Action = NotificationCompat.Action(
            // R.drawable.ic_skip_next_white_24dp,
            R.drawable.aquarius,
            //mService.getString(R.string.label_next),
            "next",
            MediaButtonReceiver.buildMediaButtonPendingIntent(
                    mService,
                    PlaybackStateCompat.ACTION_SKIP_TO_NEXT))
    private val mPrevAction: NotificationCompat.Action = NotificationCompat.Action(
            //  R.drawable.ic_skip_previous_white_24dp,
            R.drawable.aquarius,
            //mService.getString(R.string.label_previous),
            "previous",
            MediaButtonReceiver.buildMediaButtonPendingIntent(
                    mService,
                    PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS))
    val notificationManager: NotificationManager = mService.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private val isAndroidOOrHigher: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

    init {

        // Cancel all notifications to handle the case where the Service was killed and
        // restarted by the system.
        notificationManager.cancelAll()
    }

    fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
    }

    fun getNotification(metadata: MediaMetadataCompat,
                        state: PlaybackStateCompat,
                        token: MediaSessionCompat.Token): Notification {
        val isPlaying = state.state == PlaybackStateCompat.STATE_PLAYING
        val description = metadata.description
        val builder = buildNotification(state, token, isPlaying, description)
        return builder.build()
    }

    private fun buildNotification(state: PlaybackStateCompat,
                                  token: MediaSessionCompat.Token,
                                  isPlaying: Boolean,
                                  description: MediaDescriptionCompat): NotificationCompat.Builder {

        // Create the (mandatory) notification channel when running on Android Oreo.
        if (isAndroidOOrHigher) {
            createChannel()
        }

        val builder = NotificationCompat.Builder(mService, CHANNEL_ID)
        builder.setStyle(
                MediaStyle()
                        .setMediaSession(token)
                        .setShowActionsInCompactView(0, 1, 2)
                        // For backwards compatibility with Android L and earlier.
                        .setShowCancelButton(true)
                        .setCancelButtonIntent(
                                MediaButtonReceiver.buildMediaButtonPendingIntent(
                                        mService,
                                        PlaybackStateCompat.ACTION_STOP)))
                .setColor(ContextCompat.getColor(mService, R.color.blueGray800))
                //.setSmallIcon(R.drawable.ic_stat_image_audiotrack)
                .setSmallIcon(R.drawable.aquarius)
                // Pending intent that is fired when user clicks on notification.
                .setContentIntent(createContentIntent())
                // Title - Usually Song name.
                .setContentTitle(description.title)
                // Subtitle - Usually Artist name.
                .setContentText(description.subtitle)
                .setLargeIcon(MusicLibrary.getAlbumBitmap(mService, description.mediaId!!))
                // When notification is deleted (when playback is paused and notification can be
                // deleted) fire MediaButtonPendingIntent with ACTION_STOP.
                .setDeleteIntent(MediaButtonReceiver.buildMediaButtonPendingIntent(
                        mService, PlaybackStateCompat.ACTION_STOP))
                // Show controls on lock screen even when user hides sensitive content.
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        // If skip to next action is enabled.
        if (state.actions and PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS != 0L) {
            builder.addAction(mPrevAction)
        }

        builder.addAction(if (isPlaying) mPauseAction else mPlayAction)

        // If skip to prev action is enabled.
        if (state.actions and PlaybackStateCompat.ACTION_SKIP_TO_NEXT != 0L) {
            builder.addAction(mNextAction)
        }

        return builder
    }

    // Does nothing on versions of Android earlier than O.
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
            // The user-visible name of the channel.
            val name = "MediaSession"
            // The user-visible description of the channel.
            val description = "MediaSession and MediaPlayer"
            val importance = NotificationManager.IMPORTANCE_LOW
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            // Configure the notification channel.
            mChannel.description = description
            mChannel.enableLights(true)
            // Sets the notification light color for notifications posted to this
            // channel, if the device supports this feature.
            mChannel.lightColor = Color.RED
            mChannel.enableVibration(true)
            mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            notificationManager.createNotificationChannel(mChannel)
            Log.d(TAG, "createChannel: New channel created")
        } else {
            Log.d(TAG, "createChannel: Existing channel reused")
        }
    }

    private fun createContentIntent(): PendingIntent {
        val openUI = Intent(mService, MainActivity::class.java)
        openUI.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        return PendingIntent.getActivity(
                mService, REQUEST_CODE, openUI, PendingIntent.FLAG_CANCEL_CURRENT)
    }
}
