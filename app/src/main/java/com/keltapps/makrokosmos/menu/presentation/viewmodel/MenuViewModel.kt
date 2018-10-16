package com.keltapps.makrokosmos.menu.presentation.viewmodel

import com.keltapps.makrokosmos.base.viewmodel.BaseViewModel

interface MenuViewModel : BaseViewModel {
    fun openMakrokosmos()
    fun openAbout()
    fun openAuthor()
    fun openInterpreter()
}