package com.keltapps.musicalzodiacpiano.audio.client.data

import android.support.v4.media.session.PlaybackStateCompat
import com.keltapps.musicalzodiacpiano.audio.client.domain.entity.PlayingState
import org.junit.Test

class MakrokosmosPlayingStateMapperTest {

    private val sut = MakrokosmosPlayingStateMapper()

    @Test
    fun map_should_returnPlayingState_when_isPlaying() {
        val result = sut.map(PlaybackStateCompat.STATE_PLAYING)

        assert(result is PlayingState.Playing)
    }

    @Test
    fun map_should_returnPlayingState_when_isSkippingToPrevious() {
        val result = sut.map(PlaybackStateCompat.STATE_SKIPPING_TO_PREVIOUS)

        assert(result is PlayingState.Playing)
    }

    @Test
    fun map_should_returnPlayingState_when_isSkippingToNext() {
        val result = sut.map(PlaybackStateCompat.STATE_SKIPPING_TO_NEXT)

        assert(result is PlayingState.Playing)
    }

    @Test
    fun map_should_returnPlayingPaused_when_isPaused() {
        val result = sut.map(PlaybackStateCompat.STATE_PAUSED)

        assert(result is PlayingState.Paused)
    }


    @Test
    fun map_should_returnStoppedState_when_isStopped() {
        val result = sut.map(PlaybackStateCompat.STATE_STOPPED)

        assert(result is PlayingState.Stopped)
    }

    @Test
    fun map_should_returnStoppedState_when_isOtherState() {
        val result = sut.map(PlaybackStateCompat.STATE_NONE)

        assert(result is PlayingState.Stopped)
    }
}