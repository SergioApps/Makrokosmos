package com.keltapps.makrokosmos.song.presentation.detail.viewmodel

import android.animation.ValueAnimator
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.keltapps.makrokosmos.audio.client.domain.entity.PlayingState
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import com.keltapps.makrokosmos.song.data.repository.RxSchedulersOverrideRule
import com.keltapps.makrokosmos.song.domain.entity.Song
import com.keltapps.makrokosmos.song.domain.iteractor.GetSongPlayingUseCase
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.*
import org.mockito.Mockito.*

class MakrokosmosMediaSeekBarViewModelTest {

    private companion object {
        const val CURRENT_POSITION = 1L
        const val DURATION = 5
        const val TIME_FORMAT = "%d:%02d"
        const val MILLISECOND_MULTIPLIER = 1000
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
        `when`(audioRepository.getPlayingState()).thenReturn(Observable.create {})
        `when`(getSongPlayingUseCase.execute()).thenReturn(Observable.create {})
    }

    private fun initViewModel() {
        sut = MakrokosmosMediaSeekBarViewModel(
                audioRepository,
                mockValueAnimator,
                getSongPlayingUseCase,
                TIME_FORMAT
        )
    }

    @Test
    fun init_should_setProgressToCurrentPositionAndMaxToSongDuration_when_songChangeReceived() {
        `when`(getSongPlayingUseCase.execute()).thenReturn(Observable.just(mockSong))
        `when`(mockSong.durationInSeconds).thenReturn(DURATION)
        `when`(audioRepository.getCurrentPlayingState()).thenReturn(PlayingState.Paused)
        `when`(audioRepository.getCurrentPositionInSeconds()).thenReturn(CURRENT_POSITION)

        initViewModel()

        assertThat(sut.progress.value).isEqualTo(CURRENT_POSITION * MILLISECOND_MULTIPLIER)
        assertThat(sut.maxValue.value).isEqualTo(DURATION * MILLISECOND_MULTIPLIER)
        verify(mockValueAnimator).pause()
    }

    @Test
    fun init_should_cancelAnimation_when_songChangeReceivedMaxValueIsSetAndPlayingStateIsPlaying() {
        `when`(audioRepository.getCurrentPlayingState()).thenReturn(PlayingState.Playing)
        val publishSubject = PublishSubject.create<Song>()
        `when`(getSongPlayingUseCase.execute()).thenReturn(publishSubject)
        `when`(mockSong.durationInSeconds).thenReturn(DURATION)
        initViewModel()
        sut.progress.value = (CURRENT_POSITION * MILLISECOND_MULTIPLIER).toInt()
        sut.maxValue.value = DURATION * MILLISECOND_MULTIPLIER

        publishSubject.onNext(mockSong)

        assertThat(sut.progress.value).isEqualTo(0)
        assertThat(sut.maxValue.value).isEqualTo(DURATION * MILLISECOND_MULTIPLIER)
        verify(mockValueAnimator).setIntValues(0, DURATION * MILLISECOND_MULTIPLIER)
        verify(mockValueAnimator).duration = (DURATION * MILLISECOND_MULTIPLIER).toLong()
        verify(mockValueAnimator).start()
    }

    @Test
    fun init_should_cancelAnimation_when_stateChangeReceivedMaxValueIsSetAndPlayingStateIsPaused() {
        `when`(audioRepository.getPlayingState()).thenReturn(Observable.just(PlayingState.Paused))
        `when`(mockSong.durationInSeconds).thenReturn(DURATION)

        initViewModel()
        sut.progress.value = (CURRENT_POSITION * MILLISECOND_MULTIPLIER).toInt()
        sut.maxValue.value = DURATION * MILLISECOND_MULTIPLIER

        verify(mockValueAnimator).pause()
    }

    @Test
    fun init_should_cancelAnimation_when_stateChangeReceivedMaxValueIsSetAndPlayingStateIsStopped() {
        `when`(audioRepository.getPlayingState()).thenReturn(Observable.just(PlayingState.Stopped))
        `when`(mockSong.durationInSeconds).thenReturn(DURATION)

        initViewModel()
        sut.progress.value = (CURRENT_POSITION * MILLISECOND_MULTIPLIER).toInt()
        sut.maxValue.value = DURATION * MILLISECOND_MULTIPLIER

        verify(mockValueAnimator).pause()
    }

    @Test
    fun init_should_doNothing_when_stateChangeReceivedMaxValueIsNotSetAndPlayingStateIsPlaying() {
        `when`(audioRepository.getPlayingState()).thenReturn(Observable.just(PlayingState.Playing))
        `when`(mockSong.durationInSeconds).thenReturn(DURATION)

        initViewModel()
        sut.progress.value = (CURRENT_POSITION * MILLISECOND_MULTIPLIER).toInt()

        verify(mockValueAnimator, never()).pause()
        verify(mockValueAnimator, never()).start()
    }

    @Test
    fun init_should_handleAnimation_when_stateChangeReceivedMaxValueIsSetAndPlayingStateIsPlaying() {
        val publishSubject = PublishSubject.create<PlayingState>()
        `when`(audioRepository.getPlayingState()).thenReturn(publishSubject)
        `when`(mockSong.durationInSeconds).thenReturn(DURATION)
        initViewModel()
        sut.progress.value = (CURRENT_POSITION * MILLISECOND_MULTIPLIER).toInt()
        sut.maxValue.value = DURATION * MILLISECOND_MULTIPLIER

        publishSubject.onNext(PlayingState.Playing)

        verify(mockValueAnimator).setIntValues(
                (CURRENT_POSITION * MILLISECOND_MULTIPLIER).toInt(),
                DURATION * MILLISECOND_MULTIPLIER
        )
        verify(mockValueAnimator).duration = (DURATION * MILLISECOND_MULTIPLIER - CURRENT_POSITION * MILLISECOND_MULTIPLIER)
        verify(mockValueAnimator).start()
    }

    @Test
    fun init_should_setDurationWith3Digits_when_secondsAreLessThan10() {
        `when`(getSongPlayingUseCase.execute()).thenReturn(Observable.just(mockSong))
        `when`(mockSong.durationInSeconds).thenReturn(189)

        initViewModel()

        assertThat(sut.duration.value).isEqualTo("3:09")
    }

    @Test
    fun init_should_setDurationWith4Digits_when_minutesAreMoreThan10() {
        `when`(getSongPlayingUseCase.execute()).thenReturn(Observable.just(mockSong))
        `when`(mockSong.durationInSeconds).thenReturn(600)

        initViewModel()

        assertThat(sut.duration.value).isEqualTo("10:00")
    }

    @Test
    fun init_should_setDuration() {
        `when`(getSongPlayingUseCase.execute()).thenReturn(Observable.just(mockSong))
        `when`(mockSong.durationInSeconds).thenReturn(200)

        initViewModel()

        assertThat(sut.duration.value).isEqualTo("3:20")
    }

    @Test
    fun startTracking_should_cancelAnimation() {
        initViewModel()

        sut.startTracking()

        verify(mockValueAnimator).pause()
    }

    @Test
    fun stopTracking_should_callSeekToWith0_when_progressIsNull() {
        initViewModel()
        `when`(audioRepository.getCurrentPlayingState()).thenReturn(PlayingState.Paused)

        sut.stopTracking()

        verify(audioRepository).seekTo(0)
        verify(mockValueAnimator).pause()
    }

    @Test
    fun stopTracking_should_callSeekToWithProgressValue_when_progressIsNotNull() {
        initViewModel()
        sut.progress.value = CURRENT_POSITION.toInt()
        `when`(audioRepository.getCurrentPlayingState()).thenReturn(PlayingState.Stopped)

        sut.stopTracking()

        verify(audioRepository).seekTo(CURRENT_POSITION)
        verify(mockValueAnimator).pause()
    }

    @Test
    fun stopTracking_should_handleAnimation_when_maxValueIsSetAndPlayingStateIsPlaying() {
        initViewModel()
        sut.progress.value = (CURRENT_POSITION * MILLISECOND_MULTIPLIER).toInt()
        sut.maxValue.value = DURATION * MILLISECOND_MULTIPLIER
        `when`(audioRepository.getCurrentPlayingState()).thenReturn(PlayingState.Playing)

        sut.stopTracking()

        verify(audioRepository).seekTo(CURRENT_POSITION * MILLISECOND_MULTIPLIER)
        verify(mockValueAnimator).setIntValues(CURRENT_POSITION.toInt() * MILLISECOND_MULTIPLIER, DURATION * MILLISECOND_MULTIPLIER)
        verify(mockValueAnimator).duration = DURATION * MILLISECOND_MULTIPLIER - CURRENT_POSITION * MILLISECOND_MULTIPLIER
        verify(mockValueAnimator).start()
    }

    @Test
    fun onAnimationUpdate_should_callSeekToWith0_when_progressIsNull() {
        initViewModel()
        `when`(mockValueAnimator.animatedValue).thenReturn(2)

        sut.onAnimationUpdate(mockValueAnimator)

        assertThat(sut.progress.value).isEqualTo(mockValueAnimator.animatedValue as Int)
    }
}