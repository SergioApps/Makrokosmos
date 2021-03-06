package com.keltapps.musicalzodiacpiano.menu.presentation.viewmodel

import com.keltapps.musicalzodiacpiano.base.presentation.SingleLiveEvent
import com.keltapps.musicalzodiacpiano.base.presentation.viewmodel.MakrokosmosBaseViewModel
import javax.inject.Inject

class MakrokosmosMenuViewModel @Inject constructor() : MakrokosmosBaseViewModel(), MenuViewModel {

    override val openMakrokosmos = SingleLiveEvent<Unit>()
    override val openAbout = SingleLiveEvent<Unit>()
    override val openAuthor = SingleLiveEvent<Unit>()
    override val openInterpreter = SingleLiveEvent<Unit>()

    override fun openMakrokosmos() {
        openMakrokosmos.value = Unit
    }

    override fun openAbout() {
        openAbout.value = Unit
    }

    override fun openAuthor() {
        openAuthor.value = Unit
    }

    override fun openInterpreter() {
        openInterpreter.value = Unit
    }
}