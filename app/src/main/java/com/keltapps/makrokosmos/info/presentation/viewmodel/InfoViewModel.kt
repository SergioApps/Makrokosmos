package com.keltapps.makrokosmos.info.presentation.viewmodel

import android.arch.lifecycle.LiveData
import com.keltapps.makrokosmos.info.presentation.model.InfoScreen

interface InfoViewModel {
    fun initialize(infoScreen: InfoScreen)
    val image: LiveData<Int>
    val title: LiveData<String>
    val body: LiveData<String>
}