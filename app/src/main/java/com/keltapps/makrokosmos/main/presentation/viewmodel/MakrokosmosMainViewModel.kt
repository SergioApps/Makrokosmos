package com.keltapps.makrokosmos.main.presentation.viewmodel

import com.keltapps.makrokosmos.base.viewmodel.MakrokosmosBaseViewModel
import com.keltapps.makrokosmos.navigation.Navigator
import com.keltapps.makrokosmos.navigation.Navigator.Companion.ID_ABOUT
import com.keltapps.makrokosmos.navigation.Navigator.Companion.ID_AUTHOR
import com.keltapps.makrokosmos.navigation.Navigator.Companion.ID_INTERPRETER
import javax.inject.Inject

class MakrokosmosMainViewModel @Inject constructor(
        private val navigator: Navigator
) : MakrokosmosBaseViewModel(), MainViewModel {

    override fun openMakrokosmos() {
        navigator.openMakrokosmos()
    }

    override fun openAbout() {
        navigator.openInfo(ID_ABOUT)
    }

    override fun openAuthor() {
        navigator.openInfo(ID_AUTHOR)
    }

    override fun openInterpreter() {
        navigator.openInfo(ID_INTERPRETER)
    }
}