package com.keltapps.makrokosmos.menu.presentation.viewmodel

import com.keltapps.makrokosmos.navigation.Navigator
import com.keltapps.makrokosmos.navigation.Navigator.Companion.ID_ABOUT
import com.keltapps.makrokosmos.navigation.Navigator.Companion.ID_AUTHOR
import com.keltapps.makrokosmos.navigation.Navigator.Companion.ID_INTERPRETER
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
    fun openAbout_should_callOpenInfoWithIdAbout() {
        navigator.openInfo(ID_ABOUT)
    }

    @Test
    fun openAuthor_should_callOpenInfoWithIdAuthor() {
        navigator.openInfo(ID_AUTHOR)
    }

    @Test
    fun openInterpreter_should_callOpenInfoWithIdInterpreter() {
        navigator.openInfo(ID_INTERPRETER)
    }
}