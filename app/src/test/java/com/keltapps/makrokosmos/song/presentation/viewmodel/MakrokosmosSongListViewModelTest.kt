package com.keltapps.makrokosmos.song.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.song.domain.entity.*
import com.keltapps.makrokosmos.song.domain.iteractor.GetCDUseCase
import com.keltapps.makrokosmos.song.data.repository.RxSchedulersOverrideRule
import io.reactivex.Observable
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.*
import org.mockito.Mockito.`when`

class MakrokosmosSongListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val overrideSchedulersRule = RxSchedulersOverrideRule()

    private lateinit var sut: MakrokosmosSongListViewModel

    @Mock
    private lateinit var getCDUseCase: GetCDUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MakrokosmosSongListViewModel(getCDUseCase)
    }

    @Test
    fun songListItems_should_returnBlockSongListFromUseCase() {
        val cd = getCD()
        `when`(getCDUseCase.execute()).thenReturn(Observable.just(cd))
        val volumeIndex = 0
        sut.initialize(volumeIndex)

        val songListItems = sut.cdListItems.value

        assertThat(songListItems).isEqualTo(cd.volumeList[volumeIndex].songList)
    }

    @Test
    fun getTitleRes_should_returnVolumeI_when_volumeIndexIs1(){
        val result = sut.getTitleRes(0)

        assertThat(result).isEqualTo(R.string.tab_name_volume_1)
    }

    @Test
    fun getTitleRes_should_returnVolumeII_when_volumeIndexIs2(){
        val result = sut.getTitleRes(1)

        assertThat(result).isEqualTo(R.string.tab_name_volume_2)
    }

    private fun getCD(): CD {
        val blockSongList = ArrayList<Volume>()
        blockSongList += getBlockSong()
        blockSongList += getBlockSong()
        return CD(blockSongList, "title")
    }

    private fun getBlockSong(): Volume {
        val songList = ArrayList<Song>()
        songList += Song("title1", "subTitle1", ZodiacSign.Scorpio(""))
        songList += Song("title2", "subTitle2", ZodiacSign.Scorpio(""))
        return Volume("block title", songList)
    }
}