package com.keltapps.makrokosmos.info.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.keltapps.makrokosmos.info.presentation.model.InfoScreen

interface InfoViewModel {
    val image: LiveData<Int>
    val title: LiveData<String>
    val body: LiveData<String>
}