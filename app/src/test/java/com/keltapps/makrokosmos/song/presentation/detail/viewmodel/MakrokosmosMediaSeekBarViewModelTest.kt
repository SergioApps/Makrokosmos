package com.keltapps.makrokosmos.song.presentation.detail.viewmodel

import android.animation.ValueAnimator
import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.keltapps.makrokosmos.audio.client.domain.entity.PlayingState
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import com.keltapps.makrokosmos.song.data.repository.RxSchedulersOverrideRule
import com.keltapps.makrokosmos.song.domain.entity.Song
import com.keltapps.makrokosmos.song.domain.iteractor.GetSongPlayingUseCase
import io.reactivex.Observable
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.*
import org.mockito.Mockito.*

class MakrokosmosMediaSeekBarViewModelTest {

    private companion object {
        const val CURRENT_POSITION = 1L
        const val DURATION = 5
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val overrideSchedulersRule = RxSchedulersOverrideRule()

    private lateinit var sut: MakrokosmosMediaSeekBarViewModel

    @Mock
    private lateinit var audioRepository: AudioRepository
    @Mock
    private lateinit var mockValueAnimator: ValueAnimator
    @Mock
    private lateinit var getSongPlayingUseCase: GetSongPlayingUseCase

    @Mock
    private lateinit var mockSong: Song

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MakrokosmosMediaSeekBarViewModel(
                audioRepository,
                mockValueAnimator,
                getSongPlayingUseCase
        )
        `when`(audioRepository.getPlayingState()).thenReturn(Observable.create {})
        `when`(getSongPlayingUseCase.execute()).thenReturn(Observable.create {})
    }

    @Test
    fun initialize_should_setProgressTo0AndMaxToSongDuration_when_songChangeReceived() {
        `when`(getSongPlayingUseCase.execute()).thenReturn(Observable.just(mockSong))
        `when`(mockSong.durationInSeconds).thenReturn(DURATION)
        `when`(audioRepository.getCurrentPlayingState()).thenReturn(PlayingState.Paused)

        sut.initialize()

        assertThat(sut.progress.value).isEqualTo(0)
        assertThat(sut.maxValue.value).isEqualTo(DURATION)
        verify(mockValueAnimator).cancel()
    }

    @Test
    fun initialize_should_cancelAnimation_when_songChangeReceivedMaxValueIsSetAndPlayingStateIsPlaying() {
        sut.progress.value = CURRENT_POSITION.toInt()
        sut.maxValue.value = DURATION
        `when`(audioRepository.getCurrentPlayingState()).thenReturn(PlayingState.Playing)
        `when`(getSongPlayingUseCase.execute()).thenReturn(Observable.just(mockSong))
        `when`(mockSong.durationInSeconds).thenReturn(DURATION)

        sut.initialize()

        assertThat(sut.progress.value).isEqualTo(0)
        assertThat(sut.maxValue.value).isEqualTo(DURATION)
        verify(mockValueAnimator).setIntValues(0, DURATION)
        verify(mockValueAnimator).duration = DURATION.toLong()
        verify(mockValueAnimator).start()
    }

    @Test
    fun initialize_should_cancelAnimation_when_stateChangeReceivedMaxValueIsSetAndPlayingStateIsPaused() {
        sut.progress.value = CURRENT_POSITION.toInt()
        sut.maxValue.value = DURATION
        `when`(audioRepository.getPlayingState()).thenReturn(Observable.just(PlayingState.Paused))
        `when`(mockSong.durationInSeconds).thenReturn(DURATION)

        sut.initialize()

        verify(mockValueAnimator).cancel()
    }

    @Test
    fun initialize_should_cancelAnimation_when_stateChangeReceivedMaxValueIsSetAndPlayingStateIsStopped() {
        sut.progress.value = CURRENT_POSITION.toInt()
        sut.maxValue.value = DURATION
        `when`(audioRepository.getPlayingState()).thenReturn(Observable.just(PlayingState.Stopped))
        `when`(mockSong.durationInSeconds).thenReturn(DURATION)

        sut.initialize()

        verify(mockValueAnimator).cancel()
    }

    @Test
    fun initialize_should_doNothing_when_stateChangeReceivedMaxValueIsNotSetAndPlayingStateIsPlaying() {
        sut.progress.value = CURRENT_POSITION.toInt()
        `when`(audioRepository.getPlayingState()).thenReturn(Observable.just(PlayingState.Playing))
        `when`(mockSong.durationInSeconds).thenReturn(DURATION)

        sut.initialize()

        verifyZeroInteractions(mockValueAnimator)
    }

    @Test
    fun initialize_should_handleAnimation_when_stateChangeReceivedMaxValueIsSetAndPlayingStateIsPlaying() {
        sut.progress.value = CURRENT_POSITION.toInt()
        sut.maxValue.value = DURATION
        `when`(audioRepository.getPlayingState()).thenReturn(Observable.just(PlayingState.Playing))
        `when`(mockSong.durationInSeconds).thenReturn(DURATION)

        sut.initialize()

        verify(mockValueAnimator).setIntValues(CURRENT_POSITION.toInt(), DURATION)
        verify(mockValueAnimator).duration = (DURATION - CURRENT_POSITION)
        verify(mockValueAnimator).start()
    }

    @Test
    fun startTracking_should_cancelAnimation() {
        sut.startTracking()

        verify(mockValueAnimator).cancel()
    }

    @Test
    fun stopTracking_should_callSeekToWith0_when_progressIsNull() {
        `when`(audioRepository.getCurrentPlayingState()).thenReturn(PlayingState.Paused)

        sut.stopTracking()

        verify(audioRepository).seekTo(0)
        verify(mockValueAnimator).cancel()
    }

    @Test
    fun stopTracking_should_callSeekToWithProgressValue_when_progressIsNotNull() {
        sut.progress.value = CURRENT_POSITION.toInt()
        `when`(audioRepository.getCurrentPlayingState()).thenReturn(PlayingState.Stopped)

        sut.stopTracking()

        verify(audioRepository).seekTo(CURRENT_POSITION)
        verify(mockValueAnimator).cancel()
    }

    @Test
    fun stopTracking_should_handleAnimation_when_maxValueIsSetAndPlayingStateIsPlaying() {
        sut.progress.value = CURRENT_POSITION.toInt()
        sut.maxValue.value = DURATION
        `when`(audioRepository.getCurrentPlayingState()).thenReturn(PlayingState.Playing)

        sut.stopTracking()

        verify(audioRepository).seekTo(CURRENT_POSITION)
        verify(mockValueAnimator).setIntValues(CURRENT_POSITION.toInt(), DURATION)
        verify(mockValueAnimator).duration = DURATION - CURRENT_POSITION
        verify(mockValueAnimator).start()
    }

    @Test
    fun onAnimationUpdate_should_callSeekToWith0_when_progressIsNull() {
        `when`(mockValueAnimator.animatedValue).thenReturn(2)

        sut.onAnimationUpdate(mockValueAnimator)

        assertThat(sut.progress.value).isEqualTo(mockValueAnimator.animatedValue as Int)
    }
}