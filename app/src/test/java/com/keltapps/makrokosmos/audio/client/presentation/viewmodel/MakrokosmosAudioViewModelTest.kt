package com.keltapps.makrokosmos.audio.client.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.keltapps.makrokosmos.audio.client.domain.entity.PlayingState
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import com.keltapps.makrokosmos.song.presentation.detail.viewmodel.MediaSeekBarViewModel
import io.reactivex.Observable
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.*
import org.mockito.Mockito.*

class MakrokosmosAudioViewModelTest {

    private companion object {
        const val SONG_ID = "songId"
        const val PROGRESS = 33
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var sut: MakrokosmosAudioViewModel

    @Mock
    private lateinit var audioRepository: AudioRepository
    @Mock
    private lateinit var mediaSeekBarViewModel: MediaSeekBarViewModel
    private val progressLiveData = MutableLiveData<Int>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(audioRepository.getSongIdPlaying()).thenReturn(Observable.create {})
        `when`(mediaSeekBarViewModel.progress).thenReturn(progressLiveData)
    }

    private fun initViewModel() {
        sut = MakrokosmosAudioViewModel(
                audioRepository,
                mediaSeekBarViewModel
        )
    }

    @Test
    fun skipToNext_should_callSkipToNext() {
        initViewModel()

        sut.skipToNext()

        verify(audioRepository).skipToNext()
    }

    @Test
    fun skipToPrevious_should_callSkipToPrevious() {
        initViewModel()

        sut.skipToPrevious()

        verify(audioRepository).skipToPrevious()
    }

    @Test
    fun playOrPause_should_callPause_when_currentStateIsPlaying() {
        initViewModel()
        `when`(audioRepository.getCurrentPlayingState()).thenReturn(PlayingState.Playing)

        sut.playOrPause()

        verify(audioRepository).pause()
    }

    @Test
    fun playOrPause_should_callContinuePlaying_when_currentStateIsPaused() {
        initViewModel()
        `when`(audioRepository.getCurrentPlayingState()).thenReturn(PlayingState.Paused)

        sut.playOrPause()

        verify(audioRepository).continuePlaying()
    }

    @Test
    fun playOrPause_should_callPlayAndSetProgress_when_currentStateIsStoppedAndSongIdReceived() {
        `when`(audioRepository.getSongIdPlaying()).thenReturn(Observable.just(SONG_ID))
        `when`(audioRepository.getCurrentPlayingState()).thenReturn(PlayingState.Stopped)
        initViewModel()
        progressLiveData.value = PROGRESS

        sut.playOrPause()

        verify(audioRepository).play(SONG_ID)
        verify(audioRepository).seekTo(PROGRESS.toLong())
    }

    @Test
    fun playOrPause_should_doNothing_when_currentStateIsStoppedAndSongIdNot() {
        `when`(audioRepository.getCurrentPlayingState()).thenReturn(PlayingState.Stopped)
        initViewModel()

        sut.playOrPause()

        verify(audioRepository, never()).play(anyString())
        verify(audioRepository, never()).seekTo(anyLong())
    }

    @Test
    fun playOrPause_should_callPlayAndNotSetProgress_when_currentStateIsStoppedAndSongIdReceivedAndProgressIsNull() {
        `when`(audioRepository.getSongIdPlaying()).thenReturn(Observable.just(SONG_ID))
        `when`(audioRepository.getCurrentPlayingState()).thenReturn(PlayingState.Stopped)
        initViewModel()

        sut.playOrPause()

        verify(audioRepository).play(SONG_ID)
        verify(audioRepository,never()).seekTo(anyLong())
    }
}