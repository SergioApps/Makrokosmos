package com.keltapps.musicalzodiacpiano.menu.presentation.viewmodel

import com.keltapps.musicalzodiacpiano.base.presentation.SingleLiveEvent

interface MenuViewModel {
    fun openMakrokosmos()
    val openMakrokosmos: SingleLiveEvent<Unit>
    fun openAbout()
    val openAbout: SingleLiveEvent<Unit>
    fun openAuthor()
    val openAuthor: SingleLiveEvent<Unit>
    fun openInterpreter()
    val openInterpreter: SingleLiveEvent<Unit>
}