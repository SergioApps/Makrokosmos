package com.keltapps.makrokosmos.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.keltapps.makrokosmos.audio.client.domain.entity.PlayingState
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import com.keltapps.makrokosmos.audio.client.presentation.viewmodel.AudioViewModel
import com.keltapps.makrokosmos.song.data.repository.RxSchedulersOverrideRule
import com.keltapps.makrokosmos.song.domain.entity.*
import com.keltapps.makrokosmos.song.domain.iteractor.GetSongPlayingUseCase
import com.keltapps.makrokosmos.song.presentation.detail.viewmodel.MediaSeekBarViewModel
import io.reactivex.Observable
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.*
import org.mockito.Mockito.*

class MakrokosmosMainViewModelTest {

    private companion object {
        const val SONG_ID = "songId"
        const val TITLE = "title"
        const val ZODIAC_NAME = "aries"
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val overrideSchedulersRule = RxSchedulersOverrideRule()

    private lateinit var sut: MakrokosmosMainViewModel

    @Mock
    private lateinit var mediaSeekBarViewModel: MediaSeekBarViewModel
    @Mock
    private lateinit var getSongPlayingUseCase: GetSongPlayingUseCase
    @Mock
    private lateinit var audioRepository: AudioRepository
    @Mock
    private lateinit var audioViewModel: AudioViewModel

    @Mock
    private lateinit var mockSong: Song
    @Mock
    private lateinit var mockZodiacSign: ZodiacSign

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MakrokosmosMainViewModel(
                mediaSeekBarViewModel,
                getSongPlayingUseCase,
                audioRepository,
                audioViewModel
        )
        `when`(audioRepository.getPlayingState()).thenReturn(Observable.create {})
        `when`(getSongPlayingUseCase.execute()).thenReturn(Observable.create {})
    }

    @Test
    fun initialize_should_initializeMediaSeekBarViewModelAndSetTitleAndZodiacSignName_when_receiveSong() {
        mockGetSongPlayingUseCase()

        sut.initialize()

        assertThat(sut.title.value).isEqualTo(TITLE)
        assertThat(sut.zodiacSignName.value).isEqualTo(ZODIAC_NAME)
        verify(mediaSeekBarViewModel).initialize(mockSong)
    }

    private fun mockGetSongPlayingUseCase() {
        `when`(getSongPlayingUseCase.execute()).thenReturn(Observable.just(mockSong))
        with(mockSong) {
            `when`(id).thenReturn(SONG_ID)
            `when`(title).thenReturn(TITLE)
            `when`(zodiacSign).thenReturn(mockZodiacSign)
            `when`(mockZodiacSign.name).thenReturn(ZODIAC_NAME)
        }
    }

    @Test
    fun initialize_should_setVisibleAndIsPlayingToTrue_when_stateIsPlaying() {
        `when`(audioRepository.getPlayingState()).thenReturn(Observable.just(PlayingState.Playing))

        sut.initialize()

        assertThat(sut.isVisible.value).isTrue()
        assertThat(sut.isPlaying.value).isTrue()
    }

    @Test
    fun initialize_should_setVisibleToTrueAndIsPlayingToFalse_when_stateIsPaused() {
        `when`(audioRepository.getPlayingState()).thenReturn(Observable.just(PlayingState.Paused))

        sut.initialize()

        assertThat(sut.isVisible.value).isTrue()
        assertThat(sut.isPlaying.value).isFalse()
    }

    @Test
    fun initialize_should_setVisibleAndIsPlayingToFalse_when_stateIsStopped() {
        `when`(audioRepository.getPlayingState()).thenReturn(Observable.just(PlayingState.Stopped))

        sut.initialize()

        assertThat(sut.isVisible.value).isFalse()
        assertThat(sut.isPlaying.value).isFalse()
    }

    @Test
    fun openSongDetail_should_emitEvent_when_songHasBeingReceived() {
        mockGetSongPlayingUseCase()
        sut.initialize()

        sut.openSongDetail()

        assertThat(sut.openSongDetail.value).isEqualTo(SONG_ID)
    }

    @Test
    fun openSongDetail_should_doNothing_when_songHasNotBeingReceived() {
        sut.initialize()

        sut.openSongDetail()

        assertThat(sut.openSongDetail.value).isNull()
    }
}
