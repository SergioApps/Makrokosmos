package com.keltapps.makrokosmos.song.presentation.detail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import android.graphics.drawable.Drawable
import com.google.common.truth.Truth.assertThat
import com.keltapps.makrokosmos.audio.client.domain.entity.PlayingState
import com.keltapps.makrokosmos.audio.client.domain.repository.AudioRepository
import com.keltapps.makrokosmos.song.data.repository.RxSchedulersOverrideRule
import com.keltapps.makrokosmos.song.domain.entity.*
import com.keltapps.makrokosmos.song.domain.iteractor.GetSongPlayingUseCase
import com.keltapps.makrokosmos.song.domain.repository.CDRepository
import com.keltapps.makrokosmos.song.presentation.ZodiacSignViewModel
import io.reactivex.*
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.*
import org.mockito.Mockito.*

class MakrokosmosSongDetailViewModelTest {

    private companion object {
        const val MEDIA_ID = "mediaId"
        const val TITLE = "title"
        const val SUBTITLE = "subtitle"
        const val ZODIAC_NAME = "aries"
        const val AIR_COLOR = 1
        const val FIRE_COLOR = 2
        const val EARTH_COLOR = 3
        const val WATER_COLOR = 4
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val overrideSchedulersRule = RxSchedulersOverrideRule()

    private lateinit var sut: MakrokosmosSongDetailViewModel

    @Mock
    private lateinit var zodiacSignViewModel: ZodiacSignViewModel
    @Mock
    private lateinit var mediaSeekBarViewModel: MediaSeekBarViewModel
    @Mock
    private lateinit var getSongPlayingUseCase: GetSongPlayingUseCase
    @Mock
    private lateinit var audioRepository: AudioRepository
    @Mock
    private lateinit var cdRepository: CDRepository

    @Mock
    private lateinit var mockSong: Song
    @Mock
    private lateinit var mockZodiacSign: ZodiacSign

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MakrokosmosSongDetailViewModel(
                zodiacSignViewModel,
                mediaSeekBarViewModel,
                getSongPlayingUseCase,
                audioRepository,
                cdRepository,
                AIR_COLOR,
                FIRE_COLOR,
                EARTH_COLOR,
                WATER_COLOR
        )
        with(mockSong) {
            `when`(title).thenReturn(TITLE)
            `when`(subTitle).thenReturn(SUBTITLE)
            `when`(zodiacSign).thenReturn(mockZodiacSign)
            `when`(mockZodiacSign.name).thenReturn(ZODIAC_NAME)
            `when`(mockZodiacSign.element).thenReturn(Element.Earth)
        }
        `when`(cdRepository.getSong(MEDIA_ID)).thenReturn(Single.just(mockSong))
        `when`(getSongPlayingUseCase.execute()).thenReturn(Observable.create {})
        `when`(audioRepository.getPlayingState()).thenReturn(Observable.create {})
    }

    @Test
    fun initialize_should_initializeViewModels() {
        sut.initialize(MEDIA_ID)

        verify(zodiacSignViewModel).initialize(mockZodiacSign)
        verify(mediaSeekBarViewModel).initialize(mockSong)
    }

    @Test
    fun initialize_should_setTitleAndSubTitleObservables() {
        sut.initialize(MEDIA_ID)

        assertThat(sut.title.value).isEqualTo(TITLE)
        assertThat(sut.subTitle.value).isEqualTo(SUBTITLE)
    }

    @Test
    fun initialize_should_setZodiacSignName() {
        sut.initialize(MEDIA_ID)

        assertThat(sut.zodiacSignName.value).isEqualTo(ZODIAC_NAME)
    }

    @Test
    fun initialize_should_setZodiacSignColorToAirColor_when_elementIsAir() {
        `when`(mockZodiacSign.element).thenReturn(Element.Air)

        sut.initialize(MEDIA_ID)

        assertThat(sut.zodiacSignColor.value).isEqualTo(AIR_COLOR)
    }

    @Test
    fun initialize_should_setZodiacSignColorToEarthColor_when_elementIsEarth() {
        `when`(mockZodiacSign.element).thenReturn(Element.Earth)

        sut.initialize(MEDIA_ID)

        assertThat(sut.zodiacSignColor.value).isEqualTo(EARTH_COLOR)
    }

    @Test
    fun initialize_should_setZodiacSignColorToFireColor_when_elementIsFire() {
        `when`(mockZodiacSign.element).thenReturn(Element.Fire)

        sut.initialize(MEDIA_ID)

        assertThat(sut.zodiacSignColor.value).isEqualTo(FIRE_COLOR)
    }

    @Test
    fun initialize_should_setZodiacSignColorToWaterColor_when_elementIsWater() {
        `when`(mockZodiacSign.element).thenReturn(Element.Water)

        sut.initialize(MEDIA_ID)

        assertThat(sut.zodiacSignColor.value).isEqualTo(WATER_COLOR)
    }

    @Test
    fun initialize_should_playSong() {
        sut.initialize(MEDIA_ID)

        verify(audioRepository).play(mockSong)
    }

    @Test
    fun initialize_should_updateObservables_when_getSongPlayingUseCasePushEvent() {
        val mockSong2 = mock(Song::class.java)
        val mockZodiacSign2 = mock(ZodiacSign::class.java)
        with(mockSong2) {
            `when`(title).thenReturn("title2")
            `when`(subTitle).thenReturn("subTitle2")
            `when`(zodiacSign).thenReturn(mockZodiacSign2)
            `when`(mockZodiacSign2.name).thenReturn("zodiacName2")
            `when`(mockZodiacSign2.element).thenReturn(Element.Air)
            `when`(durationInSeconds).thenReturn(200)
        }
        `when`(getSongPlayingUseCase.execute()).thenReturn(Observable.just(mockSong2))

        sut.initialize(MEDIA_ID)

        assertThat(sut.title.value).isEqualTo(mockSong2.title)
        assertThat(sut.subTitle.value).isEqualTo(mockSong2.subTitle)
        assertThat(sut.zodiacSignName.value).isEqualTo(mockZodiacSign2.name)
        assertThat(sut.zodiacSignColor.value).isEqualTo(AIR_COLOR)
    }

    @Test
    fun initialize_should_updateIsPlayingToFalse_when_getPlayingStateSentPause() {
        `when`(audioRepository.getPlayingState()).thenReturn(Observable.just(PlayingState.Paused))

        sut.initialize(MEDIA_ID)

        assertThat(sut.isPlaying.value).isFalse()
    }

    @Test
    fun initialize_should_updateIsPlayingToFalse_when_getPlayingStateSentStopped() {
        `when`(audioRepository.getPlayingState()).thenReturn(Observable.just(PlayingState.Stopped))

        sut.initialize(MEDIA_ID)

        assertThat(sut.isPlaying.value).isFalse()
    }

    @Test
    fun initialize_should_updateIsPlayingToTrue_when_getPlayingStateSentPlaying() {
        `when`(audioRepository.getPlayingState()).thenReturn(Observable.just(PlayingState.Playing))

        sut.initialize(MEDIA_ID)

        assertThat(sut.isPlaying.value).isTrue()
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
        sut.initialize(MEDIA_ID)

        sut.playOrPause()

        verify(audioRepository).pause()
    }

    @Test
    fun playOrPause_should_callContinuePlaying_when_currentStateIsPaused() {
        `when`(audioRepository.getCurrentPlayingState()).thenReturn(PlayingState.Paused)
        sut.initialize(MEDIA_ID)

        sut.playOrPause()

        verify(audioRepository).continuePlaying()
    }

    @Test
    fun playOrPause_should_callContinuePlaying_when_currentStateIsStopped() {
        `when`(audioRepository.getCurrentPlayingState()).thenReturn(PlayingState.Stopped)
        sut.initialize(MEDIA_ID)

        sut.playOrPause()

        verify(audioRepository).continuePlaying()
    }
}