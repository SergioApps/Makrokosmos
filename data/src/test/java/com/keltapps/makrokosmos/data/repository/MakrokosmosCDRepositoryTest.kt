package com.keltapps.makrokosmos.data.repository

import com.google.common.truth.Truth.assertThat
import com.keltapps.makrokosmos.data.R
import com.keltapps.makrokosmos.data.repository.util.RxSchedulersOverrideRule
import com.keltapps.makrokosmos.data.resourceProvider.ResourceProvider
import com.keltapps.makrokosmos.domain.entity.*
import org.junit.*
import org.mockito.*
import org.mockito.Mockito.`when`


class MakrokosmosCDRepositoryTest {

    private lateinit var repository: MakrokosmosCDRepository

    @get:Rule
    val overrideSchedulersRule = RxSchedulersOverrideRule()

    @Mock
    private lateinit var resourceProvider: ResourceProvider

    private val cdTitle = "Makrokosmos"

    private val volumeITitle = "Volume I"
    private val volumeIFirstSongTitle = "Primeval Sounds"
    private val volumeIFirstSongSubTitle = "Genesis II Cancer"
    private val volumeISecondSongTitle = "Proteus"
    private val volumeISecondSongSubTitle = "Pisces"
    private val volumeIThirdSongTitle = "Pastorale"
    private val volumeIThirdSongSubTitle = "From the Kingdom of Atlantis, ca. 10,000 B.C. Taurus"
    private val volumeIFourthSongTitle = "Crucifixus"
    private val volumeIFourthSongSubTitle = "Capricorn"

    private val volumeIFifthSongTitle = "The Phantom Gondolier"
    private val volumeIFifthSongSubTitle = "Scorpio"
    private val volumeISixthSongTitle = "Night-Spell I"
    private val volumeISixthSongSubTitle = "Sagittarius"
    private val volumeISeventhSongTitle = "Music of Shadows"
    private val volumeISeventhSongSubTitle = "For Aeolian Harp Libra"
    private val volumeIEighthSongTitle = "The Magic Circle of Infinity"
    private val volumeIEighthSongSubTitle = "Moto Perpetuo Leo"

    private val volumeINinthSongTitle = "The Abyss of Time"
    private val volumeINinthSongSubTitle = "Virgo"
    private val volumeITenthSongTitle = "Spring-Fire"
    private val volumeITenthSongSubTitle = "Aries"
    private val volumeIEleventhSongTitle = "Dream Images"
    private val volumeIEleventhSongSubTitle = "Love-Death Music Gemini"
    private val volumeITwelfthSongTitle = "Spiral Galaxy"
    private val volumeITwelfthSongSubTitle = "Aquarius"

    private val volumeIITitle = "Volume II"
    private val volumeIIFirstSongTitle = "Morning Music"
    private val volumeIIFirstSongSubTitle = "Genesis II Cancer"
    private val volumeIISecondSongTitle = "The Mystic Chord"
    private val volumeIISecondSongSubTitle = "Sagittarius"
    private val volumeIIThirdSongTitle = "Rain-Death Variations"
    private val volumeIIThirdSongSubTitle = "Pisces"
    private val volumeIIFourthSongTitle = "Twin Suns"
    private val volumeIIFourthSongSubTitle = "Gemini"

    private val volumeIIFifthSongTitle = "Ghost-Nocturne: for the Druids of Stonehenge"
    private val volumeIIFifthSongSubTitle = "Night-Spell II Virgo"
    private val volumeIISixthSongTitle = "Gargoyles"
    private val volumeIISixthSongSubTitle = "Taurus"
    private val volumeIISeventhSongTitle = "Tora! Tora! Tora!"
    private val volumeIISeventhSongSubTitle = "Cadenza Apocalittica Scorpio"
    private val volumeIIEighthSongTitle = "A Prophecy of Nostradamus"
    private val volumeIIEighthSongSubTitle = "Aries"

    private val volumeIINinthSongTitle = "Cosmic wind"
    private val volumeIINinthSongSubTitle = "Libra"
    private val volumeIITenthSongTitle = "Voices from \"Corona Borealis\""
    private val volumeIITenthSongSubTitle = "Aquarius"
    private val volumeIIEleventhSongTitle = "Litany of the Galactic Bells"
    private val volumeIIEleventhSongSubTitle = "Leo"
    private val volumeIITwelfthSongTitle = "Agnus Dei"
    private val volumeIITwelfthSongSubTitle = "Capricorn"

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
        `when`(resourceProvider.getString(R.string.volumeISecondSongSubTitle)).thenReturn(volumeISecondSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIThirdSongTitle)).thenReturn(volumeIThirdSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIThirdSongSubTitle)).thenReturn(volumeIThirdSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIFourthSongTitle)).thenReturn(volumeIFourthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIFourthSongSubTitle)).thenReturn(volumeIFourthSongSubTitle)

        `when`(resourceProvider.getString(R.string.volumeIFifthSongTitle)).thenReturn(volumeIFifthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIFifthSongSubTitle)).thenReturn(volumeIFifthSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeISixthSongTitle)).thenReturn(volumeISixthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeISixthSongSubTitle)).thenReturn(volumeISixthSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeISeventhSongTitle)).thenReturn(volumeISeventhSongTitle)
        `when`(resourceProvider.getString(R.string.volumeISeventhSongSubTitle)).thenReturn(volumeISeventhSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIEighthSongTitle)).thenReturn(volumeIEighthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIEighthSongSubTitle)).thenReturn(volumeIEighthSongSubTitle)

        `when`(resourceProvider.getString(R.string.volumeINinthSongTitle)).thenReturn(volumeINinthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeINinthSongSubTitle)).thenReturn(volumeINinthSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeITenthSongTitle)).thenReturn(volumeITenthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeITenthSongSubTitle)).thenReturn(volumeITenthSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIEleventhSongTitle)).thenReturn(volumeIEleventhSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIEleventhSongSubTitle)).thenReturn(volumeIEleventhSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeITwelfthSongTitle)).thenReturn(volumeITwelfthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeITwelfthSongSubTitle)).thenReturn(volumeITwelfthSongSubTitle)

        `when`(resourceProvider.getString(R.string.volumeII)).thenReturn(volumeIITitle)
        `when`(resourceProvider.getString(R.string.volumeIIFirstSongTitle)).thenReturn(volumeIIFirstSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIIFirstSongSubTitle)).thenReturn(volumeIIFirstSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIISecondSongTitle)).thenReturn(volumeIISecondSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIISecondSongSubTitle)).thenReturn(volumeIISecondSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIIThirdSongTitle)).thenReturn(volumeIIThirdSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIIThirdSongSubTitle)).thenReturn(volumeIIThirdSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIIFourthSongTitle)).thenReturn(volumeIIFourthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIIFourthSongSubTitle)).thenReturn(volumeIIFourthSongSubTitle)

        `when`(resourceProvider.getString(R.string.volumeIIFifthSongTitle)).thenReturn(volumeIIFifthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIIFifthSongSubTitle)).thenReturn(volumeIIFifthSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIISixthSongTitle)).thenReturn(volumeIISixthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIISixthSongSubTitle)).thenReturn(volumeIISixthSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIISeventhSongTitle)).thenReturn(volumeIISeventhSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIISeventhSongSubTitle)).thenReturn(volumeIISeventhSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIIEighthSongTitle)).thenReturn(volumeIIEighthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIIEighthSongSubTitle)).thenReturn(volumeIIEighthSongSubTitle)

        `when`(resourceProvider.getString(R.string.volumeIINinthSongTitle)).thenReturn(volumeIINinthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIINinthSongSubTitle)).thenReturn(volumeIINinthSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIITenthSongTitle)).thenReturn(volumeIITenthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIITenthSongSubTitle)).thenReturn(volumeIITenthSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIIEleventhSongTitle)).thenReturn(volumeIIEleventhSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIIEleventhSongSubTitle)).thenReturn(volumeIIEleventhSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIITwelfthSongTitle)).thenReturn(volumeIITwelfthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIITwelfthSongSubTitle)).thenReturn(volumeIITwelfthSongSubTitle)
    }

    private fun assertVolumeI(volumeI: Volume) {
        with(volumeI) {
            assertThat(title).isEqualTo(volumeITitle)
            assertThat(songList.size).isEqualTo(12)
            assertSong(0, volumeIFirstSongTitle, volumeIFirstSongSubTitle, Element.Water)
            assertSong(1, volumeISecondSongTitle, volumeISecondSongSubTitle, Element.Water)
            assertSong(2, volumeIThirdSongTitle, volumeIThirdSongSubTitle, Element.Earth)
            assertSong(3, volumeIFourthSongTitle, volumeIFourthSongSubTitle, Element.Earth)
            assertSong(4, volumeIFifthSongTitle, volumeIFifthSongSubTitle, Element.Water)
            assertSong(5, volumeISixthSongTitle, volumeISixthSongSubTitle, Element.Fire)
            assertSong(6, volumeISeventhSongTitle, volumeISeventhSongSubTitle, Element.Air)
            assertSong(7, volumeIEighthSongTitle, volumeIEighthSongSubTitle, Element.Fire)
            assertSong(8, volumeINinthSongTitle, volumeINinthSongSubTitle, Element.Earth)
            assertSong(9, volumeITenthSongTitle, volumeITenthSongSubTitle, Element.Fire)
            assertSong(10, volumeIEleventhSongTitle, volumeIEleventhSongSubTitle, Element.Air)
            assertSong(11, volumeITwelfthSongTitle, volumeITwelfthSongSubTitle, Element.Air)
        }
    }

    private fun Volume.assertSong(songIndex: Int, title: String, subTitle: String, element: Element) {
        assertThat(songList[songIndex].title).isEqualTo(title)
        assertThat(songList[songIndex].subTitle).isEqualTo(subTitle)
        assertThat(songList[songIndex].element).isEqualTo(element)
    }

    private fun assertVolumeII(volumeII: Volume) {
        with(volumeII) {
            assertThat(title).isEqualTo(volumeIITitle)
            assertThat(songList.size).isEqualTo(12)
            assertSong(0, volumeIIFirstSongTitle, volumeIIFirstSongSubTitle, Element.Water)
            assertSong(1, volumeIISecondSongTitle, volumeIISecondSongSubTitle, Element.Fire)
            assertSong(2, volumeIIThirdSongTitle, volumeIIThirdSongSubTitle, Element.Water)
            assertSong(3, volumeIIFourthSongTitle, volumeIIFourthSongSubTitle, Element.Air)
            assertSong(4, volumeIIFifthSongTitle, volumeIIFifthSongSubTitle, Element.Earth)
            assertSong(5, volumeIISixthSongTitle, volumeIISixthSongSubTitle, Element.Earth)
            assertSong(6, volumeIISeventhSongTitle, volumeIISeventhSongSubTitle, Element.Water)
            assertSong(7, volumeIIEighthSongTitle, volumeIIEighthSongSubTitle, Element.Fire)
            assertSong(8, volumeIINinthSongTitle, volumeIINinthSongSubTitle, Element.Air)
            assertSong(9, volumeIITenthSongTitle, volumeIITenthSongSubTitle, Element.Air)
            assertSong(10, volumeIIEleventhSongTitle, volumeIIEleventhSongSubTitle, Element.Fire)
            assertSong(11, volumeIITwelfthSongTitle, volumeIITwelfthSongSubTitle, Element.Earth)
        }
    }
}