package com.keltapps.makrokosmos.song.presentation.list.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.base.presentation.SingleLiveEvent
import com.keltapps.makrokosmos.navigation.Navigator
import com.keltapps.makrokosmos.song.domain.entity.*
import com.keltapps.makrokosmos.song.presentation.ZodiacSignViewModel
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MakrokosmosSongItemViewModelTest {

    private companion object {
        const val TITLE = "title"
        const val ARIES = "Aries"
        const val TAURUS = "Taurus"
        const val GEMINI = "Gemini"
        const val CANCER = "Cancer"
        const val SONG_ID = "songID"
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var sut: MakrokosmosSongItemViewModel

    @Mock
    private lateinit var zodiacSignViewModel: ZodiacSignViewModel
    private val openSongDetail = SingleLiveEvent<String>()

    @Mock
    private lateinit var mockSong: Song

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MakrokosmosSongItemViewModel(
                zodiacSignViewModel,
                openSongDetail
        )
        `when`(mockSong.title).thenReturn(TITLE)
        `when`(mockSong.zodiacSign).thenReturn(ZodiacSign.Aries(ARIES))
    }


    @Test
    fun setSong_should_initializeZodiacSignViewModel() {
        `when`(mockSong.zodiacSign).thenReturn(ZodiacSign.Aries(ARIES))

        sut.setSong(mockSong)

        verify(zodiacSignViewModel).initialize(mockSong.zodiacSign)
    }

    @Test
    fun setSong_should_setTitle() {

        sut.setSong(mockSong)

        assertThat(sut.title.value).isEqualTo(TITLE)
    }

    @Test
    fun setSong_should_backGroundGradientFire_when_elementIsFire() {
        `when`(mockSong.zodiacSign).thenReturn(ZodiacSign.Aries(ARIES))

        sut.setSong(mockSong)

        assertThat(sut.backgroundElement.value).isEqualTo(R.drawable.gradient_fire)
    }

    @Test
    fun setSong_should_backGroundGradientEarth_when_elementIsEarth() {
        `when`(mockSong.zodiacSign).thenReturn(ZodiacSign.Taurus(TAURUS))

        sut.setSong(mockSong)

        assertThat(sut.backgroundElement.value).isEqualTo(R.drawable.gradient_earth)
    }

    @Test
    fun setSong_should_backGroundGradientAir_when_elementIsAir() {
        `when`(mockSong.zodiacSign).thenReturn(ZodiacSign.Gemini(GEMINI))

        sut.setSong(mockSong)

        assertThat(sut.backgroundElement.value).isEqualTo(R.drawable.gradient_air)
    }

    @Test
    fun setSong_should_backGroundGradientWater_when_elementIsWater() {
        `when`(mockSong.zodiacSign).thenReturn(ZodiacSign.Cancer(CANCER))

        sut.setSong(mockSong)

        assertThat(sut.backgroundElement.value).isEqualTo(R.drawable.gradient_water)
    }

    @Test
    fun setSong_should_setZodiacSign() {
        `when`(mockSong.zodiacSign).thenReturn(ZodiacSign.Aries(ARIES))

        sut.setSong(mockSong)

        assertThat(sut.subTitle.value).isEqualTo(ARIES)
    }

    @Test
    fun openSongDetail_should_callOpenSongDetail() {
        `when`(mockSong.id).thenReturn(SONG_ID)
        sut.setSong(mockSong)

        sut.openSongDetail()

        assertThat(openSongDetail.value).isEqualTo(SONG_ID)
    }
}