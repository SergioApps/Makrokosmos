package com.keltapps.makrokosmos.navigation

import androidx.navigation.NavController
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.menu.presentation.view.MenuFragmentDirections
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MakrokosmosNavigatorTest {

    private companion object {
        const val SCREEN_TYPE_ID = 1
    }

    private lateinit var sut: MakrokosmosNavigator

    @Mock
    private lateinit var navController: NavController

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MakrokosmosNavigator(
                navController
        )
    }

    @Test
    fun openMakrokosmos_should_navigateToSongListParentFragment() {
        sut.openMakrokosmos()

        verify(navController).navigate(R.id.action_menuFragment_to_songListParentFragment)
    }
/*
    @Test
    fun openInfo_should_navigateToInfoFragment() {
        sut.openInfo(SCREEN_TYPE_ID)

        verify(navController).navigate(
                MenuFragmentDirections.actionMenuFragmentToInfoFragment(
                        SCREEN_TYPE_ID
                )
        )
    }*/
}