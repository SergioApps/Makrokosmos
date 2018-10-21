package com.keltapps.makrokosmos.navigation

import androidx.navigation.NavController
import com.keltapps.makrokosmos.R
import com.keltapps.makrokosmos.info.presentation.model.InfoScreen
import com.keltapps.makrokosmos.menu.presentation.view.MenuFragmentDirections
import javax.inject.Inject

class MakrokosmosNavigator @Inject constructor(
        private val navController: NavController
) : Navigator {

    override fun openMakrokosmos() {
        navController.navigate(R.id.action_menuFragment_to_songListParentFragment)
    }

    override fun openAboutInfo() {
        navController.navigate(
                MenuFragmentDirections.actionMenuFragmentToInfoFragment(
                        InfoScreen.AboutScreen
                )
        )
    }

    override fun openAuthorInfo() {
        navController.navigate(
                MenuFragmentDirections.actionMenuFragmentToInfoFragment(
                        InfoScreen.AuthorScreen
                )
        )
    }

    override fun openInterpreterInfo() {
        navController.navigate(
                MenuFragmentDirections.actionMenuFragmentToInfoFragment(
                        InfoScreen.InterpreterScreen
                )
        )
    }
}