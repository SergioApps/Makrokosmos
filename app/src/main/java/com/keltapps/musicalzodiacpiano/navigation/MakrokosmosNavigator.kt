package com.keltapps.musicalzodiacpiano.navigation

import androidx.navigation.NavController
import com.keltapps.musicalzodiacpiano.R
import com.keltapps.musicalzodiacpiano.info.presentation.model.InfoScreen
import com.keltapps.musicalzodiacpiano.menu.presentation.view.MenuFragmentDirections
import com.keltapps.musicalzodiacpiano.song.presentation.list.view.SongListParentFragmentDirections
import javax.inject.Inject

class MakrokosmosNavigator @Inject constructor() : Navigator {

    override fun openMakrokosmos(navController: NavController) {
        navController.navigate(R.id.action_menuFragment_to_songListParentFragment)
    }

    override fun openAboutInfo(navController: NavController) {
        navController.navigate(
                MenuFragmentDirections.actionMenuFragmentToInfoFragment(
                        InfoScreen.AboutScreen
                )
        )
    }

    override fun openAuthorInfo(navController: NavController) {
        navController.navigate(
                MenuFragmentDirections.actionMenuFragmentToInfoFragment(
                        InfoScreen.AuthorScreen
                )
        )
    }

    override fun openInterpreterInfo(navController: NavController) {
        navController.navigate(
                MenuFragmentDirections.actionMenuFragmentToInfoFragment(
                        InfoScreen.InterpreterScreen
                )
        )
    }

    override fun openSongDetail(navController: NavController, songId: String) {
        navController.navigate(
                SongListParentFragmentDirections.actionToSongDetailFragment(
                        songId
                )
        )
    }
}