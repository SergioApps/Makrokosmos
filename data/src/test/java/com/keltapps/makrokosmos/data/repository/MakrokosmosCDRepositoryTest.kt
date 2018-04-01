package com.keltapps.makrokosmos.data.repository

import com.google.common.truth.Truth.assertThat
import com.keltapps.makrokosmos.data.R
import com.keltapps.makrokosmos.data.repository.util.RxSchedulersOverrideRule
import com.keltapps.makrokosmos.data.resourceProvider.ResourceProvider
import com.keltapps.makrokosmos.domain.entity.BlockSong
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.junit.Rule



class MakrokosmosCDRepositoryTest {

    private lateinit var repository: MakrokosmosCDRepository

    @get:Rule
    val overrideSchedulersRule = RxSchedulersOverrideRule()

    @Mock
    private lateinit var resourceProvider: ResourceProvider

    private val cdTitle = "Makrokosmos"

    private val volumeIPart1Title = "Volume I Part 1"
    private val volumeIFirstSongTitle = "Primeval Sounds"
    private val volumeIFirstSongSubTitle = "Genesis II Cancer"
    private val volumeISecondSongTitle = "Proteus"
    private val volumeISecondSongSubTitle = "Pisces"
    private val volumeIThirdSongTitle = "Pastorale"
    private val volumeIThirdSongSubTitle = "From the Kingdom of Atlantis, ca. 10,000 B.C. Taurus"
    private val volumeIFourthSongTitle = "Crucifixus"
    private val volumeIFourthSongSubTitle = "Capricorn"

    private val volumeIPart2Title = "Volume I Part 2"
    private val volumeIFifthSongTitle = "The Phantom Gondolier"
    private val volumeIFifthSongSubTitle = "Scorpio"
    private val volumeISixthSongTitle = "Night-Spell I"
    private val volumeISixthSongSubTitle = "Sagittarius"
    private val volumeISeventhSongTitle = "Music of Shadows"
    private val volumeISeventhSongSubTitle = "For Aeolian Harp Libra"
    private val volumeIEighthSongTitle = "The Magic Circle of Infinity"
    private val volumeIEighthSongSubTitle = "Moto Perpetuo Leo"

    private val volumeIPart3Title = "Volume I Part 3"
    private val volumeINinthSongTitle = "The Abyss of Time"
    private val volumeINinthSongSubTitle = "Virgo"
    private val volumeITenthSongTitle = "Spring-Fire"
    private val volumeITenthSongSubTitle = "Aries"
    private val volumeIEleventhSongTitle = "Dream Images"
    private val volumeIEleventhSongSubTitle = "Love-Death Music Gemini"
    private val volumeITwelfthSongTitle = "Spiral Galaxy"
    private val volumeITwelfthSongSubTitle = "Aquarius"

    private val volumeIIPart1Title = "Volume II Part 1"
    private val volumeIIFirstSongTitle = "Morning Music"
    private val volumeIIFirstSongSubTitle = "Genesis II Cancer"
    private val volumeIISecondSongTitle = "The Mystic Chord"
    private val volumeIISecondSongSubTitle = "Sagittarius"
    private val volumeIIThirdSongTitle = "Rain-Death Variations"
    private val volumeIIThirdSongSubTitle = "Pisces"
    private val volumeIIFourthSongTitle = "Twin Suns"
    private val volumeIIFourthSongSubTitle = "Gemini"

    private val volumeIIPart2Title = "Volume II Part 2"
    private val volumeIIFifthSongTitle = "Ghost-Nocturne: for the Druids of Stonehenge"
    private val volumeIIFifthSongSubTitle = "Night-Spell II Virgo"
    private val volumeIISixthSongTitle = "Gargoyles"
    private val volumeIISixthSongSubTitle = "Taurus"
    private val volumeIISeventhSongTitle = "Tora! Tora! Tora!"
    private val volumeIISeventhSongSubTitle = "Cadenza Apocalittica Scorpio"
    private val volumeIIEighthSongTitle = "A Prophecy of Nostradamus"
    private val volumeIIEighthSongSubTitle = "Aries"

    private val volumeIIPart3Title = "Volume II Part 3"
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

        val repositoryModel = testObserver.values()[0]
        assertThat(repositoryModel.isSuccessful()).isTrue()
        val cd = repositoryModel.data ?: throw(NullPointerException())
        assertThat(cd.title).isEqualTo(cdTitle)
        assertThat(cd.blockSongList.size).isEqualTo(6)
        assertVolumeIPart1(cd.blockSongList[0])
        assertVolumeIPart2(cd.blockSongList[1])
        assertVolumeIPart3(cd.blockSongList[2])
        assertVolumeIIPart1(cd.blockSongList[3])
        assertVolumeIIPart2(cd.blockSongList[4])
        assertVolumeIIPart3(cd.blockSongList[5])
    }

    private fun mockStrings() {
        `when`(resourceProvider.getString(R.string.title_cd_title)).thenReturn(cdTitle)

        `when`(resourceProvider.getString(R.string.volumeIPart1Title)).thenReturn(volumeIPart1Title)
        `when`(resourceProvider.getString(R.string.volumeIFirstSongTitle)).thenReturn(volumeIFirstSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIFirstSongSubTitle)).thenReturn(volumeIFirstSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeISecondSongTitle)).thenReturn(volumeISecondSongTitle)
        `when`(resourceProvider.getString(R.string.volumeISecondSongSubTitle)).thenReturn(volumeISecondSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIThirdSongTitle)).thenReturn(volumeIThirdSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIThirdSongSubTitle)).thenReturn(volumeIThirdSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIFourthSongTitle)).thenReturn(volumeIFourthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIFourthSongSubTitle)).thenReturn(volumeIFourthSongSubTitle)

        `when`(resourceProvider.getString(R.string.volumeIPart2Title)).thenReturn(volumeIPart2Title)
        `when`(resourceProvider.getString(R.string.volumeIFifthSongTitle)).thenReturn(volumeIFifthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIFifthSongSubTitle)).thenReturn(volumeIFifthSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeISixthSongTitle)).thenReturn(volumeISixthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeISixthSongSubTitle)).thenReturn(volumeISixthSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeISeventhSongTitle)).thenReturn(volumeISeventhSongTitle)
        `when`(resourceProvider.getString(R.string.volumeISeventhSongSubTitle)).thenReturn(volumeISeventhSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIEighthSongTitle)).thenReturn(volumeIEighthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIEighthSongSubTitle)).thenReturn(volumeIEighthSongSubTitle)

        `when`(resourceProvider.getString(R.string.volumeIPart3Title)).thenReturn(volumeIPart3Title)
        `when`(resourceProvider.getString(R.string.volumeINinthSongTitle)).thenReturn(volumeINinthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeINinthSongSubTitle)).thenReturn(volumeINinthSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeITenthSongTitle)).thenReturn(volumeITenthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeITenthSongSubTitle)).thenReturn(volumeITenthSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIEleventhSongTitle)).thenReturn(volumeIEleventhSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIEleventhSongSubTitle)).thenReturn(volumeIEleventhSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeITwelfthSongTitle)).thenReturn(volumeITwelfthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeITwelfthSongSubTitle)).thenReturn(volumeITwelfthSongSubTitle)

        `when`(resourceProvider.getString(R.string.volumeIIPart1Title)).thenReturn(volumeIIPart1Title)
        `when`(resourceProvider.getString(R.string.volumeIIFirstSongTitle)).thenReturn(volumeIIFirstSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIIFirstSongSubTitle)).thenReturn(volumeIIFirstSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIISecondSongTitle)).thenReturn(volumeIISecondSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIISecondSongSubTitle)).thenReturn(volumeIISecondSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIIThirdSongTitle)).thenReturn(volumeIIThirdSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIIThirdSongSubTitle)).thenReturn(volumeIIThirdSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIIFourthSongTitle)).thenReturn(volumeIIFourthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIIFourthSongSubTitle)).thenReturn(volumeIIFourthSongSubTitle)

        `when`(resourceProvider.getString(R.string.volumeIIPart2Title)).thenReturn(volumeIIPart2Title)
        `when`(resourceProvider.getString(R.string.volumeIIFifthSongTitle)).thenReturn(volumeIIFifthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIIFifthSongSubTitle)).thenReturn(volumeIIFifthSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIISixthSongTitle)).thenReturn(volumeIISixthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIISixthSongSubTitle)).thenReturn(volumeIISixthSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIISeventhSongTitle)).thenReturn(volumeIISeventhSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIISeventhSongSubTitle)).thenReturn(volumeIISeventhSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIIEighthSongTitle)).thenReturn(volumeIIEighthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIIEighthSongSubTitle)).thenReturn(volumeIIEighthSongSubTitle)

        `when`(resourceProvider.getString(R.string.volumeIIPart3Title)).thenReturn(volumeIIPart3Title)
        `when`(resourceProvider.getString(R.string.volumeIINinthSongTitle)).thenReturn(volumeIINinthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIINinthSongSubTitle)).thenReturn(volumeIINinthSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIITenthSongTitle)).thenReturn(volumeIITenthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIITenthSongSubTitle)).thenReturn(volumeIITenthSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIIEleventhSongTitle)).thenReturn(volumeIIEleventhSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIIEleventhSongSubTitle)).thenReturn(volumeIIEleventhSongSubTitle)
        `when`(resourceProvider.getString(R.string.volumeIITwelfthSongTitle)).thenReturn(volumeIITwelfthSongTitle)
        `when`(resourceProvider.getString(R.string.volumeIITwelfthSongSubTitle)).thenReturn(volumeIITwelfthSongSubTitle)
    }

    private fun assertVolumeIPart1(volumeIPart1: BlockSong) {
        with(volumeIPart1) {
            assertThat(title).isEqualTo(volumeIPart1Title)
            assertThat(songList.size).isEqualTo(4)
            assertThat(songList[0].title).isEqualTo(volumeIFirstSongTitle)
            assertThat(songList[0].subTitle).isEqualTo(volumeIFirstSongSubTitle)
            assertThat(songList[1].title).isEqualTo(volumeISecondSongTitle)
            assertThat(songList[1].subTitle).isEqualTo(volumeISecondSongSubTitle)
            assertThat(songList[2].title).isEqualTo(volumeIThirdSongTitle)
            assertThat(songList[2].subTitle).isEqualTo(volumeIThirdSongSubTitle)
            assertThat(songList[3].title).isEqualTo(volumeIFourthSongTitle)
            assertThat(songList[3].subTitle).isEqualTo(volumeIFourthSongSubTitle)
        }
    }

    private fun assertVolumeIPart2(volumeIPart2: BlockSong) {
        with(volumeIPart2) {
            assertThat(title).isEqualTo(volumeIPart2Title)
            assertThat(songList.size).isEqualTo(4)
            assertThat(songList[0].title).isEqualTo(volumeIFifthSongTitle)
            assertThat(songList[0].subTitle).isEqualTo(volumeIFifthSongSubTitle)
            assertThat(songList[1].title).isEqualTo(volumeISixthSongTitle)
            assertThat(songList[1].subTitle).isEqualTo(volumeISixthSongSubTitle)
            assertThat(songList[2].title).isEqualTo(volumeISeventhSongTitle)
            assertThat(songList[2].subTitle).isEqualTo(volumeISeventhSongSubTitle)
            assertThat(songList[3].title).isEqualTo(volumeIEighthSongTitle)
            assertThat(songList[3].subTitle).isEqualTo(volumeIEighthSongSubTitle)
        }
    }

    private fun assertVolumeIPart3(volumeIPart3: BlockSong) {
        with(volumeIPart3) {
            assertThat(title).isEqualTo(volumeIPart3Title)
            assertThat(songList.size).isEqualTo(4)
            assertThat(songList[0].title).isEqualTo(volumeINinthSongTitle)
            assertThat(songList[0].subTitle).isEqualTo(volumeINinthSongSubTitle)
            assertThat(songList[1].title).isEqualTo(volumeITenthSongTitle)
            assertThat(songList[1].subTitle).isEqualTo(volumeITenthSongSubTitle)
            assertThat(songList[2].title).isEqualTo(volumeIEleventhSongTitle)
            assertThat(songList[2].subTitle).isEqualTo(volumeIEleventhSongSubTitle)
            assertThat(songList[3].title).isEqualTo(volumeITwelfthSongTitle)
            assertThat(songList[3].subTitle).isEqualTo(volumeITwelfthSongSubTitle)
        }
    }

    private fun assertVolumeIIPart1(volumeIIPart1: BlockSong) {
        with(volumeIIPart1) {
            assertThat(title).isEqualTo(volumeIIPart1Title)
            assertThat(songList.size).isEqualTo(4)
            assertThat(songList[0].title).isEqualTo(volumeIIFirstSongTitle)
            assertThat(songList[0].subTitle).isEqualTo(volumeIIFirstSongSubTitle)
            assertThat(songList[1].title).isEqualTo(volumeIISecondSongTitle)
            assertThat(songList[1].subTitle).isEqualTo(volumeIISecondSongSubTitle)
            assertThat(songList[2].title).isEqualTo(volumeIIThirdSongTitle)
            assertThat(songList[2].subTitle).isEqualTo(volumeIIThirdSongSubTitle)
            assertThat(songList[3].title).isEqualTo(volumeIIFourthSongTitle)
            assertThat(songList[3].subTitle).isEqualTo(volumeIIFourthSongSubTitle)
        }
    }

    private fun assertVolumeIIPart2(volumeIIPart2: BlockSong) {
        with(volumeIIPart2) {
            assertThat(title).isEqualTo(volumeIIPart2Title)
            assertThat(songList.size).isEqualTo(4)
            assertThat(songList[0].title).isEqualTo(volumeIIFifthSongTitle)
            assertThat(songList[0].subTitle).isEqualTo(volumeIIFifthSongSubTitle)
            assertThat(songList[1].title).isEqualTo(volumeIISixthSongTitle)
            assertThat(songList[1].subTitle).isEqualTo(volumeIISixthSongSubTitle)
            assertThat(songList[2].title).isEqualTo(volumeIISeventhSongTitle)
            assertThat(songList[2].subTitle).isEqualTo(volumeIISeventhSongSubTitle)
            assertThat(songList[3].title).isEqualTo(volumeIIEighthSongTitle)
            assertThat(songList[3].subTitle).isEqualTo(volumeIIEighthSongSubTitle)
        }
    }

    private fun assertVolumeIIPart3(volumeIIPart3: BlockSong) {
        with(volumeIIPart3) {
            assertThat(title).isEqualTo(volumeIIPart3Title)
            assertThat(songList.size).isEqualTo(4)
            assertThat(songList[0].title).isEqualTo(volumeIINinthSongTitle)
            assertThat(songList[0].subTitle).isEqualTo(volumeIINinthSongSubTitle)
            assertThat(songList[1].title).isEqualTo(volumeIITenthSongTitle)
            assertThat(songList[1].subTitle).isEqualTo(volumeIITenthSongSubTitle)
            assertThat(songList[2].title).isEqualTo(volumeIIEleventhSongTitle)
            assertThat(songList[2].subTitle).isEqualTo(volumeIIEleventhSongSubTitle)
            assertThat(songList[3].title).isEqualTo(volumeIITwelfthSongTitle)
            assertThat(songList[3].subTitle).isEqualTo(volumeIITwelfthSongSubTitle)
        }
    }
}