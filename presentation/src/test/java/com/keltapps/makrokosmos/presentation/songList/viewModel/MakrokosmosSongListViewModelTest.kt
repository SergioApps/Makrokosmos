package com.keltapps.makrokosmos.presentation.songList.viewModel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.keltapps.makrokosmos.domain.entity.*
import com.keltapps.makrokosmos.domain.iteractor.GetCDUseCase
import com.keltapps.makrokosmos.presentation.RxSchedulersOverrideRule
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
        `when`(getCDUseCase.execute()).thenReturn(Observable.just(cd))
        viewModel.initialize(0)

        val songListItems = viewModel.cdListItems.value

        assertThat(songListItems!!.size).isEqualTo(6)
        /*   assertThat(songListItems[0] is TitleListItem).isTrue()
           assertThat(songListItems[1] is SongListItem).isTrue()
           assertThat((songListItems[1] as SongListItem).song).isEqualTo(cd.volumeList[0].songList[0])
           assertThat(songListItems[2] is SongListItem).isTrue()
           assertThat((songListItems[2] as SongListItem).song).isEqualTo(cd.volumeList[0].songList[1])
           assertThat(songListItems[3] is TitleListItem).isTrue()
           assertThat(songListItems[4] is SongListItem).isTrue()
           assertThat((songListItems[4] as SongListItem).song).isEqualTo(cd.volumeList[1].songList[0])
           assertThat(songListItems[5] is SongListItem).isTrue()
           assertThat((songListItems[5] as SongListItem).song).isEqualTo(cd.volumeList[1].songList[1])*/
    }

    @Test
    fun title_should_returnTitleFromUseCase() {
        val cd = getCD()
        `when`(getCDUseCase.execute()).thenReturn(Observable.just(cd))
        viewModel.initialize(0)

        val result = viewModel.title.value

        assertThat(result).isEqualTo(cd.title)
    }

    private fun getCD(): CD {
        val blockSongList = ArrayList<Volume>()
        blockSongList += getBlockSong()
        blockSongList += getBlockSong()
        return CD(blockSongList, "title")
    }

    private fun getBlockSong(): Volume {
        val songList = ArrayList<Song>()
        songList += Song("title1", "subTitle1", Element.Fire)
        songList += Song("title2", "subTitle2", Element.Fire)
        return Volume("block title", songList)
    }
}