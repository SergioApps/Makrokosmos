package com.keltapps.musicalzodiacpiano.audio.service.data.player.audiofocus

import android.media.AudioManager
import android.media.AudioManager.*
import com.google.common.truth.Truth.assertThat
import com.keltapps.musicalzodiacpiano.audio.service.data.player.MakrokosmosMediaPlayerAdapter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MakrokosmosAudioFocusHelperTest {

    private lateinit var sut: MakrokosmosAudioFocusHelper

    @Mock
    private lateinit var audioManager: AudioManager
    @Mock
    private lateinit var playerAdapterMakrokosmos: MakrokosmosMediaPlayerAdapter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MakrokosmosAudioFocusHelper(audioManager)
        sut.initialize(playerAdapterMakrokosmos)
    }

    @Test
    fun requestAudioFocus_should_requestAudioFocusAndReturnTrue_when_focusGranted() {
        `when`(audioManager.requestAudioFocus(
                sut,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN)
        ).thenReturn(AudioManager.AUDIOFOCUS_REQUEST_GRANTED)

        val result = sut.requestAudioFocus()

        assertThat(result).isTrue()
    }

    @Test
    fun requestAudioFocus_should_requestAudioFocusAndReturnFalse_when_focusNotGranted() {
        `when`(audioManager.requestAudioFocus(
                sut,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN)
        ).thenReturn(AudioManager.AUDIOFOCUS_REQUEST_FAILED)

        val result = sut.requestAudioFocus()

        assertThat(result).isFalse()
    }

    @Test
    fun abandonAudioFocus_should_callAbandonAudioFocus() {
        sut.abandonAudioFocus()

        verify(audioManager).abandonAudioFocus(sut)
    }

    @Test
    fun onAudioFocusChange_should_callPlay_when_focusGainAndAudioFocusIsTrueAndIsNotPlaying() {
        sut.playOnAudioFocus = true
        `when`(playerAdapterMakrokosmos.isPlaying()).thenReturn(false)

        sut.onAudioFocusChange(AUDIOFOCUS_GAIN)

        verify(playerAdapterMakrokosmos).play()
        assertThat(sut.playOnAudioFocus).isFalse()
    }

    @Test
    fun onAudioFocusChange_should_doNothing_when_focusGainAndAudioFocusIsFalse() {
        sut.playOnAudioFocus = false

        sut.onAudioFocusChange(AUDIOFOCUS_GAIN)


        verify(playerAdapterMakrokosmos, never()).play()
        verify(playerAdapterMakrokosmos, never()).setVolume(anyFloat())
        assertThat(sut.playOnAudioFocus).isFalse()
    }

    @Test
    fun onAudioFocusChange_should_callSetVolume_when_focusGainAndAudioFocusIsTrueAndIsPlaying() {
        sut.playOnAudioFocus = true
        `when`(playerAdapterMakrokosmos.isPlaying()).thenReturn(true)

        sut.onAudioFocusChange(AUDIOFOCUS_GAIN)

        verify(playerAdapterMakrokosmos, never()).play()
        verify(playerAdapterMakrokosmos).setVolume(anyFloat())
        assertThat(sut.playOnAudioFocus).isFalse()
    }

    @Test
    fun onAudioFocusChange_should_callSetVolume_when_focusLossTransientCanDuck() {

        sut.onAudioFocusChange(AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)

        verify(playerAdapterMakrokosmos).setVolume(anyFloat())
    }


    @Test
    fun onAudioFocusChange_should_callPause_when_focusLossTransientIsPlaying() {
        `when`(playerAdapterMakrokosmos.isPlaying()).thenReturn(true)

        sut.onAudioFocusChange(AUDIOFOCUS_LOSS_TRANSIENT)

        verify(playerAdapterMakrokosmos).pause()
        assertThat(sut.playOnAudioFocus).isTrue()
    }

    @Test
    fun onAudioFocusChange_should_doNothing_when_focusLossTransientIsNotPlaying() {
        sut.playOnAudioFocus = false
        `when`(playerAdapterMakrokosmos.isPlaying()).thenReturn(false)

        sut.onAudioFocusChange(AUDIOFOCUS_LOSS_TRANSIENT)

        verify(playerAdapterMakrokosmos, never()).pause()
        assertThat(sut.playOnAudioFocus).isFalse()
    }

    @Test
    fun onAudioFocusChange_should_callStop_when_focusLoss() {
        sut.playOnAudioFocus = true

        sut.onAudioFocusChange(AUDIOFOCUS_LOSS)

        verify(audioManager).abandonAudioFocus(sut)
        verify(playerAdapterMakrokosmos).stop()
        assertThat(sut.playOnAudioFocus).isFalse()
    }

}