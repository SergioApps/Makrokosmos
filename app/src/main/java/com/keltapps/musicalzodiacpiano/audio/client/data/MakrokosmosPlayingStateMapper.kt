package com.keltapps.musicalzodiacpiano.audio.client.data

import android.support.v4.media.session.PlaybackStateCompat
import com.keltapps.musicalzodiacpiano.audio.client.domain.entity.PlayingState
import javax.inject.Inject

class MakrokosmosPlayingStateMapper @Inject constructor() : PlayingStateMapper {
    override fun map(payingState: Int): PlayingState {
        return when (payingState) {
            PlaybackStateCompat.STATE_SKIPPING_TO_PREVIOUS -> PlayingState.Playing
            PlaybackStateCompat.STATE_SKIPPING_TO_NEXT -> PlayingState.Playing
            PlaybackStateCompat.STATE_PLAYING -> PlayingState.Playing
            PlaybackStateCompat.STATE_PAUSED -> PlayingState.Paused
            else -> PlayingState.Stopped
        }
    }
}