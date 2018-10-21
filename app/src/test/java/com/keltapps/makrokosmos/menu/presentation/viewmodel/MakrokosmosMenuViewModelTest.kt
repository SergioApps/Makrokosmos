package com.keltapps.makrokosmos.menu.presentation.viewmodel

import com.keltapps.makrokosmos.navigation.Navigator
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MakrokosmosMenuViewModelTest {

    private lateinit var sut: MakrokosmosMenuViewModel

    @Mock
    private lateinit var navigator: Navigator

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MakrokosmosMenuViewModel(
                navigator
        )
    }

    @Test
    fun openMakrokosmos_should_callOpenMakrokosmos() {
        navigator.openMakrokosmos()
    }

    @Test
    fun openAbout_should_callOpenAboutInfo() {
        navigator.openAboutInfo()
    }

    @Test
    fun openAuthor_should_callOpenInfo() {
        navigator.openAuthorInfo()
    }

    @Test
    fun openInterpreter_should_callOpenInterpreterInfo() {
        navigator.openInterpreterInfo()
    }
}