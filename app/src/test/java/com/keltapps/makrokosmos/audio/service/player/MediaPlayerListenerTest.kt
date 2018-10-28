package com.keltapps.makrokosmos.audio.service.player

import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.support.v4.media.session.PlaybackStateCompat.*
import com.keltapps.makrokosmos.audio.service.manager.ServiceManager
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MediaPlayerListenerTest {

    private lateinit var sut: MediaPlayerListener

    @Mock
    private lateinit var session: MediaSessionCompat
    @Mock
    private lateinit var serviceManager: ServiceManager

    @Mock
    private lateinit var mockState: PlaybackStateCompat
    @Mock
    private lateinit var mockCurrentMedia: MediaMetadataCompat

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MediaPlayerListener(
                session,
                serviceManager
        )
    }

    @Test
    fun onPlaybackStateChange_should_moveServiceToStartedState_when_stateIsPlaying() {
        `when`(mockState.state).thenReturn(STATE_PLAYING)

        sut.onPlaybackStateChange(mockState, mockCurrentMedia)

        verify(session).setPlaybackState(mockState)
        verify(serviceManager).moveServiceToStartedState(mockState, mockCurrentMedia)
    }

    @Test
    fun onPlaybackStateChange_should_updateNotificationForPause_when_stateIsPaused() {
        `when`(mockState.state).thenReturn(STATE_PAUSED)

        sut.onPlaybackStateChange(mockState, mockCurrentMedia)

        verify(session).setPlaybackState(mockState)
        verify(serviceManager).updateNotificationForPause(mockState, mockCurrentMedia)
    }

    @Test
    fun onPlaybackStateChange_should_moveServiceOutOfStartedState_when_stateIsStopped() {
        `when`(mockState.state).thenReturn(STATE_STOPPED)

        sut.onPlaybackStateChange(mockState, mockCurrentMedia)

        verify(session).setPlaybackState(mockState)
        verify(serviceManager).moveServiceOutOfStartedState()
    }
}