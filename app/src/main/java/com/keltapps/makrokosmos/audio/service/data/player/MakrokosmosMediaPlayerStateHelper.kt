package com.keltapps.makrokosmos.audio.service.data.player

import android.media.session.PlaybackState.*
import android.os.SystemClock
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat
import javax.inject.Inject

class MakrokosmosMediaPlayerStateHelper @Inject constructor(
        private val playbackInfoListener: PlaybackInfoListener
) : MediaPlayerStateHelper {

    private var currentState: Int = STATE_STOPPED

    override fun setNewState(
            state: Int,
            currentMedia: MediaMetadataCompat,
            position: Long
    ) {
        currentState = state
        val newState = PlaybackStateCompat.Builder()
                .setActions(getAvailableActions(state))
                .setState(
                        state,
                        position,
                        1.0f,
                        SystemClock.elapsedRealtime()
                ).build()
        playbackInfoListener.onPlaybackStateChange(newState, currentMedia)
    }

    override fun seekTo(
            currentMedia: MediaMetadataCompat,
            position: Long
    ) {
        setNewState(currentState, currentMedia, position)
    }

    private fun getAvailableActions(state: Int): Long {
        var actions = (
                ACTION_PLAY_FROM_MEDIA_ID
                        or ACTION_PLAY_FROM_SEARCH
                        or ACTION_SKIP_TO_NEXT
                        or ACTION_SKIP_TO_PREVIOUS
                )
        actions = when (state) {
            STATE_STOPPED -> actions or (ACTION_PLAY or ACTION_PAUSE)
            STATE_PLAYING -> actions or (
                    ACTION_STOP
                            or ACTION_PAUSE
                            or ACTION_SEEK_TO
                    )
            STATE_PAUSED -> actions or (ACTION_PLAY or ACTION_STOP)
            else -> actions or (
                    ACTION_PLAY
                            or ACTION_PLAY_PAUSE
                            or ACTION_STOP
                            or ACTION_PAUSE
                    )
        }
        return actions
    }
}