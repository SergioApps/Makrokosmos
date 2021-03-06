package com.keltapps.musicalzodiacpiano.song.presentation.detail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.keltapps.musicalzodiacpiano.audio.client.domain.entity.PlayingState
import com.keltapps.musicalzodiacpiano.audio.client.domain.repository.AudioRepository
import com.keltapps.musicalzodiacpiano.audio.client.presentation.viewmodel.AudioViewModel
import com.keltapps.musicalzodiacpiano.song.data.repository.RxSchedulersOverrideRule
import com.keltapps.musicalzodiacpiano.song.domain.entity.*
import com.keltapps.musicalzodiacpiano.song.domain.iteractor.GetSongPlayingUseCase
import com.keltapps.musicalzodiacpiano.song.domain.repository.CDRepository
import com.keltapps.musicalzodiacpiano.song.presentation.ZodiacSignViewModel
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
    private lateinit var audioViewModel: AudioViewModel

    @Mock
    private lateinit var mockSong: Song
    @Mock
    private lateinit var mockZodiacSign: ZodiacSign

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

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

    private fun initViewModel() {
        sut = MakrokosmosSongDetailViewModel(
                zodiacSignViewModel,
                mediaSeekBarViewModel,
                getSongPlayingUseCase,
                audioRepository,
                cdRepository,
                AIR_COLOR,
                FIRE_COLOR,
                EARTH_COLOR,
                WATER_COLOR,
                audioViewModel,
                MEDIA_ID
        )
    }

    @Test
    fun initialize_should_initializeViewModels() {
        initViewModel()

        verify(zodiacSignViewModel).initialize(mockZodiacSign)
    }

    @Test
    fun initialize_should_setTitleAndSubTitleObservables() {
        initViewModel()

        assertThat(sut.title.value).isEqualTo(TITLE)
        assertThat(sut.subTitle.value).isEqualTo(SUBTITLE)
    }

    @Test
    fun initialize_should_setZodiacSignName() {
        initViewModel()

        assertThat(sut.zodiacSignName.value).isEqualTo(ZODIAC_NAME)
    }

    @Test
    fun initialize_should_setZodiacSignColorToAirColor_when_elementIsAir() {
        `when`(mockZodiacSign.element).thenReturn(Element.Air)

        initViewModel()

        assertThat(sut.zodiacSignColor.value).isEqualTo(AIR_COLOR)
    }

    @Test
    fun initialize_should_setZodiacSignColorToEarthColor_when_elementIsEarth() {
        `when`(mockZodiacSign.element).thenReturn(Element.Earth)

        initViewModel()

        assertThat(sut.zodiacSignColor.value).isEqualTo(EARTH_COLOR)
    }

    @Test
    fun initialize_should_setZodiacSignColorToFireColor_when_elementIsFire() {
        `when`(mockZodiacSign.element).thenReturn(Element.Fire)

        initViewModel()

        assertThat(sut.zodiacSignColor.value).isEqualTo(FIRE_COLOR)
    }

    @Test
    fun initialize_should_setZodiacSignColorToWaterColor_when_elementIsWater() {
        `when`(mockZodiacSign.element).thenReturn(Element.Water)

        initViewModel()

        assertThat(sut.zodiacSignColor.value).isEqualTo(WATER_COLOR)
    }

    @Test
    fun initialize_should_playSong() {
        initViewModel()

        verify(audioRepository).play(mockSong.id)
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

        initViewModel()

        assertThat(sut.title.value).isEqualTo(mockSong2.title)
        assertThat(sut.subTitle.value).isEqualTo(mockSong2.subTitle)
        assertThat(sut.zodiacSignName.value).isEqualTo(mockZodiacSign2.name)
        assertThat(sut.zodiacSignColor.value).isEqualTo(AIR_COLOR)
    }

    @Test
    fun initialize_should_updateIsPlayingToFalse_when_getPlayingStateSentPause() {
        `when`(audioRepository.getPlayingState()).thenReturn(Observable.just(PlayingState.Paused))

        initViewModel()

        assertThat(sut.isPlaying.value).isFalse()
    }

    @Test
    fun initialize_should_updateIsPlayingToFalse_when_getPlayingStateSentStopped() {
        `when`(audioRepository.getPlayingState()).thenReturn(Observable.just(PlayingState.Stopped))

        initViewModel()

        assertThat(sut.isPlaying.value).isFalse()
    }

    @Test
    fun initialize_should_updateIsPlayingToTrue_when_getPlayingStateSentPlaying() {
        `when`(audioRepository.getPlayingState()).thenReturn(Observable.just(PlayingState.Playing))

        initViewModel()

        assertThat(sut.isPlaying.value).isTrue()
    }
}