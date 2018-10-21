package com.keltapps.makrokosmos.menu.presentation.viewmodel

import com.keltapps.makrokosmos.base.viewmodel.MakrokosmosBaseViewModel
import com.keltapps.makrokosmos.navigation.Navigator
import javax.inject.Inject

class MakrokosmosMenuViewModel @Inject constructor(
        private val navigator: Navigator
) : MakrokosmosBaseViewModel(), MenuViewModel {

    override fun openMakrokosmos() {
        navigator.openMakrokosmos()
    }

    override fun openAbout() {
        navigator.openAboutInfo()
    }

    override fun openAuthor() {
        navigator.openAuthorInfo()
    }

    override fun openInterpreter() {
        navigator.openInterpreterInfo()
    }
}