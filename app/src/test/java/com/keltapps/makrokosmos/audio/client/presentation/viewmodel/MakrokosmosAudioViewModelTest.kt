package com.keltapps.makrokosmos.audio.client.presentation.viewmodel

import com.keltapps.makrokosmos.audio.client.domain.entity.PlayingState
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import org.junit.*
import org.mockito.*
import org.mockito.Mockito.*

class MakrokosmosAudioViewModelTest {

    private lateinit var sut: MakrokosmosAudioViewModel

    @Mock
    private lateinit var audioRepository: AudioRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MakrokosmosAudioViewModel(audioRepository)
    }

    @Test
    fun skipToNext_should_callSkipToNext() {
        sut.skipToNext()

        verify(audioRepository).skipToNext()
    }

    @Test
    fun skipToPrevious_should_callSkipToPrevious() {
        sut.skipToPrevious()

        verify(audioRepository).skipToPrevious()
    }

    @Test
    fun playOrPause_should_callPause_when_currentStateIsPlaying() {
        `when`(audioRepository.getCurrentPlayingState()).thenReturn(PlayingState.Playing)

        sut.playOrPause()

        verify(audioRepository).pause()
    }

    @Test
    fun playOrPause_should_callContinuePlaying_when_currentStateIsPaused() {
        `when`(audioRepository.getCurrentPlayingState()).thenReturn(PlayingState.Paused)

        sut.playOrPause()

        verify(audioRepository).continuePlaying()
    }

    @Test
    fun playOrPause_should_callContinuePlaying_when_currentStateIsStopped() {
        `when`(audioRepository.getCurrentPlayingState()).thenReturn(PlayingState.Stopped)

        sut.playOrPause()

        verify(audioRepository).continuePlaying()
    }
}