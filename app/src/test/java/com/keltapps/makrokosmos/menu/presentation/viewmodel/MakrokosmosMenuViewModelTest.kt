package com.keltapps.makrokosmos.menu.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

class MakrokosmosMenuViewModelTest {

    private lateinit var sut: MakrokosmosMenuViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MakrokosmosMenuViewModel()
    }

    @Test
    fun openMakrokosmos_should_callOpenMakrokosmos() {
        sut.openMakrokosmos()

        assertThat(sut.openMakrokosmos.value).isEqualTo(Unit)

    }

    @Test
    fun openAbout_should_callOpenAboutInfo() {
        sut.openAbout()

        assertThat(sut.openAbout.value).isEqualTo(Unit)
    }

    @Test
    fun openAuthor_should_callOpenInfo() {
        sut.openAuthor()

        assertThat(sut.openAuthor.value).isEqualTo(Unit)
    }

    @Test
    fun openInterpreter_should_callOpenInterpreterInfo() {
        sut.openInterpreter()

        assertThat(sut.openInterpreter.value).isEqualTo(Unit)
    }
}