package com.keltapps.musicalzodiacpiano.song.presentation.list.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.keltapps.musicalzodiacpiano.song.domain.entity.*
import com.keltapps.musicalzodiacpiano.song.domain.iteractor.GetCDUseCase
import com.keltapps.musicalzodiacpiano.song.data.repository.RxSchedulersOverrideRule
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
    }

    @Test
    fun songListItems_should_returnBlockSongListFromUseCase() {
        val cd = getCD()
        `when`(getCDUseCase.execute()).thenReturn(Observable.just(cd))
        val volumeIndex = 0

        sut = MakrokosmosSongListViewModel(getCDUseCase, volumeIndex)

        val songListItems = sut.cdListItems.value

        assertThat(songListItems).isEqualTo(cd.volumeList[volumeIndex].songList)
    }

    private fun getCD(): CD {
        val blockSongList = ArrayList<Volume>()
        blockSongList += getBlockSong()
        blockSongList += getBlockSong()
        return CD(blockSongList, "title")
    }

    private fun getBlockSong(): Volume {
        val songList = ArrayList<Song>()
        songList += Song("id", "title1", "subTitle1", ZodiacSign.Scorpio(""), 60)
        songList += Song("id", "title2", "subTitle2", ZodiacSign.Scorpio(""), 60)
        return Volume("block title", songList)
    }
}