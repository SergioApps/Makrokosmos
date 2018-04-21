package com.keltapps.makrokosmos.presentation.songList.viewModel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.keltapps.makrokosmos.domain.entity.BlockSong
import com.keltapps.makrokosmos.domain.entity.CD
import com.keltapps.makrokosmos.domain.entity.Song
import com.keltapps.makrokosmos.domain.iteractor.GetCDUseCase
import com.keltapps.makrokosmos.domain.model.UseCaseModel
import com.keltapps.makrokosmos.presentation.songList.model.CDListItem.Companion.TYPE_SONG
import com.keltapps.makrokosmos.presentation.songList.model.CDListItem.Companion.TYPE_TITLE
import com.keltapps.makrokosmos.presentation.songList.model.SongListItem
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MakrokosmosSongListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MakrokosmosSongListViewModel

    @Mock
    private lateinit var getCDUseCase: GetCDUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MakrokosmosSongListViewModel(getCDUseCase)
    }

    @Test
    fun songListItems_should_returnBlockSongListFromUseCase() {
        val cd = getCD()
        val useCaseModel = UseCaseModel(cd)
        `when`(getCDUseCase.execute()).thenReturn(Observable.just(useCaseModel))
        viewModel.initialize()

        val songListItems = viewModel.cdListItems.value

        assertThat(songListItems!!.size).isEqualTo(6)
        assertThat(songListItems[0].type).isEqualTo(TYPE_TITLE)
        assertThat(songListItems[1].type).isEqualTo(TYPE_SONG)
        assertThat((songListItems[1] as SongListItem).song).isEqualTo(cd.blockSongList[0].songList[0])
        assertThat(songListItems[2].type).isEqualTo(TYPE_SONG)
        assertThat((songListItems[2] as SongListItem).song).isEqualTo(cd.blockSongList[0].songList[1])
        assertThat(songListItems[3].type).isEqualTo(TYPE_TITLE)
        assertThat(songListItems[4].type).isEqualTo(TYPE_SONG)
        assertThat((songListItems[4] as SongListItem).song).isEqualTo(cd.blockSongList[1].songList[0])
        assertThat(songListItems[5].type).isEqualTo(TYPE_SONG)
        assertThat((songListItems[5] as SongListItem).song).isEqualTo(cd.blockSongList[1].songList[1])
    }

    @Test
    fun title_should_returnTitleFromUseCase() {
        val cd = getCD()
        val useCaseModel = UseCaseModel(cd)
        `when`(getCDUseCase.execute()).thenReturn(Observable.just(useCaseModel))
        viewModel.initialize()

        val result = viewModel.title.value

        assertThat(result).isEqualTo(cd.title)
    }

    private fun getCD(): CD {
        val blockSongList = ArrayList<BlockSong>()
        blockSongList += getBlockSong()
        blockSongList += getBlockSong()
        return CD(blockSongList, "title")
    }

    private fun getBlockSong(): BlockSong {
        val songList = ArrayList<Song>()
        songList +=Song("title1","subTitle1")
        songList += Song("title2","subTitle2")
        return BlockSong("block title", songList)
    }
}