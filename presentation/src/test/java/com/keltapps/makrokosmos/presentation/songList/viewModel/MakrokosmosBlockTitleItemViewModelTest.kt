package com.keltapps.makrokosmos.presentation.songList.viewModel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MakrokosmosBlockTitleItemViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val viewModel = MakrokosmosBlockTitleItemViewModel()

    @Test
    fun setTitle_shouldSetTitle() {
        val title = "title"

        viewModel.setTitle(title)

        assertThat(viewModel.title.value).isEqualTo(title)
    }
}