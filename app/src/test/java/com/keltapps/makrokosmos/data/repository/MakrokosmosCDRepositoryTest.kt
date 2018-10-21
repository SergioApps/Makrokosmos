package com.keltapps.makrokosmos.data.repository

import com.google.common.truth.Truth.assertThat
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.song.data.repository.MakrokosmosCDRepository
import com.keltapps.makrokosmos.song.data.repository.RxSchedulersOverrideRule
import com.keltapps.makrokosmos.base.resourceprovider.ResourceProvider
import com.keltapps.makrokosmos.song.domain.entity.*
import org.junit.*
import org.mockito.*
import org.mockito.Mockito.`when`


class MakrokosmosCDRepositoryTest {

    private lateinit var repository: MakrokosmosCDRepository

    @get:Rule
    val overrideSchedulersRule = RxSchedulersOverrideRule()

    @Mock
    private lateinit var resourceProvider: ResourceProvider

    private companion object {
        const val TITLE = "title"
        const val SUBTITLE = "subtitle"
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

    private val cdTitle = "Makrokosmos"

    private val volumeITitle = "Volume I"
    private val volumeIFirstSongTitle = "Primeval Sounds"
    private val volumeIFirstSongSubTitle = "Genesis I"
    private val volumeISecondSongTitle = "Proteus"
    private val volumeIThirdSongTitle = "Pastorale"
    private val volumeIThirdSongSubTitle = "From the Kingdom of Atlantis, ca. 10,000 B.C."
    private val volumeIFourthSongTitle = "Crucifixus"

    private val volumeIFifthSongTitle = "The Phantom Gondolier"
    private val volumeISixthSongTitle = "Night-Spell I"
    private val volumeISeventhSongTitle = "Music of Shadows"
    private val volumeISeventhSongSubTitle = "For Aeolian Harp"
    private val volumeIEighthSongTitle = "The Magic Circle of Infinity"
    private val volumeIEighthSongSubTitle = "Moto Perpetuo [SYMBOL]"

    private val volumeINinthSongTitle = "The Abyss of Time"
    private val volumeITenthSongTitle = "Spring-Fire"
    private val volumeIEleventhSongTitle = "Dream Images"
    private val volumeIEleventhSongSubTitle = "Love-Death Music"
    private val volumeITwelfthSongTitle = "Spiral Galaxy"
    private val volumeITwelfthSongSubTitle = "[SYMBOL]"

    private val volumeIITitle = "Volume II"
    private val volumeIIFirstSongTitle = "Morning Music"
    private val volumeIIFirstSongSubTitle = "Genesis II"
    private val volumeIISecondSongTitle = "The Mystic Chord"
    private val volumeIIThirdSongTitle = "Rain-Death Variations"
    private val volumeIIFourthSongTitle = "Twin Suns"
    private val volumeIIFourthSongSubTitle = "DoppelgÑnger aus der Ewigkeit [SYMBOL]"

    private val volumeIIFifthSongTitle = "Ghost-Nocturne: for the Druids of Stonehenge"
    private val volumeIIFifthSongSubTitle = "Night-Spell II"
    private val volumeIISixthSongTitle = "Gargoyles"
    private val volumeIISeventhSongTitle = "Tora! Tora! Tora!"
    private val volumeIISeventhSongSubTitle = "Cadenza Apocalittica"
    private val volumeIIEighthSongTitle = "A Prophecy of Nostradamus"
    private val volumeIIEighthSongSubTitle = "[SYMBOL]"

    private val volumeIINinthSongTitle = "Cosmic wind"
    private val volumeIITenthSongTitle = "Voices from \"Corona Borealis\""
    private val volumeIIEleventhSongTitle = "Litany of the Galactic Bells"
    private val volumeIITwelfthSongTitle = "Agnus Dei"
    private val volumeIITwelfthSongSubTitle = "[SYMBOL]"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = MakrokosmosCDRepository(resourceProvider)
    }

    @Test
    fun getCD_should_returnMakrokosmosCD() {
        mockStrings()

        val testObserver = repository.getCD().test()

        val cd = testObserver.values()[0]
        assertThat(cd.title).isEqualTo(cdTitle)
        assertThat(cd.volumeList.size).isEqualTo(2)
        assertVolumeI(cd.volumeList[0])
        assertVolumeII(cd.volumeList[1])
    }

    private fun mockStrings() {
        `when`(resourceProvider.getString(R.string.title_cd_title)).thenReturn(cdTitle)

        `when`(resourceProvider.getString(R.string.volumeITitle)).thenReturn(volumeITitle)
        `when`(resourceProvider.getString(R.string.volumeIFirstSongTitle)).thenReturn(volumeIFirstSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIFirstSongSubTitle)).thenReturn(volumeIFirstSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeISecondSongTitle)).thenReturn(volumeISecondSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIThirdSongTitle)).thenReturn(volumeIThirdSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIThirdSongSubTitle)).thenReturn(volumeIThirdSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIFourthSongTitle)).thenReturn(volumeIFourthSongTitle)

        `when`(resourceProvider.getString(R.string.volumeIFifthSongTitle)).thenReturn(volumeIFifthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeISixthSongTitle)).thenReturn(volumeISixthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeISeventhSongTitle)).thenReturn(volumeISeventhSongTitle)
        `when`(resourceProvider.getString(R.string.volumeISeventhSongSubTitle)).thenReturn(volumeISeventhSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIEighthSongTitle)).thenReturn(volumeIEighthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIEighthSongSubTitle)).thenReturn(volumeIEighthSongSubTitle)

        `when`(resourceProvider.getString(R.string.volumeINinthSongTitle)).thenReturn(volumeINinthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeITenthSongTitle)).thenReturn(volumeITenthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIEleventhSongTitle)).thenReturn(volumeIEleventhSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIEleventhSongSubTitle)).thenReturn(volumeIEleventhSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeITwelfthSongTitle)).thenReturn(volumeITwelfthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeITwelfthSongSubTitle)).thenReturn(volumeITwelfthSongSubTitle)

        `when`(resourceProvider.getString(R.string.volumeII)).thenReturn(volumeIITitle)
        `when`(resourceProvider.getString(R.string.volumeIIFirstSongTitle)).thenReturn(volumeIIFirstSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIIFirstSongSubTitle)).thenReturn(volumeIIFirstSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIISecondSongTitle)).thenReturn(volumeIISecondSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIIThirdSongTitle)).thenReturn(volumeIIThirdSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIIFourthSongTitle)).thenReturn(volumeIIFourthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIIFourthSongSubTitle)).thenReturn(volumeIIFourthSongSubTitle)

        `when`(resourceProvider.getString(R.string.volumeIIFifthSongTitle)).thenReturn(volumeIIFifthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIIFifthSongSubTitle)).thenReturn(volumeIIFifthSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIISixthSongTitle)).thenReturn(volumeIISixthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIISeventhSongTitle)).thenReturn(volumeIISeventhSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIISeventhSongSubTitle)).thenReturn(volumeIISeventhSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIIEighthSongTitle)).thenReturn(volumeIIEighthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIIEighthSongSubTitle)).thenReturn(volumeIIEighthSongSubTitle)

        `when`(resourceProvider.getString(R.string.volumeIINinthSongTitle)).thenReturn(volumeIINinthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIITenthSongTitle)).thenReturn(volumeIITenthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIIEleventhSongTitle)).thenReturn(volumeIIEleventhSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIITwelfthSongTitle)).thenReturn(volumeIITwelfthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIITwelfthSongSubTitle)).thenReturn(volumeIITwelfthSongSubTitle)


        `when`(resourceProvider.getString(R.string.zodiacSign_aries)).thenReturn(ARIES)
        `when`(resourceProvider.getString(R.string.zodiacSign_taurus)).thenReturn(TAURUS)
        `when`(resourceProvider.getString(R.string.zodiacSign_gemini)).thenReturn(GEMINI)
        `when`(resourceProvider.getString(R.string.zodiacSign_cancer)).thenReturn(CANCER)
        `when`(resourceProvider.getString(R.string.zodiacSign_leo)).thenReturn(LEO)
        `when`(resourceProvider.getString(R.string.zodiacSign_virgo)).thenReturn(VIRGO)
        `when`(resourceProvider.getString(R.string.zodiacSign_libra)).thenReturn(LIBRA)
        `when`(resourceProvider.getString(R.string.zodiacSign_scorpio)).thenReturn(SCORPIO)
        `when`(resourceProvider.getString(R.string.zodiacSign_sagittarius)).thenReturn(SAGITTARIUS)
        `when`(resourceProvider.getString(R.string.zodiacSign_capricorn)).thenReturn(CAPRICORN)
        `when`(resourceProvider.getString(R.string.zodiacSign_aquarius)).thenReturn(AQUARIUS)
        `when`(resourceProvider.getString(R.string.zodiacSign_pisces)).thenReturn(PISCES)
    }

    private fun assertVolumeI(volumeI: Volume) {
        with(volumeI) {
            assertThat(title).isEqualTo(volumeITitle)
            assertThat(songList.size).isEqualTo(12)
            assertSong(0, volumeIFirstSongTitle, volumeIFirstSongSubTitle, ZodiacSign.Cancer(resourceProvider.getString(R.string.zodiacSign_cancer)))
            assertSong(1, volumeISecondSongTitle, null, ZodiacSign.Pisces(resourceProvider.getString(R.string.zodiacSign_pisces)))
            assertSong(2, volumeIThirdSongTitle, volumeIThirdSongSubTitle, ZodiacSign.Taurus(resourceProvider.getString(R.string.zodiacSign_taurus)))
            assertSong(3, volumeIFourthSongTitle, null, ZodiacSign.Capricorn(resourceProvider.getString(R.string.zodiacSign_capricorn)))
            assertSong(4, volumeIFifthSongTitle, null, ZodiacSign.Scorpio(resourceProvider.getString(R.string.zodiacSign_scorpio)))
            assertSong(5, volumeISixthSongTitle, null, ZodiacSign.Sagittarius(resourceProvider.getString(R.string.zodiacSign_sagittarius)))
            assertSong(6, volumeISeventhSongTitle, volumeISeventhSongSubTitle, ZodiacSign.Libra(resourceProvider.getString(R.string.zodiacSign_libra)))
            assertSong(7, volumeIEighthSongTitle, volumeIEighthSongSubTitle, ZodiacSign.Leo(resourceProvider.getString(R.string.zodiacSign_leo)))
            assertSong(8, volumeINinthSongTitle, null, ZodiacSign.Virgo(resourceProvider.getString(R.string.zodiacSign_virgo)))
            assertSong(9, volumeITenthSongTitle, null, ZodiacSign.Aries(resourceProvider.getString(R.string.zodiacSign_aries)))
            assertSong(10, volumeIEleventhSongTitle, volumeIEleventhSongSubTitle, ZodiacSign.Gemini(resourceProvider.getString(R.string.zodiacSign_gemini)))
            assertSong(11, volumeITwelfthSongTitle, volumeITwelfthSongSubTitle, ZodiacSign.Aquarius(resourceProvider.getString(R.string.zodiacSign_aquarius)))
        }
    }

    private fun Volume.assertSong(songIndex: Int, title: String, subTitle: String?, zodiacSign: ZodiacSign) {
        assertThat(songList[songIndex].title).isEqualTo(title)
        assertThat(songList[songIndex].subTitle).isEqualTo(subTitle)
        assertThat(songList[songIndex].zodiacSign).isEqualTo(zodiacSign)
    }

    private fun assertVolumeII(volumeII: Volume) {
        with(volumeII) {
            assertThat(title).isEqualTo(volumeIITitle)
            assertThat(songList.size).isEqualTo(12)
            assertSong(0, volumeIIFirstSongTitle, volumeIIFirstSongSubTitle, ZodiacSign.Cancer(resourceProvider.getString(R.string.zodiacSign_cancer)))
            assertSong(1, volumeIISecondSongTitle, null, ZodiacSign.Sagittarius(resourceProvider.getString(R.string.zodiacSign_sagittarius)))
            assertSong(2, volumeIIThirdSongTitle, null, ZodiacSign.Pisces(resourceProvider.getString(R.string.zodiacSign_pisces)))
            assertSong(3, volumeIIFourthSongTitle, volumeIIFourthSongSubTitle, ZodiacSign.Gemini(resourceProvider.getString(R.string.zodiacSign_gemini)))
            assertSong(4, volumeIIFifthSongTitle, volumeIIFifthSongSubTitle, ZodiacSign.Virgo(resourceProvider.getString(R.string.zodiacSign_virgo)))
            assertSong(5, volumeIISixthSongTitle, null, ZodiacSign.Taurus(resourceProvider.getString(R.string.zodiacSign_taurus)))
            assertSong(6, volumeIISeventhSongTitle, volumeIISeventhSongSubTitle, ZodiacSign.Scorpio(resourceProvider.getString(R.string.zodiacSign_scorpio)))
            assertSong(7, volumeIIEighthSongTitle, volumeIIEighthSongSubTitle, ZodiacSign.Aries(resourceProvider.getString(R.string.zodiacSign_aries)))
            assertSong(8, volumeIINinthSongTitle, null, ZodiacSign.Libra(resourceProvider.getString(R.string.zodiacSign_libra)))
            assertSong(9, volumeIITenthSongTitle, null, ZodiacSign.Aquarius(resourceProvider.getString(R.string.zodiacSign_aquarius)))
            assertSong(10, volumeIIEleventhSongTitle, null, ZodiacSign.Leo(resourceProvider.getString(R.string.zodiacSign_leo)))
            assertSong(11, volumeIITwelfthSongTitle, volumeIITwelfthSongSubTitle, ZodiacSign.Capricorn(resourceProvider.getString(R.string.zodiacSign_capricorn)))
        }
    }
}