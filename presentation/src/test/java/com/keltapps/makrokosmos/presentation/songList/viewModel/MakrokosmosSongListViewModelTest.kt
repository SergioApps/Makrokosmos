package com.keltapps.makrokosmos.presentation.songList.viewModel

import com.google.common.truth.Truth.assertThat
import com.keltapps.makrokosmos.domain.entity.BlockSong
import com.keltapps.makrokosmos.domain.entity.CD
import com.keltapps.makrokosmos.domain.iteractor.GetCDUseCase
import com.keltapps.makrokosmos.domain.model.UseCaseModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.junit.Before

import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.Spy

class MakrokosmosSongListViewModelTest {


    private lateinit var viewModel: MakrokosmosSongListViewModel

    @Mock
    private lateinit var getCDUseCase: GetCDUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun blockSongList_should_returnBlockSongListFromUseCase() {
        val blockSongList = ArrayList<BlockSong>()
        blockSongList += mock(BlockSong::class.java)
        blockSongList += mock(BlockSong::class.java)
        val cd = CD(blockSongList, "title")
        val useCaseModel = UseCaseModel(cd)
        `when`(getCDUseCase.execute()).thenReturn(Observable.just(useCaseModel))
        viewModel = MakrokosmosSongListViewModel(getCDUseCase)

        val result = viewModel.blockSongList

        assertThat(result.get()).isEqualTo(blockSongList)
    }

    @Test
    fun title_should_returnTitleFromUseCase() {
        val blockSongList = ArrayList<BlockSong>()
        blockSongList += mock(BlockSong::class.java)
        blockSongList += mock(BlockSong::class.java)
        val title = "title"
        val cd = CD(blockSongList, title)
        val useCaseModel = UseCaseModel(cd)
        `when`(getCDUseCase.execute()).thenReturn(Observable.just(useCaseModel))
        viewModel = MakrokosmosSongListViewModel(getCDUseCase)

        val result = viewModel.title

        assertThat(result.get()).isEqualTo(title)
    }
}