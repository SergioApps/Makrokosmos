package com.keltapps.musicalzodiacpiano.song.presentation.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.keltapps.musicalzodiacpiano.R
import com.keltapps.musicalzodiacpiano.song.domain.entity.ZodiacSign
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MakrokosmosZodiacSignViewModelTest {

    private companion object {
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

    private lateinit var sut: MakrokosmosZodiacSignViewModel

    @Before
    fun setUp() {
        sut = MakrokosmosZodiacSignViewModel()
    }

    @Test
    fun initialize_should_setZodiacSignAries_when_zodiacSignIsAries() {
        sut.initialize(ZodiacSign.Aries(ARIES))

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.aries)
    }

    @Test
    fun initialize_should_setZodiacSignTaurus_when_zodiacSignIsTaurus() {
        sut.initialize(ZodiacSign.Taurus(TAURUS))

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.taurus)
    }

    @Test
    fun initialize_should_setZodiacSignGemini_when_zodiacSignIsGemini() {
        sut.initialize(ZodiacSign.Gemini(GEMINI))

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.gemini)
    }

    @Test
    fun initialize_should_setZodiacSignCancer_when_zodiacSignIsCancer() {
        sut.initialize(ZodiacSign.Cancer(CANCER))

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.cancer)
    }

    @Test
    fun initialize_should_setZodiacSignLeo_when_zodiacSignIsLeo() {
        sut.initialize(ZodiacSign.Leo(LEO))

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.leo)
    }

    @Test
    fun initialize_should_setZodiacSignVirgo_when_zodiacSignIsVirgo() {
        sut.initialize(ZodiacSign.Virgo(VIRGO))

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.virgo)
    }

    @Test
    fun initialize_should_setZodiacSignLibra_when_zodiacSignIsLibra() {
        sut.initialize(ZodiacSign.Libra(LIBRA))

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.libra)
    }

    @Test
    fun initialize_should_setZodiacSignScorpio_when_zodiacSignIsScorpio() {
        sut.initialize(ZodiacSign.Scorpio(SCORPIO))

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.scorpio)
    }

    @Test
    fun initialize_should_setZodiacSignSagittarius_when_zodiacSignIsSagittarius() {
        sut.initialize(ZodiacSign.Sagittarius(SAGITTARIUS))

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.sagittarius)
    }

    @Test
    fun initialize_should_setZodiacSignCapricorn_when_zodiacSignIsCapricorn() {
        sut.initialize(ZodiacSign.Capricorn(CAPRICORN))

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.capricorn)
    }

    @Test
    fun initialize_should_setZodiacSignAquarius_when_zodiacSignIsAquarius() {
        sut.initialize(ZodiacSign.Aquarius(AQUARIUS))

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.aquarius)
    }

    @Test
    fun initialize_should_setZodiacSignPisces_when_zodiacSignIsPisces() {
        sut.initialize(ZodiacSign.Pisces(PISCES))

        assertThat(sut.zodiacSign.value).isEqualTo(R.drawable.pisces)
    }
}