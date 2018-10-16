package com.keltapps.makrokosmos.song.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.song.domain.entity.*
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MakrokosmosSongItemViewModelTest {

    private companion object {
        const val TITLE = "title"
        const val ARIES = "Aries"
        const val TAURUS = "Taurus"
        const val GEMINI = "Gemini"
        const val CANCER = "Cancer"
        const val LEO = "Leo"
        const val VIRGO = "Virgo"
        const val LIBRA = "Libra"
        const val SCORPIO = "Scorpio"
        const val SAGITTARIUS = "Sagittarius"
        const val CAPRICORN = "Capricorn"
        const val AQUARIUS = "Aquarius"
        const val PISCES = "Pisces"
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val sut = MakrokosmosSongItemViewModel()

    @Mock
    private lateinit var mockSong: Song

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(mockSong.title).thenReturn(TITLE)
        `when`(mockSong.zodiacSign).thenReturn(ZodiacSign.Aries(ARIES))
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
    fun setSong_should_setZodiacSignAries_when_zodiacSignIsAries() {
        `when`(mockSong.zodiacSign).thenReturn(ZodiacSign.Aries(ARIES))

        sut.setSong(mockSong)

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.aries)
        assertThat(sut.subTitle.value).isEqualTo(ARIES)
    }

    @Test
    fun setSong_should_setZodiacSignTaurus_when_zodiacSignIsTaurus() {
        `when`(mockSong.zodiacSign).thenReturn(ZodiacSign.Taurus(TAURUS))

        sut.setSong(mockSong)

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.taurus)
        assertThat(sut.subTitle.value).isEqualTo(TAURUS)
    }

    @Test
    fun setSong_should_setZodiacSignGemini_when_zodiacSignIsGemini() {
        `when`(mockSong.zodiacSign).thenReturn(ZodiacSign.Gemini(GEMINI))

        sut.setSong(mockSong)

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.gemini)
        assertThat(sut.subTitle.value).isEqualTo(GEMINI)
    }

    @Test
    fun setSong_should_setZodiacSignCancer_when_zodiacSignIsCancer() {
        `when`(mockSong.zodiacSign).thenReturn(ZodiacSign.Cancer(CANCER))

        sut.setSong(mockSong)

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.cancer)
        assertThat(sut.subTitle.value).isEqualTo(CANCER)
    }

    @Test
    fun setSong_should_setZodiacSignLeo_when_zodiacSignIsLeo() {
        `when`(mockSong.zodiacSign).thenReturn(ZodiacSign.Leo(LEO))

        sut.setSong(mockSong)

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.leo)
        assertThat(sut.subTitle.value).isEqualTo(LEO)
    }

    @Test
    fun setSong_should_setZodiacSignVirgo_when_zodiacSignIsVirgo() {
        `when`(mockSong.zodiacSign).thenReturn(ZodiacSign.Virgo(VIRGO))

        sut.setSong(mockSong)

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.virgo)
        assertThat(sut.subTitle.value).isEqualTo(VIRGO)
    }

    @Test
    fun setSong_should_setZodiacSignLibra_when_zodiacSignIsLibra() {
        `when`(mockSong.zodiacSign).thenReturn(ZodiacSign.Libra(LIBRA))

        sut.setSong(mockSong)

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.libra)
        assertThat(sut.subTitle.value).isEqualTo(LIBRA)
    }

    @Test
    fun setSong_should_setZodiacSignScorpio_when_zodiacSignIsScorpio() {
        `when`(mockSong.zodiacSign).thenReturn(ZodiacSign.Scorpio(SCORPIO))

        sut.setSong(mockSong)

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.scorpio)
        assertThat(sut.subTitle.value).isEqualTo(SCORPIO)
    }

    @Test
    fun setSong_should_setZodiacSignSagittarius_when_zodiacSignIsSagittarius() {
        `when`(mockSong.zodiacSign).thenReturn(ZodiacSign.Sagittarius(SAGITTARIUS))

        sut.setSong(mockSong)

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.sagittarius)
        assertThat(sut.subTitle.value).isEqualTo(SAGITTARIUS)
    }

    @Test
    fun setSong_should_setZodiacSignCapricorn_when_zodiacSignIsCapricorn() {
        `when`(mockSong.zodiacSign).thenReturn(ZodiacSign.Capricorn(CAPRICORN))

        sut.setSong(mockSong)

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.capricorn)
        assertThat(sut.subTitle.value).isEqualTo(CAPRICORN)
    }

    @Test
    fun setSong_should_setZodiacSignAquarius_when_zodiacSignIsAquarius() {
        `when`(mockSong.zodiacSign).thenReturn(ZodiacSign.Aquarius(AQUARIUS))

        sut.setSong(mockSong)

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.aquarius)
        assertThat(sut.subTitle.value).isEqualTo(AQUARIUS)
    }

    @Test
    fun setSong_should_setZodiacSignPisces_when_zodiacSignIsPisces() {
        `when`(mockSong.zodiacSign).thenReturn(ZodiacSign.Pisces(PISCES))

        sut.setSong(mockSong)

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.pisces)
        assertThat(sut.subTitle.value).isEqualTo(PISCES)
    }
}