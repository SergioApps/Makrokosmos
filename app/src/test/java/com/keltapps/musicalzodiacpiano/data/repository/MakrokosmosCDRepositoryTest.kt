package com.keltapps.musicalzodiacpiano.data.repository

import com.google.common.truth.Truth.assertThat
import com.keltapps.musicalzodiacpiano.R
import com.keltapps.musicalzodiacpiano.base.resourceprovider.ResourceProvider
import com.keltapps.musicalzodiacpiano.song.data.repository.MakrokosmosCDRepository
import com.keltapps.musicalzodiacpiano.song.domain.entity.*
import org.junit.*
import org.mockito.*
import org.mockito.Mockito.`when`


class MakrokosmosCDRepositoryTest {

    private lateinit var sut: MakrokosmosCDRepository

    @Mock
    private lateinit var resourceProvider: ResourceProvider

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

        const val cdTitle = "Makrokosmos"

        const val volumeITitle = "Volume I"
        const val volumeIFirstSongTitle = "Primeval Sounds"
        const val volumeIFirstSongSubTitle = "Genesis I"
        const val volumeISecondSongTitle = "Proteus"
        const val volumeIThirdSongTitle = "Pastorale"
        const val volumeIThirdSongSubTitle = "From the Kingdom of Atlantis, ca. 10,000 B.C."
        const val volumeIFourthSongTitle = "Crucifixus"

        const val volumeIFifthSongTitle = "The Phantom Gondolier"
        const val volumeISixthSongTitle = "Night-Spell I"
        const val volumeISeventhSongTitle = "Music of Shadows"
        const val volumeISeventhSongSubTitle = "For Aeolian Harp"
        const val volumeIEighthSongTitle = "The Magic Circle of Infinity"
        const val volumeIEighthSongSubTitle = "Moto Perpetuo [SYMBOL]"

        const val volumeINinthSongTitle = "The Abyss of Time"
        const val volumeITenthSongTitle = "Spring-Fire"
        const val volumeIEleventhSongTitle = "Dream Images"
        const val volumeIEleventhSongSubTitle = "Love-Death Music"
        const val volumeITwelfthSongTitle = "Spiral Galaxy"
        const val volumeITwelfthSongSubTitle = "[SYMBOL]"

        const val volumeIITitle = "Volume II"
        const val volumeIIFirstSongTitle = "Morning Music"
        const val volumeIIFirstSongSubTitle = "Genesis II"
        const val volumeIISecondSongTitle = "The Mystic Chord"
        const val volumeIIThirdSongTitle = "Rain-Death Variations"
        const val volumeIIFourthSongTitle = "Twin Suns"
        const val volumeIIFourthSongSubTitle = "DoppelgÑnger aus der Ewigkeit [SYMBOL]"

        const val volumeIIFifthSongTitle = "Ghost-Nocturne: for the Druids of Stonehenge"
        const val volumeIIFifthSongSubTitle = "Night-Spell II"
        const val volumeIISixthSongTitle = "Gargoyles"
        const val volumeIISeventhSongTitle = "Tora! Tora! Tora!"
        const val volumeIISeventhSongSubTitle = "Cadenza Apocalittica"
        const val volumeIIEighthSongTitle = "A Prophecy of Nostradamus"
        const val volumeIIEighthSongSubTitle = "[SYMBOL]"

        const val volumeIINinthSongTitle = "Cosmic wind"
        const val volumeIITenthSongTitle = "Voices from \"Corona Borealis\""
        const val volumeIIEleventhSongTitle = "Litany of the Galactic Bells"
        const val volumeIITwelfthSongTitle = "Agnus Dei"
        const val volumeIITwelfthSongSubTitle = "[SYMBOL]"
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MakrokosmosCDRepository(resourceProvider)
        mockStrings()
    }

    @Test
    fun getCD_should_returnMakrokosmosCD() {

        val testObserver = sut.getCD().test()

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

        `when`(resourceProvider.getString(R.string.volumeIITitle)).thenReturn(volumeIITitle)
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

    @Test
    fun getSong_should_returnVolumeISeventhSong_when_idIsVolumeISeventhSong() {
        val songId = "7. Music of Shadows (for Aeolian Harp) (Libra).mp3"

        val testObserver = sut.getSong(songId).test()

        testObserver.assertValue { it.title == volumeISeventhSongTitle }
                .assertValue { it.subTitle == volumeISeventhSongSubTitle }
                .assertValue { it.zodiacSign == ZodiacSign.Libra(resourceProvider.getString(R.string.zodiacSign_libra)) }
    }

    @Test
    fun getSong_should_returnVolume2EleventhSong_when_idIsVolume2EleventhSong() {
        val songId = "23. Litany of the Galactic Bells (Leo).mp3"

        val testObserver = sut.getSong(songId).test()

        testObserver.assertValue { it.title == volumeIIEleventhSongTitle }
                .assertValue { it.subTitle == null }
                .assertValue { it.zodiacSign == ZodiacSign.Leo(resourceProvider.getString(R.string.zodiacSign_leo)) }
    }
}