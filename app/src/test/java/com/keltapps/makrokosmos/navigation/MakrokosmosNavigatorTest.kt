package com.keltapps.makrokosmos.navigation

import androidx.navigation.NavController
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.info.presentation.model.InfoScreen
import com.keltapps.makrokosmos.menu.presentation.view.MenuFragmentDirections
import com.keltapps.makrokosmos.song.presentation.list.view.SongListParentFragmentDirections
import org.junit.*
import org.mockito.*
import org.mockito.Mockito.verify

class MakrokosmosNavigatorTest {

    private companion object {
        const val SONG_ID = "songId"
    }

    private lateinit var sut: MakrokosmosNavigator

    @Mock
    private lateinit var navController: NavController

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MakrokosmosNavigator()
    }

    @Test
    fun openMakrokosmos_should_navigateToSongListParentFragment() {
        sut.openMakrokosmos(navController)

        verify(navController).navigate(R.id.action_menuFragment_to_songListParentFragment)
    }

    @Test
    fun openAboutInfo_should_navigateToAboutInfoFragment() {
        sut.openAboutInfo(navController)

        verify(navController).navigate(
                MenuFragmentDirections.actionMenuFragmentToInfoFragment(
                        InfoScreen.AboutScreen
                )
        )
    }

    @Test
    fun openAuthorInfo_should_navigateToAuthorInfoFragment() {
        sut.openAuthorInfo(navController)

        verify(navController).navigate(
                MenuFragmentDirections.actionMenuFragmentToInfoFragment(
                        InfoScreen.AuthorScreen
                )
        )
    }

    @Test
    fun openInterpreterInfo_should_navigateToInterpreterInfoFragment() {
        sut.openInterpreterInfo(navController)

        verify(navController).navigate(
                MenuFragmentDirections.actionMenuFragmentToInfoFragment(
                        InfoScreen.InterpreterScreen
                )
        )
    }

    @Test
    fun openSongDetails_should_navigateToSongDetailsFragment() {
        sut.openSongDetail(navController, SONG_ID)

        verify(navController).navigate(
                SongListParentFragmentDirections.actionToSongDetailFragment(
                        SONG_ID
                )
        )
    }
}