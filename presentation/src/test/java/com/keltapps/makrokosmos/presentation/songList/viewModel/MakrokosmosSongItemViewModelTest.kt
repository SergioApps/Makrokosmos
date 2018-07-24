package com.keltapps.makrokosmos.presentation.songList.viewModel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.keltapps.makrokosmos.domain.entity.*
import org.junit.*
import org.junit.rules.TestRule


class MakrokosmosSongItemViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val viewModel = MakrokosmosSongItemViewModel()

    @Test
    fun setSong_should_setTitleAndSubTitle() {
        val song = Song(title = "title", subTitle = "subTitle", element = Element.Fire)

        viewModel.setSong(song)

        assertThat(viewModel.title.value).isEqualTo(song.title)
        assertThat(viewModel.subTitle.value).isEqualTo(song.subTitle)
    }
}